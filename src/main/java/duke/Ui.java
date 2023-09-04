package duke;

import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "──────────────────────────────"
            + "────────────────────────────────────────────";
    private static final String LOGO = " _____   __                 _____ _           _   _           _  ___\n"
            + "|  _\\ \\ / /                /  __ \\ |         | | | |         | ||_  |\n"
            + "| |  \\ V /___  _   _ _ __  | /  \\/ |__   __ _| |_| |__   ___ | |_ | |\n"
            + "| |   \\ // _ \\| | | | '__| | |   | '_ \\ / _` | __| '_ \\ / _ \\| __|| |\n"
            + "| |   | | (_) | |_| | |    | \\__/\\ | | | (_| | |_| |_) | (_) | |_ | |\n"
            + "| |_  \\_/\\___/ \\__,_|_|     \\____/_| |_|\\__,_|\\__|_.__/ \\___/ \\__|| |\n"
            + "|___|                                                           |___|\n";
    private static final String greetPhrase = LINE
            + "\nHello! I'm\n"
            + LOGO
            + "What can I do for you?\n"
            + LINE + "\n";

    private static final String sendOffPhrase = LINE
            + "\nBye. Hope to see you again soon!\n"
            + LINE + "\n";

    public void greet() {
        System.out.println(greetPhrase);
    }

    public void sendOff() {
        System.out.println(sendOffPhrase);
    }

    public void printList(TaskList taskList) {
        System.out.println(LINE);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(Integer.toString(i + 1)
                    + ". "
                    + taskList.get(i));
        }
        System.out.println(LINE + "\n");
    }

    public void printTaskAdded(Task taskAdded, int listSize) {
        System.out.println(LINE
                + "\nGot it. I've added this task:\n"
                + taskAdded
                + "\nNow you have " + listSize + " tasks in the list.\n"
                + LINE
                + "\n");
    }
    
    public void printTaskMarked(Task task) {
        System.out.println(LINE
                + "\nNice! I've marked this task as done:\n"
                + task + "\n"
                + LINE + "\n");
    }
    
    public void printTaskUnmarked(Task task) {
        System.out.println(LINE + "\n"
                + "OK, I've marked this task as not done yet:\n"
                + task + "\n"
                + LINE);
    }
    
    public void printTaskDeleted(Task task, int listSize) {
        System.out.println(LINE + "\n"
                + "Noted. I've removed this task:\n"
                + task
                + "\nNow you have " + listSize + " tasks in the list.\n"
                + LINE + "\n");
    }
    
    public void printException(String message) {
        System.out.println(LINE + "\n"
                + message + "\n"
                + LINE + "\n");
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
