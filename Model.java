public class Model {
    // private View view;
    Interpreter interpreter;

    public Model() {
        // view = new View();
        interpreter = new Interpreter();
    } 

    public boolean run(String input) {
        if (input.isEmpty())
            return false;

        // for (String token : Lexer.lex(input))
        //     System.out.println(token);

        Parser parser = new Parser(Lexer.lex(input));
        AST root = parser.parse();

        if (root == null) 
            return true;

        root.print();
        System.out.println();

        interpreter.run(root);
        System.out.println("Output: " + interpreter.getResult());

    
        return true;
    }  
}
