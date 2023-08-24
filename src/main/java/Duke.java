import java.util.Scanner;

public class Duke {
    private static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void main(String[] args) {
        printWithSeparator("Hello! I'm David.\nWhat can I do for you?");

        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String firstWord = input.split(" ", 2)[0];

            if (input.equals("bye")) {
                printWithSeparator("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                taskList.list();
            } else if (input.length() >= 4 && input.substring(0, 4).equals("mark")) {
                int index = extractNumber(input);
                taskList.markTaskAsDone(index);
            } else if (input.length() >= 6 && input.substring(0, 6).equals("unmark")) {
                int index = extractNumber(input);
                taskList.unmarkTask(index);
            } else if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                taskList.addTask(input);
            } else {
                printWithSeparator("Please enter a valid command.");
            }
        }
    }

    private static void printWithSeparator(String message) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(message);
        System.out.println(LINE_SEPARATOR);
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
