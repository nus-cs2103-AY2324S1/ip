import pardiyem.logic.Pardi;
import java.util.Scanner;

public class Pardiyem {
    public static void main(String[] args) {
        Pardi pardiyem = new Pardi();
        Scanner scanner = new Scanner(System.in);
        pardiyem.greeting();
        while (pardiyem.run(scanner.nextLine())) {}
        pardiyem.exit();    
    }
}
