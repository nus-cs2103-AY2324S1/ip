package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void readTasksFromStorage(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isValidIndex(int taskIndex) {
        return taskIndex > 0 && taskIndex <= tasks.size();
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        Ui.printLines("Got it. I've added this task:",
                "\t " + newTask,
                "Now you have " + getSize() + " tasks in your list. Good luck!");

    }

    public void deleteTask(int taskIndex) {
        Task deletedTask = tasks.get(taskIndex - 1);

        tasks.remove(taskIndex - 1);

        Ui.printLines("Noted. I've removed this task:",
                deletedTask.toString(),
                "Now you have " + this.tasks.size() + " tasks in your list. Good luck!");
    }

    public void markTask(int taskIndex) {
        Task task = this.tasks.get(taskIndex - 1);
        task.markAsDone();

        Ui.printLines("Nice job! I've marked this task as done:",
                "\t " + task);
    }

    public void unmarkTask(int taskIndex) {
        Task task = this.tasks.get(taskIndex - 1);
        task.markAsNotDone();

        Ui.printLines("What happened? I've marked this task as not done yet:",
                "\t " + task);
    }

    /**
     * Prints all tasks that has a certain keyword.
     *
     * @param keyword Keyword the task needs to have
     */
    public void printTasksByKeyword(String keyword) {
        int index = 1;
        List<String> linesToPrint = new ArrayList<>();
        linesToPrint.add("Here are the matching tasks in your list:");

        for (Task task : tasks) {
            if (task.hasKeyword(keyword)) {
                linesToPrint.add(index + "." + task);
                index++;
            }
        }

        if (index == 1) {
            linesToPrint.set(0, "Cannot find any tasks with this keyword");
        }

        String lines[] = new String[linesToPrint.size()];

        for (int i = 0; i < linesToPrint.size(); i++) {
            lines[i] = linesToPrint.get(i);
        }

        Ui.printLines(lines);
    }

    @Override
    public String toString() {
        String tasksList;

        if (getSize() > 0) {
            tasksList = "Here are the tasks in your list:\n";

            for (int taskIndex = 1; taskIndex <= tasks.size(); taskIndex++) {
                tasksList += ("\t " + taskIndex + "." + getTask(taskIndex) + "\n");
            }

            tasksList += ("\t Keep up the good work!");
        } else {
            tasksList = "You currently have no tasks :)";
        }

        return tasksList;
    }


}
