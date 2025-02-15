import java.util.ArrayList;

public class Parser {
    ArrayList<String> tokens;
    int i;

    public Parser(ArrayList<String> tokens) {
        this.tokens = tokens;
        i = 0;
    }

    public AST parse() {
        return parseVariableStmt();
    } 

    public AST parseVariableStmt() {
        Expr name = parseExpr();

        if (name.getType() == ASTType.NameExpr && tokens.get(i).equals("=")) {
            i++;
            return new VariableStmt(name.getName(), parseExpr());
        }

        return name;
    }

    public Expr parseExpr() {
        return parseAdditiveExpr();
    }

    public Expr parseAdditiveExpr() {
        Expr lhs = parseMultiplicativeExpr();

        while (tokens.get(i).equals("+") || tokens.get(i).equals("-"))
            lhs = new BinaryExpr(eat().charAt(0), lhs, parseMultiplicativeExpr());
        
        return lhs;
    }

    public Expr parseMultiplicativeExpr() {
        Expr lhs = parsePrimaryExpr();

        while (tokens.get(i).equals("*") || tokens.get(i).equals("/"))
            lhs = new BinaryExpr(eat().charAt(0), lhs, parsePrimaryExpr());

        return lhs;
    }

    public Expr parsePrimaryExpr() {
        if (tokens.get(i).chars().allMatch(Character::isDigit)) 
            return new NumberExpr(Integer.parseInt(eat()));

        if (tokens.get(i).chars().allMatch(Character::isLetter))
            return new NameExpr(eat());

        switch (eat()) {
            case "(": {
                Expr result = parseExpr();
                eat(")");
                return result; 
            }
            case ")": {
                System.err.println("Unexpected character ')'");
                break;
            }
        }

        return null;
    }

    private String eat() {
        if (!tokens.get(i).equals("\0"))
            return tokens.get(i++);
        
        return tokens.get(i);
    }

    private String eat(String expect) {
        if (!tokens.get(i).equals("\0") && tokens.get(i).equals(")"))
            return tokens.get(i++);
    
        System.err.println("Expected '"+expect+"' but found '"+tokens.get(i)+"'");
        return tokens.get(i);
    }
}
