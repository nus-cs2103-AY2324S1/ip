package Duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
public class TaskList {

    private ArrayList<Task> tasks;
    private Storage storage = new Storage(); //take tasks from Duke.Storage.

    public TaskList() { //tries to take any previously stored tasks
        try {
            this.tasks = this.storage.readTasks(); //will return empty ArrayList when nothing in file.
        } catch (FileNotFoundException e) {
            System.out.println("Looks like you do not have any previous tasks saved!");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
        Ui.addedTask(task);
    }

    public void deleteTask(int index) throws GmanException{
        try {
            if (index > this.getSize() || this.getSize() == 0) {
                throw new GmanException("HEYHEYHEY! There's nothing to delete here!");
            }
            Ui.removedTask(tasks.get(index));
            tasks.remove(index);

        } catch (GmanException e) {
            System.out.println(e.getMessage());
        }

    }
    public void unmark(int index) throws GmanException {
        //needed to let tasks remain private
        Task task = tasks.get(index);
        task.unmark();
        Ui.unmark(task.toString());
    }

    public void mark(int index) throws GmanException {
        Task task = tasks.get(index);
        task.mark();
        Ui.mark(task.toString());
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void write() throws IOException {
        Storage.writeTasks(tasks);
    }

}
