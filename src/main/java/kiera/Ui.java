package kiera;

import kiera.task.Task;
import kiera.tasktype.TaskType;

import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private final String LINE = "   ---------------------------------------------";

    public Ui() {
    }
    public void showLoadingError() {
        System.out.println("tasks cannot be loaded; starting with an empty list!");
    }
    public void showError(String e) {
        System.out.println(e);

    }
    public void showLine() {
        System.out.println(LINE);
    }
    public void showHello() {
        System.out.println(LINE
                + "\n"
                + "    "
                + "hi, it's kiera.\n"
                + "    "
                + "what do you need?\n"
                + LINE);
    }
    public void showBye() {
        System.out.println(LINE
                + "\n"
                + "    "
                + "muaks! <3\n"
                + LINE);
    }
    public String readCommand() {
        Scanner in = new Scanner(System.in);
        if (in.hasNext()) {
            return in.nextLine();
        }
        return "sorry, i didn't quite catch that.";
    }
    public void showAddNotice(Task task, TaskType t, int listSize) {
        String plural = listSize == 1 ? "task" : "tasks";
        System.out.println("    "
                + "alright, one "
                + t
                + " has been added: \n"
                + "      "
                + task
                + "\n    "
                + listSize
                + " more "
                + plural
                + " to go!");
    }
    public void showDeleteNotice(Task task, int listSize) {
        String plural = listSize == 1 ? "task" : "tasks";
        System.out.println("    "
                + "alright, this task is gone: \n"
                + "      "
                + task
                + "\n    "
                + listSize
                + " more "
                + plural
                + " left!");
    }
    public void showFilteredNotice(LocalDate d, TaskType t, String content, int listSize) {
        String plural = listSize == 1 ? " " : "s ";
        String verb = listSize == 1 ? "is " :  "are ";
        System.out.println("    "
                + "there "
                + verb
                + listSize
                + t
                + plural
                + "due on "
                + d
                + ":\n     "
                + content
                + "    "
        );
    }
    public void showList(String content) {
        System.out.println("    you need to get these done:\n" + content.stripTrailing());
    }
}
