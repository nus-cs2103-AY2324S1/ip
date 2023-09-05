/**
 * Parses user commands and performs actions based on the parsed command.
 */
public class Parser {

    private enum Commands {
        invalid, todo, deadline, event, mark, unmark, list, delete, bye;
    }

    private TaskList taskList;
    private Ui ui;

    /**
     * Initializes a parser with a user interface and a task list.
     *
     * @param ui       The user interface for displaying messages.
     * @param taskList The task list for managing tasks.
     */
    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Parses a user command and performs the corresponding action.
     *
     * @param command The user's input command.
     * @return True if the command was successfully parsed and executed, false otherwise.
     */
    public boolean parseCommand(String command) {
        Commands cmd = Commands.invalid;
        for (Commands c : Commands.values()) {
            if (command.startsWith(c.toString())) {
                cmd = c;
            }
        }
        if (cmd.equals(Commands.bye)) {
            ui.byeMessage();
            return false;
        } else if (cmd.equals(Commands.list)) {
            ui.showTasks(taskList);
            return true;
        } else if (cmd.equals(Commands.mark)) {
            try {
                int id = Integer.parseInt(command.split(" ")[1]);
                taskList.markTaskAsDone(id - 1);
                ui.markedMessage(taskList.getTask(id - 1));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("To mark a task you need to include the index");
            }
        } else if (cmd.equals(Commands.unmark)) {
            try {
                int id = Integer.parseInt(command.split(" ")[1]);
                taskList.markTaskAsUnDone(id - 1);
                ui.unmarkedMessage(taskList.getTask(id - 1));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("To unmark a task you need to include the index");
            }
        } else if (cmd.equals(Commands.todo)) {
            try {
                String description = command.substring(5);
                Todo todo = new Todo(description);
                taskList.addTask(todo);
                ui.addTaskMessage(todo, taskList.numOfTasks());
                //writer.write(todo.getDescription().concat("\n"));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (cmd.equals(Commands.deadline)) {
            try {
                int index = command.indexOf("/");
                String description = command.substring(9, index - 1);
                String by = command.substring(index + 3);
                Deadline deadline = new Deadline(description, by.trim());
                taskList.addTask(deadline);
                ui.addTaskMessage(deadline, taskList.numOfTasks());
                //writer.write(deadline.getDescription().concat("\n"));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (cmd.equals(Commands.event)) {
            try {
                int indexOfFrom = command.indexOf("/");
                String description = command.substring( 6, indexOfFrom - 1);
                String duration = command.substring(indexOfFrom + 4);
                int indexOfTo = duration.indexOf("/");
                String from = duration.substring(1, indexOfTo - 1);
                String to = duration.substring(indexOfTo + 3);
                Event event = new Event(description, from, to);
                taskList.addTask(event);
                ui.addTaskMessage(event, taskList.numOfTasks());
                //writer.write(event.getDescription().concat("\n"));
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else if (cmd.equals(Commands.delete)) {
            try {
                int id = Integer.parseInt(command.split(" ")[1]);
                taskList.deleteTask(id - 1);
                return true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("To delete a task you have to include the index");
            }
        } else {
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return false;
    }
}
