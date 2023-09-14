package Kevin.Ui;

import java.util.Scanner;

public class Ui {
    public static final String line = "____________________________________________________________";
    public void printGreetMessage() {
        String greet = line + "\n"
                + "Hello! I'm Kevin.Kevin.\n"
                + "What can I do for you?\n"
                + line;
        System.out.println(greet);
    }

    public void printByeMessage() {
        String bye = line + "\n"
                + "Bye. Hope to see you again soon!\n"
                + line;
        System.out.println(bye);
    }

    public String readUserCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
