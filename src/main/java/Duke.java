import java.util.Scanner;

public class Duke {
    public enum COMMAND {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        DEADLINE("deadline"),
        TODO("todo"),
        EVENT("event"),
        BY("by"),
        FROM("from"),
        TO("to");

        private String cmd;
        private int strLength;

        COMMAND(String cmd) {
            this.cmd = cmd;
            this.strLength = cmd.length();
        }

        public String getCommand() {
            return this.cmd;
        }

        public int getCommandStringLength() {
            return this.strLength;
        }
    }
    private static TaskList tasks = new TaskList();
    private static Reply reply = Reply.init();
    public static void main(String[] args) {
        //Start user interaction
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.equals(COMMAND.BYE.getCommand())) {
                    reply.printDialog("Bye. Hope to see you again soon!");
                    return;
                } else if (input.equals(COMMAND.LIST.getCommand())) {
                    tasks.printTasks();
                } else if (input.startsWith(COMMAND.MARK.getCommand())) {
                    markTask(input, true);
                } else if (input.startsWith(COMMAND.UNMARK.getCommand())) {
                    markTask(input, false);
                } else if (input.startsWith(COMMAND.TODO.getCommand())) {
                    handleToDo(input);
                } else if (input.startsWith(COMMAND.DEADLINE.getCommand())) {
                    handleDeadline(input);
                } else if (input.startsWith(COMMAND.EVENT.getCommand())) {
                    handleEvent(input);
                } else if (input.startsWith(COMMAND.DELETE.getCommand())) {
                    deleteTask(input);
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

    public static void markTask(String input, boolean mark) throws InvalidInputException, MissingArgumentException {
        COMMAND command = mark ? COMMAND.MARK : COMMAND.UNMARK;
        String number = getCommandArguments(input, command);
        try {
            if (mark) {
                tasks.markDone(Integer.parseInt(number));
            } else {
                tasks.unmarkDone(Integer.parseInt(number));
            }
        } catch (NumberFormatException e ) {
            reply.printDialog(" Strictly type 1 number only");
        } catch (IndexOutOfBoundsException e) {
            reply.printDialog(" Index number does not exist in our list");
        }
    }

    private static void handleToDo(String input) throws MissingArgumentException, InvalidInputException {
        String todo = getCommandArguments(input, COMMAND.TODO);
        if (todo.isEmpty()) {
            throw new MissingTaskArgumentException(COMMAND.TODO.getCommand());
        }
        tasks.addTask(new ToDo(todo));
    }

    private static void handleDeadline(String input) throws InvalidInputException, MissingArgumentException {
        String deadline = getCommandArguments(input, COMMAND.DEADLINE);
        String[] slice = deadline.split("/");
        if (slice.length > 2) {
            throw new InvalidInputException();
        } else if (slice.length == 1) {
            throw new MissingDateArgumentException(COMMAND.BY.getCommand());
        } else {
            String desc = slice[0];
            String date;
            if (slice[1].startsWith(COMMAND.BY.getCommand())) {
                date = getCommandArguments(slice[1], COMMAND.BY);
            } else {
                throw new MissingDateArgumentException(COMMAND.BY.getCommand());
            }

            tasks.addTask(new Deadlines(desc, date));
        }
    }

    private static void handleEvent(String input) throws InvalidInputException, MissingArgumentException {
        String event = getCommandArguments(input, COMMAND.EVENT);
        String[] slice = event.split("/");

        if (slice.length > 3) {
            throw new InvalidInputException();
        } else if (slice.length < 3) {
            throw new MissingDateArgumentException("from and /to");
        } else {
            String desc = slice[0];
            String from;
            String to;

            if (slice[1].startsWith(COMMAND.FROM.getCommand())) {
                from = getCommandArguments(slice[1], COMMAND.FROM);
            } else {
                throw new MissingDateArgumentException(COMMAND.FROM.getCommand());
            }

            if (slice[2].startsWith(COMMAND.TO.getCommand())) {
                to = getCommandArguments(slice[2], COMMAND.TO);
            } else {
                throw new MissingDateArgumentException(COMMAND.TO.getCommand());
            }
            tasks.addTask(new Events(desc, from, to));
        }
    }

    private static void deleteTask(String input) throws InvalidInputException, MissingArgumentException {
        String number = getCommandArguments(input,COMMAND.DELETE);
        try {
            tasks.deleteTask(Integer.parseInt(number));
        } catch (NumberFormatException e ) {
            reply.printDialog(" Strictly type 1 number only");
        } catch (IndexOutOfBoundsException e) {
            reply.printDialog(" Index number does not exist in our list");
        }
    }

    private static String getCommandArguments(String input, COMMAND command) throws InvalidInputException, MissingArgumentException {
        int cmdLength = command.getCommandStringLength();
        String args = input.substring(cmdLength);

        if (args.isBlank()) {
            switch (command) {
                case TODO:
                case EVENT:
                case DEADLINE:
                    throw new MissingTaskArgumentException(command.getCommand());
                case BY:
                case TO:
                case FROM:
                    throw new MissingDateArgumentException(command.getCommand());
                default:
                    throw new InvalidInputException();
            }
        } else if (args.startsWith(" ")){
            return args.substring(1);
        } else {
            throw new InvalidInputException();
        }
    }
}