package duke.main;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);
    // Constants
    private String DIVIDER = "    ____________________________________________________________\n";
    private String LOGO = "    (• >       (• >       (• >       (• >       (• >       (• >\n"
            +  "    /))        /))        /))        /))        /))        /))\n"
            +  "     ``         ``         ``         ``         ``         ``\n";
    private String HELP = "    IMPORTANT NOTES:\n"
            + "        Todo: todo <task>\n"
            + "        Deadline: deadline <deadline> /by <duedate>\n"
            + "        Event: event <event> /from <start> /to <end>\n"
            + "        Datetime format: \"dd/MM/yyyy HH:mm\"\n";
    private String GREET = "    Hello! I'm Birdy\n"
            + "    chirp chirp! How can I help?\n";
    private String PARTING = "    chirp! See you around!\n";

    public String readCommand() {
        return sc.nextLine();
    }
    public void showError(String error) {
        String output = DIVIDER + "    " + error + DIVIDER;
        System.out.println(output);
    }
    public void showLoadingError() {
        String output = DIVIDER + "    chirp! cannot load tasks from file\n" + DIVIDER;
        System.out.println(output);
    }

    public void showParting() {
        String output = DIVIDER + PARTING + DIVIDER;
        System.out.println(output);
    }

    public void showWelcome() {
        String output = LOGO + DIVIDER + HELP + DIVIDER + GREET;
        System.out.println(output);
    }

    public void showHelp() {
        String output = DIVIDER + HELP + DIVIDER;
        System.out.println(output);
    }
    public void showLine() {
        System.out.println(DIVIDER);
    }
    public void showList(List<Task> tasks) {
        System.out.println(DIVIDER);
        tasks.forEach(task -> System.out.println("    " + task.toString()));
        System.out.println(DIVIDER);
    }
    public void showAddSuccess(String task, int size) {
        System.out.println(DIVIDER + "    chirp! I've added this task:\n"
                + String.format("    %s\n", task)
                + String.format("    Now you have %d tasks in the list\n", size)
                + DIVIDER);
    }
    public void showDeleteSuccess(String task) {
        System.out.println(DIVIDER + "    chirp! chirp! duke.task.Task right out the window!\n" + DIVIDER);
    }
    public void showMark(Task task) {
        System.out.println(DIVIDER + "    chirp! I've mark this task as done:\n" +  String.format("    %s\n", task.toString()) + DIVIDER);
    }
    public void showUnmark(Task task) {
        System.out.println(DIVIDER + "    chirp! I've mark this task as not done yet:\n" +  String.format("    %s\n", task.toString()) + DIVIDER);
    }

    /**
     * Display tasks in the filtered list.
     *
     * @param filteredTasks The filtered list of tasks
     */
    public void showMatch(List<Task> filteredTasks) {
        System.out.println(DIVIDER);
        System.out.println("    tweet! Here are the matching tasks in your list:");
        for (int i = 0; i < filteredTasks.size(); i ++) {
            System.out.printf("    %d. %s\n", i + 1, filteredTasks.get(i).toString());
        }
        System.out.println(DIVIDER);
    }
}
