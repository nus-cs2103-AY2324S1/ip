package duke;

public class Parser {

    public static int run = 1;
    
    public static String parse(String task) {
        try {
            if (task.equals("bye")) {
                return handleBye();
            }
            if (task.equals("list")) {
                return handleList();
            }
            String[] elements = task.split((" "));
            if (elements.length >= 2) {
                String instruction = elements[0];
                switch (instruction) {
                case "mark":
                case "unmark":
                    return handleMarkOrUnmark(instruction, elements);
                case "delete":
                    return handleDelete(elements);
                case "find":
                    return handleFind(elements);
                default:
                    return handleTaskCreation(task, instruction);
                }
            } else if (elements[0].equals("todo") || elements[0].equals("deadline") || elements[0].equals("event")) {
                throw new DukeException(Ui.horizontalLine + "OOPS!!! The description of a " + elements[0] + " cannot be empty.\n" + Ui.horizontalLine);
            } else {
                throw new DukeException(Ui.horizontalLine + "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + Ui.horizontalLine);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private static String handleBye() {
        run = 0;
        return Ui.sayBye();
    }

    private static String handleList() {
        Task taskInstance = new Task();
        return taskInstance.formatList();
    }

    private static String handleMarkOrUnmark(String instruction, String[] elements) {
        try {
            int no = Integer.parseInt(elements[1]);
            Task taskInstance = new Task();
            if (instruction.equals("mark")) {
                return taskInstance.mark(no);
            } else {
                return taskInstance.unmark(no);
            }
        } catch (NumberFormatException e) {
            return (Ui.horizontalLine
                    + "You did not enter a valid integer :(\n"
                    + Ui.horizontalLine);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private static String handleDelete(String[] elements) {
        try {
            int no = Integer.parseInt(elements[1]);
            Task taskInstance = new Task();
            return taskInstance.delete(no);
        } catch (NumberFormatException e) {
            return (Ui.horizontalLine
                    + "You did not enter a valid integer :(\n"
                    + Ui.horizontalLine);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private static String handleTodo(String actualTask) {
        Todo todo = new Todo(actualTask, true);
        return todo.print();
    }

    private static String handleDeadline(String actualTask) throws DukeException {
        String[] taskAndDeadline = actualTask.split("/by");
        if (taskAndDeadline.length == 1 || taskAndDeadline.length == 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for deadline :-(\n"
                    + Ui.horizontalLine);
        }
        if (taskAndDeadline.length == 1 || taskAndDeadline.length == 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for deadline :-(\n"
                    + Ui.horizontalLine);
        }
        String onlyTask = taskAndDeadline[0].trim(); // Trim to remove extra spaces
        String by = taskAndDeadline[1].trim();// Trim to remove extra spaces
        if (!by.contains(" ")) {
            by += " 2359";
        }
        Deadline deadline = new Deadline(onlyTask, true, by);
        return deadline.print();
    }

    private static String handleEvent(String actualTask) throws DukeException {
        String[] taskAndToFrom = actualTask.split("/from");
        if (taskAndToFrom.length == 1 || taskAndToFrom.length == 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for event :-(\n"
                    + Ui.horizontalLine);
        }
        String onlyTask = taskAndToFrom[0].trim(); // Trim to remove extra spaces
        if (onlyTask.isEmpty()) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for event :-(\n"
                    + Ui.horizontalLine);
        }
        String[] ToFrom = taskAndToFrom[1].split("/to");
        if (ToFrom.length == 1 || ToFrom.length == 0) {
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! Invalid format for event :-(\n"
                    + Ui.horizontalLine);
        }
        String from = ToFrom[0].trim(); // Trim to remove extra spaces
        String to = ToFrom[1].trim(); // Trim to remove extra spaces
        if (!from.contains(" ")) {
            from += " 2359";
        }
        if (!to.contains(" ")) {
            to += " 2359";
        }
        Event event = new Event(onlyTask, true, from, to);
        return event.print();
    }

    private static String handleFind(String[] elements) {
        String keyword = elements[1];
        return Ui.displaySearchResults(Duke.taskList, keyword);
    }

    private static String handleTaskCreation(String task, String instruction) throws DukeException {
        int firstSpaceIndex = task.indexOf(' ');
        String actualTask = task.substring(firstSpaceIndex + 1);
        switch (instruction) {
        case "todo":
            return handleTodo(actualTask);
        case "deadline":
            return handleDeadline(actualTask);
        case "event":
            return handleEvent(actualTask);
        default:
            throw new DukeException(Ui.horizontalLine
                    + "OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + Ui.horizontalLine);
        }
    }

}
