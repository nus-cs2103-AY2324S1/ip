import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private String line;
    public Parser(String line) {
        this.line = line;
    }

    public String getCommand() {
        String command = this.line.split(" ")[0];
        return command.toUpperCase();
    }

    public void parseMark(ItemList items) {
        Pattern markpattern = Pattern.compile("mark (\\d+).*");
        Matcher matcher = markpattern.matcher(this.line);
        if(matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            items.markDone(number);
        } else {
            UI.printMessage("Invalid mark input");
        }
    }

    public void parseUnmark(ItemList items) {
        Pattern unmarkpattern = Pattern.compile("unmark (\\d+).*");
        Matcher matcher = unmarkpattern.matcher(this.line);
        if(matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            items.markUndone(number);
        } else {
            UI.printMessage("Invalid unmark input");
        }
    }

    public void parseDelete(ItemList items) {
        Pattern deletepattern = Pattern.compile("delete (\\d+).*");
        Matcher matcher = deletepattern.matcher(this.line);
        if(matcher.matches()) {
            String digitString = matcher.group(1);
            int number = Integer.parseInt(digitString);
            items.delete(number);
        } else {
            UI.printMessage("Invalid delete input");
        }
    }

    public void parseDeadline(ItemList items) throws DeadlineException {
        Pattern deadlinepattern = Pattern.compile( "deadline (.*?) /by (.*)");
        Matcher matcher = deadlinepattern.matcher(this.line);
        if(matcher.matches()) {
            String task = matcher.group(1);
            String by = matcher.group(2);

            items.addDeadline(task, by);
        } else {
            throw new DeadlineException();
        }
    }

    public void parseTodo(ItemList items) throws ToDoException {
        Pattern todopattern = Pattern.compile( "todo (.*)");
        Matcher matcher = todopattern.matcher(line);
        if(matcher.matches()) {
            String task = matcher.group(1);
            items.addTodo(task);
        } else {
            throw new ToDoException();
        }
    }

    public void parseEvent(ItemList items) throws EventException {
        Pattern eventpattern = Pattern.compile( "event (.*?) /from (.*?) /to (.*)");
        Matcher matcher = eventpattern.matcher(this.line);
        if(matcher.matches()) {
            String task = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            items.addEvent(task, from, to);
        } else {
            throw new EventException();
        }
    }

}
