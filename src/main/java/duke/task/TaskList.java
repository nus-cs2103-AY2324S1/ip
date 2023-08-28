package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> lines) throws DukeException {
        this.tasks = new ArrayList<>();
        for (String line : lines) {
            String[] inputs = line.split(" \\| ");
            String taskType = inputs[0];
            Boolean isDone = inputs[1].equals("1");
            String description = inputs[2];
            switch (taskType) {
                case "T":
                    this.add(new Todo(description, isDone));
                    break;
                case "D":
                    String by = inputs[3];
                    this.add(new Deadline(description, by, isDone));
                    break;
                case "E":
                    String from = inputs[3];
                    String to = inputs[4];
                    this.add(new Event(description, from, to, isDone));
                    break;
                default:
                    break;
            }
        }
    }

    public List<String> toFileString() {
        List<String> lines = new ArrayList<>();
        for (Task task : this.tasks) {
            lines.add(task.toFileString());
        }
        return lines;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }

    public void markAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    public void unmarkAsDone(int index) {
        this.tasks.get(index).unmarkAsDone();
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }
}
