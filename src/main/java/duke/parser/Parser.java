package duke.parser;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.dates.Dates;
import duke.task.ItemList;
import duke.task.deadline.Deadline;
import duke.task.deadline.DeadlineException;
import duke.task.event.Event;
import duke.task.event.EventException;
import duke.task.todo.ToDoException;
import duke.ui.UI;




/**
 * Parser class is responsible for parsing a single String command line into operation on item list.
 */
public class Parser {
    private static final Pattern MARK_PATTERN = Pattern.compile("mark (\\d+).*");
    private static final Pattern UNMARK_PATTERN = Pattern.compile("unmark (\\d+).*");
    private static final Pattern DELETE_PATTERN = Pattern.compile("delete (\\d+).*");
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("deadline (.*?) /by (.*)");
    private static final Pattern TODO_PATTERN = Pattern.compile("todo (.*)");
    private static final Pattern EVENT_PATTERN = Pattern.compile("event (.*?) /from (.*?) /to (.*)");
    private static final Pattern RESCHEDULE_EVENT_PATTERN = Pattern.compile("reschedule (\\d+) /from (.*?) /to (.*)");
    private static final Pattern RESCHEDULE_DEADLINE_PATTERN = Pattern.compile("reschedule (\\d+) /by (.*?)");
    private static final Pattern FIND_PATTERN = Pattern.compile("find (.*?)");

    private final String line;
    public Parser(String line) {
        assert line != null && !line.isEmpty() : "Line should not be null or empty!";
        this.line = line;
    }


    /**
     * return the string line into Command string
     * @return the Command in string form
     */
    public String getCommand() {
        String command = this.line.split(" ")[0];
        return command.toUpperCase();
    }

    /**
     * Execute marking operation on the item list based on the line String
     *
     * @param items Items used to execute the operation on
     * @return String representation of the marking operation of a task
     */
    public String parseMark(ItemList items) {
        Matcher matcher = MARK_PATTERN.matcher(this.line);
        if (matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            return items.markDone(number);
        } else {
            return UI.printMessage("Invalid mark input");
        }
    }

    /**
     * Execute Unmarking operation on the item list based on the line String
     *
     * @param items Items used to execute the operation on
     * @return String representation of the unmarking operation of a task
     */
    public String parseUnmark(ItemList items) {
        Matcher matcher = UNMARK_PATTERN.matcher(this.line);
        if (matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            return items.markUndone(number);
        } else {
            return UI.printMessage("Invalid unmark input");
        }
    }

    /**
     * Execute adding Event operation on the item list based on the line String
     *
     * @param items Items used to execute the operation on
     * @return String representation of the Deletion of the task
     */
    public String parseDelete(ItemList items) {
        Matcher matcher = DELETE_PATTERN.matcher(this.line);
        if (matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            return items.delete(number);
        } else {
            return UI.printMessage("Invalid delete input");
        }
    }

    /**
     * Execute adding Deadline operation on the item list based on the line String
     *
     * @param items Items used to execute the operation on
     * @return String representation of the Deadline that is parsed
     */
    public String parseDeadline(ItemList items) throws DeadlineException {
        Matcher matcher = DEADLINE_PATTERN.matcher(this.line);
        if (matcher.matches()) {
            String task = matcher.group(1);
            String by = matcher.group(2);
            return items.addDeadline(task, by);
        } else {
            throw new DeadlineException();
        }
    }

    /**
     * Executes adding ToDo operation on the item list based on the line String
     *
     * @param items Items used to execute the operation on
     * @return String representation of the Todo that is parsed
     */
    public String parseTodo(ItemList items) throws ToDoException {

        Matcher matcher = TODO_PATTERN.matcher(line);
        if (matcher.matches()) {
            String task = matcher.group(1);
            return items.addTodo(task);
        } else {
            throw new ToDoException();
        }
    }

    /**
     * Executes adding Event operation on the item list based on the line String
     *
     * @param items Items used to execute the operation on
     * @return String representation of the Event that is parsed
     */
    public String parseEvent(ItemList items) throws EventException {

        Matcher matcher = EVENT_PATTERN.matcher(this.line);
        if (matcher.matches()) {
            String task = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return items.addEvent(task, from, to);
        } else {
            throw new EventException();
        }
    }

    public String parseFind(ItemList items) {

        Matcher matcher = FIND_PATTERN.matcher(this.line);
        if (matcher.matches()) {
            String task = matcher.group(1);
            return items.find(task);
        } else {
            return UI.printMessage("Invalid find input");
        }
    }

    public String parseReschedule(ItemList items) {
        Matcher eventMatcher = RESCHEDULE_EVENT_PATTERN.matcher(this.line);
        Matcher deadlineMatcher = RESCHEDULE_DEADLINE_PATTERN.matcher(this.line);
        if (eventMatcher.matches()) {
            String number = eventMatcher.group(1);
            String checkOutcome = items.checkType(number);
            if (!checkOutcome.equals("EVENT")) {
                return UI.printMessage("This is not an Event task!");
            }

            String from = eventMatcher.group(2);
            String to = eventMatcher.group(3);
            Event newEvent = (Event) items.getTask(number);
            if (Dates.checkDateinput(from) && Dates.checkDateinput(to)) {
                return items.setItems(newEvent.reschedule(Dates.convertToDateTime(from),
                        Dates.convertToDateTime(to)), number);
            } else {
                return items.setItems(newEvent.reschedule(from, to), number);
            }

        }
        if (deadlineMatcher.matches()) {
            String number = deadlineMatcher.group(1);
            String checkOutcome = items.checkType(number);

            if (!checkOutcome.equals("DEADLINE")) {
                return UI.printMessage("This is not a Deadline task!");
            }



            String by = deadlineMatcher.group(2);
            Deadline newDeadline = (Deadline) items.getTask(number);
            if (Dates.checkDateinput(by)) {
                return items.setItems(newDeadline.reschedule(Dates.convertToDateTime(by)), number);
            } else {
                return items.setItems(newDeadline.reschedule(by), number);
            }
        } else {
            return UI.printMessage("Invalid Reschedule input");
        }
    }

}
