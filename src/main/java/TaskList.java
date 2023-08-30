import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.ui = new Ui();
    }


    public void addTask(Task task) {
        this.tasks.add(task);
        this.ui.addTaskMessage(task, tasks.size());

    }
    public void deleteTask(int index) throws DukeException{
        try {
            Task deletedTask = tasks.get(index - 1);
            tasks.remove(index - 1);
            this.ui.deleteTaskMessage(deletedTask, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds! Please choose a valid index");
        }
    }

    public void listTask() {
        if (tasks.size() == 0) {
            this.ui.emptyTaskMessage();
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ". " + task);
        }
    }

    public void modifyTask(String input, int index) throws DukeException{
        try {
            Task task = tasks.get(index - 1);
            if (input.equals("mark")) {
                task.markAsDone();
                this.ui.markDoneMessage(task);
            } else {
                task.markAsUndone();
                this.ui.unmarkDoneMessage(task);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Index is out of bounds! Please choose a valid index");
        }

    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
