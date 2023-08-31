package duke.task;
import java.util.ArrayList;
import duke.DukeException;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList (ArrayList<Task> list) {
        this.list = list;
    }
    public TaskList() {
        this.list = null;
    }

    public Task addTask(String type, String description) throws DukeException {
        if (type.equals("todo")) {
            return ToDo.addTodo(description, list);
        } else if (type.equals("deadline")) {
            return Deadline.addDeadline(description, list);
        } else if (type.equals("event")) {
            return Event.addEvent(description, list);
        } else {
            throw new DukeException("That's not a valid input!");
        }
    }

    public Task deleteTask(String pos) throws DukeException {
        if (pos.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please indicate which item you wish to delete");
        }
        int amt = Integer.parseInt(pos.strip()) - 1;
        if (amt >= list.size()) throw new DukeException("☹ OOPS!!! That's not a valid item!");

        Task temp = list.get(amt);
        list.remove(amt);

        return temp;

    }

    public Task markTask(String type, String pos) throws DukeException {
        if (pos.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Please indicate which item you wish to mark");
        }
        int amt = Integer.parseInt(pos.strip()) - 1;
        if (amt >= list.size()) throw new DukeException("☹ OOPS!!! That's not a valid item!");
        Task newTask = list.get(amt);

        if (type.equals("mark")) {
            newTask.mark();

        } else if (type.equals("unmark")) {
            newTask.unMark();
        }
        return newTask;
    }

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> resultList = new ArrayList<>();
        Task[] temp = list.toArray(new Task[0]);

        for (Task task : temp) {
            String description = task.getDescription().stripTrailing().toLowerCase();
            if (description.contains(keyword.stripTrailing().toLowerCase())) {
                resultList.add(task);
            }
        }

        return resultList;
    }

    public Task getTask(int pos) {
        return list.get(pos);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
