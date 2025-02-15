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
                switch (root.getOperator()) {
                    case '+': stack.push(stack.pop() + stack.pop()); break;
                    case '-': {
                        // stack: b a ...
                        // a - b
                        double b = stack.pop();
                        stack.push(stack.pop() - b);
                        break;
                    }
                    case '*': stack.push(stack.pop() * stack.pop()); break;
                    case '/': {
                        // stack: b a ...
                        // a / b
                        double b = stack.pop();
                        stack.push(stack.pop() / b);
                        break;
                    }
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
