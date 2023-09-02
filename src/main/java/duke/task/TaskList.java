package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Task list of user's task
 *
 */
public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds task into list
     *
     * @param task user's task
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Delete task from list
     *
     * @param i index of item in list
     */
    public void delete(int i) {
        list.remove(i);
    }

    /**
     * Mark task from list as done
     *
     * @param i index of item in list
     */
    public void mark(int i) {
        list.get(i).mark();
    }

    /**
     * Mark task from list as not done
     *
     * @param i index of item in list
     */
    public void unmark(int i) {
        list.get(i).unmark();
    }

    /**
     * Returns the list
     *
     * @return list
     */
    public List<Task> list() {
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TaskList) {
            TaskList temp = (TaskList) o;
            List<Task> check = temp.list();

            if (list.size() != check.size()) {
                return false;
            }

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(check.get(i))) {
                    continue;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Find tasks with matching keyword
     *
     * @param keyword
     * @return
     */
    public Task[] findTask(String keyword) {
        ArrayList<Task> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).taskName();

            if (name.contains(keyword)) {
                result.add(list.get(i));
            }
        }
        return result.toArray(new Task[0]);
    }
}
