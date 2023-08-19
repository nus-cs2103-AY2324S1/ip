import java.util.Scanner;
public class Duke {
    private TaskList taskList = new TaskList();
    private void greet() {
        String greetMsg = "Hello! I'm Atlas\n"
                + "What can I do for you?\n";
        System.out.println(greetMsg);
    }

    private void exit() {
        String exitMsg = "Bye. Hope to see you again soon!\n";
        System.out.println(exitMsg);
    }

    private void add(String msg) {
        this.taskList.add(msg);
    }

    private void list() {
        this.taskList.list();
    }

    private void listen() {
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        if (msg.equals("bye")) {
            exit();
        } else if (msg.equals("list")) {
            list();
            listen();
        } else {
            add(msg);
            listen();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.listen();
    }
}
