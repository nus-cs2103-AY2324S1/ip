package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    private final String line = "------------------------------------";
    private final String chatbot = "chuababyy";
    private final String commands =
            line + "\n"
            + "List of commands\n"
            + "1. todo [description]\n"
            + "2. deadline [description] /by [deadline in DD-MM-YYYY TIME]\n"
            + "3. event [description] /from [start date in DD-MM-YYYY TIME] /to [end date in DD-MM-YYYY TIME]\n"
            + "4. mark [item_number]\n"
            + "5. unmark [item_number]\n"
            + "6. delete [item_number]\n"
            + "7. list\n"
            + "8. bye\n"
            + line ;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void line() {
        System.out.println(line);
    }

    public void showWelcome() {
        line();
        System.out.println("Hello! I'm " + chatbot);
        System.out.println("What can I do for you?");
        line();
    }

    public void showEmptyMessage() {
        line();
        System.out.println("Item to be added cannot be empty");
        line();
    }

    public void showUnknownMessage() {
        line();
        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
        line();
    }

    public void showInvalidMessage() {
        System.out.println("Invalid structure. Please follow the valid commands below.\n" + commands);
    }

    public void showNoItemMessage() {
        line();
        System.out.println("No such item exists");
        line();
    }

    public void showByeMessage() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public void showList(TaskList fullList) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(fullList.toString());
    }

    public void showAddMessage(Task task, int size) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(line);
    };

    public void showDeleteMessage(Task task, int size) {
        line();
        System.out.println("Noted. I've removed this task:\n" +
                task.toString() + "\n" +
                "Now you have " + size + " tasks in the list.");
        line();
    }

    public void showMarkMessage(Task task) {
        line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        line();
    }

    public void showUnmarkMessage(Task task) {
        line();
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(task.toString());
        line();
    }

    public void showInvalidDate() {
        line();
        System.out.println("Invalid date format. Start date is after end date");
        line();
    }
}
