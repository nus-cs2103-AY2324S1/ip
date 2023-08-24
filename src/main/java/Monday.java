import java.util.Scanner;

public class Monday {
    private static void startMonday() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        printSeparator();
        greet();
        printSeparator();
        while (running) {
            String userInput = scanner.nextLine();
            printSeparator();
            try {
                running = mondayParser(userInput);
            } catch (MondayExceptions e) {
                System.out.println(e);
            } catch (NumberFormatException e) {
                System.out.println("Mark/UnMark number error. " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Argument Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index out of Bound Error: " + e.getMessage());
            }
            printSeparator();
        }
        scanner.close();
    }

    private static boolean mondayParser(String userInput) throws MondayExceptions {
        String[] input = userInput.split(" ", 2);
        String command = input[0];
        String content = input.length > 1 ? input[1] : null;


        switch (command) {
            case "bye":
                exit();
                return false;
            case "list":
                Storage.displayList();
                break;
            case "mark": {
                if (content == null) {
                    throw new MondayExceptions("Mark requires a index to mark the task as completed.");
                }
                int index = Integer.parseInt(content);

                Storage.mark(index);
                break;
            }
            case "unmark": {
                if (content == null) {
                    throw new MondayExceptions("UnMark requires a index to mark the task as uncompleted.");
                }

                int index = Integer.parseInt(content);

                Storage.unMark(index);
                break;
            }
            case "todo":
                if (content == null) {
                    throw new MondayExceptions("The description of a todo cannot be empty.\n" +
                            "Usage: todo (task)");
                }

                Storage.addToTask(new ToDos(content));
                break;
            case "deadline":
                try {
                    if (content == null) {
                        throw new MondayExceptions("The description of a deadline cannot be empty.\n" +
                                "Usage: deadline (task) /by (time)");
                    }

                    String[] taskDetails = content.split("/by",2);
                    String description = taskDetails[0];
                    String date = taskDetails[1];

                    Storage.addToTask(new Deadlines(description.trim(), date.trim()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid Format. " +
                            "Usage: deadline (task) /by (time)");
                }
                break;
            case "event":
                try {
                    if (content == null) {
                        throw new MondayExceptions("The description of a event cannot be empty.\n" +
                                "Usage: event (task) /from (start time) /to (end time)");
                    }

                    String[] taskDetails = content.split("/from", 2);
                    String description = taskDetails[0];
                    String[] taskTiming = taskDetails[1].split("/to", 2);
                    String start = taskTiming[0];
                    String end = taskTiming[1];

                    Storage.addToTask(new Events(description.trim(),
                            start.trim(),
                            end.trim()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("Invalid Format. " +
                            "Usage: event (task) /from (start time) /to (end time)");
                }
                break;
            case "delete":
                if (content == null) {
                    throw new MondayExceptions("Delete requires a index to delete the task");
                }
                int index = Integer.parseInt(content);

                Storage.delete(index);
                break;
            default:
                throw new MondayExceptions("Sorry, I do not understand what that means.\n" +
                        "Please provide a valid input/command. e.g todo read book");
        }
        return true;
    }


    private static void greet() {
        System.out.println("Hello! I'm " + "Monday");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        startMonday();
    }

}
