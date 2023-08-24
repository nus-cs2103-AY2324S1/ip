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
            }
            Task task = new Task(message);
            addItem(task);
            System.out.println("added : " + message);
            message = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
