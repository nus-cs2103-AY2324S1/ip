package duke;

import java.util.Scanner;
public class Parser {
    private final Scanner scanner;

    Parser() {
        this.scanner = new Scanner(System.in);
    }

    String getInput() {
        return this.scanner.nextLine();

    }

    void closeParser() {
        scanner.close();
    }

}
