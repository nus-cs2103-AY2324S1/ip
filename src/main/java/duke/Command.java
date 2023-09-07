package duke;

import duke.exception.DetailsUnknownException;
import duke.exception.IncompleteMessageException;
import duke.exception.InvalidTaskNumberException;

/**
 * Command is responsible for handling the different command
 * enter by the user.
 */
public class Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for Command class
     *
     * @param tasklist list of task created when the program runs
     * @param ui the Ui object created when the program runs
     */
    public Command(TaskList tasklist, Ui ui) {
        this.taskList = tasklist;
        this.ui = ui;
    }

    /**
     * method to handle the list command from user. Calls the listOutTask() method from TaskList
     *
     */
    public void handleList() {
        this.taskList.listOutTasks();
    }

    /**
     * method to handle the delete command from user.
     * Check if the user entered the command in the correct format. Else throw the relevant exception.
     *
     * @param uI the input entered by user
     * @param keyword the key command word i.e. delete entered by user
     * @throws IncompleteMessageException when user did not specify what task to delete
     * @throws InvalidTaskNumberException when user specify a task number that does not exist
     */
    public void handleDelete(String uI, String keyword) throws IncompleteMessageException, InvalidTaskNumberException {
        if (uI.length() == keyword.length()) {
            throw new IncompleteMessageException("Delete");
        }

        String getIndex = uI.substring(keyword.length() + 1);
        int taskIndex = Integer.parseInt(getIndex);

        if (taskIndex > this.taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }

        this.taskList.deleteTask(taskIndex);
    }

    /**
     * method that handles the mark done command from user.
     * Check if the user entered the command in the correct format. Else throw the relevant exception.
     *
     * @param uI the input entered by user
     * @param keyword the key command word i.e. mark entered by user
     * @throws IncompleteMessageException when user did not specify what task to mark done
     * @throws InvalidTaskNumberException when user specify a task number that does not exist
     */
    public void handleDone(String uI, String keyword) throws IncompleteMessageException, InvalidTaskNumberException {
        if (uI.length() == keyword.length()) {
            throw new IncompleteMessageException("Done");
        }

        String getIndex = uI.substring(keyword.length() + 1);
        int taskIndex = Integer.parseInt(getIndex);

        if (taskIndex > this.taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }

        this.taskList.markAsDone(taskIndex);
    }

    /**
     * method that handles the unmark done command from user.
     * Check if the user entered the command in the correct format. Else throw the relevant exception.
     *
     * @param uI the input entered by user
     * @param keyword the key command word i.e. unmark entered by user
     * @throws IncompleteMessageException when user did not specify what task to mark undone
     * @throws InvalidTaskNumberException when user specify a task number that does not exist
     */
    public void handleUndone(String uI, String keyword) throws IncompleteMessageException, InvalidTaskNumberException {
        if (uI.length() == keyword.length()) {
            throw new IncompleteMessageException("Undone");
        }

        String getIndex = uI.substring(keyword.length() + 1);
        int taskIndex = Integer.parseInt(getIndex);

        if (taskIndex > this.taskList.numOfTasks()) {
            throw new InvalidTaskNumberException();
        }

        this.taskList.markAsUndone(taskIndex);
    }

    /**
     * method that handles the addition of deadline task.
     * check if the necessary details such as the deadline are entered and whether they are in the right format.
     * Else throw the relevant exception.
     *
     * @param uI the input entered by user
     * @param keyword the key command word i.e. deadline entered by user
     * @throws IncompleteMessageException when the user only enter the keyword deadline with no other details
     * @throws DetailsUnknownException when user did not enter the deadline of the task
     */
    public void handleDeadline(String uI, String keyword) throws IncompleteMessageException, DetailsUnknownException {

        try {
            String[] inputToArr = Parser.splitInput(uI); //return [keyword, task /by time]

            if (uI.length() == keyword.length()) {
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

    /**
     * method that handles the addition of event task.
     * check if the necessary details such as the start and end timing are entered
     * and whether they are in the right format.
     * Else throw the relevant exception.
     *
     * @param uI the input entered by user
     * @param keyword the key command word i.e. event entered by user
     * @throws IncompleteMessageException when the user only enter the keyword deadline with no other details
     * @throws DetailsUnknownException when user did not enter the start or end timing of the task
     */
    public void handleEvent(String uI, String keyword) throws IncompleteMessageException, DetailsUnknownException {

        try {
            String[] inputToArr = Parser.splitInput(uI); // return [kw, task /from time /to time]

            if (uI.length() == keyword.length()) {
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

    /**
     * method that handles the addition of todo task.
     * check if the necessary details such as the task details and whether they are in the right format.
     * Else throw the relevant exception.
     *
     * @param userInput the input entered by user
     * @param keyword the key command word i.e. todo entered by user
     * @throws IncompleteMessageException when the user only enter the keyword todo with no other details
     */
    public void handleTodo(String userInput, String keyword) throws IncompleteMessageException {
        if (userInput.length() <= keyword.length() + 1) {
            throw new IncompleteMessageException("duke.ToDo");
        }

        String[] splitInput = Parser.splitInput(userInput);
        String taskDescription = splitInput[1];

        ToDo task = new ToDo(taskDescription);
        this.taskList.addTask(task);
    }

    /**
     * method that handles the find command
     * check if the keyword is entered, else throw the relevant exception
     *
     * @param userInput the user input
     * @param keyword the key command word i.e. find
     * @return the list of task with the keywords inside
     * @throws IncompleteMessageException when the user only enter the keyword without the details
     */
    public String handleFind(String userInput, String keyword) throws IncompleteMessageException {
        if (userInput.length() <= keyword.length()) {
            throw new IncompleteMessageException("Find");
        }

        String[] splitInput = Parser.splitInput(userInput);
        String taskDescription = splitInput[1];

        return ui.find(taskDescription);
    }
}
