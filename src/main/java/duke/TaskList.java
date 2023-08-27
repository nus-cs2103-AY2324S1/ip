package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<String> strings) {
        this.tasks = new ArrayList<>();
        Parser parser = new Parser(this);
        for (String s : strings) {
            parser.parseFromFile(s);
        }
    }
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public ArrayList<String> toStringInFile() {
        ArrayList<String> strings = new ArrayList<>();
        for (Task t : tasks) {
            strings.add(t.toStringInFile());
        }
        return strings;
    }
}
