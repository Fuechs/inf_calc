public class CallExpr extends Expr {
    private String callee;
    private Expr argument;
    
    public CallExpr(String callee, Expr argument) {
        this.callee = callee;
        this.argument = argument;
    }
    
    public void print() { 
        System.out.print(callee+"(");
        if(argument != null)
            argument.print();
        System.out.print(")");
    }
    
    public ASTType getType() { return ASTType.CallExpr; }
    
    public Expr getLHS() { return argument; }
    
    public String getName() { return callee; }
}