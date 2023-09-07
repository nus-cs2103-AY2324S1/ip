package duke;

/**
 * Parses user command and executes tasks based on user commands.
 */
public class Parser {
    private String userCommand;

    /**
     * Constructs new <code>Parser</code> object.
     * @param command the command taken from user input.
     */
    public Parser(String command) {
        this.userCommand = command;
    }

    /**
     * Parses and responds to the user command.
     * @throws DukeException when user command is invalid.
     */
    public String parseAndRespond() throws DukeException {
        String[] splitted = userCommand.split(" ", 2);
        String response = "Huhhhhhhh??? (o_O) ? "
                + "Please use one of the command words: todo, event, deadline, list, mark, unmark, delete, bye";
        switch (splitted[0]) {
        case "list":
            response = Ui.listTasks();
            break;
        case "mark":
            if (splitted.length <= 1) {
                throw new DukeException("Please indicate which task to mark!");
            } else {
                int taskNo = Integer.parseInt(splitted[1]);
                response = TaskList.mark(taskNo);
            }
            break;
        case "unmark":
            if (splitted.length <= 1) {
                throw new DukeException("Please indicate which task to unmark!");
            } else {
                int taskNo = Integer.parseInt(splitted[1]);
                response = TaskList.unmark(taskNo);
            }
            break;
        case "find":
            if (splitted.length <= 1) {
                throw new DukeException("Please provide a term to search for! (⋟﹏⋞)");
            } else {
                String searchTerm = splitted[1];
                response = TaskList.searchFor(searchTerm);
            }
            break;
        case "todo":
            if (splitted.length <= 1) {
                throw new DukeException("Please provide a description for this todo! (⋟﹏⋞)");
            } else {
                String todoTask = splitted[1];
                Todo newTodo = new Todo(todoTask);
                response = TaskList.add(newTodo, "todo");
            }
            break;
        case "deadline":
            if (splitted.length <= 1) {
                throw new DukeException("Please provide a description for this deadline! (⋟﹏⋞)");
            } else {
                String[] deadTask = splitted[1].split("/by");
                if (deadTask.length == 1) {
                    throw new DukeException("Please provide a deadline! (⋟﹏⋞)");
                } else {
                    String deadDescription = deadTask[0];
                    String deadTime = deadTask[1];
                    deadTime = deadTime.trim();
                    Deadline deadlineTask = new Deadline(deadDescription, deadTime);
                    response = TaskList.add(deadlineTask, "deadline");
                }
            }
            break;
        case "event":
            if (splitted.length <= 1) {
                throw new DukeException("Please provide a description for this deadline! (⋟﹏⋞)");
            } else {
                String[] eventTask = splitted[1].split("/from");
                if (eventTask.length == 1) {
                    throw new DukeException("Please provide a start time! (⋟﹏⋞)");
                } else {
                    String eventDescription = eventTask[0];
                    String[] startEnd = eventTask[1].split("/to");
                    if (startEnd.length == 1) {
                        throw new DukeException("Please provide an end time! (⋟﹏⋞)");
                    } else {
                        String eventStart = startEnd[0];
                        String eventEnd = startEnd[1];
                        Event newEvent = new Event(eventDescription, eventStart, eventEnd);
                        response = TaskList.add(newEvent, "event");
                    }
                }
            }
            break;
        case "delete":
            if (splitted.length <= 1) {
                throw new DukeException("You do not have that much tasks! (⋟﹏⋞)");
            } else {
                int target = Integer.parseInt(splitted[1]);
                response = TaskList.delete(target);
            }
            break;
        default:
            response =  response;
        }

        return response;
    }
}
