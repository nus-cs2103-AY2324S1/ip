import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class MattBot {
    private static final String NAME = "MattBot";
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<Task>();
        String userInput;
        while (true) {
            // Take user input in, and process user input
            userInput = sc.nextLine();
            String command = userInput.split(" ",2)[0];
            if (command.equals("bye")) {
                printTop();
                System.out.println("Bye, Hope to see you soon!");
                printBottom();
                break;
            } else if (command.equals("list")) {
                printTop();
                for (int i = 0; i < taskList.size(); i++) {
                    Task t = taskList.get(i);
                    System.out.println(String.format("%d. %s", i+1, t));
                }
                printBottom();
            } else if (command.equals("mark")) {
                printTop();
                int taskId = Integer.parseInt(userInput.split(" ",2)[1]) - 1;
                Task t = taskList.get(taskId);
                t.markAsDone();
                taskList.set(taskId, t);
                System.out.println("Great job! You have completed the task " + t.showName());
                printBottom();
            } else if (command.equals("unmark")) {
                printTop();
                int taskId = Integer.parseInt(userInput.split(" ",2)[1]) - 1;
                Task t = taskList.get(taskId);
                t.markAsNotDone();
                taskList.set(taskId, t);
                System.out.println("Oh no, you have uncompleted " + t.showName());
                printBottom();
            } else {
                int len = userInput.split(" ",2).length;
                if (len <= 1) {
                    printTop();
                    System.out.println("Oh no, your input is bad.");
                    System.out.println(command + " requires an argument.");
                    printBottom();
                    continue;
                }
                printTop();
                String arguments = userInput.split(" ",2)[1];
                Task t;
                if (command.equals("todo")) {
                    t = new Todo(arguments);
                    taskList.add(t);
                    System.out.println("I've added this to your tasks: ");
                    System.out.println(t);
                } else if (command.equals("deadline")) {
                    String name = arguments.split(" /by ",2)[0];
                    String dueDate = arguments.split(" /by ",2)[1];
                    t = new Deadline(name, dueDate);
                    taskList.add(t);
                    System.out.println("I've added this to your tasks: ");
                    System.out.println(t);
                } else if (command.equals("event")) {
                    String name = arguments.split(" /from ",2)[0];
                    String dates = arguments.split(" /from ",2)[1];
                    String startDate = dates.split(" /to ")[0];
                    String endDate = dates.split(" /to ",2)[1];
                    t = new Event(name, startDate, endDate);
                    taskList.add(t);
                    System.out.println("I've added this to your tasks: ");
                    System.out.println(t);
                } else if (command.equals("delete")) {
                    if (taskList.size() == 0 || taskList.size() < Integer.parseInt(arguments)) {
                        System.out.println("Oops, you're deleting a task that doesn't exist.");
                        continue;
                    }
                    t = taskList.get(Integer.parseInt(arguments)-1);
                    System.out.println("I have removed this task:");
                    System.out.println(t);
                    taskList.remove(Integer.parseInt(arguments)-1);
                } else {
                    System.out.println("I didn't quite understand your input.");
                    continue;
                }
                printBottom();
            }
        }
    }
    public static void printTop() {
        System.out.println("____________________________________________________________");
    }
    public static void printBottom() {
        System.out.println("____________________________________________________________");
    }
}
