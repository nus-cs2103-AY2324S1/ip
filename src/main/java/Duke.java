import java.util.Scanner;

public class Duke {
    private static TaskList tasks = new TaskList();
    private static Reply reply = Reply.init();
    public static void main(String[] args) {
        //Start user interaction
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    reply.printDialog("Bye. Hope to see you again soon!");
                    return;
                } else if (input.equals("list")) {
                    tasks.printTasks();
                } else if (input.startsWith("mark")) {
                    markTask(input, true);
                } else if (input.startsWith("unmark")) {
                    markTask(input, false);
                } else if (input.startsWith("todo")) {
                    handleToDo(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else {
                    throw new InvalidInputException();
                }
            } catch (InvalidInputException e) {
                reply.printDialog("Invalid input");
            }
        }

    }

    public static void markTask(String input, boolean mark) throws InvalidInputException {
        String[] split = input.split(" ");
        if (split.length > 2) {
            throw new InvalidInputException();
        } else {
            try {
                if (mark) {
                    tasks.markDone(Integer.parseInt(split[1]));
                } else {
                    tasks.unmarkDone(Integer.parseInt(split[1]));
                }
            } catch (NumberFormatException e ) {
                reply.printDialog("Invalid number");
            } catch (IndexOutOfBoundsException e) {
                reply.printDialog("Index number does not exist");
            }
        }
    }

    private static void handleToDo(String input) {
        String todo = input.substring(4);
        tasks.addTask(new ToDo(todo));
    }

    private static void handleDeadline(String input) throws InvalidInputException {
        String deadline = input.substring(8);
        String[] slice = deadline.split("/");
        if (slice.length != 2) {
            throw new InvalidInputException();
        } else {
            if (!slice[1].startsWith("by")) {
                throw new InvalidInputException();
            }

            String desc = slice[0];
            String date = slice[1].substring(3);
            tasks.addTask(new Deadlines(desc, date));
        }
    }

    private static void handleEvent(String input) throws InvalidInputException {
        String event = input.substring(5);
        String[] slice = event.split("/");
        if (slice.length != 3) {
            throw new InvalidInputException();
        } else {
            if (!slice[1].startsWith("from") || !slice[2].startsWith("to")) {
                throw new InvalidInputException();
            }

            String desc = slice[0];
            String from = slice[1].substring(4);
            String to = slice[2].substring(2);
            tasks.addTask(new Events(desc, from, to));
        }
    }
}
