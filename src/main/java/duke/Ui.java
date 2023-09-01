package duke;

import java.util.Scanner;

public class Ui {
    private static String logo =
            " ___________________\n" +
                    ":' ,__________,  ':  `.\n" +
                    "| '            `  |    `.\n" +
                    "| |  Dukey     |  |      `.\n" +
                    "| |            |  |        \\\n" +
                    "| |  Hello!!!  |  |         ]\n" +
                    "| |            |  |~~~~~~.  )\n" +
                    "| `,__________,'  |\\__O\\_| ,'\n" +
                    "|    _______      |     \\.`\n" +
                    "|<> [___=___](@)<>|    .'\\\n" +
                    "':________________/__.'   )\n" +
                    "   (____________)        /\n" +
                    "                        /\n" +
                    "              _________/\n" +
                    "  ___________/______\n" +
                    " /''''=========='(@)\\___\n" +
                    " |[][][][][][][][][]|   \\ _______\n" +
                    " |[][][][][][][][][]|    \\__     \\\n" +
                    " |[][][][][][][][][]|    |  \\..  |\n" +
                    " \\------------------/    | ( # ) |\n" +
                    "                         |  '''  |\n" +
                    "                         \\_______/";
    ;

    /**
     * Reads a command input from the user.
     *
     * @return The user-entered command.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user upon starting the application.
     */
    public void showWelcomeMessage() {
        System.out.println(logo);
        System.out.println("Hello! I am Dukey.\n"
                + "What can I do for you?");
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Displays a message after marking or unmarking a task, indicating the task's new status.
     *
     * @param taskMarked The task that was marked or unmarked.
     * @param isMark     Whether the task was marked (true) or unmarked (false).
     */
    public void showMarkTaskMessage(String taskMarked, Boolean isMark) {
        if (isMark = true) {
            System.out.println("Good Job! I have marked this task as done!");
        } else {
            System.out.println("Aw man! I have marked this task as undone. We go again!");
        }
        System.out.println(taskMarked);
    }


    /**
     * Displays an error message to the user in case of an exception or incorrect input.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays the list of tasks in a formatted manner, along with the total task count.
     *
     * @param taskList The TaskList containing tasks to be displayed.
     */
    public void showList(TaskList taskList) {
        for (int i = 1; i < taskList.getLength() + 1; i++) {
            System.out.println(i + ". " + taskList.getTaskInString(i - 1));
        }
        taskList.printTaskListInString();
    }

    /**
     * Displays the list of matching query tasks in a formatted manner,
     * along with the total matching task count.
     *
     * @param taskList The TaskList containing tasks to be displayed.
     */
    public void showQueryList(TaskList taskList) {
        for (int i = 1; i < taskList.getLength() + 1; i++) {
            System.out.println(i + ". " + taskList.getTaskInString(i - 1));
        }
        System.out.println(String.format("%d task(s) match your query",
                taskList.getLength()));
    }
}
