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
            try {
                processInput(input);
            } catch (ChadException e) {
                ioHandler.writeOutput("Error: " + e.getMessage());
            }
        }
        ioHandler.sayGoodbye();
    }

    private void processInput(String input) throws ChadException {
        if (input.trim().isEmpty()) {
            throw new ChadException.InvalidArgumentException("Input cannot be empty.");
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0];

        try {
            switch (command.toLowerCase()) {
                case "mark":
                case "unmark":
                    if (parts.length < 2) {
                        throw new ChadException.InvalidArgumentException("Missing index for " + command);
                    }
                    int index = Integer.parseInt(parts[1]) - 1;
                    String response = "mark".equalsIgnoreCase(command) ? taskList.markTaskAsDone(index) : taskList.markTaskAsNotDone(index);
                    ioHandler.writeOutput(response);
                    break;

                case "list":
                    ioHandler.writeOutput(taskList.displayTasks());
                    break;

                case "todo":
                    if (parts.length < 2) {
                        throw new ChadException.InvalidArgumentException("Missing task description for ToDo.");
                    }
                    taskList.addTodo(parts[1]);
                    ioHandler.writeOutput("Added new ToDo: " + parts[1]);
                    break;

                case "deadline":
                    if (parts.length < 2 || !parts[1].contains(" /by ")) {
                        throw new ChadException.InvalidArgumentException("Invalid format for Deadline. Use: deadline {task} /by {date}");
                    }
                    String[] deadlineParts = parts[1].split(" /by ", 2);
                    taskList.addDeadline(deadlineParts[0], deadlineParts[1]);
                    ioHandler.writeOutput("Added new Deadline: " + deadlineParts[0] + " by " + deadlineParts[1]);
                    break;

                case "event":
                    if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
                        throw new ChadException.InvalidArgumentException("Invalid format for Event. Use: event {task} /from {start} /to {end}");
                    }
                    String[] eventParts = parts[1].split(" /from | /to ", 3);
                    taskList.addEvent(eventParts[0], eventParts[1], eventParts[2]);
                    ioHandler.writeOutput("Added new Event: " + eventParts[0] + " from " + eventParts[1] + " to " + eventParts[2]);
                    break;

                case "delete":
                    if (parts.length < 2) {
                        throw new ChadException.InvalidArgumentException("Invalid format for Delete Task Operation. Use: delete {taskIndex}");
                    }
                    int indexOfTaskToDelete = Integer.parseInt(parts[1]) - 1;
                    String responseFromDeleteOperation= taskList.deleteTask(indexOfTaskToDelete);
                    ioHandler.writeOutput(responseFromDeleteOperation);
                    break;

                default:
                    throw new ChadException.InvalidCommandException("Unknown command: " + command);
            }
        } catch (NumberFormatException e) {
            throw new ChadException.InvalidFormatException("Invalid number format.");
        }
    }

}
