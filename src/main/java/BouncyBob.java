import jdk.jfr.Event;

import java.util.Scanner;
import java.util.regex.*;
public class BouncyBob {
    private static final String TOP_BORDER = "================================================";
    private static final String MIDDLE_BORDER = "|                                              |";
    private static final String BOTTOM_BORDER = "================================================";

    public static void printTaskStatus(Task task) {
        String marking = " ";
        if (task.isDone()) {
            marking = "X";
        }
        System.out.println("[" + task.getSymbol() + "]" + "[" + marking + "]" + " " + task.getDescription());
    }

    public static void printBye() {
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("|        BouncyBob: Bye! Bounce back soon!     |");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);
    }

    public static void printDatabase(Task[] database, int pointer) {
        System.out.println(TOP_BORDER);
        System.out.println("Here are your tasks!");
        for (int i = 0; i < pointer; i++) {
            Task curTask = database[i];
            printTaskStatus(curTask);
        }
        System.out.println(BOTTOM_BORDER);
    }

    public static void modifyTask(String[] parts, Task[] database) {
        String action = parts[0];
        int index = Integer.parseInt(parts[1]); // This assumes that second part contains an int
        Task task = database[index];
        System.out.println(TOP_BORDER);
        switch(action) {
            case "mark":
                System.out.println("You've done it, very bouncy!");
                task.setDone();
                printTaskStatus(task);
                break;
            case "unmark":
                System.out.println("Gotta pump for air! It's unmarked!");
                task.setUnDone();
                printTaskStatus(task);
                break;
        }
        System.out.println(BOTTOM_BORDER);
    }

    public static String getTaskEvent(String input) {
        String task = input.split("/from")[0].trim();
        return task;
    }

    public static String getTaskDeadline(String input) {
        String task = input.split("/by")[0].trim();
        return task;
    }

    public static void addTaskAndPrint(String[] parts, Task[] database, int pointer) {
        String taskType = parts[0];
        String taskName = "";
        switch (taskType) {
            case "todo":
                taskName = arrayToString(parts, 1); // we skip the string in pos -, which is the command
                database[pointer] = new ToDos(taskName);
                break;
            case "deadline":
                taskName = arrayToString(parts, 1);
                String datetime = extractDatetime(taskName);
                taskName = getTaskDeadline(taskName);
                database[pointer] = new Deadlines(taskName, datetime);
                break;
            case "event":
                taskName = arrayToString(parts, 1);
                String[] fromTo = extractFromTo(taskName);
                taskName = getTaskEvent(taskName);
                database[pointer] = new Events(taskName, fromTo[0], fromTo[1]);
                break;
        }
        System.out.println(TOP_BORDER);
        System.out.println("Added to database: " + database[pointer].getDescription());
        printTaskCount(pointer);
        System.out.println(BOTTOM_BORDER);
    }

    private static String arrayToString(String[] arr, int index) {
        String combinedString = "";
        for (int i = index; i < arr.length; i++) {
            combinedString = combinedString + arr[i];
            if (i != arr.length - 1) {
                combinedString += " ";
            }
        }
        return combinedString;
    }

    private static String extractDatetime(String input) {
        String[] parts = input.split("/by", 2);
        if (parts.length > 1) {
            return parts[1].trim();
        }
        return "";
    }

    private static String[] extractFromTo(String string) {
        Pattern pattern = Pattern.compile("/from\\s+(.*?)\\s+/to\\s+(.*)");
        Matcher matcher = pattern.matcher(string);
        String[] fromTo = new String[2];

        if (matcher.find()) {
            fromTo[0] = matcher.group(1).trim();
            fromTo[1] = matcher.group(2).trim();
        }
        return fromTo;
    }

    private static void printTaskCount(int pointer) {
        String s = pointer == 0 ? "task" : "tasks";
        String str = String.format("Currently, you have %s %s, start bouncing!", pointer + 1, s);
        System.out.println(str);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] database = new Task[100];
        int pointer = 0;
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("|                   Hey there!                 |");
        System.out.println("|      I'm BouncyBob, your bubbly chatbot!     |");
        System.out.println("| Wow you look very round today, like a ball!  |");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);

        while (true) {
            System.out.println("Enter something:");
            String userInput = scanner.nextLine();
            String[] parts = userInput.split(" ");
            if (userInput.equals("bye")) {
                printBye();
                break;
            } else if (userInput.equals("list")) {
                printDatabase(database, pointer);
            } else if (parts[0].equals("mark") || parts[0].equals("unmark")) {
                modifyTask(parts, database);
            } else {
                addTaskAndPrint(parts, database, pointer);
                pointer++;
            }
        }
    }
}
