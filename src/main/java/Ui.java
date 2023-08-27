import java.util.List;

public class Ui {
    private final String LOGO = ",------.,--.              ,--.  \n"
            + "|  .---\'|  |,-.,--.,--. ,-|  |  \n"
            + "|  `--, |     /|  ||  |' .-. |   \n"
            + "|  `---.|  \\\\  \\\\  ''  '\\\\ `-\'   \n"
            + "`------'`--'`--'`----'  `---' \n";
    private final String LINE = "-".repeat(60);

    public Ui () {
    }

    public void printWelcomeMessage() {
        System.out.println(LINE);
        System.out.println("Hello! I'm \n");
        System.out.println(LOGO);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void printFarewellMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public void printAddedTaskConfirmation(Task task, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printDeletedTaskConfirmation(Task task, TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public void printErrorMessage(DukeException e) {
        System.out.println(LINE);
        System.out.println("OOPS!!! " + e.getMessage());
        System.out.println(LINE);
    }

    public void printList(List<Task> tasks) {
        System.out.println(LINE);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(LINE);
    }

    
}
