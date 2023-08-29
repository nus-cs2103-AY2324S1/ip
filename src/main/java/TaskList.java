import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {

    public ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void deleteItem(String input) throws DukeException {
        int index = this.getIndex(input) - 1;
        this.checkOutOfBounds(index);
        if (index <= this.tasks.size()) {
            Task deletedTask = this.tasks.remove(index);
            Ui.deleteItem(this.tasks.size(), deletedTask);
        }
    }

    public void addItem(Parser.taskType task, String input) throws DukeException {
        String[] splitSentence = input.split(" /");
        String taskName = getRestOfSentence(splitSentence[0]).strip();
        Task newTask;

        switch(task) {
            case DEADLINE:
                this.checkEmpty(taskName, "deadline");
                if (splitSentence.length < 2 || !splitSentence[1].contains("by")) {
                    throw new DukeException("\u2639 OOPS!!! Please indicate a deadline with the /by keyword");
                }
                String date = splitSentence[1].replaceAll("by", "").strip();
                newTask = new Deadline(taskName, date);
                break;
            case EVENT:
                this.checkEmpty(taskName, "event");
                if (splitSentence.length < 3 || (!splitSentence[1].contains("from") && !splitSentence[2].contains("to"))) {
                    throw new DukeException("\u2639 OOPS!!! Please indicate a duration for the event with the /from and /to keywords");
                }
                String fromDatetime = splitSentence[1].replaceAll("from", "from:");
                String toDatetime = splitSentence[2].replaceAll("to", "to:");
                taskName = String.format("%s (%s %s)", taskName, fromDatetime, toDatetime);
                newTask = new Event(taskName);
                break;
            case TODO:
                this.checkEmpty(taskName, "todo");
                newTask = new Todo(taskName);
                break;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        this.tasks.add(newTask);

        String sayWord = "Got it. I've added this task:\n"
                + newTask
                + "\nNow you have "
                + this.tasks.size()
                + " tasks in the list.";
        Ui.printWrapped(sayWord);
    }

    public void modifyStatus(Parser.modifyStatus status, String input) throws DukeException {
        int index = getIndex(input) - 1;
        this.checkOutOfBounds(index);
        switch(status) {
            case MARK:
                this.tasks.get(index).markTask();
                printMarkedOrUnmarked(index, "Nice! I've marked this task as done:");
                break;
            case UNMARK:
                this.tasks.get(index).unMarkTask();
                printMarkedOrUnmarked(index, "OK, I've marked this task as not done yet:");
                break;
            default:
                throw new DukeException("\u2639 OOPS!!! I'm sorry, but an error occurred when modifying your task :-(");
        }
    }

    public void printMarkedOrUnmarked(int index, String input) {
        if (index < this.tasks.size()) {
            String markedTask = String.format("%s\n%s", input, this.tasks.get(index));
            Ui.printWrapped(markedTask);
        }
    }

    public Task getTaskWithIndex(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getIndex(String input) {
        String[] parts = input.split(" ");

        if (parts.length >= 2) {
            String secondPart = parts[1];
            return Integer.parseInt(secondPart);
        }
        return -1;
    }

    public void checkOutOfBounds(int index) throws DukeException {
        if (index > this.tasks.size() - 1) {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but the index you have inputted is out of bounds :-(");
        }
    }

    public static void checkEmpty(String input, String taskName) throws DukeException {
        if (input.equals("")) {
            throw new DukeException(String.format("\u2639 OOPS!!! The description of a %s cannot be empty.", taskName));
        }
    }

    public static String getRestOfSentence(String input) {
        String[] parts = input.split(" ");
        StringBuilder result = new StringBuilder();

        if (parts.length > 1) {
            for (int i = 1; i < parts.length - 1; i++) {
                result.append(parts[i]).append(" ");
            }
            result.append(parts[parts.length - 1]);
        }
        return result.toString();
    }
}
