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
    public void parseMark(ItemList items) {
        Pattern markpattern = Pattern.compile("mark (\\d+).*");
        Matcher matcher = markpattern.matcher(this.line);
        if (matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            items.markDone(number);
        } else {
            UI.printMessage("Invalid mark input");
        }
    }

    /**
     * Execute unmark operation on the item list based on the line String
     */
    public void parseUnmark(ItemList items) {
        Pattern unmarkpattern = Pattern.compile("unmark (\\d+).*");
        Matcher matcher = unmarkpattern.matcher(this.line);
        if (matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            items.markUndone(number);
        } else {
            UI.printMessage("Invalid unmark input");
        }
    }

    /**
     * Execute delete operation on the item list based on the line String
     */
    public void parseDelete(ItemList items) {
        Pattern deletepattern = Pattern.compile("delete (\\d+).*");
        Matcher matcher = deletepattern.matcher(this.line);
        if (matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            items.delete(number);
        } else {
            UI.printMessage("Invalid delete input");
        }
    }

    /**
     * Execute adding Deadline operation on the item list based on the line String
     */
    public void parseDeadline(ItemList items) throws DeadlineException {
        Pattern deadlinepattern = Pattern.compile("deadline (.*?) /by (.*)");
        Matcher matcher = deadlinepattern.matcher(this.line);
        if (matcher.matches()) {
            String task = matcher.group(1);
            String by = matcher.group(2);

            items.addDeadline(task, by);
        } else {
            throw new DeadlineException();
        }
    }

    /**
     * Execute adding ToDo operation on the item list based on the line String
     */
    public void parseTodo(ItemList items) throws ToDoException {
        Pattern todopattern = Pattern.compile("todo (.*)");
        Matcher matcher = todopattern.matcher(line);
        if (matcher.matches()) {
            String task = matcher.group(1);
            items.addTodo(task);
        } else {
            throw new ToDoException();
        }
    }

    /**
     * Execute adding Event operation on the item list based on the line String
     */
    public void parseEvent(ItemList items) throws EventException {
        Pattern eventpattern = Pattern.compile("event (.*?) /from (.*?) /to (.*)");
        Matcher matcher = eventpattern.matcher(this.line);
        if (matcher.matches()) {
            String task = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            items.addEvent(task, from, to);
        } else {
            throw new EventException();
        }
    }

    public void parseFind(ItemList items) {
        Pattern eventpattern = Pattern.compile("find (.*?)");
        Matcher matcher = eventpattern.matcher(this.line);
        if (matcher.matches()) {
            String task = matcher.group(1);
            items.find(task);
        } else {
            UI.printMessage("Invalid find input");
        }
    }

}
