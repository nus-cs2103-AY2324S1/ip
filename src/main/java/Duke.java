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
                reply.printDialog(e.toString());
            } catch (MissingArgumentException e) {
                reply.printDialog(e.toString());
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
                reply.printDialog(" I can only understand numbers");
            } catch (IndexOutOfBoundsException e) {
                reply.printDialog(" Index number does not exist in our list");
            }
        }
    }

    private static void handleToDo(String input) throws MissingArgumentException, InvalidInputException {
        String todo = getCommandArguments(input, "todo");
        if (todo.isEmpty()) {
            throw new MissingTaskArgumentException("todo");
        }
        tasks.addTask(new ToDo(todo));
    }

    private static void handleDeadline(String input) throws InvalidInputException, MissingArgumentException {
        String deadline = getCommandArguments(input, "deadline");
        String[] slice = deadline.split("/");
        if (slice.length > 2) {
            throw new InvalidInputException();
        } else if (slice.length == 1) {
            throw new MissingDateArgumentException("by");
        } else {
            String desc = slice[0];
            String date;
            if (slice[1].startsWith("by")) {
                date = getCommandArguments(slice[1], "by");
            } else {
                throw new MissingDateArgumentException("by");
            }

            tasks.addTask(new Deadlines(desc, date));
        }
    }

    private static void handleEvent(String input) throws InvalidInputException, MissingArgumentException {
        String event = getCommandArguments(input, "event");
        String[] slice = event.split("/");

        if (slice.length > 3) {
            throw new InvalidInputException();
        } else if (slice.length < 3) {
            throw new MissingDateArgumentException("from and /to");
        } else {
            String desc = slice[0];
            String from;
            String to;

            if (slice[1].startsWith("from")) {
                from = getCommandArguments(slice[1], "from");
            } else {
                throw new MissingDateArgumentException("from");
            }

            if (slice[2].startsWith("to")) {
                to = getCommandArguments(slice[2], "to");
            } else {
                throw new MissingDateArgumentException("to");
            }

            tasks.addTask(new Events(desc, from, to));
        }
    }

    private static String getCommandArguments(String input, String command) throws InvalidInputException, MissingArgumentException {
        int cmdLength = command.length();
        String args = input.substring(cmdLength);

        if (args.isBlank()) {
            if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
                throw new MissingTaskArgumentException(command);
            } else {
                throw new MissingDateArgumentException(command);
            }
        } else if (args.startsWith(" ")){
            return args.substring(1);
        } else {
            throw new InvalidInputException();
        }
    }
}