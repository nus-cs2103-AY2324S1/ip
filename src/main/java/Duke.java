import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static String name = "Dook";
    public static ArrayList<String> taskList = new ArrayList<>();
    public static void main(String[] args) {

        GreetUser();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (Objects.equals(input, "bye")) {
                BidFarewell();
                break;
            } else if (Objects.equals(input, "list")) {
                DisplayList();
            } else {
                PrintMessage("added: " + input);
                AddToTaskList(input);
            }
        }
    }

    private static void AddToTaskList(String str) {
        taskList.add(str);
    }

    private static void DisplayList() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        PrintMessage(result);
    }


    private static void GreetUser() {
        PrintMessage(String.format("%s here.\nWhat can I do for you?", name));
    }

    public static void PrintMessage(String msg) {
        PrintDivider();
        System.out.println(msg);
        PrintDivider();
    }
    public static void PrintDivider() {
        System.out.println("_______________________________________");
    }
    private static void BidFarewell() {
        PrintMessage("Goodbye.");
    }
}
