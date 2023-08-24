import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> taskList = new ArrayList<>();
    static int counter = 0;

    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void addTask(String userCommand) {
        Task task = new Task(userCommand);
        taskList.add(task);
        counter++;
        System.out.println("added: " + userCommand);
    }

    private static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ". " + taskList.get(i - 1).getTask());
        }
    }

    private static void markDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.setTaskState(true);
        System.out.println("Nice! I've marked this task as done:\n" + task.getTask());
    }

    private static void markUndone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.setTaskState(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + task.getTask());
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        String userCommand = sc.nextLine();
        while(!userCommand.equals("bye")) {
            if(userCommand.equals("list")) {
                listTask();
            } else {
                String[] words = userCommand.split("\\s");
                if (words[0].equals("mark")) {
                    int taskNumber = Integer.parseInt(words[1]);
                    markDone(taskNumber);
                } else if (words[0].equals("unmark")) {
                    int taskNumber = Integer.parseInt(words[1]);
                    markUndone(taskNumber);
                } else {
                    addTask(userCommand);
                }
            }
            userCommand = sc.nextLine();
        }
        exit();
    }
}
