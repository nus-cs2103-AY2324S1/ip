import java.awt.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String chatbotName = "Bleep";
        String header = "____________________________________________________________\n" +
                "Hello! I'm " + chatbotName +
                " \nWhat can I do for you?";

        //ArrayList<String> userEntries = new ArrayList<>();
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(header);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("____________________________________________________________\n");
            String userInput = scanner.nextLine();
            System.out.println("____________________________________________________________\n");
            if (userInput.equals("bye")){
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here are your entries:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
                }
            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markDone();
                    System.out.println("Nice! I've marked this task as done:\n\t" +
                            taskList.get(taskIndex).toString());
                } else {
                    System.out.println("Invalid task index.");
                }
            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:\n\t" +
                            taskList.get(taskIndex).toString());
                } else {
                    System.out.println("Invalid task index.");
                }
            }else {
                Task task = new Task(userInput);
                taskList.add(task);
                System.out.println("\t added: " + userInput);
                //System.out.println();
            }
        }
    }
}
