import java.util.ArrayList;
import java.util.Scanner;
public class Richie {
    static private String CHATBOT_NAME = "Richie";
    static private ArrayList<Task> itemArray = new ArrayList<>();
    public static void addItem(Task item) {
        itemArray.add(item);
    }
    public static String listItems() {
        int length = itemArray.size();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (i + 1) + "." + itemArray.get(i).toString() + "\n";
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        String message = input.nextLine();
        while (!message.equals("bye")) {
            String[] stringArray = message.split(" ", 2);
            if (message.equals("list")) {
                System.out.println(listItems());
                message = input.nextLine();
                continue;
            } else if (stringArray[0].equals("mark")) {
                int taskIndex = Integer.parseInt(stringArray[1]);
                Task task = itemArray.get(taskIndex - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n " + task);
                message = input.nextLine();
                continue;
            } else if (stringArray[0].equals("deadline")) {
                String[] stringArray2 = stringArray[1].split(" /by ", 2);
                Deadline deadline = new Deadline(stringArray2[0], stringArray2[1]);
                addItem(deadline);
                System.out.println("Got it. I've added this task:\n" + deadline.toString()
                        + "\nNow you have " + itemArray.size() + " tasks in the list.");
                message = input.nextLine();
                continue;
            } else if (stringArray[0].equals("todo")) {
                Todo todo = new Todo(stringArray[1]);
                addItem(todo);
                System.out.println("Got it. I've added this task:\n" + todo.toString()
                        + "\nNow you have " + itemArray.size() + " tasks in the list.");
                message = input.nextLine();
                continue;
            } else if (stringArray[0].equals("event")) {
                String[] stringArray2 = stringArray[1].split(" /from ", 2);
                String[] stringArray3 = stringArray2[1].split(" /to ", 2);
                Event event = new Event(stringArray2[0], stringArray3[0], stringArray3[1]);
                addItem(event);
                System.out.println("Got it. I've added this task:\n" + event.toString()
                        + "\nNow you have " + itemArray.size() + " tasks in the list.");
                message = input.nextLine();
                continue;
            }

        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
