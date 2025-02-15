import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) {
        Model model = new Model();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try { 
            while (model.run(br.readLine()));
        } catch (IOException e) {
            System.err.println(e);
        } 
    }
}