import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static String extractAfterKeyword(String userInput, String keyword, String... additionalKeywords) {
        String[] tokens = userInput.split(keyword);
        if (additionalKeywords != null && additionalKeywords.length > 0) {
            String[] tokensAfterSecondKeyword = tokens[1].split(additionalKeywords[0]);
            return tokensAfterSecondKeyword[0];
        }
        return tokens[1];
    }

    private static String extractTaskDetails(String userInput, String command, String keyword) {
        int startIndex = userInput.indexOf(command) + command.length();
        int endIndex = userInput.indexOf(keyword);

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return userInput.substring(startIndex, endIndex).trim();
        }
        return "";  // Return an empty string if extraction is not possible
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | | / _ \\\n"
                + "| |_| | |_| |  |_   __/\n"
                + "|____/ \\__,_|___|\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean ongoing = true;
        while(ongoing) {
            System.out.print("> ");
            userInput = scanner.nextLine();
            if (userInput.equals("list")) {
                Task.listAllTasks();
            } else if (userInput.equals("bye")) {
                ongoing = false;
            } else if (userInput.startsWith("mark ")) {
                String[] parts = userInput.split(" ", 2);
                if (parts.length != 2) {
                    Task task = new Task(parts[1]);
                    Task.addTask(task);
                } else {
                    int taskIndex = Integer.parseInt(parts[1]);
                    Task.mark(taskIndex);
                }
            } else if (userInput.startsWith("unmark ")) {
                String[] parts = userInput.split(" ", 2);
                if (parts.length != 2) {
                    Task task = new Task(parts[1]);
                    Task.addTask(task);
                } else {
                    int taskIndex = Integer.parseInt(parts[1]);
                    Task.unMark(taskIndex);
                }
            } else if (userInput.startsWith("todo")) {
                String[] parts = userInput.split(" ", 2);
                Task todoTask = new Todo(parts[1]);
                Task.addTask(todoTask);
            } else if (userInput.startsWith("deadline")) {
                String details = extractTaskDetails(userInput, "deadline", "/by");
                String date = extractAfterKeyword(userInput, "/by");
                Task deadlineTask = new Deadline(details, date);
                Task.addTask(deadlineTask);
            } else if (userInput.startsWith("event")) {
                String details = extractTaskDetails(userInput, "event", "/from");
                String from = extractAfterKeyword(userInput, "/from", "/to");
                String to = extractAfterKeyword(userInput, "/to");
                Task eventTask = new Event(details, from, to);
                Task.addTask(eventTask);
            } else {
                Task task = new Task(userInput);
                Task.addTask(task);
            }
        }

        exit();
        scanner.close();
    }
}
