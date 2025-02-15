public class BinaryExpr extends Expr {
    private char operator;
    private Expr lhs, rhs;

    public BinaryExpr(char operator, Expr lhs, Expr rhs) {
        this.operator = operator;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void print() {
        System.out.print("(");
        lhs.print();
        System.out.print(operator);
        rhs.print();
        System.out.print(")");

    }

    public ASTType getType() { return ASTType.BinaryExpr; }

    public AST getLHS() { return lhs; }
    
    public AST getRHS() { return rhs; }

    public char getOperator() { return operator; }
}
