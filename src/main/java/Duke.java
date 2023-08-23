import java.util.Scanner;

public class Duke {
    private static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Extracts the task details in between `command` and `keyword`.
     * E.g. deadline read the book /by tuesday
     *      -> extracts `read the book`
     *
     * @param userInput string input by user.
     * @param command todo/deadline/event
     * @param keyword keyword to stop at
     * @return string in between command and keyword
     */
    private static String extractTaskDetails(String userInput, String command, String... keyword) throws DukeException {
        if (keyword.length == 0 && command == "todo") {
            String[] parts = userInput.split(" ", 2);
            if (parts[1].isEmpty()) {
                throw new DukeException("Task description cannot be empty");
            }
            return parts[1];
        }
        int startIndex = userInput.indexOf(command) + command.length();
        int endIndex = userInput.indexOf(keyword[0]);

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            String toReturn = userInput.substring(startIndex, endIndex).trim();
            if (toReturn.isEmpty()) {
                throw new DukeException("Task description cannot be empty");
            }
            return toReturn;
        }
        return "";  // Return an empty string if extraction is not possible

    }

    /**
     * Extracts the details after the keyword.
     * E.g. event go to school /from mon 2pm /to tues 3pm
     *      -> extracts `mon 2pm`
     *
     * @param userInput string input by user.
     * @param keyword the keyword to stop before
     * @param additionalKeywords if there are more than one keywords
     * @return string after keyword/ between 2 keywords.
     */
    private static String extractAfterKeyword(String userInput, String keyword, String... additionalKeywords) throws DukeException {
        int keywordIndex = userInput.indexOf(keyword);
        if (keywordIndex == -1) {
            throw new DukeException("Keyword " + keyword + " not found");
        }
        String[] tokens = userInput.split(keyword);
        if (additionalKeywords != null && additionalKeywords.length > 0) {
            String[] tokensAfterSecondKeyword = tokens[1].split(additionalKeywords[0]);
            return tokensAfterSecondKeyword[0];
        }
        return tokens[1];
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
            if (userInput.equals("bye")) {
                ongoing = false;
            } else if (userInput.equals("list")) {
                Task.listAllTasks();
            } else if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ", 2);
                try {
                    int taskIndex = Integer.parseInt(parts[1]);
                    Task.deleteTask(taskIndex);
                } catch (NumberFormatException e) {
                    // to catch if integer is not passed. e.g. "mark e"
                    System.out.println("   Invalid task index. Please provide a valid integer. e.g. mark 3");
                } catch (TaskException e) {
                    // to catch if there is a task exception e.g. input > taskList size
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("mark ")) {
                String[] parts = userInput.split(" ", 2);
                try {
                    int taskIndex = Integer.parseInt(parts[1]);
                    Task.mark(taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println("   Invalid task index. Please provide a valid integer. e.g. mark 3");
                } catch (TaskException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("unmark ")) {
                String[] parts = userInput.split(" ", 2);
                try {
                    int taskIndex = Integer.parseInt(parts[1]);
                    Task.unMark(taskIndex);
                } catch (NumberFormatException e) {
                    System.out.println("   Invalid task index. Please provide a valid integer. e.g. mark 3");
                } catch (TaskException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("todo ")) {
                try {
                    String details = extractTaskDetails(userInput, "todo");
                    Task todoTask = new Todo(details);
                    Task.addTask(todoTask);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("deadline ")) {
                // throw some DukeExceptions here is doesnt conform to /by
                try {
                    String details = extractTaskDetails(userInput, "deadline", "/by");
                    String date = extractAfterKeyword(userInput, "/by");
                    Task deadlineTask = new Deadline(details, date);
                    Task.addTask(deadlineTask);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (userInput.startsWith("event ")) {
                try {
                    String details = extractTaskDetails(userInput, "event", "/from");
                    String from = extractAfterKeyword(userInput, "/from", "/to");
                    String to = extractAfterKeyword(userInput, "/to");
                    Task eventTask = new Event(details, from, to);
                    Task.addTask(eventTask);
                } catch(DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        exit();
        scanner.close();
    }
}
