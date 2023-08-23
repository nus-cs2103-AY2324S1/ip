import java.util.ArrayList;
import java.util.Scanner;
public class UI {
    private final Scanner scanner = new Scanner(System.in);
    public void horizontalLines() {
        System.out.println("____________________________________________________________"); //length taken from sample
    }

    public void lineSandwich(String message) {
        horizontalLines();
        display(message);
        horizontalLines();
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void display(String message) {
        System.out.println(message);
    }

    public void openingMessage() {
        lineSandwich("Hello there. I'm Whelmed.\nWhat do you want?");
    }
}