import Exception.InvalidDateException;
import java.util.Scanner;

import Enums.Command;
import Exception.InvalidInputException;
import Exception.InvalidCommandException;
import Exception.MissingArgumentException;
import Exception.MissingTaskArgumentException;
import Task.Deadlines;
import Task.Events;
import Task.ToDo;

public class Duke {

    private static TaskList tasks = TaskList.init();
    private static Reply reply = Reply.init();
    public static void main(String[] args) {
        //Start user interaction
        Scanner scanner = new Scanner(System.in);
        while(true) {
            try {
                String input = scanner.nextLine();
                if (input.toLowerCase().equals(Command.BYE.getCommand())) {
                    reply.printDialog("Bye. Hope to see you again soon!");
                    return;
                } else if (input.toLowerCase().equals(Command.LIST.getCommand())) {
                    tasks.printTasks();
                } else if (input.startsWith(Command.MARK.getCommand())) {
                    markTask(input, true);
                } else if (input.startsWith(Command.UNMARK.getCommand())) {
                    markTask(input, false);
                } else if (input.toLowerCase().equals(Command.TODO.getCommand())) {
                    handleToDo(scanner);
                } else if (input.toLowerCase().equals(Command.DEADLINE.getCommand())) {
                    handleDeadline(scanner);
                } else if (input.toLowerCase().equals(Command.EVENT.getCommand())) {
                    handleEvent(scanner);
                } else if (input.startsWith(Command.DELETE.getCommand())) {
                    deleteTask(input);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidInputException e) {
                reply.printDialog(e.toString());
            } catch (MissingArgumentException e) {
                reply.printDialog(e.toString());
            } catch (InvalidCommandException e) {
                reply.printDialog(e.toString());
            }
        }

    }

    private static void handleToDo(Scanner scanner) {
        reply.printDialog("So you want to add a ToDo task. Tell me what's the task.");
        String desc = scanner.nextLine();
        tasks.addTask(new ToDo(desc));
    }

    private static void handleDeadline(Scanner scanner) {

        reply.printDialog("So you want to add a task with deadline. Tell me what's the task.");
        String desc = scanner.nextLine();
        reply.printDialog("Now indicate the deadline date.");
        String date = scanner.nextLine();

        try {
            date = Time.FormatDate(date);
            tasks.addTask(new Deadlines(desc, date));
        } catch (InvalidDateException e) {
            reply.printDialog(e.toString());
        }
    }

    private static void handleEvent(Scanner scanner) {

        reply.printDialog("So you want to add a event task. Tell me what's the task.");
        String desc = scanner.nextLine();
        reply.printDialog("Now indicate the start date.");
        String from = scanner.nextLine();
        reply.printDialog("Now indicate the end date.");
        String to = scanner.nextLine();

        try {
            from = Time.FormatDate(from);
            to = Time.FormatDate(to);
            tasks.addTask(new Events(desc, from, to));
        } catch (InvalidDateException e) {
            reply.printDialog(e.toString());
        }
    }
    public static void markTask(String input, boolean mark) throws
            InvalidInputException,
            MissingArgumentException,
            InvalidCommandException {

        Command command = mark ? Command.MARK : Command.UNMARK;
        String number = getCommandArguments(input, command);
        try {
            if (mark) {
                tasks.markDone(Integer.parseInt(number));
            } else {
                tasks.unmarkDone(Integer.parseInt(number));
            }
        } catch (NumberFormatException e ) {
            reply.printDialog("Strictly type 1 number only");
        } catch (IndexOutOfBoundsException e) {
            reply.printDialog("Index number does not exist in our list");
        }
    }
    private static void deleteTask(String input) throws
            InvalidInputException,
            MissingArgumentException,
            InvalidCommandException {

        String number = getCommandArguments(input,Command.DELETE);
        try {
            tasks.deleteTask(Integer.parseInt(number));
        } catch (NumberFormatException e ) {
            reply.printDialog(" Strictly type 1 number only");
        } catch (IndexOutOfBoundsException e) {
            reply.printDialog(" Index number does not exist in our list");
        }
    }

    private static String getCommandArguments(String input, Command command) throws
            InvalidInputException,
            MissingArgumentException,
            InvalidCommandException {

        int cmdLength = command.getCommandStringLength();
        String args = input.substring(cmdLength);

        if (args.isBlank()) {
            switch (command) {
                case MARK:
                case UNMARK:
                case DELETE:
                    throw new MissingTaskArgumentException();
                default:
                    throw new InvalidCommandException();
            }
        } else if (args.startsWith(" ")){
            return args.substring(1);
        } else {
            throw new InvalidInputException();
        }
    }
}