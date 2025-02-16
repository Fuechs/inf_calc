import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Interpreter {
    Stack<Double> stack;
    Map<String, Double> map;

    public Interpreter() {
        stack = new Stack<>();
        map = new HashMap<>();
    }

    public void run(AST root) {
        switch (root.getType()) {
            case VariableStmt:
                run(root.getLHS());
                map.put(root.getName(), stack.pop());
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
                }
                break;
            case NumberExpr: 
                stack.push(root.getValue());
                break;
            case NameExpr:
                if (!map.containsKey(root.getName())) {
                    System.err.println("No variable called '"+root.getName()+"'");
                    System.exit(1);
                    return;
                }
                stack.push(map.get(root.getName()));
                break;
        }
    }

    public String getResult() { 
        return stack.empty() ? "null" : stack.pop().toString(); 
    }
}
