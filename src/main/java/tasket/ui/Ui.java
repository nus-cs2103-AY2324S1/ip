package tasket.ui;

import java.util.Scanner;

public class Ui {

    private final String TEXT_SEPARATION = "\t____________________________________________________________";
    private final String TEXT_GREETING = "\tHello, I'm Tasket\n\tWhat can I do for you?";
    private final String TEXT_GOODBYE = "\tBye. Hope to see you again soon!";
    private final String TEXT_ADDED = "\tGot it. We have added this task:";
    private final String TEXT_LIST = "\tHere are the tasks in your list:";
    private final String TEXT_TASK_LENGTH = "\tNow you have %d tasks in the list";
    private final String TEXT_MARKED_TASK = "\tNice! I've marked this task as done:";
    private final String TEXT_UNMARKED_TASK = "\tOk, I've marked this task as undone:";
    private final String TEXT_DELETED_TASK = "\tNoted, I've deleted this task:";
    private final String TEXT_ERROR = "\tOOPS!!! ";

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return this.sc.nextLine().trim();
    }

    public void showWelcome() {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_GREETING);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    public void showGoodBye() {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_GOODBYE);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    public void showAddedTask(String task, int len) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_ADDED);
        System.out.println("\t" + task);
        System.out.printf(TEXT_TASK_LENGTH + "\n", len);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    public void showTaskList(String[] tasks) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_LIST);
        for (int i = 1; i <= tasks.length; i++) {
            System.out.printf("\t%d. %s\n", i, tasks[i - 1]);
        }
        System.out.println(TEXT_SEPARATION + "\n");
    }

    public void showMarkedTask(String task) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_MARKED_TASK);
        System.out.println("\t" + task);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    public void showUnmarkedTask(String task) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_UNMARKED_TASK);
        System.out.println("\t" + task);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    public void showDeletedTask(String task, int len) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_DELETED_TASK);
        System.out.println("\t" + task);
        System.out.printf(TEXT_TASK_LENGTH + "\n", len);
        System.out.println(TEXT_SEPARATION + "\n");
    }

    public void showError(String errorMessage) {
        System.out.println(TEXT_SEPARATION);
        System.out.println(TEXT_ERROR + errorMessage);
        System.out.println(TEXT_SEPARATION + "\n");
    }
}
