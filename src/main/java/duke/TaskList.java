package duke;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskList {
    ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < tasks.size()) {
            Ui.showMessage("Noted. I've removed this task:");
            System.out.println(tasks.get(taskNumber).toString());
            tasks.remove(taskNumber);
        } else {
            throw new DukeException("☹ OOPS!!! The delete command needs to be followed by an existing task number.");
        }
    }

    public void markTask(int taskNumber) throws DukeException {
        // Assumption: "mark" is not allowed as a task name & you can mark already done tasks.
        // Issue: "mark" by itself crashes.
        if (taskNumber < tasks.size()) {
            tasks.get(taskNumber).completeTask();
            Ui.showMessage("Nice! I've marked this task as done:");
            Ui.showMessage(tasks.get(taskNumber).toString());
        } else {
            throw new DukeException("☹ OOPS!!! The mark command needs to be followed by an existing task number.");
        }
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public ArrayList<Task> findTasks(String keyword) {
        Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            Matcher matcher = pattern.matcher(task.description);
            if (matcher.find()) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
