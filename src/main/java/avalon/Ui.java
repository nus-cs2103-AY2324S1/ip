package avalon;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return this.scanner.nextLine();
    }

    public void linePrint() {
        System.out.print("   _________________________________________" +
                "________________________________________\n");

    }

    public void styleMessage(String message) {
        linePrint();
        System.out.print(message);
        linePrint();
    }

    public void greetMessage() {
        styleMessage("    Hello! I'm Arthur Pendragon.\n    What can I do for you?\n");
    }

    public void byeMessage() {
        styleMessage("    Farewell. We will meet again!\n");
    }

    public void showTasksList(TaskList tasks) {
        if (tasks.isEmpty()) {
            styleMessage("    You haven't added anything, my sire.\n");
        } else {
            linePrint();
            System.out.println("   Here are the quests in thy list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
            linePrint();
        }
    }

    public void showMarkMessage(TaskList tasks, int taskIndex) {
        linePrint();
        System.out.println("   Very well. I have marked this task as accomplished:\n  " + "  " +
                tasks.get(taskIndex).getStatusIcon() + " " +
                tasks.get(taskIndex).description);
        linePrint();
    }

    public void showUnmarkMessage(TaskList tasks, int taskIndex) {
        linePrint();
        System.out.println("   By the heavens! I have declared this task as yet to be completed:\n  " +
                "  " + tasks.get(taskIndex).getStatusIcon() +
                " " + tasks.get(taskIndex).description);
        linePrint();
    }

    public void showAddTaskMessage(TaskList tasks) {
        linePrint();
        System.out.println("   Understood. I have included this quest:\n  " +
                "  " + tasks.get(tasks.size() - 1));
        System.out.println("   Now you have " + tasks.size() + " task(s) in the list.");
        linePrint();
    }

    public void showDeleteTaskMessage(TaskList tasks, Task deletedTask) {
        linePrint();
        System.out.println("   Noted. I've removed this quest:");
        System.out.println("    " + deletedTask);
        System.out.println("   Now you have " + tasks.size() + " task(s) in the list.");
        linePrint();
    }
}
