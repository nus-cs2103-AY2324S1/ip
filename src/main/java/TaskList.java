import java.lang.reflect.Array;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void newTodo(String input) throws DukeMissingArgumentException {
        try {
            String[] msgArr = input.split("todo ");
            Task newTask = new Todo(msgArr[1]);
            this.taskList.add(newTask);
            String msg = "Got it. I've added this task:\n"
                    + "\t" + newTask + "\n"
                    + "Now you have " + this.taskList.size() + " task"
                    + (taskList.size() <= 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void newDeadline(String input) throws DukeMissingArgumentException {
        try {
            String[] msgArr = input.split("deadline ");             // {"", "return book /by Sunday"}
            msgArr = msgArr[1].split(" /by ");                      // {"return book", "Sunday"}
            Task newTask = new Deadline(msgArr[0], msgArr[1]);
            this.taskList.add(newTask);
            String msg = "Got it. I've added this task:\n"
                    + "\t" + newTask + "\n"
                    + "Now you have " + this.taskList.size() + " task"
                    + (taskList.size() <= 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void newEvent(String input) throws DukeMissingArgumentException {
        try {
            String[] msgArr = input.split("event ");       // "event project meeting /from Mon 2pm /to 4pm"
            msgArr = msgArr[1].split(" /from ");           // {"", "project meeting /from Mon 2pm /to 4pm"}
            String description = msgArr[0];                // {"project meeting", "Mon 2pm /to 4pm"}
            msgArr = msgArr[1].split(" /to ");             // {"Mon 2pm", "4pm"}
            Task newTask = new Event(description, msgArr[0], msgArr[1]);
            taskList.add(newTask);
            String msg = "Got it. I've added this task:\n"
                    + "\t" + newTask + "\n"
                    + "Now you have " + this.taskList.size() + " task"
                    + (taskList.size() <= 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }
    public void list() {
        String msg = "";
        int num = 1;
        for (Task task : taskList) {
            if (task != null) {
                msg = msg + num + ": " + task + "\n";
                num ++;
            } else {
                break;
            }
        }
        if (taskList.size() == 0) {
            msg = "You have no task currently.";
        }
        System.out.println(msg);
    }

    public void markAsDone(String[] msgArr) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(msgArr[1]);
            if (i - 1 >= this.taskList.size()) {
                throw new DukeNoTaskFoundException();
            }
            this.taskList.get(i - 1).markAsDone();
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void markAsUndone(String[] msgArr) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(msgArr[1]);
            if (i - 1 >= this.taskList.size()) {
                throw new DukeNoTaskFoundException();
            }
            this.taskList.get(i - 1).markAsUndone();
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }

    public void delete(String[] msgArr) throws DukeNoTaskFoundException,
            DukeInvalidArgumentException, DukeMissingArgumentException {
        try {
            int i = Integer.parseInt(msgArr[1]);
            if (i - 1 >= this.taskList.size()) {
                throw new DukeNoTaskFoundException();
            }
            Task removedTask = this.taskList.get(i - 1);
            this.taskList.remove(i - 1);
            String msg = "Noted. I've removed this task:\n"
                    + "\t" + removedTask + "\n"
                    + "Now you have " + this.taskList.size() + " task"
                    + (this.taskList.size() <= 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentException();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        }
    }
}
