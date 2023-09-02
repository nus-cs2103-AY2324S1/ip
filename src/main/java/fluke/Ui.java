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

    public void showTaskAdded(Task addedTask, TaskList tasks) {
        System.out.println("(Scribbles randomly). Hope I got it right!");
        System.out.println("  " + addedTask);
        System.out.println("I think there are now " + tasks.getSize() + " tasks in the list.");
        addHorizontalLine();
    }

    public void showTaskDeleted(Task deletedTask, TaskList tasks) {
        System.out.println("fluke.tasks.Task deleted! I hope it's the right one:");
        System.out.println("  " + deletedTask);
        System.out.println("I think there are now " + tasks.getSize() + " tasks in the list.");
        addHorizontalLine();
    }

    public void showTaskMarkedAsDone(Task markedTask) {
        System.out.println("I have marked this task as done, I hope it's the right one:");
        System.out.println("  " + markedTask);
        addHorizontalLine();
    }

    public void showTaskMarkedAsUndone(Task markedTask) {
        System.out.println("I have marked this task as not done yet, I hope it's the right one:");
        System.out.println("  " + markedTask);
        addHorizontalLine();
    }

    public void showLoadingError() {
        showError("Failed to load!");
    }
}
