import exception.EmptyCommandException;
import exception.InvalidCommandException;

public class Duke {
    public static void main(String[] args) {
        System.out.println(SystemText.greeting());

        // Load list of tasks stored in text file "task.txt" into the local TaskList
        TaskList list = new TaskList();
        Backend storage = new Backend();
        storage.handleLoad(list);

        // Start Scanner to read user inputs
        SystemText ui = new SystemText(list);
        String answer = ui.getInput();

        // Initialize relevant classes
        Command command = new Command(storage, ui, list);

        // Listens to user commands and perform the relevant functions
        while (true) {
            if (answer.equalsIgnoreCase("bye")) {
                break;
            } else if (answer.equalsIgnoreCase("list")) {
                System.out.println(ui.listTasks());
                answer = ui.getInput();
            } else if (answer.startsWith("mark")) {
                System.out.println(command.handleMark(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("unmark")) {
                System.out.println(command.handleUnMark(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("todo")) {
                System.out.println(command.handleToDo(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("deadline")) {
                System.out.println(command.handleDeadline(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("event")) {
                System.out.println(command.handleEvent(answer));
                answer = ui.getInput();
            } else if (answer.startsWith("delete")) {
                System.out.println(command.handleDelete(answer));
                answer = ui.getInput();
            } else if (answer.length() == 0) {
                System.out.println(ui.printError(new EmptyCommandException()));
                answer = ui.getInput();
            } else {
                System.out.println(ui.printError(new InvalidCommandException()));
                answer = ui.getInput();
            }
        }
        System.out.println(SystemText.exit());
        ui.stopScanner();
    }
}
