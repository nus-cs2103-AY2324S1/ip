package duke.helper;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    Scanner scan;
    String lines;
    public Ui() {
        this.lines = "______________________________";
        this.scan = new Scanner(System.in);
    }
    public void showLoadingError() {
        System.out.println("Error! Cannot initialise new Storage");
    }

    public String readCommand() {
        // no string processing
        String command = this.scan.nextLine();
        return command;
    }
    public void greet() {
        System.out.println(this.lines);
        System.out.println("Hello! I'm MeowBot!");
        System.out.println("What can I do for you ?");
        System.out.println(this.lines);
    }

    public void bye() {
        System.out.println(lines);
        System.out.println("Bye. Hope to see you again soon!");
    }

}
