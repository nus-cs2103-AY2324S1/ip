import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

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

    public void newDeadline(String input) throws DukeMissingArgumentException, DukeInvalidDateFormatException {
        try {
            String[] msgArr = input.split("deadline ");
            msgArr = msgArr[1].split(" /by ");
            Task newTask = new Deadline(msgArr[0], LocalDate.parse(msgArr[1]));
            this.taskList.add(newTask);
            String msg = "Got it. I've added this task:\n"
                    + "\t" + newTask + "\n"
                    + "Now you have " + this.taskList.size() + " task"
                    + (taskList.size() <= 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        }
    }

    public void newEvent(String input) throws DukeMissingArgumentException, DukeInvalidDateFormatException {
        try {
            String[] msgArr = input.split("event ");
            msgArr = msgArr[1].split(" /from ");
            String description = msgArr[0];
            msgArr = msgArr[1].split(" /to ");
            Task newTask = new Event(description, LocalDate.parse(msgArr[0]), LocalDate.parse(msgArr[1]));
            taskList.add(newTask);
            String msg = "Got it. I've added this task:\n"
                    + "\t" + newTask + "\n"
                    + "Now you have " + this.taskList.size() + " task"
                    + (taskList.size() <= 1 ? "" : "s") + " in the list.\n";
            System.out.println(msg);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeMissingArgumentException();
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException();
        } catch (DukeEndDateBeforeStartDateException e) {
            System.out.println(e);
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

    public ArrayList<String> stringify() {
        ArrayList<String> stringList = new ArrayList<>();
        for (Task task : this.taskList) {
            stringList.add(task.toString());
        }
        return stringList;
    }
}
