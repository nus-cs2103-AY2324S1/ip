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
            if (arguments.isEmpty()) {
                throw new SanaException("OOPS!!! Incomplete task description.\nMake sure you follow the format " +
                        "'deadline [name of task] /by [deadline]'");
            }

            int lastDescId = arguments.indexOf('/');
            if (lastDescId == -1 || arguments.length() < lastDescId + 4 || arguments.substring(lastDescId + 4).isBlank()) {
                throw new SanaException("OOPS!! The deadline cannot be empty.\nMake sure you follow the format " +
                        "'deadline [name of task] /by [deadline]'");
            }

            String desc = arguments.substring(0, lastDescId - 1);
            String by = arguments.substring(lastDescId + 4);

            try {
                LocalDate byDate = LocalDate.parse(by);
                Task newDeadline = new Deadline(desc, byDate);

                storage.save("/Users/ariellacallista/Desktop",
                        "/Users/ariellacallista/Desktop/SanaTasks.txt", newDeadline);
                System.out.println("Got it. I've added this task:\n" + newDeadline + "\n"
                        + "Now you have " + tasks.size() + (tasks.size() <= 1 ? " task" : " tasks")
                        + " in the list\n");
            } catch (DateTimeParseException e) {
                ui.showError(e.getMessage());
            }

        case "event":

        }
    }
}
