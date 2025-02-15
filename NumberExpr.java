public class NumberExpr extends Expr {
    private double value;

    public NumberExpr(int value) {
        this.value = value;
    }

    public void print() {
        System.out.print(value);
    }

    public ASTType getType() { return ASTType.NumberExpr; }

    public double getValue() { return value; }
}
