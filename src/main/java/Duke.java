import java.util.Scanner;

public class Duke {
    private void greet() {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Hello! I'm Duke\n");
        System.out.println("    What can I do for you?\n");
        System.out.println("    ____________________________________________________________\n");
    }

    private void bye() {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    Bye. Hope to see you again soon!\n");
        System.out.println("    ____________________________________________________________\n");
    }

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                this.bye();
                break;
            } else {
                System.out.println("    ____________________________________________________________\n");
                System.out.println("    " + command + "\n");
                System.out.println("    ____________________________________________________________\n");
            }
        }
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.echo();
    }
}
