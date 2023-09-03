/**
 * contains the task list, and methods to modify the tasks in the list
 */
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;

    //case where there are no existing tasks in storage
    public TaskList(ArrayList<Task> list, Ui ui) {
        this.list = list;
        this.ui = ui;
    }

    public boolean isValidTaskID(int taskID) {
        if (taskID > this.list.size() - 1 || taskID < 0) {
            ui.showInvalidTaskID();
            return false;
        }
        return true;
    }

    public void listTasks() {
        if (list.size() == 0) {
            System.out.println("(o´ω`o)ﾉ You have no upcoming tasks!\n" + Ui.line);
        } else {
            String result = "";
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                Task task = list.get(i);
                result += index + ". " + task.toString() + "\n";
            }
            System.out.println("(⇀‸↼‶)⊃━☆ﾟ.*･｡ﾟ Here are your tasks for the day:");
            System.out.println(result + Ui.line);
        }
    }
    public void addTask(Task task, Storage storage, Ui ui) throws IOException {
        this.list.add(task);
        storage.updateFile(this.list);
        ui.newTaskAdded(task);
        ui.getNumberOfTasks(this.getListSize());
    }

    //might remove returning of boolean!! see first
    public boolean deleteTask(int taskID, Storage storage, Ui ui) throws IOException {
        if (!isValidTaskID(taskID)) {
            return false;
        } else {
            Task toRemove = list.get(taskID);
            list.remove(taskID);
            storage.updateFile(this.list);
            System.out.println("ଘ(੭ˊᵕˋ)੭ Ok! I've removed this task:");
            System.out.println(toRemove);
            ui.getNumberOfTasks(getListSize());
            return true;
        }
    }

    public void markTask(int taskID, Storage storage) throws IOException {
        this.list.get(taskID).mark();
        storage.updateFile(this.list);
    }

    public void unmarkTask(int taskID, Storage storage) throws IOException {
        this.list.get(taskID).unmark();
        storage.updateFile(this.list);
    }
    public int getListSize() {
        return this.list.size();
    }
}
