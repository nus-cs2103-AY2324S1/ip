package tasks;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The TaskList class is a arraylist of Tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }


    /**
     * A function which returns a list of tasks which matches the given description
     * @param description The description of the task to search for
     * @return A list of tasks which loosely matches the description
     */
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



}
