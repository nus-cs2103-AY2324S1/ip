package JavAI;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public Task get(int index) throws JavAIException {

        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new JavAIException("☹ OOPS!!! The task number does not exist.Please enter valid task number.");
        }
    }

    public ArrayList<Task> getTasksAsArrayList() {
        return this.tasks;
    }



}
