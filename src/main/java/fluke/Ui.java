package fluke;

import fluke.tasks.Task;

/**
 * This class contains all user interface related functionalities.
 */
public class Ui {
    private final static String CHATBOT_NAME = "fluke.Fluke";
    private final static String LOGO =
            "    ________      __\n" +
                    "   / ____/ /_  __/ /_____\n" +
                    "  / /_  / / / / / //_/ _ \\\n" +
                    " / __/ / / /_/ / ,< /  __/\n" +
                    "/_/   /_/\\__,_/_/|_|\\___/";
    private final static String GREETING =
            "Hello! I'm " + CHATBOT_NAME + ", everything I do is down to luck!" + "\n" +
                    "Feeling lucky today?";
    private final static String GOODBYE = "Bye. Good luck!";
    public void greet() {
        // introduce fluke.Fluke
        System.out.println(LOGO);
        addHorizontalLine();
        System.out.println(GREETING);
        addHorizontalLine();
    }
    public void sayBye() {
        System.out.println(GOODBYE);
    }
    private void addHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
    public void showError(String message) {
        System.out.println("☹ OOPS!!! " + message);
        addHorizontalLine();
    }

    public void showListOfTasks(TaskList tasks) {
        System.out.println("Here are the tasks we have currently!");
        System.out.println(tasks);
        addHorizontalLine();
    }

    public void showTaskAdded(Task task, TaskList tasks) {
        System.out.println("(Scribbles randomly). Hope I got it right!");
        System.out.println("  " + task);
        System.out.println("I think there are now " + tasks.getSize() + " tasks in the list.");
        addHorizontalLine();
    }

    public void showTaskDeleted(Task task, TaskList tasks) {
        System.out.println("fluke.tasks.Task deleted! I hope it's the right one:");
        System.out.println("  " + task);
        System.out.println("I think there are now " + tasks.getSize() + " tasks in the list.");
        addHorizontalLine();
    }

    public void showTaskMarkedAsDone(Task task) {
        System.out.println("I have marked this task as done, I hope it's the right one:");
        System.out.println("  " + task);
        addHorizontalLine();
    }

    public void showTaskMarkedAsUndone(Task task) {
        System.out.println("I have marked this task as not done yet, I hope it's the right one:");
        System.out.println("  " + task);
        addHorizontalLine();
    }

    /**
     * Shows the user tasks with a certain keyword
     * @param tasksWithKeyword list of tasks to show
     */
    public void showTasksWithKeyword(TaskList tasksWithKeyword) {
        System.out.println("I have randomly picked out a few tasks. Looks like they have what you want!");
        System.out.println(tasksWithKeyword);
        addHorizontalLine();
    }

    public void showLoadingError() {
        showError("Failed to load!");
    }
}
