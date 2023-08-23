import Tasks.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

enum taskTypes {
    todo,
    deadline,
    event
}

public class Duke {
    static final String logo = "\n   _____ _    _          _____   _____ _____ _______ \n" +
            "  / ____| |  | |   /\\   |  __ \\ / ____|  __ \\__   __|\n" +
            " | |    | |__| |  /  \\  | |  | | |  __| |__) | | |   \n" +
            " | |    |  __  | / /\\ \\ | |  | | | |_ |  ___/  | |   \n" +
            " | |____| |  | |/ ____ \\| |__| | |__| | |      | |   \n" +
            "  \\_____|_|  |_/_/    \\_\\_____/ \\_____|_|      |_|   \n";
    static final String horizontal = "--------------------------------------------------------" +
            "-----------";
    static List<Task> taskList = new ArrayList<Task>();
    static int taskCounter = 0;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println(horizontal + logo + horizontal);
        System.out.println("ChadGPT: Welcome to ChadGPT, What would you like to do today?\n" + horizontal);
        System.out.print("User: ");
        while (!sc.hasNext("bye")) {
            String nextLine = sc.nextLine();
            passCommand(nextLine);
        }

        sc.close();

        System.out.print("ChadGPT: Bye. Hope to see you again soon!\n" + horizontal);
    }

    private static void passCommand(String nextLine) {
        String[] strArray = nextLine.split(" ");
        String command = strArray[0];
        if (command.equals("list")) {
            System.out.println("ChadGPT: Here are your tasks: ");
            int counter = 0;
            for (Task t : taskList) {
                System.out.print("    " + ++counter + ". ");
                t.printStatus();
            }
        } else if (command.equals("mark")) {
            int index = Integer.parseInt(strArray[1]) - 1;
            taskList.get(index).completeTask();
            System.out.println("ChadGPT: Nice! I'll mark the task as done: ");
            taskList.get(index).printStatus();
        } else if (command.equals("unmark")) {
            int index = Integer.parseInt(strArray[1]) - 1;
            taskList.get(index).undo();
            System.out.println("ChadGPT: No problem, I'll mark this task as not done yet: ");
            taskList.get(index).printStatus();
        } else {
            Task newTask = createTask(nextLine, strArray);
            System.out.println("ChadGPT: added task '" + newTask.toString() + "'");
            System.out.println("You now have " + taskCounter + " tasks in the list.");
            taskList.add(newTask);
        }
        System.out.print(horizontal + "\nUser: ");
    }

   private static Task createTask(String nextLine, String[] strArr) {
        String[] delimited = nextLine.split("/");
        switch(strArr[0].toLowerCase()) {
            case "todo":
                taskCounter++;
                return new ToDo(nextLine.substring(5));
            case "deadline":
                taskCounter++;
                return new Deadline(delimited[0].substring(9, delimited[0].length() - 1),
                        delimited[1].substring(3, delimited[1].length()));
            case "event":
                taskCounter++;
                return new Event(delimited[0].substring(6, delimited[0].length() - 1),
                        delimited[1].substring(5, delimited[1].length() - 1),
                        delimited[2].substring(3, delimited[2].length()));
        }
        throw new IllegalArgumentException("Invalid task type");
   }
}
