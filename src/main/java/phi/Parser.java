package phi;

public class Parser {
    private final TaskList tasks;
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public String handle(String input) {
        try {
            if (input.startsWith("list")) {
                return tasks.printList(input);
            } else if (input.startsWith("mark")) {
                return tasks.doTask(input);
            } else if (input.startsWith("unmark")) {
                return tasks.undoTask(input);
            } else if (input.startsWith("todo")) {
                return tasks.addTask(ToDo.newToDo(input));
            } else if (input.startsWith("deadline")) {
                return tasks.addTask(Deadline.newDeadline(input));
            } else if (input.startsWith("event")) {
                return tasks.addTask(Event.newEvent(input));
            } else if (input.startsWith("delete")) {
                return tasks.deleteTask(input);
            } else if (input.equals("help")) {
                return "Here's a list of commands:\n" +
                        "list [prints out a list of all tasks]\n" +
                        "mark X [marks task X in the list]\n" +
                        "unmark X [unmarks task X in the list]\n" +
                        "todo test [creates a to-do with body \"test\"]\n" +
                        "deadline test \\by yyyy-MM-dd [creates a corresponding deadline with body \"test\"]\n" +
                        "event test \\from yyyy-MM-dd \\to yyyy-MM-dd [creates a corresponding event with body \"test\"]\n" +
                        "bye [exits the program]";
            }
        }
        catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return("SIKE I can't process that! Try again or say \"bye\" to exit");
    }
}
