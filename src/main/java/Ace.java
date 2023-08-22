import java.util.ArrayList;
import java.util.Scanner;

public class Ace {
    private String line() {
        return "_____________________________________________________\n";
    }

    private String greet() {
        return "Hello! I'm Ace\nWhat can I do for you?\n";
    }

    private String goodbye() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String sendMessage() {
        return line() + greet() + line() + goodbye() + line();
    }
}
