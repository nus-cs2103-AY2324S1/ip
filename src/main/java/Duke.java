import java.util.Scanner;
public class Duke {
    private void greet() {
        String greetMsg = "Hello! I'm Atlas\n"
                + "What can I do for you?\n";
        System.out.println(greetMsg);
    }

    private void exit() {
        String exitMsg = "Bye. Hope to see you again soon!\n";
        System.out.println(exitMsg);
    }

    private void echo(String msg) {
        System.out.println(msg);
    }

    private void listen() {
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        if (msg.equals("bye")) {
            exit();
        } else {
            echo(msg);
            listen();
        }
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.listen();
    }
}
