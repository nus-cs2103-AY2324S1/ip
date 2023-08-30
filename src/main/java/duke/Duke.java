package duke;

public class Duke {
    private final String name;
    private final TaskList taskList;

    private final Storage storage;
    private final Ui ui;

    public Duke(String name, String filePath) {
        this.name = name;
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(filePath);
        storage.loadTasks(this.taskList);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("Duke", "src/main/java/resource/duke.txt");
        duke.run();
    }

    public void handleInput(String input) {
        String[] inputArr = input.split(" ");
        String command = inputArr[0];

        try {
            switch (command) {
            case "list":
                this.taskList.showAllTasks(this.ui);
                break;
            case "mark":
                int markTaskNumber = Parser.parseInt(inputArr[1]);
                this.taskList.markTaskAsDone(markTaskNumber, this.storage, this.ui);
                break;
            case "unmark":
                int unmarkTaskNumber = Parser.parseInt(inputArr[1]);
                this.taskList.unmarkTaskAsDone(unmarkTaskNumber, this.storage, this.ui);
                break;
            case "todo":
                String todoDescription = Parser.validateToDoCommand(input);
                this.taskList.addTask(todoDescription, this.storage, this.ui);
                break;
            case "deadline":
                String deadlineDescription = Parser.validateDeadlineCommand(input);
                this.taskList.addTask(deadlineDescription, this.storage, this.ui);
                break;
            case "event":
                String eventDescription = Parser.validateEventCommand(input);
                this.taskList.addTask(eventDescription, this.storage, this.ui);
                break;
            case "delete":
                int deleteTaskNumber = Parser.parseInt(inputArr[1]);
                this.taskList.deleteTask(deleteTaskNumber, this.storage, this.ui);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            ui.formatPrintMessage(e.getMessage());
        }
    }

    public void run() {
        ui.greet(this.name);
        String command = ui.readCommand();
        while (!command.equals("bye")) {
            handleInput(command);
            command = ui.readCommand();
        }
        ui.exit();
    }

}
