import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<String> taskList = new ArrayList<>();
    static int counter = 0;

    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void addTask(String userCommand) {
        taskList.add(userCommand);
        counter++;
        System.out.println("added: " + userCommand);
    }

    private static void listTask() {
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + ". " + taskList.get(i - 1));
        }
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
                addTask(userCommand);
            }
            userCommand = sc.nextLine();
        }
        exit();
    }
}
