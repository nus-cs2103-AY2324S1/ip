package taskmaster.parser;
import taskmaster.tasks.TaskList;
import taskmaster.storage.Storage;
import taskmaster.exceptions.DukeException;
import taskmaster.ui.Ui;

public class Parser {
    /**
     * Reads user input and calls the relevant method.
     *
     * @param userInput Input from user.
     * @param storage Storage of the program.
     * @param taskList Task list of the program
     * @throws DukeException If the input is not valid command.
     */
    public String parse(String userInput, Storage storage, TaskList taskList) throws DukeException {
        if (userInput.equalsIgnoreCase("bye")) {
            storage.saveTasksToFile();
            return Ui.GOODBYE_MESSAGE;
        } else if (userInput.equalsIgnoreCase("list")) {
            return TaskList.printList();
        } else if (userInput.startsWith("todo")) {
            String description = userInput.substring(5).trim();
            String response = taskList.addTask(TaskList.TaskType.TODO, description, "unmarked");
            return response;
        } else if (userInput.startsWith("event")) {
            String description = userInput.substring(5);
            String response = taskList.addTask(TaskList.TaskType.EVENT, description, "unmarked");
//            storage.saveTasksToFile();
            return response;
        } else if (userInput.startsWith("deadline")) {
            String description = userInput.substring(8);
            String response = taskList.addTask(TaskList.TaskType.DEADLINE, description, "unmarked");
//            storage.saveTasksToFile();
            return response;
        } else if (userInput.startsWith("mark")) {
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                String response = taskList.toggleMark(TaskList.MarkStatus.MARK, taskIndex);
//                storage.saveTasksToFile();
                return response;
            } else {
                throw new DukeException("Invalid command");
            }
        } else if (userInput.startsWith("unmark")) {
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                String response = taskList.toggleMark(TaskList.MarkStatus.UNMARK, taskIndex);
//                storage.saveTasksToFile();
                return response;
            } else {
                throw new DukeException("Invalid command");
            }
        } else if (userInput.startsWith("delete")) {
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                return taskList.deleteTask(taskIndex);
            } else {
                System.out.println("Please specify the task number to delete.");
            }
//            storage.saveTasksToFile();
        } else if (userInput.startsWith("due")) {
            String date = userInput.substring(4).trim();
            return taskList.printTasksByDate(date);
        } else if (userInput.startsWith("find")) {
            String keyword = userInput.substring(5).trim();
            return taskList.findTask(keyword);
        } else {
            throw new DukeException("Please enter a valid command!");
        }
        return "Input valid command";
    }
}
