package duke.ui;

import duke.tasks.TaskList;

public class Ui {
    private String botName;
    private static String SPACER = "--------------------------------------------------------------------------";

    public Ui(String botName) {
        this.botName = botName;
    }

    public void playGreeting() {
        System.out.println(SPACER);
        System.out.println("Howdy, I'm " + botName + ", your friendly personal assistant!");
        System.out.println("What can I do for you today?");
        System.out.println(SPACER);
    }

    public void playGoodbye() {
        System.out.println(SPACER);
        System.out.println("See ya later, alligator! I'm waiting here if you need anything :>");
        System.out.println(SPACER);
    }

    public void playTaskComplete(String taskDescription) {
        System.out.println(SPACER);
        System.out.println("Good job on completing this task! You are an awesome possum!!");
        System.out.println(taskDescription);
        System.out.println(SPACER);
    }

    public void playTaskIncomplete(String taskDescription) {
        System.out.println(SPACER);
        System.out.println("Man, you've got this extra thing to do now...");
        System.out.println(taskDescription);
        System.out.println(SPACER);
    }

    public void playRemoveTask(String taskDescription, int taskCount) {
        System.out.println(SPACER);
        System.out.println("Guess you've got one less thing to do now.");
        System.out.println(taskDescription);
        System.out.println("Get going! You have " + taskCount + " tasks on record!!");
        System.out.println(SPACER);
    }

    public void playAddTask(String taskDescription, int taskCount) {
        System.out.println(SPACER);
        System.out.println("Okay, so here is the new thing to keep you occupied:");
        System.out.println(taskDescription);
        System.out.println("Get going! You have " + taskCount + " tasks on record!!");
        System.out.println(SPACER);
    }

    public void printTaskList(TaskList taskList) {
        System.out.println(SPACER);
        System.out.println("Hey buddy, here's the stuff you need to do:");
        taskList.printTasksAsList();
        System.out.println(SPACER);
    }

    public void playExceptionMessage(ExceptionMessage exceptionMessage) {
        System.out.println(SPACER);
        System.out.println(exceptionMessage.message);
        System.out.println(SPACER);
    }

    public enum ExceptionMessage {
        MarkCommand_NumberFormatException("HOLD UP! Invalid input for mark/unmark command. Input must be a positive non-zero integer."),
        DeleteCommand_NumberFormatException("HOLD UP! Invalid input for delete command. Input must be a positive non-zero integer."),
        TaskList_IndexOutOfBoundsException("HOLD UP! There is no such task in your list!"),
        AddTask_MissingDescription("HOLD UP! So... what is this task about??"),
        AddTask_DateTimeParseException("HOLD UP! You are not formatting your dates right! Use \"yyyy-MM-dd HH:mm\" >:("),
        AddTask_MissingDeadline("HOLD UP! C'mon, what's the deadline?"),
        AddTask_MissingStartEndDate("HOLD UP! C'mon, when does it start and end?"),
        InvalidInput("HOLD UP! What on earth do you mean??");

        public String message;

        ExceptionMessage(String message) {
            this.message = message;
        }
    }
}
