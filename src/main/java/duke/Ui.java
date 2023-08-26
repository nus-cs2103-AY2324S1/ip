package duke;

import task.Task;
import java.util.ArrayList;

public class Ui {
    private final String CHATBOTNAME = "notDuke";
    private final String INTRO = "Hello! I'm " + this.CHATBOTNAME + "\n"
            + "What can I do for you?";
    private final String EXITMESSAGE = "Bye. Hope to see you again soon!";
    public static String line = "--------------------------------------------------------------------------------------------";
    public Ui() {
        System.out.printf("%s\n%s\n%s\n", Ui.line, INTRO, Ui.line);
    }

    public void showExceptionError(Exception e) {
        System.out.println(e.getMessage());
    }

    public void emptyFilePath() {
        System.out.println("File path is empty!");
    }

    public void createLine() {
        System.out.println(Ui.line);
    }

    public void exit() {
        System.out.printf("%s\n%s\n", EXITMESSAGE, Ui.line);
    }

    public void listTask(TaskList taskList) {
        ArrayList<Task> arrList = taskList.getArrList();
        int counter = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task task : arrList) {
            counter ++;
            System.out.printf("%d.%s\n", counter, task.toString());
        }
    }

    public void displayMarkTask(TaskList tasks, int choice) {
        System.out.printf("Nice! I've marked this task as done:\n" +
                "  %s\n", tasks.taskToString(choice));
    }

    public void displayUnmarkTask(TaskList tasks, int choice) {
        System.out.printf("OK, I've marked this task as not done yet:\n" +
                "  %s\n", tasks.taskToString(choice));
    }

    public void displayDeleteTask(Task removedTask, TaskList tasks) {
        System.out.printf("Noted. I've removed this task:\n" +
                        "  %s\n" +
                        "Now you have %d tasks in the list.\n"
                , removedTask.toString(), tasks.size());
    }

    public void displayAddTask(Task addedTask, TaskList tasks) {
        System.out.printf("Got it. I've added this task:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list.\n", addedTask.toString(), tasks.size());
    }
}
