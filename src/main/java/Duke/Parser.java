package Duke;


public class Parser {
    public Parser() {
    }
    public void parse(String s, TaskList taskList) throws DukeException {
        if (s.matches(".*\\bdelete\\b.*")) {
            String[] parts = s.split(" ");
            if (parts.length < 2) {
                throw new DukeException("Boy ah need to know which one u want delete eh.");
            }
            int number = Integer.parseInt(parts[1]);
            taskList.deleteTask(number);
            System.out.println(String.format("Got %d task in list boy", taskList.taskList.size()));
        } else if (s.equals("list")) {
            taskList.list();
        } else if (s.matches(".*\\bmark\\b.*")) {
            String[] parts = s.split(" ");
            int number = Integer.parseInt(parts[1]);
            taskList.mark(number);
        } else if (s.matches(".*\\bunmark\\b.*")) {
            String[] parts = s.split(" ");
            int number = Integer.parseInt(parts[1]);
            taskList.unmark(number);
        } else if (s.matches("(?i)^\\s*(todo|event|deadline)\\b.*")) {
            String[] parts = s.split(" ", 2);
            String TypeOfEvent = parts[0].toLowerCase();
            switch (TypeOfEvent) {
                case "todo":
                    if (parts.length < 2) {
                        throw new DukeException("Boy ah todo need description eh.");
                    }
                    String content = parts[1];
                    taskList.createToDo(content);
                    break;
                case "deadline":
                    if (parts.length < 2) {
                        throw new DukeException("Boy need to know when the deadline is eh.");
                    }
                    String[] part = parts[1].split("/by ");
                    if (part.length < 2) {
                        throw new DukeException("BOY AH The deadline need to write a /by time!!");
                    }
                    taskList.createDeadline(part[0], part[1]);
                    break;
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
                    taskList.createEvent(strarray[0], from, to);
                    break;
                default:
            }
        } else {
            throw new DukeException("Boy idk what you saying eh must tell me todo or deadline or event :(");
        }
    }
}
