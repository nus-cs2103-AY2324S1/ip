package tasks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tasks.Task;


public class TaskList {

    public TaskList() {
        tasks = new ArrayList<>();
    }

    @JsonIgnore
    public List<Task> findTasks(String description) {
        List<Task> output;
        output = tasks.stream().filter(task -> task.getDescription().contains(description)).collect(
            Collectors.toList());

        return output;
    }

    @JsonIgnore
    public int size() {
        return tasks.size();
    }


    @JsonIgnore
    public boolean isEmpty() {
        return tasks.isEmpty();
    }


    @JsonIgnore
    public void add(Task task) {
        tasks.add(task);
    }

    @JsonIgnore
    public Task get(int index) {
        return tasks.get(index);
    }

    @JsonIgnore
    public void remove(int index) {
        tasks.remove(index);
    }


    private ArrayList<Task> tasks;

}
