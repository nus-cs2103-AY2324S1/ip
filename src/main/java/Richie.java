import java.util.ArrayList;
import java.util.Scanner;
public class Richie {
    static private String CHATBOT_NAME = "Richie";
    static private ArrayList<String> itemArray = new ArrayList<>();
    public static void addItem(String item) {
        itemArray.add(item);
    }
    public static String listItems() {
        int length = itemArray.size();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (i + 1) + "." + itemArray.get(i) + "\n";
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        String message = input.nextLine();
        while (!message.equals("bye")) {
            if (message.equals("list")) {
                System.out.println(listItems());
                message = input.nextLine();
                continue;
            }
            addItem(message);
            System.out.println("added : " + message);
            message = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
