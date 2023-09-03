import java.time.LocalDateTime;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks;
        boolean running = true;
        TaskStorage taskStorage = new TaskStorage();

        // Load tasks from TaskStorage
        try {
            tasks = taskStorage.loadExistingTasks();
        } catch (DukeException e) {
            System.out.printf("[!] %s\n", e.getMessage());
            return;
        }

        Ui ui = new Ui("Pong");
        ui.init();

        while (running) {
            try {
                Parser parser = ui.getParsedInput();
                running = Duke.handleInput(parser, tasks, ui);
                taskStorage.storeTasks(tasks);
            } catch (DukeException e) {
                ui.printException(e);
            }
        }

        ui.exit();
    }

    private static boolean handleInput(Parser parser, ArrayList<Task> tasks, Ui ui) throws DukeException {
        switch (parser.getCommand()) {
        case "bye":
            return false;
        case "list":
            ui.listTasks(tasks);
            break;
        case "mark":
            int index = parser.getArgAsInt() - 1;
            tasks.get(index).markDone();
            ui.markTask(tasks.get(index));
            break;
        case "unmark":
            index = parser.getArgAsInt() - 1;
            tasks.get(index).unmarkDone();
            ui.unmarkTask(tasks.get(index));
            break;
        case "delete":
            index = parser.getArgAsInt() - 1;
            Task task = tasks.remove(index - 1);
            ui.deleteTask(task);
            break;
        case "todo":
            String todoName = parser.getArg();
            if (todoName == null || todoName.equals("")) {
                throw new DukeException("Todo name cannot be empty");
            }
            tasks.add(new Todo(todoName));
            ui.addTask(tasks.get(tasks.size() - 1));
            break;
        case "deadline":
            String deadlineName = parser.getArg();
            if (deadlineName == null || deadlineName.equals("")) {
                throw new DukeException("Deadline name cannot be empty");
            }

            LocalDateTime deadline;
            deadline = parser.getOptArgAsDateTime("by");

            if (deadline == null) {
                throw new DukeException("Use /by to specify deadline date");
            }

            tasks.add(new Deadline(deadlineName, deadline));
            ui.addTask(tasks.get(tasks.size() - 1));
            break;
        case "event":
            String eventName = parser.getArg();
            if (eventName == null || eventName.equals("")) {
                throw new DukeException("Deadline name cannot be empty");
            }

            LocalDateTime from, to;
            from = parser.getOptArgAsDateTime("from");
            to = parser.getOptArgAsDateTime("to");

            if (from == null || to == null) {
                throw new DukeException("Use /from and /to to specify event duration");
            }

            tasks.add(new Event(eventName, from, to));
            ui.addTask(tasks.get(tasks.size() - 1));
            break;
        }

        return true;
    }
}
