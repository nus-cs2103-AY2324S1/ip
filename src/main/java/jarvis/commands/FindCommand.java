package jarvis.commands;

import java.util.ArrayList;

import jarvis.Storage;
import jarvis.Ui;

import jarvis.exceptions.InvalidTaskFormatException;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;

public class FindCommand implements Command {

    private String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidTaskFormatException {
        if (userInput.equalsIgnoreCase("event")) {
            throw new InvalidTaskFormatException(null);
        }

        int indexOfFind = userInput.indexOf("find");
        String keyword = userInput.substring(indexOfFind + 4).trim();

        ArrayList<Task> tasks = taskList.getTaskList();
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            String taskTitle = task.getTitle();
            if (taskTitle.contains(keyword)) {
                foundTasks.add(task);
            }
        }
        if (foundTasks.size() == 0) {
            ui.printResponse("No matching task is found, Master. Please check your spelling or use another word");
        } else {
            ui.printTasks(foundTasks);
        }
    }
}