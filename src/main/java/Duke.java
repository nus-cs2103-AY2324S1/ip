import java.util.Scanner;
public class Duke {
    private TaskList taskList = new TaskList();
    private void greet() {
        String greetMsg = "Hello! I'm Atlas\n"
                + "What can I do for you?\n";
        System.out.println(greetMsg);
    }

    private void exit() {
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println(exitMsg);
    }

    private void list() {
        this.taskList.list();
    }

    private void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String msg = sc.nextLine();
                String[] msgArr = msg.split(" ");
                if (msgArr[0].equals("bye")) {
                    exit();
                    break;
                } else if (msgArr[0].equals("list")) {
                    list();
                } else if (msgArr[0].equals("mark")) {
                    this.taskList.markAsDone(msgArr);
                } else if (msgArr[0].equals("unmark")) {
                    this.taskList.markAsUndone(msgArr);
                } else if (msgArr[0].equals("delete")) {
                    this.taskList.delete(msgArr);
                } else if (msgArr[0].equals("todo")){
                    this.taskList.newTodo(msg);
                } else if (msgArr[0].equals("deadline")){
                    this.taskList.newDeadline(msg);
                } else if (msgArr[0].equals("event")){
                    this.taskList.newEvent(msg);
                } else {
                    throw new DukeNoSuchCommandException();
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.listen();
    }
}
