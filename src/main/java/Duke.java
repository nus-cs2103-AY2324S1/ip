import java.util.Scanner;
public class Duke {
    static Scanner scanner = new Scanner(System.in);
    private String name;
    private TaskList taskList;

    public Duke(String name) {
        this.name = name;
        this.taskList = new TaskList();
    }

    public void echo(String line) {
        formatPrintMessage(line);
    }

    public void greet() {
        System.out.println();
        formatPrintMessage("Hello! I'm " + this.name + "\nWhat can I do for you?");
    }
    public void exit() {
        System.out.println();
        formatPrintMessage("Bye. Hope to see you again soon!");
    }

    public void formatPrintMessage(String message) {
        System.out.println(message);
        System.out.println();
    }

    public void handleInput(String input){
        String[] inputArr = input.split(" ");
        String command = inputArr[0];

        try {
            switch (command) {
                case "list":
                    this.taskList.showAllTasks();
                    break;
                case "mark":
                    int taskNumber = Integer.parseInt(inputArr[1]);
                    this.taskList.markTaskAsDone(taskNumber);
                    break;
                case "unmark":
                    int taskNumber2 = Integer.parseInt(inputArr[1]);
                    this.taskList.unmarkTaskAsDone(taskNumber2);
                    break;
                case "todo":
                    String description = validateToDoCommand(input);
                    this.taskList.addTask(description);
                    break;
                case "deadline":
                    String description2 = validateDeadlineCommand(input);
                    this.taskList.addTask(description2);
                    break;
                case "event":
                    String description3 = validateEventCommand(input);
                    this.taskList.addTask(description3);
                    break;
                case "delete":
                    int taskNumber3 = Integer.parseInt(inputArr[1]);
                    this.taskList.deleteTask(taskNumber3);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println();
            formatPrintMessage(e.getMessage());
        }
    }

    public String validateToDoCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return input.replace("todo", "");
    }

    public String validateDeadlineCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] description = input.replace("deadline", "").trim().split(" ");
        int index = -1;
        if (description.length > 2) {
            for (int i = 0; i < description.length; i++) {
                if (description[i].equals("/by")) {
                    index = i;
                    break;
                }
            }
        }

        if (index == -1 || index == 0 || index == description.length - 1) {
            throw new DukeException("☹ OOPS!!! The format of a deadline is invalid. Format: deadline <task name> /by <date>");
        }

        return input.replace("deadline", "");
    }

    public String validateEventCommand(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        if (inputArr.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }

        String[] description = input.replace("event", "").trim().split(" ");
        int fromIndex = -1;
        int toIndex = -1;
        if (description.length > 2) {
            for (int i = 0; i < description.length; i++) {
                if (description[i].equals("/from")) {
                    fromIndex = i;
                }
                if (description[i].equals("/to")) {
                    toIndex = i;
                }
            }
        }

        if (fromIndex == -1 || toIndex == -1 || fromIndex == 0 || toIndex == 0 || fromIndex >= toIndex || fromIndex == description.length - 1 || toIndex == description.length - 1) {
            throw new DukeException("☹ OOPS!!! The format of a event is invalid. Format: event <task name> /from <date> /to <date>");
        }

        return input.replace("event", "");
    }

    public void run(){
        greet();
        String line = scanner.nextLine();
        while (!line.equals("bye")) {
            handleInput(line);
            line = scanner.nextLine();
        }
        exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("Duke");
        duke.run();
    }

}