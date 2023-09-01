package Tasks;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class TaskManager {
    ArrayList<Task> storage;

    private Task StringToTask(String taskInStringForm) {
        String[] taskData = taskInStringForm.split(" \\| "); // assuming the description and all that doesnt have |
        // splits into type, mark or not, description, from, to
        switch (taskData[0]) {
            case "T":
                Task todo = new Todo(taskData[2]);
                if (taskData[1] == "1") {
                    todo.mark();
                }
                return todo;
            case "D":
                Task deadline = new Deadline(taskData[2], LocalDateTime.parse(taskData[3]));
                if (taskData[1] == "1") {
                    deadline.mark();
                }
                return deadline;
            case "E":
                Task event = new Event(taskData[2], taskData[3], taskData[4]);
                if (taskData[1] == "1") {
                    event.mark();
                }
                return event;
            default:
                return null;
        }
    }

    public TaskManager(File taskListData) {
        try {
            storage = new ArrayList<>(); // everytime you initialize, you start with a new storage
            Scanner fileSc = new Scanner(taskListData);
            while (fileSc.hasNextLine()) { // if no task, storage is empty
                Task task = StringToTask(fileSc.nextLine());
                if (task != null) {
                    storage.add(task);
                }
            }
            fileSc.close();
        } catch (FileNotFoundException e) {
            System.out.println("storage manager did not load file properly");
        }
    }

    public ArrayList<Task> getTasks() {
        return this.storage;
    }

    public Task getTask(int idx) {
        return this.storage.get(idx);
    }

    public void addTask(Task task) {
        this.storage.add(task);
    }

    public Task deleteIndex(int idx) {
        return this.storage.remove(idx);
    }

    public int getTasksSize() {
        return this.storage.size();
    }

    public void mark(int idxMark) {
        storage.get(idxMark).mark();
    }

    public void unmark(int idxMark) {
        storage.get(idxMark).unmark();
    }
}
