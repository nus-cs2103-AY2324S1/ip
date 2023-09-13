package duke;

import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Parser {
    public Parser(){}

    public static String[] parse(String input, TaskList tasks) {
        try {
            if (input.equals("GET SCHWIFTY")) {
                System.out.print("I LIKE WHAT YOU'VE GOT. GOOD JOB.");
                return new String[]{"exit"};
            } else if (input.equals("list")) {
                return new String[]{"list"};
            } else if (input.startsWith("mark")) {
                return formatMark(input, tasks);
            } else if (input.startsWith("unmark")) {
                return formatUnmark(input, tasks);
            } else if (input.startsWith("delete")) {
                return formatDelete(input, tasks);
            } else if (input.startsWith("find")) {
                return formatFind(input);
            } else if (input.startsWith("todo")) {
                return formatTodo(input);
            } else if (input.startsWith("deadline")) {
                return formatDeadline(input);
            } else if (input.startsWith("event")) {
                return formatEvent(input);
            } else if (input.equals("undo")) {
                return formatUndo();
            } else {
                throw new InvalidInputException();
            }
        } catch (DukeException e) {
            return new String[]{"Exception", e.getMessage()};
        }
    }

    private static String[] formatUndo() {
        return new String[]{"undo"};
    }

    private static String[] formatEvent(String input) throws EmptyEventException, MissingFromException, MissingTitleException, MissingToException {
        int minLength = 7;
        if (input.length() < minLength) {
            throw new EmptyEventException();
        }
        int fromIndex = input.indexOf("/from");
        if (fromIndex == -1) {
            throw new MissingFromException();
        }
        if (fromIndex < 7) {
            throw new MissingTitleException();
        }
        String title = input.substring(6, fromIndex - 1);
        int toIndex = input.indexOf("/to");
        if (toIndex == -1) {
            throw new MissingToException();
        }
        String from = input.substring(fromIndex + 6, toIndex - 1);
        String to = input.substring(toIndex + 4);
        return new String[]{"event", title,from, to};
    }

    private static String[] formatDeadline(String input) throws EmptyDeadlineException, MissingByException, MissingTitleException {
        if (input.length() < 10) {
            throw new EmptyDeadlineException();
        }
        int byIndex= input.indexOf("/by");
        if (byIndex == -1) {
            throw new MissingByException();
        }
        if (byIndex < 10) {
            throw new MissingTitleException();
        }
        int titleIndex = 9;
        String title = input.substring(titleIndex, byIndex - 1);
        String dueDate = input.substring(byIndex + 4);
        return new String[]{"deadline", title, dueDate};
    }

    private static String[] formatTodo(String input) throws EmptyTodoException {
        if (input.length() < 6) {
            throw new EmptyTodoException();
        }
        int titleIndex = 5;
        String title = input.substring(titleIndex);
        return new String[]{"todo", title};
    }

    private static String[] formatFind(String input) throws InvalidFindException {
        int startIndex = 5;
        if (input.length() < 5) {
            throw new InvalidFindException();
        }
        String keywords = input.substring(startIndex);
        return new String[]{"find", keywords};
    }

    private static String[] formatDelete(String input, TaskList tasks) throws OutOfIndexException {
        Integer index = input.charAt(7) - '0';
        if (index < 0 || index > tasks.getSize() - 1) {
            throw new OutOfIndexException();
        }
        return new String[]{"delete", index.toString()};
    }

    private static String[] formatUnmark(String input, TaskList tasks) throws OutOfIndexException {
        Integer index = input.charAt(7) - '0';
        if (index < 0 || index > tasks.getSize() - 1) {
            throw new OutOfIndexException();
        }
        return new String[]{"unmark", index.toString()};
    }

    private static String[] formatMark(String input, TaskList tasks) throws OutOfIndexException {
        Integer index = input.charAt(5) - '0';
        if (index < 0 || index > tasks.getSize() - 1) {
            throw new OutOfIndexException();
        }
        return new String[]{"mark", index.toString()};
    }
}
