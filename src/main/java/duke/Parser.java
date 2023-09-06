package duke;

import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

public class Parser {
    public Parser(){}

    public static String[] parse(String input, TaskList tasks) {
        String parsedText;
        try {
            if (input.equals("GET SCHWIFTY")) {
                System.out.print("I LIKE WHAT YOU'VE GOT. GOOD JOB.");
                return new String[]{"exit"};
            } else if (input.equals("list")) {
                return new String[]{"list"};
            } else if (input.startsWith("mark")) {
                Integer index = input.charAt(5) - '0';
                if (index < 0 || index > tasks.getSize() - 1) {
                    throw new OutOfIndexException();
                }
                return new String[]{"mark", index.toString()};
            } else if (input.startsWith("unmark")) {
                Integer index = input.charAt(7) - '0';
                if (index < 0 || index > tasks.getSize() - 1) {
                    throw new OutOfIndexException();
                }
                return new String[]{"unmark", index.toString()};
            } else if (input.startsWith("delete")) {
                Integer index = input.charAt(7) - '0';
                if (index < 0 || index > tasks.getSize() - 1) {
                    throw new OutOfIndexException();
                }
                return new String[]{"delete", index.toString()};
            } else if (input.startsWith("find")) {
                if (input.length() < 5) {
                    throw new InvalidFindException();
                }
                String keywords = input.substring(5);
                return new String[]{"find", keywords};
            } else {
                if (input.startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new EmptyTodoException();
                    }
                    String title = input.substring(5);
                    return new String[]{"todo", title};
                } else if (input.startsWith("deadline")) {
                    if (input.length() < 10) {
                        throw new EmptyDeadlineException();
                    }
                    int index = input.indexOf("/by");
                    if (index == -1) {
                        throw new MissingByException();
                    }
                    if (index < 10) {
                        throw new MissingTitleException();
                    }
                    String title = input.substring(9, index - 1);
                    String dueDate = input.substring(index + 4);
                    return new String[]{"deadline", title, dueDate};
                } else if (input.startsWith("event")) {
                    if (input.length() < 7) {
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
                    return new String[]{"event", from, to};
                } else {
                    throw new InvalidInputException();
                }
            }
        } catch (DukeException e) {
            return new String[]{"Exception", e.getMessage()};
        }
    }
}
