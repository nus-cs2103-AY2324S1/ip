import exceptions.*;
import extensions.*;
import java.util.Scanner;

public class Duke {

    private static TaskList list = new TaskList();

    private static String horizontalLine() {
        return "_____________________________________________________\n";
    }

    private static void intro() {
        String output = horizontalLine() +
                " ____  _   _   ____  _____  ____   _     ____  _____\n" +
                "/ (__`| |_| | / () \\|_   _|/ () \\ | |__ / () \\|_   _|\n" +
                "\\____)|_| |_|/__/\\__\\ |_| /__/\\__\\|____|\\____/  |_|\n\n" +
                "Hello! I'm ChatALot.\n" +
                "What can I do for you?\n" +
                horizontalLine();
        System.out.print(output);
    }

    private static String mark(String taskNumString) {
        if (Duke.list.getSize() < 1) {
            return "The task list is empty.";
        }

        if (!taskNumString.matches("[0-9]+")) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum < 1 || taskNum > Duke.list.getSize()) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        Task task = Duke.list.markTaskAsDone(taskNum);
        return "Nice! I've marked this task as done:\n" +
                "  " +
                task;
    }

    private static String unmark(String taskNumString) {
        if (Duke.list.getSize() < 1) {
            return "The task list is empty.";
        }

        if (!taskNumString.matches("[0-9]+")) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum < 1 || taskNum > Duke.list.getSize()) {
            throw new InvalidIndexException(Duke.list.getSize());
        }

        Task task = Duke.list.unmarkTask(taskNum);
        return "OK, I've marked this task as not done yet:\n" +
                "  " +
                task;
    }

    private static String createTodo(String desc) {
        if (desc.equals("")) {
            throw new InvalidTodoException();
        }
        Task task = Duke.list.addTodo(desc);
        return "Got it. I've added this task:\n" +
                "  " +
                task +
                "\nNow you have " +
                Duke.list.getSize() +
                " tasks in the list.";
    }

    private static String createDeadline(String action) {
        if (!action.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        String[] arr = action.split("/by");
        if (arr.length < 2) {
            throw new InvalidDeadlineException();
        }

        String desc = arr[0].trim();
        String deadline = arr[1].trim();

        if (desc.equals("") || deadline.equals("")) {
            throw new InvalidDeadlineException();
        }
        Task task = Duke.list.addDeadline(desc, deadline);
        return "Got it. I've added this task:\n" +
                "  " +
                task +
                "\nNow you have " +
                Duke.list.getSize() +
                " tasks in the list.";
    }

    private static String createEvent(String action) {
        if (!action.contains("/from") || !action.contains("/to")) {
            throw new InvalidEventException();
        }

        String[] arr = action.split("/from|/to");
        if (arr.length < 3) {
            throw new InvalidEventException();
        }

        String desc = arr[0].trim();
        String start = arr[1].trim();
        String end = arr[2].trim();

        if (desc.equals("") || start.equals("") || end.equals("")) {
            throw new InvalidEventException();
        }

        Task task = Duke.list.addEvent(desc, start, end);
        return "Got it. I've added this task:\n" +
                "  " +
                task +
                "\nNow you have " +
                Duke.list.getSize() +
                " tasks in the list.";
    }

    private static String processInput(String userInput) {
        String[] inputArr = userInput.trim().split(" ");
        String restOfInput;

        if (inputArr.length == 0) {
            throw new UnknownCommandException();
        }

        String command = inputArr[0];
        switch(command) {
            case "list":
                return Duke.list.toString();
            case "mark":
                restOfInput = userInput.trim().substring(4).trim();
                return mark(restOfInput);
            case "unmark":
                restOfInput = userInput.trim().substring(6).trim();
                return unmark(restOfInput);
            case "todo":
                restOfInput = userInput.trim().substring(4).trim();
                return createTodo(restOfInput);
            case "deadline":
                restOfInput = userInput.trim().substring(8).trim();
                return createDeadline(restOfInput);
            case "event":
                restOfInput = userInput.trim().substring(5).trim();
                return createEvent(restOfInput);
            default:
                throw new UnknownCommandException();
        }
    }

    private static String displayOutput(String userInput) {
        String output = "";
        try {
            output = processInput(userInput);
        } catch (DukeException e) {
            output = e.getMessage();
        } catch (RuntimeException e) {
            output = "Runtime: " + e.getMessage();
        } finally {
            String displayed = horizontalLine() +
                    output +
                    "\n" +
                    horizontalLine();
            System.out.print(displayed);
            return displayed;
        }
    }

    private static String exit() {
        String outro = horizontalLine() +
                "Bye. Hope to see you again soon!\n" +
                horizontalLine();
        System.out.print(outro);
        return outro;
    }

    public static void main(String[] args) {
        intro();

        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        String bye = "bye";

        while (!userInput.toLowerCase().equals(bye)) {
            displayOutput(userInput);
            userInput = myObj.nextLine();
        }

        exit();
    }

}
