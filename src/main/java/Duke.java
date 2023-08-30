import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Duke {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void main(String[] args) {
        String directoryPath = "./data";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directory.getAbsolutePath());
            } else {
                System.out.println("Failed to create directory.");
            }
        }

        String filePath = "./data/duke.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Failed to create file.");
                }
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        TaskList taskList = new TaskList();
        try {
            taskList.loadTasksFromFile("./data/duke.txt");
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        printWithSeparator("Hello! I'm David.\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String input = sc.nextLine();
                String firstWord = input.split(" ", 2)[0];

                if (input.equals("bye")) {
                    printWithSeparator("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    taskList.list();
                } else if (firstWord.equals("mark")) {
                    int index = extractNumber(input) - 1;
                    taskList.markTaskAsDone(index);
                } else if (firstWord.equals("unmark")) {
                    int index = extractNumber(input) - 1;
                    taskList.unmarkTask(index);
                } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                    taskList.addTask(input);
                } else if (firstWord.equals("delete")) {
                    int index = extractNumber(input) - 1;
                    taskList.deleteTask(index);
                } else {
                    throw new DukeException(messageWithSeparator("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
                }
                try {
                    taskList.saveTasksToFile("./data/duke.txt");
                } catch (IOException e) {
                    System.out.println("Error saving tasks to file: " + e.getMessage());
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void printWithSeparator(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(LINE_SEPARATOR);
    }

    private static String messageWithSeparator(String message) {
        return LINE_SEPARATOR+ "\n" + message + "\n" + LINE_SEPARATOR;
    }

    private static int extractNumber(String input) {
        String[] words = input.split(" ");
        for (String word : words) {
            if (word.matches("\\d+")) {
                return Integer.parseInt(word);
            }
        }
        return -1; // No number found
    }
}
