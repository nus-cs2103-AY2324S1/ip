import java.util.Scanner;

public class Duke {
    private Storage storage = new Storage();
    private TaskList taskList;

    private enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }
    private void greet() {
        String greetMsg = "Hello! I'm Atlas\n"
                + "What can I do for you?\n";
        System.out.println(greetMsg);
    }

    private void exit() {
        String exitMsg = "Bye. Hope to see you again soon!";
        this.storage.saveData(this.taskList);
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
                Command command = this.convert((msgArr[0]));
                switch (command) {
                    case BYE:
                        this.exit();
                        return;
                    case LIST:
                        this.list();
                        break;
                    case MARK:
                        this.taskList.markAsDone(msgArr);
                        break;
                    case UNMARK:
                        this.taskList.markAsUndone(msgArr);
                        break;
                    case DELETE:
                        this.taskList.delete(msgArr);
                        break;
                    case TODO:
                        this.taskList.newTodo(msg);
                        break;
                    case DEADLINE:
                        this.taskList.newDeadline(msg);
                        break;
                    case EVENT:
                        this.taskList.newEvent(msg);
                        break;
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    private Command convert(String str) throws DukeNoSuchCommandException {
        try {
            return Command.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DukeNoSuchCommandException();
        }
    }

    private void start() {
        this.greet();
        this.taskList = new TaskList(this.storage.loadData());
        this.listen();
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
