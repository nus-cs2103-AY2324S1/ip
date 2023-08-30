import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    public AddCommand(String cmd, String arguments) {
        super(cmd, arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SanaException {
        switch (cmd) {
        case "todo":
            if (arguments.isEmpty()) {
                throw new SanaException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task newTodo = new Todo(arguments);
            //tasks.add(newTodo);

            storage.save("/Users/ariellacallista/Desktop",
                    "/Users/ariellacallista/Desktop/SanaTasks.txt", newTodo);
            //saveTasks("/Users/ariellacallista/Desktop", "/Users/ariellacallista/Desktop/SanaTasks.txt", newTodo);

            System.out.println("Got it. I've added this task:\n" + newTodo + "\n"
                    + "Now you have " + tasks.size() + (tasks.size() <= 1 ? " task" : " tasks")
                    + " in the list");

        case "deadline":

        case "event":

        }
    }
}
