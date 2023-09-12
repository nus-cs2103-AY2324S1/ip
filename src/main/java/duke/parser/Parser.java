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

    private String line;
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
     * Execute mark operation on the item list based on the line String
     */
    public String parseMark(ItemList items) {
        Pattern markpattern = Pattern.compile("mark (\\d+).*");
        Matcher matcher = markpattern.matcher(this.line);
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
        Pattern unmarkpattern = Pattern.compile("unmark (\\d+).*");
        Matcher matcher = unmarkpattern.matcher(this.line);
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
        Pattern deletepattern = Pattern.compile("delete (\\d+).*");
        Matcher matcher = deletepattern.matcher(this.line);
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
        Pattern deadlinepattern = Pattern.compile("deadline (.*?) /by (.*)");
        Matcher matcher = deadlinepattern.matcher(this.line);
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
        Pattern todopattern = Pattern.compile("todo (.*)");
        Matcher matcher = todopattern.matcher(line);
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
        Pattern eventpattern = Pattern.compile("event (.*?) /from (.*?) /to (.*)");
        Matcher matcher = eventpattern.matcher(this.line);
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
        Pattern eventpattern = Pattern.compile("find (.*?)");
        Matcher matcher = eventpattern.matcher(this.line);
        if (matcher.matches()) {
            String task = matcher.group(1);
            return items.find(task);
        } else {
            return UI.printMessage("Invalid find input");
        }
    }

}
