import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }
    public static void printList(ArrayList<String> arr) {
        int index = 0;
        for (String str: arr) {
            System.out.println("    " + (++index) + ". " + str);
        }
    }
    public static void addTask(ArrayList<String> arr, String task) {
        System.out.println("    added: " + task);
        arr.add(task);
    }
    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        String horizontalLine = "    _________________________________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println("    Hello! I'm Thinh's chatbot\n    What can I do for you?");
        System.out.println(horizontalLine);
        Scanner in = new Scanner(System.in);
        String inputStr;
        do {
            inputStr = in.nextLine();
            System.out.println(horizontalLine);
            switch(inputStr) {
                case "bye":
                    bye();
                    break;
                case "list":
                    printList(arr);
                    break;
                default:
                    addTask(arr, inputStr);
            }
            System.out.println(horizontalLine);
        } while (!inputStr.equals("bye"));
    }
}
