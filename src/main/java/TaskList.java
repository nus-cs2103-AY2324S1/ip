import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> taskList = new ArrayList<>();
    public TaskList() {}

    public void deleteTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskList.get(taskIndex));
        taskList.remove(taskIndex);
    }

    public void addEvent(String input) {
        System.out.println("Got it. I've added this task:");

        String[] list = input.split("/");
        String title = list[0].substring(6);
        String start = list[1].substring(5);
        String end = list[2].substring(3);

        Event event = new Event(title, start, end);
        System.out.println(event.toString());
        taskList.add(event);
    }

    public void addDeadline(String input) {
        System.out.println("Got it. I've added this task:");

        String[] list = input.split("/");
        String title = list[0].substring(9);
        String time = list[1].substring(3);

        Deadline deadline = new Deadline(title, time);
        System.out.println(deadline.toString());
        taskList.add(deadline);
    }

    public void addToDo(String input) throws DukeException {

        System.out.println("Got it. I've added this task:");
        ToDo toDo = new ToDo(input);
        System.out.println(toDo.toString());
        taskList.add(toDo);
    }

    // method to quickly load up tasks into the tasklist during loading
    public void addTask(Task task) {
        taskList.add(task);
    }

    public void list() {
        Duke.line();
        if (taskList.size() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                int index = i + 1;
                Task t = taskList.get(i);
                System.out.println(index + "." + t.toString());
            }
        }
        Duke.line();
    }

    public void mark(int taskIndex) {
        Task currTask = taskList.get(taskIndex);
        currTask.taskDone(true);
    }

    public void unmark(int taskIndex) {
        Task currTask = taskList.get(taskIndex);
        currTask.taskDone(false);
    }

    public BufferedWriter printStoreFormat(BufferedWriter writer) throws IOException {
        for (Task t : taskList) {
            writer.append(t.storeFormat()).append("\n");

        }
        return writer;
    }

    public int size() {
        return taskList.size();
    }
}
