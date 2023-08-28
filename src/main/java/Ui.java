public class Ui {

    public void greeting() {
        System.out.println("Hello! I'm FRIDAY!\n" +
                "What can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void listTasks(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(taskList);
    }

    public void unmark() {
        System.out.println("Nice! I've marked this task as not done yet:");
    }

    public void mark() {
        System.out.println("Nice! I've marked this task as done:");
    }

    public void taskListMessage(TaskList taskList) {
        taskList.message();
    }

    public void addTask(Task taskType) {
        System.out.println("added: " + taskType);
    }

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
}