import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static int count = 0;
    private static ArrayList<Task> taskList = new ArrayList<>(); //universal task list in memory

    public static void main(String[] args) {
        String logo = " __          _        \n"
                + "| |     _   _| | _____ \n"
                + "| |    | | | | / / _ \\\n"
                + "| |___ | |_| |   <  __/\n"
                + "|____/ \\__,__|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                int id = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task : taskList) {
                    System.out.println(String.valueOf(id) + ". " + task);
                    id++;
                }
                continue;
            }

            //for identifying first word keyword: mark/ unmark, todo, deadline, event
            String[] parts = input.split(" ");
            String keyword = parts[0]; //first word is command
            String restOfSentence = input.substring(keyword.length()).trim();
            String[] descr = restOfSentence.split("/"); //you get 0: taskName, 1: deadline/start, 2: end
            String taskName = descr[0];


            if (keyword.equals("todo")) {
                //new todo
                Task newTask = new ToDo(taskName);
                taskList.add(newTask);
                count++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + count + " tasks in the list.");
            } else if (keyword.equals("deadline")) {
                //new deadline
                if (descr.length > 1) {
                    String st = descr[1];
                Task newTask = new Deadline(taskName, st);
                taskList.add(newTask);
                count++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else if (keyword.equals("event")) {
                //new event
                if (descr.length > 1) {
                    String st = descr[1];
                    String end = descr[2];
                Task newTask = new Event(taskName, st, end);
                taskList.add(newTask);
                count++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + count + " tasks in the list.");
                }
            } else {
                int taskID = Integer.parseInt(restOfSentence) - 1;
                Task taskChanged = taskList.get(taskID);
                if (keyword.equals("mark")) {
                    taskChanged.markDone();
                    System.out.println("Nice! I've marked this task as done:");
                } else {
                    taskChanged.markUndone();
                    System.out.println("OK, I've marked this task as not done yet:");
                }
                System.out.println(taskChanged);
            }
        }
        scanner.close();
    }
}
