package friday;
public class Ui {

    public void greeting() {
        System.out.println("Hello! I'm FRIDAY!\n" +
                "What can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the tasks currently in the task list.
     *
     * @param taskList The list of tasks.
     */
    public void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList);
    }
    public void findTasks(TaskList taskList) {
        System.out.println("Here are the matching tasks in your list");
        System.out.println(taskList);
    }

    /**
     * Notifies the user that a task has been marked as not done.
     */
    public void unmark() {
        System.out.println("Nice! I've marked this task as not done yet:");
    }

    /**
     * Notifies the user that a task has been marked as done.
     */
    public void mark() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Displays a message related to the task list.
     *
     * @param taskList The list of tasks.
     */
    public void taskListMessage(TaskList taskList) {
        taskList.message();
    }

    /**
     * Notifies the user that a task has been added.
     *
     * @param taskType The type of task that was added.
     */
    public void addTask(Task taskType) {
        System.out.println("added: " + taskType);
    }

    /**
     * Displays an error message when loading tasks fails.
     *
     * @param e The exception containing the error message.
     */
    public void showLoadingError(FridayException e) {
        System.out.println(e.getMessage());
    }

    public void showTodoError() throws FridayException {
        throw new FridayException("OOPS!!! The description of a todo cannot be empty.\n");
    }

    public void showDeadlineFormatError() throws FridayException {
        throw new FridayException("Incorrect format for 'deadline'. Here is a sample:\ndeadline return book /by Sunday\n");
    }

    public void showDeadlineMissingError() throws FridayException {
        throw new FridayException("Please provide both a task description and a deadline date.");
    }

    public void showEventFormatError() throws FridayException {
        throw new FridayException("Incorrect format for 'event'. Expected format: event TASK_DESCRIPTION /from START_TIME /to END_TIME");
    }

    public void showEventMissingError() throws FridayException {
        throw new FridayException("Please provide a task description, start time, and end time.");
    }

    public void showBadInputError() throws FridayException {
        throw new FridayException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }

    public void showFindError() throws FridayException {
        throw new FridayException("Oops! Please add a keyword to search for!");
    }
}