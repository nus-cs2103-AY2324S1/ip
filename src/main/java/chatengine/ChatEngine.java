package chatengine;

import io.IOHandler;
import io.ConsoleIO;
import task.Task;
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
        } else {
            Task newTask = new Task(command);
            taskList.addTask(newTask);
            ioHandler.writeOutput("Added new task: " + command);
        }
    }
}
