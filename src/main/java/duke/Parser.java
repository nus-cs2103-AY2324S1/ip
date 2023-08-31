package duke;

public class Parser {

    private boolean isExit = false;

    public void parseAndExecute(String fullCommand, TaskList tasks, Ui ui, Storage storage) throws DukeException {
        validateInput(fullCommand);
        String[] tokens = fullCommand.split("\\s+");
        String command = tokens[0];
        switch (command) {
            case "bye":
                this.isExit = true;
                ui.printBye();
//                System.exit(0);
                break;
            case "list":
                tasks.printList();
                break;
            case "todo":
                Task todo = new Todo (fullCommand.substring(5));
                tasks.add(todo);
                ui.printAdded(todo, tasks.getSize());
                storage.save(tasks.getTasks());
                break;
            case "deadline":
                String[] arr = fullCommand.substring(9).split(" /by ");
                Task deadline = new Deadline(arr[0], arr[1]);
                tasks.add(deadline);
                ui.printAdded(deadline, tasks.getSize());
                storage.save(tasks.getTasks());
                break;
            case "event":
                String[] arr1 = fullCommand.split("\\s*/from\\s*|\\s*/to\\s*");
                Task event = new Event(arr1[0], arr1[1], arr1[2]);
                tasks.add(event);
                ui.printAdded(event, tasks.getSize());
                storage.save(tasks.getTasks());
                break;
            case "delete":
                int indexToDelete = Integer.parseInt(fullCommand.substring(7));
                Task taskToDelete = tasks.get(indexToDelete);
                tasks.delete(indexToDelete - 1);
                ui.printDeleted(taskToDelete, tasks.getSize());
                storage.save(tasks.getTasks());
                break;
            case "mark":
                int indexToMark = Integer.parseInt(fullCommand.substring(5));
                Task taskToMark = tasks.get(indexToMark);
                taskToMark.markAsDone();
                ui.printDone(taskToMark);
                storage.save(tasks.getTasks());
                break;
            case "unmark":
                int indexToUnmark = Integer.parseInt(fullCommand.substring(7));
                Task taskToUnmark = tasks.get(indexToUnmark);
                taskToUnmark.markAsUndone();
                ui.printUndone(taskToUnmark);
                storage.save(tasks.getTasks());
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void validateInput(String input) throws DukeException {
        if (input.equals("todo") || input.equals("deadline") || input.equals("event") || input.equals("mark")
                || input.equals("unmark") || input.equals("delete")) {
            throw new DukeException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
        }

        if (input.startsWith("deadline ") && !input.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline must contain /by.");
        }

        if (input.startsWith("event ") && !input.contains("/from") && !input.contains("/to")) {
            throw new DukeException("☹ OOPS!!! The description of a event must contain /from and /to.");
        }

        if (!input.startsWith("todo ") && !input.startsWith("deadline ") && !input.startsWith("event ")
                && !input.equals("list") && !input.equals("bye") && !input.startsWith("mark ")
                && !input.startsWith("unmark ") && !input.startsWith("delete ")) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
