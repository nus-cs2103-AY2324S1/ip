package duke;

import task.Task;

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

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println(logo);
        System.out.println("Hello! I am Dukey.\n"
                + "What can I do for you?");
    }

    public void showLine() {
        System.out.println("_______");
    }


    public void showMarkTaskMessage(String taskMarked, Boolean isMark) {
        if (isMark = true) {
            System.out.println("Good Job! I have marked this task as done!");
        } else {
            System.out.println("Aw man! I have marked this task as undone. We go again!");
        }
        System.out.println(taskMarked);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showList(TaskList taskList) {
        for (int i = 1; i < taskList.getLength() + 1; i++) {
            System.out.println(i + ". " + taskList.getTaskInString(i - 1));
        }
        taskList.printTaskListInString();
    }

    public void showQueryList(TaskList taskList) {
        for (int i = 1; i < taskList.getLength() + 1; i++) {
            System.out.println(i + ". " + taskList.getTaskInString(i - 1));
        }
        System.out.println(String.format("%d task(s) match your query",
                taskList.getLength()));
    }

}
