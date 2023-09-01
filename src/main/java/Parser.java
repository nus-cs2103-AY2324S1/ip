import java.util.Scanner;
public class Parser {
    Scanner scanner;
    public Parser() {
        this.scanner = new Scanner(System.in);
    }
    public String getInput() {
        String str = scanner.nextLine();
        return str;
    }
}
