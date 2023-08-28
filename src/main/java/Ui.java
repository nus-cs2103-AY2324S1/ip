import java.util.List;
import java.util.Scanner;

public class Ui {

    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | | / _ \\\n"
                + "| |_| | |_| |  |_   __/\n"
                + "|____/ \\__,_|___|\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm Duke!\nWhat can I do for you?\n");
    }

    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTasksOnDate(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks.\n");
        } else {
            System.out.println(taskList.size() +" task: ");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    public void printAddingTask(Task task, List<Task> taskList) {
        String message = String.format("Got it. I've added this task:\n  "
                + task
                + "\nNow you have %s tasks in the list\n", taskList.size());
        System.out.println(message);
    }

    public void printAllTasks(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks.\n");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task);
            }
            System.out.println("");
        }
    }

    public void printDeleteTask(Task task) {
        System.out.println("Noted. I've removed this task:" + "\n  " + task + "\n");
    }

    public void printMarkTask(Task task, boolean beforeMarking) {
        if (beforeMarking) {
            System.out.println("Error! Task already marked!\n");
        } else {
            System.out.println("Nice! I've marked this task as done:\n  " + task + "\n");
        }
    }

    public void printUnMarkTask(Task task, boolean beforeMarking) {
        if (beforeMarking) {
            System.out.println("I've unmarked this task:\n  " + task + "\n");
        } else {
            System.out.println("Error! Task already unmarked\n");
        }
    }
}
