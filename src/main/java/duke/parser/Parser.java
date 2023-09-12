package duke.parser;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.task.ItemList;
import duke.task.deadline.DeadlineException;
import duke.task.event.EventException;
import duke.task.todo.ToDoException;
import duke.ui.UI;




/**
 * Parser class is responsible for parsing a single String command line into operation on item list.
 */
public class Parser {

    private final String line;
    private final Pattern MARK_PATTERN = Pattern.compile("mark (\\d+).*");
    private final Pattern UNMARK_PATTERN = Pattern.compile("unmark (\\d+).*");
    private final Pattern DELETE_PATTERN = Pattern.compile("delete (\\d+).*");
    private final Pattern DEADLINE_PATTERN = Pattern.compile("deadline (.*?) /by (.*)");
    private final Pattern TODO_PATTERN = Pattern.compile("todo (.*)");
    private final Pattern EVENT_PATTERN = Pattern.compile("event (.*?) /from (.*?) /to (.*)");
    private final Pattern FIND_PATTERN = Pattern.compile("find (.*?)");
    public Parser(String line) {
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
     * Execute mark operation on the item list based on the line String
     */
    public String parseMark(ItemList items) {
        Matcher matcher = MARK_PATTERN.matcher(this.line);
        if (matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            return items.markDone(number);
        } else {
            UI.printMessage("Invalid mark input");
            return "Invalid mark input";
        }
    }

    /**
     * Execute unmark operation on the item list based on the line String
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
     * Execute delete operation on the item list based on the line String
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
     * Execute adding ToDo operation on the item list based on the line String
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
     * Execute adding Event operation on the item list based on the line String
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

}
