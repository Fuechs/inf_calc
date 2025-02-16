import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class NumberExpr extends Expr {
    private double value;

    public NumberExpr(double value) {
        this.value = value;
    }

    public void print() {
        Locale locale = new Locale("en_us"); // use . instead of ,
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
        DecimalFormat format = new DecimalFormat("##.###", symbols); // remove unecessary zeros
        System.out.print(format.format(value));
    }

    public ASTType getType() { return ASTType.NumberExpr; }

    public double getValue() { return value; }
}
