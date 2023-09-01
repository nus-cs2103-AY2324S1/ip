package phi;

public class Parser {
    private final TaskList tasks;
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public void handle(String input) {
        try {
            if (input.startsWith("list")) {
                tasks.printList(input);
            } else if (input.startsWith("mark")) {
                tasks.doTask(input);
            } else if (input.startsWith("unmark")) {
                tasks.undoTask(input);
            } else if (input.startsWith("todo")) {
                tasks.addTask(ToDo.newToDo(input));
            } else if (input.startsWith("deadline")) {
                tasks.addTask(Deadline.newDeadline(input));
            } else if (input.startsWith("event")) {
                tasks.addTask(Event.newEvent(input));
            } else if (input.startsWith("delete")) {
                tasks.deleteTask(input);
            } else {
                System.out.println("SIKE I can't process that! Try again or say \"bye\" to exit");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
