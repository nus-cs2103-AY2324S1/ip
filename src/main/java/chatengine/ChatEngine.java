package chatengine;

import io.IOHandler;
import io.ConsoleIO;
import task.TaskList;

public class ChatEngine {
    private final IOHandler ioHandler;
    private final TaskList taskList; // stores list of tasks


    public ChatEngine() {
        this.ioHandler = new ConsoleIO();
        this.taskList = new TaskList();
    }

    public void start() {
        ioHandler.greet();
        String input;
        while(!(input = ioHandler.readInput()).equals("bye")) {
            processInput(input);
        }
        ioHandler.sayGoodbye();
    }

    private void processInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        if ("mark".equalsIgnoreCase(command) && parts.length > 1) {
            int index = Integer.parseInt(parts[1]) - 1;  // Convert to zero-based index
            String response = taskList.markTaskAsDone(index);
            ioHandler.writeOutput(response);
        } else if ("unmark".equalsIgnoreCase(command) && parts.length > 1) {
            int index = Integer.parseInt(parts[1]) - 1;  // Convert to zero-based index
            String response = taskList.markTaskAsNotDone(index);
            ioHandler.writeOutput(response);
        } else if ("list".equalsIgnoreCase(command)) {
            String taskListString = taskList.displayTasks();
            ioHandler.writeOutput(taskListString);
        } else if ("todo".equalsIgnoreCase(command)) {
            taskList.addTodo(parts[1]);
            ioHandler.writeOutput("Added new ToDo: " + parts[1]);
        } else if ("deadline".equalsIgnoreCase(command)) {
            String[] deadlineParts = parts[1].split(" /by ", 2);
            taskList.addDeadline(deadlineParts[0], deadlineParts[1]);
            ioHandler.writeOutput("Added new Deadline: " + deadlineParts[0] + " by " + deadlineParts[1]);
        } else if ("event".equalsIgnoreCase(command)) {
            String[] eventParts = parts[1].split(" /from | /to ", 3);
            taskList.addEvent(eventParts[0], eventParts[1], eventParts[2]);
            ioHandler.writeOutput("Added new Event: " + eventParts[0] + " from " + eventParts[1] + " to " + eventParts[2]);
        } else {
            // Handle unknown commands
            ioHandler.writeOutput("Unknown command: " + command);
        }
    }
}
