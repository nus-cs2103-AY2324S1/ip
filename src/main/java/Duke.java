import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> taskList = new ArrayList<>();
    static int counter = 0;

    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static String formatTask(Task task) {
        return "Got it. I've added this task:\n  " +
                task + "\nNow you have " +
                taskList.size() + " tasks in the list.";
    }
    private static void addTodo(String description) {
        Task task = new Task(description);
        taskList.add(task);
        counter++;
        System.out.println(formatTask(task));
    }

    private static void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        taskList.add(task);
        counter++;
        System.out.println(formatTask(task));
    }

    private static void addEvent(String description, String from, String by) {
        Task task = new Event(description, from, by);
        taskList.add(task);
        counter++;
        System.out.println(formatTask(task));
    }

    private static void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ". " + taskList.get(i - 1).toString());
        }
    }

    private static void markDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.setTaskState(true);
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    private static void markUndone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.setTaskState(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
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
                String[] words = userCommand.split(" ", 2);
                switch (words[0]) {
                    case "mark": {
                        int taskNumber = Integer.parseInt(words[1]);
                        markDone(taskNumber);
                        break;
                    }
                    case "unmark": {
                        int taskNumber = Integer.parseInt(words[1]);
                        markUndone(taskNumber);
                        break;
                    }
                    case "todo":
                        addTodo(words[1]);
                        break;
                    case "deadline":
                        String[] deadlineTask = words[1].split(" /by ");
                        addDeadline(deadlineTask[0], deadlineTask[1]);
                        break;
                    case "event":
                        String[] eventTask = words[1].split(" /from ");
                        String[] duration = eventTask[1].split(" /to ");
                        addEvent(eventTask[0], duration[0], duration[1]);
                        break;
                    default:
                        System.out.println("Error: no keywords detected");
                        break;
                }
            }
            userCommand = sc.nextLine();
        }
        exit();
    }
}
