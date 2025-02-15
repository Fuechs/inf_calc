public class NameExpr extends Expr {
    private String name;
    
    NameExpr(String name) {
        this.name = name;
    }

    public void print() { System.out.print(name); }

    public ASTType getType() { return ASTType.NameExpr; }

    public String getName() { return name; }
}
