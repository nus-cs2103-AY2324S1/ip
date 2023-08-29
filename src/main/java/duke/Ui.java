import tasks.Task;
import tasks.TaskList;

public class Ui {
    private static final String BAR = "____________________________________________________________";

    public static void showError(String errorMessage) {
        printWrapped(errorMessage);
    }

    public static void greetUser() {
        String greeting = "Hello! I'm CringeBot\n"
                + "What can I do for you?";
        printWrapped(greeting);
    }

    public static void bidFarewell() {
        printWrapped("Bye. Hope to see you again soon!");
    }

    public static void printItems(TaskList tasks) {
        StringBuilder sayWord = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            sayWord.append(String.format("\n%d.%s", i + 1, tasks.getTaskWithIndex(i)));
        }
        printWrapped(sayWord.toString());
    }

    public static void deleteItem(int taskSize, Task deletedTask) {
        String sayWord = "Noted. I've removed this task:\n"
                + deletedTask
                + "\nNow you have "
                + taskSize
                + " tasks in the list.";
        printWrapped((sayWord));
    }

    public static void printWrapped(String input) {
        System.out.printf("%s\n%s\n%s%n", Ui.BAR, input, Ui.BAR);
    }
}
