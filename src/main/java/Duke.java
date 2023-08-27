import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage = new Storage();
    private TaskList taskList;

    private void greet() {
        String greetMsg = "Hello! I'm Atlas\n"
                + "What can I do for you?\n"
                + "Type 'help' to view available commands\n";
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
                String input = sc.nextLine();
                ArrayList<String> parsedInput = Parser.parseUserInput(input);
                Command command = Parser.parseCommand(input);
                switch (command) {
                    case BYE:
                        this.exit();
                        return;
                    case LIST:
                        this.list();
                        break;
                    case HELP:
                        this.help();
                        break;
                    case MARK:
                        this.taskList.markAsDone(parsedInput);
                        break;
                    case UNMARK:
                        this.taskList.markAsUndone(parsedInput);
                        break;
                    case DELETE:
                        this.taskList.delete(parsedInput);
                        break;
                    case TODO:
                        this.taskList.newTodo(parsedInput);
                        break;
                    case DEADLINE:
                        this.taskList.newDeadline(parsedInput);
                        break;
                    case EVENT:
                        this.taskList.newEvent(parsedInput);
                        break;
                }
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    private void start() {
        this.greet();
        this.taskList = new TaskList(this.storage.loadData());
        this.listen();
    }

    private void help() {
        String helpMsg = "Here are the available commands:\n"
                + "1. bye - Exit the program\n"
                + "2. list - List all tasks\n"
                + "3. mark <taskNumber> - Mark a task as done\n"
                + "4. unmark <taskNumber> - Mark a task as undone\n"
                + "5. delete <taskNumber> - Delete a task\n"
                + "6. todo <description> - Add a new todo task\n"
                + "7. deadline <description> /by <dueDate> - Add a new deadline task\n"
                + "8. event <description> /from <startDate> /to <endDate> - Add a new event task\n"
                + "9. help - Displays the available commands\n";
        System.out.println(helpMsg);
    }

    public static void main(String[] args) {
        new Duke().start();
    }
}
