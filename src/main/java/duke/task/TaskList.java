package duke.task;

import duke.DukeException;
import duke.Ui;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.showTaskAddedMessage(task, tasks.size());
    }

    public void deleteTask(int index, Ui ui) {
        Task removedTask = tasks.remove(index - 1);
        ui.showTaskRemoveMessage(removedTask, tasks.size());
    }

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }
        return tasks.get(index);
    }

    /**
     * Finds tasks that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for.
     * @param ui      The Ui instance to display the results.
     */
    public void findTasks(String keyword, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            // No matching tasks found
            ui.showNoMatchingTasksMessage();
        } else {
            // Matching tasks found, display them to the user
            ui.showFindTaskMessage();
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(i + 1 + ". " + matchingTasks.get(i));
            }
        }
    }
    public int getTotalTasks() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void listTask() throws DukeException {
        if (this.getTotalTasks() == 0) {
            System.out.println("List is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int item = 0; item < this.getTotalTasks(); item++)  {
                System.out.println(item + 1 + ". " + this.tasks.get(item));
            }
        }
    }

}
