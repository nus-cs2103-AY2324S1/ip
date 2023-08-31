package chatbot;

import chatbot.exceptions.InvalidTaskIndexException;
import chatbot.tasks.Task;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {
    private final ArrayList<Task> taskList;
    static final int maxNumberOfTasks = 100;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>(maxNumberOfTasks);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public String markAs(boolean isDone, int index) throws InvalidTaskIndexException {
        if (index < 1 || index > taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task task = taskList.get(index-1);
        task.markAs(isDone);
        return task.toString();
    }

    public String deleteTask(int index) throws InvalidTaskIndexException {
        if (index < 1 || index > taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task task = taskList.get(index-1);
        taskList.remove(task);
        return task.toString();
    }

    public String taskListToStrings() {
        StringBuilder sb = new StringBuilder();
        for (Task task: taskList) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public String listTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("\t%d.%s\n",
                    i+1,
                    task.toString()));
        }
        return sb.toString();
    }

    public String findTasks(String name) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        taskList.forEach(new Consumer<Task>() {
            @Override
            public void accept(Task t) {
                if (t.getName().contains(name)) {
                    matchedTasks.add(t);
                }
            }
        });
        if (matchedTasks.isEmpty()) {
            return "\tNo task in the list matches the query.";
        } else {
            return "\tHere are the matching tasks in your list:\n" + new TaskList(matchedTasks).listTasks();
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

}
