package duke;

import duke.task.Task;
import java.util.List;

/**
 * The UI class provides methods for displaying messages and interacting with the user.
 * It handles printing various messages for various contexts.
 */
public class Ui {

    private final static String LINE = "____________________________________________________________";

    /**
     * Prints a horizontal line
     */
    public void printLine(){
        System.out.println(LINE);
    }

    /**
     * Prints greeting message when the application starts.
     */
    public void showGreeting(){
        printLine();
        System.out.println("Hello! I'm MYBOT:)");
        System.out.println("What can I do for you?");
        System.out.println("(if you are entering a deadline/event time please enter in the format date,day,time)");
        printLine();
    }

    /**
     * Prints exit message when the application is exited.
     */
    public void closeGreeting(){
        System.out.println(LINE + "\nBye. Hope to see you again soon!\n" + LINE);
    }

    /**
     * Prints an exception message.
     *
     * @param e The customed exception to be printed.
     */
    public void printException(MYBotExceptions e){
        System.out.println(LINE + "\n" + e.getMessage() + "\n" + LINE);
    }

    /**
     * Prints the tasks entered in order.
     *
     * @param tasks The list of tasks to be printed.
     * @param taskCount The number of tasks in the list.
     */
    public void printTaskList(List<Task> tasks, int taskCount){
        System.out.println(LINE + "\nHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++){
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
        printLine();
    }

    /**
     * Prints a message indicating a task has been added.
     *
     * @param task The task to be added.
     * @param taskCount The total number of tasks after new task is added.
     */
    public void printAddTask(Task task, int taskCount){
        System.out.println(LINE + "\nI've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message indicating a task has been completed.
     *
     * @param task The task that has been marked as completed.
     */
    public void printMarkTask(Task task){
        System.out.println(LINE + "\nGood job completing! I've marked these task as done:):");
        System.out.println(task.toString() + "\n" + LINE);
    }

    /**
     * Prints a message indicating a task has been marked as not completed.
     *
     * @param task The task that has been marked as not completed.
     */
    public void printUnmarkTask(Task task){
        System.out.println(LINE + "\nOK, I've marked this task as not done yet:");
        System.out.println(task.toString() + "\n" + LINE);
    }

    /**
     * Prints a message indicating a task has been removed.
     *
     * @param task The task that has been removed.
     * @param task_List The updated task list after task is removed.
     */
    public void printRemoveTasks(Task task, TaskList task_List) {
        printLine();
        System.out.println("Noted. I've removed this task:\n  " + task.toString());
        System.out.println("Now you have " + task_List.getTask_Count() + " tasks in the list.");
        printLine();
    }

    /**
     * Prints a message notifying users a file to store their tasks has been created.
     */
    public void printFileError() {
        System.out.println("MYBOT has created a file MYBOT.txt to record your tasks");
    }


    public void printNoTasks() {
        System.out.println("You have no task at the moment!");
    }

    public void printMatchingTasks(List<Task> matchingTasks) {
        printLine();
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + "." + matchingTasks.get(i));
        }
        printLine();
    }
}
