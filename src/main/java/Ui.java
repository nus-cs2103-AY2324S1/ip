import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return this.scanner.nextLine();
    }
    public void styleMessage(String message) {
        System.out.print("   ____________________________________________________________\n");
        System.out.print(message);
        System.out.print("   ____________________________________________________________\n");
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
            System.out.println("   ____________________________________________________________\n" +
                    "   Here are the quests in thy list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + "." + tasks.get(i));
            }
            System.out.println("   ____________________________________________________________");
        }
    }

    public void showUnknownCommandMessage() {
        styleMessage("I'm sorry, but I don't know what that means :-(");
    }

    public void showLoadingErrorMessage() {
        styleMessage("Error loading tasks from file.");
    }
}
