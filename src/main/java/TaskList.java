import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    private Ui msg;

    public TaskList() {

        this.tasks = new ArrayList<>();
        this.msg = new Ui();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            tasks.remove(index);
            msg.deleteTaskMessage(task, tasks.size());
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.markTask();
        msg.markAsDoneMessage(task);
    }

    public void unMarkTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.unMarkTask();
        msg.unMarkAsDoneMessage(task);
    }

    public void listTasks() {
        int len = tasks.size();
        for (int i = 0; i < len; i++) {
            Task curr = tasks.get(i);
            System.out.println((i + 1) + "." + curr.toString());
        }
    }

    public void addToDoTask(String description) {
        Todo task = new Todo(description);
        tasks.add(task);
        msg.addTaskMessage(task, tasks.size());
    }

    public void addDeadlineTask(String description, LocalDateTime by) {
        Deadline task = new Deadline(description, by);
        tasks.add(task);
        msg.addTaskMessage(task, tasks.size());
    }

    public void addEventTask(String description, String from, String to) {
        Event task = new Event(description, from, to);
        tasks.add(task);
        msg.addTaskMessage(task, tasks.size());
    }

    public Task get(int pos) {
        return tasks.get(pos);
    }

    public int size(){
        return tasks.size();
    }

}
