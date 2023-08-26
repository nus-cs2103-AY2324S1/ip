import pardiyem.logic.Pardi;
import pardiyem.parser.Parser;

import java.util.ArrayList;
import java.util.Scanner;

public class Pardiyem {
    public static void main(String[] args) {
        Pardi pardiyem = new Pardi();
        Scanner scanner = new Scanner(System.in);
        pardiyem.greeting();
        while (scanner.hasNext()) {
            try {
                Parser parser = new Parser();
                ArrayList<String> id = parser.parseCommand(scanner.nextLine());
                if (!pardiyem.run(id)) {
                    break;
                }
            } catch (Exception e) {
                System.out.printf("\n%s\n\n", e.toString());
            }
        }
        pardiyem.exit();    
    }
}
