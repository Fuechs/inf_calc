public abstract class AST {
    public abstract void print();
    public abstract ASTType getType();

    public AST getLHS() { return null; }
    public AST getRHS() { return null; }
    public double getValue() { return -1; }
    public String getName() { return null; }
    public char getOperator() { return ' '; }
}