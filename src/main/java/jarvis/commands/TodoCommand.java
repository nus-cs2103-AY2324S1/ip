package jarvis.commands;

import jarvis.Storage;
import jarvis.TaskList;
import jarvis.Todo;
import jarvis.Ui;
import jarvis.exceptions.InvalidIndexException;
import jarvis.exceptions.InvalidTaskFormatException;

public class TodoCommand implements Command{

    private String userInput;

    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidIndexException, InvalidTaskFormatException {
        if (userInput.equalsIgnoreCase("todo")) {
                throw new InvalidTaskFormatException(null);
            }
            String taskTitle = userInput.substring(5).trim();
            Todo todo = new Todo(taskTitle, false);
            taskList.addTask(todo);
            storage.saveTasks(taskList.getTaskList());
            ui.printResponse("Yes Master! I've added this task: \n" + "\t" + todo.toString() + "\n" +
                    "    Master, you have " + taskList.getTaskCount() + " tasks in the list.");
    }
}
