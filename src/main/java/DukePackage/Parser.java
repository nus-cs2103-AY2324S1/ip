package DukePackage;

import java.util.Scanner;


//function to retrieve string that the user input
public class Parser {
    Scanner scanner;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public String getInput() {
        Scanner scanner = this.scanner;
        //to mimic chatBot
        System.out.print(" ");
        return scanner.nextLine();
    }
}
