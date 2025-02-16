import java.util.ArrayList;

public class Lexer {
    public static ArrayList<String> lex(String input) {
        ArrayList<String> tokens = new ArrayList<>();
        String current = "";

        for (int i = 0; i < input.length(); i++) 
            switch (input.charAt(i)) {
                case '+':
                case '-':
                case '*':
                case '/':
                case '^':
                case '(':
                case ')':
                case '=':
                    tokens.add(String.valueOf(input.charAt(i)));
                    break;

                case ' ': break;

                default:
                    if (Character.isDigit(input.charAt(i))) { // parse numbers
                        boolean hasDot = false;
                        current += input.charAt(i);
                        while (i+1 < input.length() && (Character.isDigit(input.charAt(i+1)) || input.charAt(i+1) == '.')) {
                            if (input.charAt(i+1) == '.')
                                if (hasDot) {
                                    System.err.println("Invalid number: '"+current+".'");
                                    break;
                                } else
                                    hasDot = true;
                            current += input.charAt(++i);
                        }
                    } else if (Character.isLetter(input.charAt(i))) { // parse identifiers
                        current += input.charAt(i);
                        while (i+1 < input.length() && Character.isLetter(input.charAt(i+1)))
                            current += input.charAt(++i);
                    } else  
                        System.err.println("Unknown character: '"+input.charAt(i)+"'");

                    tokens.add(current);
                    current = "";
                    break;
            }  

        tokens.add("\0");
        return tokens;
    }
}
