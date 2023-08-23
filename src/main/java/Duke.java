import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;

public class Duke {

    public static void printWithTab(String s) {
        String tab = "     ";
        System.out.println(tab + s);
    }

    public static void main(String[] args) {
        String tab = "     ";
        String line = "____________________________________________________________";
        String logo = """
                _______         _
                |  ____|       (_)
                | |__ ___  _ __ _ _ __   ___
                |  __/ _ \\| '__| | '_ \\ / _ \\
                | | | (_) | |  | | | | |  __/
                |_|  \\___/|_|  |_|_| |_|\\___|
                              """;

        ArrayList<Task> taskList = new ArrayList<Task>();

        System.out.println(logo);
        printWithTab(line);
        printWithTab("Hello! I'm Forine");
        printWithTab("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            printWithTab(line);
            Commands command = Commands.parseCommand(input);
            if (command == null) {
                printWithTab("added: " + input);
                taskList.add(new Task(input));
            } else {
                switch (command) {
                    case LIST:
                        for (int i = 0; i < taskList.size(); i++) {
                            printWithTab(tab + (i + 1) + ". " + taskList.get(i));
                        }
                        break;
                    case MARK:
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (index >= taskList.size() || index < 0) {
                            printWithTab("Invalid index!");
                            break;
                        }
                        taskList.get(index).markAsDone();
                        printWithTab("Nice! I've marked this task as done:");
                        printWithTab(tab + taskList.get(index).toString());
                        break;
                    case UNMARK:
                        index = Integer.parseInt(input.split(" ")[1]) - 1;
                        if (index >= taskList.size()) {
                            printWithTab("Invalid index!");
                            break;
                        }
                        taskList.get(index).markAsUndone();
                        printWithTab("Ok! I've marked this task as not done yet:");
                        printWithTab(tab + taskList.get(index).toString());
                        break;
                    default:
                        printWithTab("added: " + input);
                        taskList.add(new Task(input));
                        break;
                }
            }
            printWithTab(line);
            input = sc.nextLine();
        }
        sc.close();
        printWithTab("Bye. Hope to see you again soon!");
        printWithTab(line);
    }
}
