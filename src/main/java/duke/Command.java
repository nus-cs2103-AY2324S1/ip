package duke;

import duke.exception.IncompleteMessageException;
import duke.exception.DetailsUnknownException;
import duke.exception.InvalidTaskNumberException;
public class Command {

    private TaskList taskList;
    private Ui ui;

    public Command(TaskList tasklist, Ui ui) {
        this.taskList = tasklist;
        this.ui = ui;
    }

    public void handleList() {
        this.taskList.listOutTasks();
    }

    public void handleDelete(String userInput, String keyword) throws IncompleteMessageException, InvalidTaskNumberException {
        if (userInput.length() == keyword.length()) {
            throw new IncompleteMessageException("Delete");
        }

        String getIndex = userInput.substring(keyword.length() + 1);
        int taskIndex = Integer.parseInt(getIndex);

        if (taskIndex > this.taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }

        this.taskList.deleteTask(taskIndex);
    }

    public void handleDone(String userInput, String keyword) throws IncompleteMessageException, InvalidTaskNumberException {
        if (userInput.length() == keyword.length()) {
            throw new IncompleteMessageException("Done");
        }

        String getIndex = userInput.substring(keyword.length() + 1);
        int taskIndex = Integer.parseInt(getIndex);

        if (taskIndex > this.taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }

        this.taskList.markAsDone(taskIndex);
    }

    public void handleUndone(String userInput, String keyword) throws IncompleteMessageException, InvalidTaskNumberException {
        if (userInput.length() == keyword.length()) {
            throw new IncompleteMessageException("Undone");
        }

        String getIndex = userInput.substring(keyword.length() + 1);
        int taskIndex = Integer.parseInt(getIndex);

        if (taskIndex > this.taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }

        this.taskList.markAsUndone(taskIndex);
    }

    public void handleDeadline(String userInput, String keyword) throws IncompleteMessageException, DetailsUnknownException {
        if (userInput.length() == keyword.length()) {
            throw new IncompleteMessageException("duke.Deadline");
        }

        int byIndex = userInput.indexOf("/by");

        if (byIndex == -1 || byIndex == userInput.length() - 4) {
            throw new DetailsUnknownException();
        }

        String taskDescription = userInput.substring(keyword.length() + 1, byIndex);
        String deadlineInfo = userInput.substring(byIndex + 4);
        if (taskDescription.isEmpty() || deadlineInfo.isEmpty()) {
            throw new DetailsUnknownException();
        }

        Deadline task = new Deadline(taskDescription, deadlineInfo);
        this.taskList.addTask(task);
    }

    public void handleEvent(String userInput, String keyword) throws IncompleteMessageException, DetailsUnknownException {
        int fromIndex = userInput.indexOf("/from");
        int toIndex = userInput.indexOf("/to");

        if (userInput.length() == keyword.length() || fromIndex == -1 || toIndex == -1) {
            throw new IncompleteMessageException("duke.Event");
        }

        String taskDescription = userInput.substring(keyword.length() + 1, userInput.indexOf("/from") - 1);
        String startDetails = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
        String endDetails = userInput.substring(userInput.indexOf("/to") + 4);
        if (taskDescription.isEmpty() || startDetails.isEmpty() || endDetails.isEmpty()) {
            throw new DetailsUnknownException();
        }

        Event task = new Event(taskDescription, startDetails, endDetails);
        this.taskList.addTask(task);
    }

    public void handleTodo(String userInput, String keyword) throws IncompleteMessageException, DetailsUnknownException {
        if (userInput.length() <= keyword.length() + 1) {
            throw new IncompleteMessageException("duke.ToDo");
        }

        String taskDescription = userInput.substring(keyword.length() + 1);

        ToDo task = new ToDo(taskDescription);
        this.taskList.addTask(task);
    }
}
