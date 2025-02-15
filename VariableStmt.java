public class VariableStmt extends AST {
    private String name;
    private Expr value;

    public VariableStmt(String name, Expr value) {
        this.name = name;
        this.value = value;
    }

    public void print() {
        System.out.print(name+" = ");
        value.print();
        System.out.println(";");
    }

    public ASTType getType() { return ASTType.VariableStmt; }

    public Expr getLHS() { return value; }

    public String getName() { return name; }
}
