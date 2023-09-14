package benben;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<? extends Task> list) {
        tasks = new ArrayList<Task>();
        for (int i = 0; i < list.size(); i++) {
            Task t = (Task) list.get(i);
            tasks.add(t);
        }
    }

    public void add(Task t) {

        tasks.add(t);
    }

    public void remove(int i) {
        tasks.remove(i);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TaskList)) {
            return false;
        }
        @SuppressWarnings("unchecked")
        TaskList list = (TaskList) o;
         return this.tasks.equals(list.tasks);
    }

    public TaskList sortByDescription() {
        ArrayList<Task> sorted = (ArrayList<Task>) this.tasks.clone();
        sorted.sort(new DescriptionComparator());
        return new TaskList(sorted);
    }

    public TaskList sortTodo() {
        ArrayList<Task> sorted = (ArrayList<Task>) this.tasks.clone();
        sorted.removeIf(t -> !(t instanceof Todo));
        sorted.sort(new DescriptionComparator());
        return new TaskList(sorted);
    }


    public TaskList sortDeadline() {
        ArrayList<Deadline> sorted = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Deadline) {
                @SuppressWarnings("unchecked")
                Deadline temp = (Deadline) tasks.get(i);
                sorted.add(temp);
            }
        }
        sorted.sort(new DeadlineComparator());
        return new TaskList(sorted);
    }


    public TaskList sortEvent() {
        ArrayList<Event> sorted = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Event) {
                @SuppressWarnings("unchecked")
                Event temp = (Event) tasks.get(i);
                sorted.add(temp);
            }
        }
        sorted.sort(new EventComparator());
        return new TaskList(sorted);
    }
}
