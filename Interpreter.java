import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Interpreter {
    Stack<Double> stack;
    Map<String, Double> variables;
    Map<String, AST> functions;

    public Interpreter() {
        stack = new Stack<>();
        variables = new HashMap<>();
        functions = new HashMap<>();

        functions.put("sqrt", // sqrt(x) = x^(1/2)
            new BinaryExpr('^', 
                new NameExpr("x"),
                new BinaryExpr('/',    
                    new NumberExpr(1), 
                    new NumberExpr(2))));
    }

    public void run(AST root) {
        switch (root.getType()) {
            case VariableStmt:
                run(root.getLHS());
                variables.put(root.getName(), stack.pop());
                break;
            case BinaryExpr:
                run(root.getLHS());
                run(root.getRHS());
                double b = stack.pop();
                double a = stack.pop();
                switch (root.getOperator()) {
                    case '+' -> stack.push(a + b); 
                    case '-' -> stack.push(a - b); 
                    case '*' -> stack.push(a * b);
                    case '/' -> stack.push(a / b);
                    case '^' -> stack.push(Math.pow(a, b));
                }
                break;
            case NumberExpr: 
                stack.push(root.getValue());
                break;
            case NameExpr:
                if (!variables.containsKey(root.getName())) {
                    System.err.println("No variable called '"+root.getName()+"'");
                    System.exit(1);
                    return;
                }
                stack.push(variables.get(root.getName()));
                break;
        }
    }

    public String getResult() { 
        return stack.empty() ? "null" : stack.pop().toString(); 
    }
}
