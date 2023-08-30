import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    TaskList() {
        super();
    }

    TaskList(ArrayList<Task> tasks) {
        super(tasks);
    }
    
    Task markDone(int index) throws DukeException {
        if (index < 0 || index >= super.size()) {
            throw new DukeException("Invalid task index");
        }
        super.get(index).markDone();
        return super.get(index);
    }

    Task unmarkDone(int index) throws DukeException {
        if (index < 0 || index >= super.size()) {
            throw new DukeException("Invalid task index");
        }
        super.get(index).unmarkDone();
        return super.get(index);
    }
}
