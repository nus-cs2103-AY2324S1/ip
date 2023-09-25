package duke;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static String LINE = "______________________\n";
    private static String NAME = "Remi";
    private static String INTRO = "Greetings. I am " + NAME + ".\n" + "What can I do for you?\n";
    private static String DONE = "It is accomplished.\n";
    private static String UNDONE = "It is unfinished.\n";
    private static String ADD_TASK = "Another task? Very well.\n";
    private static String REMOVE_TASK = "It is gone with the wind.\n";
    private static String FAILURE = "A critical failure occurred. Farewell.";
    private static String LOAD_NEW = "Here's a blank one instead.";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public String startup() {
        return LINE + INTRO + LINE;
    }

    public String failure() {
        return FAILURE + "\n";
    }

    public String showLoadingError() {
        return LOAD_NEW + "\n";
    }

    public String exit() {
        return LINE + "Farewell.\n" + LINE;
    }

    public String markText(Task task) {
        return LINE + DONE + task.toString() + "\n" + LINE;
    }

    public String unmarkText(Task task) {
        return LINE + UNDONE + task.toString() + "\n" + LINE;
    }

    public String taskText(Task task, int len) {
        return LINE + ADD_TASK + task.toString() + "\n" + "There are now "
                + len + " task(s) in your backlog.\n" + LINE;
    }

    public String removeText(Task task, int len) {
        return LINE + REMOVE_TASK + task.toString() + "\n" + "There are now "
                + len + " task(s) in your backlog.\n" + LINE;
    }

    public String errorMsg(String err) {
        return LINE + err + "\n" + LINE;
    }

    public String getList(TaskList list) {
        if (list.getLength() != 0) {
            String res = "Here are your tasks: \n";
            for (int i = 0; i < list.getLength(); i++) {
                String prev = res;
                int count = i + 1;
                res = prev + count + "." + list.getTask(i).toString() + "\n";
            }
            return LINE + res + LINE;
        }
        return LINE + "You have no tasks for the day. Congratulations?" + "\n" + LINE;
    }
}
