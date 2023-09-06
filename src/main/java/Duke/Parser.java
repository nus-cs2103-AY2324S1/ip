package Duke;

/**
 * The Parser class represents the parser for the Duke program.
 * It provides methods to parse user input and execute commands.
 */
public class Parser {
    public Parser() {
    }

    /**
     * Parses the given user input and executes the corresponding command.
     * @param s the user input to parse
     * @param taskList the TaskList object to store and manage tasks
     * @throws DukeException if there is an error parsing the user input
     */
    public String parse(String s, TaskList taskList) throws DukeException {
        if (s.matches(".*\\bdelete\\b.*")) {
            String[] parts = s.split(" ");
            if (parts.length < 2) {
                throw new DukeException("Boy ah need to know which one u want delete eh.");
            }
            int number = Integer.parseInt(parts[1]);
            String string = taskList.deleteTask(number);
            string += String.format("\nGot %d task in list boy", taskList.taskList.size());
            return string;
        } else if (s.equals("list")) {
            return taskList.list();
        } else if (s.matches(".*\\bmark\\b.*")) {
            String[] parts = s.split(" ");
            int number = Integer.parseInt(parts[1]);
            return taskList.mark(number);
        } else if (s.matches(".*\\bunmark\\b.*")) {
            String[] parts = s.split(" ");
            int number = Integer.parseInt(parts[1]);
            return taskList.unmark(number);
        } else if (s.matches(".*\\bfind\\b.*")) {
            String[] parts = s.split(" ");
            if (parts.length < 2) {
                throw new DukeException("Boy ah need to know which one u want delete eh.");
            }
            String keyword = parts[1];
            return taskList.find(keyword);
        } else if (s.matches("(?i)^\\s*(todo|event|deadline)\\b.*")) {
            String[] parts = s.split(" ", 2);
            String TypeOfEvent = parts[0].toLowerCase();
            switch (TypeOfEvent) {
                case "todo":
                    if (parts.length < 2) {
                        throw new DukeException("Boy ah todo need description eh.");
                    }
                    String content = parts[1];
                    return taskList.createToDo(content);
                case "deadline":
                    if (parts.length < 2) {
                        throw new DukeException("Boy need to know when the deadline is eh.");
                    }
                    String[] part = parts[1].split("/by ");
                    if (part.length < 2) {
                        throw new DukeException("BOY AH The deadline need to write a /by time!!");
                    }
                    return taskList.createDeadline(part[0], part[1]);
                case "event":
                    if (parts.length < 2) {
                        throw new DukeException("Boy need to know the event description eh.");
                    }
                    String[] strarray = parts[1].split("/");
                    if (strarray.length < 3) {
                        throw new DukeException("BOY!! The event command need /from and /to times.");
                    }
                    String from = strarray[1].replace("from", "").trim();
                    String to = strarray[2].replace("to", "").trim();
                    return taskList.createEvent(strarray[0], from, to);
                default:
            }
        } else {
            throw new DukeException("Boy idk what you saying eh must tell me todo or deadline or event :(");
        }
        return "Boy idk what you saying eh must tell me todo or deadline or event :(";
    }
}
