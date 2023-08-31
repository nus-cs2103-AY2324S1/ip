package Duke;


import java.util.ArrayList;
public class TaskList {
    ArrayList<SingleTask> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<SingleTask> taskList) {
        this.taskList = taskList;
    }

    public void deleteTask(int TaskNumber) throws DukeException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new DukeException("Boy ah cannot delete a task you dont have eh.");
        }
        SingleTask task = this.taskList.get(TaskNumber - 1);
        System.out.println(task.remove());
        this.taskList.remove(TaskNumber - 1);
    }

    public void list() {
        if (this.taskList.size() == 0) {
            System.out.println("No tasks here ah boy");
        } else {
            System.out.println("Here are your tasks ah boy:");
            for (int i = 0; i < this.taskList.size(); i++) {
                SingleTask task = this.taskList.get(i);
                System.out.println((i + 1) + task.listString());
            }
        }
    }

    public void mark(int TaskNumber) throws DukeException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new DukeException("Boy ah cannot mark a task you dont have eh.");
        }
        SingleTask task = this.taskList.get(TaskNumber - 1);
        task.mark();
    }

    public void unmark(int TaskNumber) throws DukeException {
        if (TaskNumber - 1 >=  this.taskList.size()) {
            throw new DukeException("Boy ah cannot unmark a task you dont have eh.");
        }
        SingleTask task = this.taskList.get(TaskNumber - 1);
        task.unmark();
    }

    public void createToDo(String content) {
        SingleTask taskT = new ToDo(content);
        this.taskList.add(taskT);
        System.out.println(taskT.toString());
        System.out.println(String.format("Got %d task in list boy", taskList.size()));
    }

    public void createDeadline(String description, String deadline) {
        SingleTask taskD = new Deadline(description, deadline);
        taskList.add(taskD);
        System.out.println(taskD.toString());
        System.out.println(String.format("Got %d task in list boy", taskList.size()));
    }

    public void createEvent(String description, String from, String to) {
        SingleTask taskE = new Event(description, from, to);
        taskList.add(taskE);
        System.out.println(taskE.toString());
        System.out.println(String.format("Got %d task in list boy", taskList.size()));
    }
}
