import java.util.ArrayList;
import java.util.Scanner;

public class GBot {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello I'm GBot!");
        System.out.println("What can I do for you?\n");

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String message = scanner.nextLine();
            String prefix = message.split(" ")[0];
            switch (prefix) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    int len = list.size();
                    for (int i = 0; i < len; i++) {
                        System.out.println((i + 1) + ". " + list.get(i).toString());
                    }
                    break;
                case "mark":
                    System.out.println("Nice, I've marked this task as done:");
                    int taskNum = Integer.parseInt(message.split(" ")[1]);
                    Task curr = list.get(taskNum - 1);
                    curr.markAsDone();
                    System.out.println(curr);
                    break;
                case "unmark":
                    System.out.println("Nice, I've marked this task as not done yet:");
                    taskNum = Integer.parseInt(message.split(" ")[1]);
                    curr = list.get(taskNum - 1);
                    curr.markAsUndone();
                    System.out.println(curr);
                    break;
                case "todo":
                    String tStr = message.substring(prefix.length() + 1);
                    Todo newTodo = new Todo(tStr);
                    list.add(newTodo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTodo);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                case "deadline":
                    String dStr = message.substring(prefix.length() + 1);
                    String dDesc = dStr.split(" /by ")[0];
                    String by = dStr.split(" /by ")[1];
                    Deadline newDeadline = new Deadline(dDesc, by);
                    list.add(newDeadline);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newDeadline);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
                case "event":
                    String eStr = message.substring(prefix.length() + 1);
                    String eDesc = eStr.split(" /from ")[0];
                    String from = eStr.split(" /from ")[1].split(" /to ")[0];
                    String to = eStr.split(" /from ")[1].split(" /to ")[1];
                    Event newEvent = new Event(eDesc, from, to);
                    list.add(newEvent);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(newEvent);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    break;
            }
        }
    }
}

