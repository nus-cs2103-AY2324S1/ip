package duke.management;

import java.util.ArrayList;

import duke.DukeException;
import duke.note.Note;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Provides parse method to execute different commands.
 */
public class Parser {
    /**
     * Parses user input into the various commands.
     *
     * @param command User input.
     * @param tasks The list of all Tasks.
     * @return Bot's response in String.
     */
    public String parse(String command, TaskList tasks, NotesList notes) {
        try {
            String result;
            String[] commandArr = command.split(" ", 2);
            switch (commandArr[0]) {
            case "note":
                if (commandArr[1].isEmpty()) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY, "NOTE"));
                }
                Note note = new Note(commandArr[1]);
                notes.addNote(note);

                return "added new note: " + note.toString();
            case "notes":
                return notes.printNotes();
            case "find":
                if (commandArr[1].isEmpty()) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY, "FIND"));
                }
                ArrayList<Task> targetList = new ArrayList<>();
                String target = commandArr[1];
                for (Task task : tasks.getTasks()) {
                    if (task.isFound(target)) {
                        targetList.add(task);
                    }
                }
                result = "Here are the matching tasks in your list: \n";
                for (int i = 0; i < targetList.size(); i++) {
                    int index = i + 1;
                    Task t = targetList.get(i);
                    result += index + "." + t.toString() + "\n";
                }

                return result;
            case "list":
                return tasks.printTasks();
            case "mark":
                int mIndex = command.charAt(5) - 49;
                Task t = tasks.getTask(mIndex);
                t.markAsDone();
                return result = "Nice! I've marked this task as done:\n" + t.toString();
            case "unmark":
                int umIndex = command.charAt(7) - 49;
                Task umTask = tasks.getTask(umIndex);
                umTask.markAsUndone();
                return result = "Nice! I've marked this task as not done yet:\n" + umTask.toString();
            case "deadline":
                if (commandArr[1].isEmpty()) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY, "DEADLINE"));
                }
                String[] deadlineArr = commandArr[1].split("/by ", 2);
                Task deadline = new Deadline(deadlineArr[0], deadlineArr[1]);
                tasks.addTask(deadline);
                result = "added " + deadline.toString() + "\n";
                return result + "Now you have " + tasks.getSize() + " tasks in the list.";
            case "event":
                if (commandArr[1].isEmpty()) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY, "EVENT"));
                }
                String[] eventArr1 = commandArr[1].split(" /from ", 2);
                String[] eventArr2 = eventArr1[1].split(" /to ", 2);
                Task event = new Event(eventArr1[0], eventArr2[0], eventArr2[1]);
                tasks.addTask(event);
                result = "added " + event.toString() + "\n";
                return result + "Now you have " + tasks.getSize() + " tasks in the list.";
            case "todo":
                if (commandArr[1].isEmpty()) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY, "TODO"));
                }
                Task todo = new Todo(commandArr[1]);
                tasks.addTask(todo);
                result = "added " + todo.toString() + "\n";
                return result + "Now you have " + tasks.getSize() + " tasks in the list.";
            case "delete":
                int deleteIndex = command.charAt(7) - 49;
                System.out.println(deleteIndex);
                Task deleted = tasks.deleteTask(deleteIndex);
                result = "Noted. I've removed the task: \n" + deleted.toString() + "\n";
                return result + "Now you have " + tasks.getSize() + " tasks in the list.";
            case "bye":
                return Ui.bye();
            default:
                throw new DukeException(DukeException.UNKNOWN);
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
