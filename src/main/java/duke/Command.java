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

        try {
            String[] inputToArr = Parser.splitInput(userInput); //return [keyword, task /by time]

            if (userInput.length() == keyword.length()) {
                throw new IncompleteMessageException("D");
            }

            String[] taskArr = Parser.splitDeadlineDetails(inputToArr[1]); // return [task, /by time]
            String taskDescription = taskArr[0];
            String taskTime = taskArr[1];

            Deadline deadline = new Deadline(taskDescription, taskTime);
            this.taskList.addTask(deadline);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DetailsUnknownException();
        } catch (IncompleteMessageException e) {
            e.getMessage();
        }
    }

    public void handleEvent(String userInput, String keyword) throws IncompleteMessageException, DetailsUnknownException {

        try {
            String[] inputToArr = Parser.splitInput(userInput); // return [kw, task /from time /to time]

            if (userInput.length() == keyword.length()) {
                throw new IncompleteMessageException("E");
            }

            String[] taskArr = Parser.splitEventDetails(inputToArr[1]); // return [task, /from time /to time]
            String taskDescription = taskArr[0];
            String taskTime = taskArr[1];

            Event event = new Event(taskDescription, taskTime);
            this.taskList.addTask(event);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DetailsUnknownException();
        } catch (IncompleteMessageException e) {
            e.getMessage();
        }
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