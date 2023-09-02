import java.util.ArrayList;

/**
 * Tasklist containing the tasks added by the user.
 */
public class TaskList {
    private final ArrayList<Task> TASKLST;

    /**
     * Constructor for an instance of an empty tasklist.
     */
    public TaskList() {
        this.TASKLST = new ArrayList<>();
    }

    /**
     * Constructor for a tasklist with a string of tasks.
     * @param fileData ArrayList of strings with each string being a task to be added into the tasklist
     * @throws DukeException exceptions that will be encountered when trying to add tasks
     */
    public TaskList(ArrayList<String> fileData) throws DukeException {
        String[] fields;
        this.TASKLST = new ArrayList<>();
        for (String info : fileData) {
            fields = info.split(" \\| ");
            Task tempT;
            switch (fields[0].trim()) {
            case "T":
                if(fields[1].isEmpty()){
                    throw new NoTaskException("Error! Cannot add an empty todo!");
                }
                tempT = new ToDo(fields[1]);
                TASKLST.add(tempT);
                break;
            case "D":
                tempT = new Deadline(fields[2], fields[3]);
                TASKLST.add(tempT);
                break;
            case "E":
                String[] time;
                time = fields[3].split("->");
                tempT = new Event(fields[2], time[0], time[1]);
                TASKLST.add(tempT);
                break;
            }
        }
    }

    /**
     * Marks a task within the tasklist as complete.
     * @param taskNumber task number to be marked
     * @param ui instance of user interface
     * @throws InvalidTaskNumberException Error when given task number exceeds the number of tasks in the list
     */
    public void markTask(int taskNumber, Ui ui) throws InvalidTaskNumberException {
        if (taskNumber > TASKLST.size()) {
            throw new InvalidTaskNumberException("Error! Task Number given is outside range of current list size of "
                    + TASKLST.size());
        }
        TASKLST.get(taskNumber - 1).markAsDone();
        ui.showMarked(TASKLST.get(taskNumber - 1));
    }

    /**
     * Displays the list of tasks stored in the tasklist.
     */
    public void displayTasks() {
        for (Task task : TASKLST) {
            System.out.println(task.toString());
        }
    }

    /**
     * Unmarks a task with the given task number in the tasklist.
     * @param taskNumber task number to be unmarked
     * @param ui instance of user interface
     * @throws InvalidTaskNumberException exception when the task number given is outside the count of tasklist
     */
    public void unmarkTask(int taskNumber, Ui ui) throws InvalidTaskNumberException {
        if (taskNumber > TASKLST.size()) {
            throw new InvalidTaskNumberException("Error! Task Number given is outside range of current list size of "
                    + TASKLST.size());
        } else {
            TASKLST.get(taskNumber - 1).unmarkAsDone();
            ui.showUnmarked(TASKLST.get(taskNumber - 1));
        }
    }

    /**
     * Adds given task to the tasklist.
     * @param task Task to be added to the list
     * @param ui instance of user interface
     */
    public void addTask(Task task, Ui ui) {
        TASKLST.add(task);
        ui.showTaskAdded(task, TASKLST.size());
    }

    /**
     * Deletes a task from the tasklist.
     * @param taskNumber Task number of task to be deleted
     * @param ui instance of user interface
     * @throws InvalidTaskNumberException Exception when given task number is outside range of tasks in the list
     */
    public void deleteTask(int taskNumber, Ui ui) throws InvalidTaskNumberException {
        if (taskNumber > TASKLST.size()) {
            throw new InvalidTaskNumberException("Error! Task Number given is outside range of current list size of "
                    + TASKLST.size());
        } else {
            Task temp = TASKLST.remove(taskNumber - 1);
            ui.showComplete("Noted. I've removed this task:"
                    + temp
                    + "Now you have " + this.TASKLST.size() + " task(s) in the list");
        }
    }

    /**
     * Returns tasks in the tasklist in format to be written into a file.
     * @return ArrayList of formatted strings to be written
     */
    public ArrayList<String> toWriteFormat() {
        ArrayList<String> tasks = new ArrayList<>();
        for(Task task : TASKLST) {
            tasks.add(task.toSaveFormat());
        }
        return tasks;
    }
}
