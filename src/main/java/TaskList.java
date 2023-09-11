// contains the task list e.g., it has operations to add/delete tasks in the list
import java.time.LocalDateTime;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // getters
    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    private ArrayList getAllTasks() {
        return this.taskList;
    }

    // setters
    public Task markTask(int index) {
        Task task = taskList.get(index);
        task.setDone(true);
        return task;
    }
    public Task unmarkTask(int index) {
        Task task = taskList.get(index);
        task.setDone(false);
        return task;
    }

    // add

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void addTodo(String task) {
        ToDo newTask = new ToDo(task);
        this.addTask(newTask);

    }
//    public void addDeadline(String task, LocalDateTime by) {
//        Deadline newTask = new Deadline(task, by);
//        taskList.add(newTask);
//        System.out.printf("Got it. I've added this task:\n%s\n", newTask.toString());
//        System.out.printf("Now you have %d tasks in the list. \n", taskList.size());
//    }
//    public void addEvent(String task, LocalDateTime from, LocalDateTime to) {
//        Event newTask = new Event(task, from, to);
//        taskList.add(newTask);
//        System.out.printf("Got it. I've added this task:\n%s\n", newTask.toString());
//        System.out.printf("Now you have %d tasks in the list. \n", taskList.size());
//    }

    public Task deleteTask(int index) {
        Task removedTask = taskList.get(index);
        taskList.remove(index);
        return removedTask;
    }

}
