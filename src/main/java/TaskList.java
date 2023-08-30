import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public TaskList(ArrayList<String> storedData) {
        tasks = new ArrayList<>();
        loadState(storedData);
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getTaskCount() {
        return this.tasks.size();
    }

    public Task add(Task task) {
        this.tasks.add(task);
        return task;
    }

    public Task remove(int index) throws InvalidCommandException {
        if (!isTaskValid(index)) {
            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
        }
        Task task = this.tasks.remove(index);
        return task;
    }

    public void printContents() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + "." + this.tasks.get(i));
        }
    }

    public ArrayList<Task> getTasksOn(LocalDate date) {
        ArrayList<Task> tasksOn = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).isOnDate(date)) {
                tasksOn.add(this.tasks.get(i));
            }
        }
        return tasksOn;
    }

    public Task mark(int index) throws InvalidCommandException {
        if (!isTaskValid(index)) {
            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
        }
        Task task = this.tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmark(int index) throws InvalidCommandException {
        if (!isTaskValid(index)) {
            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
        }
        Task task = this.tasks.get(index);
        task.markAsUndone();
        return task;
    }

    public void saveState(Storage storage) {
        try {
            ArrayList<String> stringRepresentation = new ArrayList<>();
            for (int i = 0; i < this.tasks.size(); i++) {
                stringRepresentation.add(this.tasks.get(i).toSaveStateString());
            }
            storage.saveData(stringRepresentation);
            System.out.println("Sucessfully saved state");
        } catch (IOException e) {
            System.out.println("Failed to save state");
        }
    }

    private void loadState(ArrayList<String> storedData) {
        for (int i = 0; i < storedData.size(); i++) {
            String[] taskArray = storedData.get(i).split(" / ");
            Task task;

            if (taskArray[0].equals(Command.TODO.getCommand())) {
                task = new ToDo(taskArray[2]);
            } else if (taskArray[0].equals(Command.DEADLINE.getCommand())) {
                task = new Deadline(taskArray[2], LocalDateTime.parse(taskArray[3], dateTimeInputFormatter));
            } else {
                task = new Event(taskArray[2], LocalDateTime.parse(taskArray[3], dateTimeInputFormatter),
                        LocalDateTime.parse(taskArray[4], dateTimeInputFormatter));
            }

            if (taskArray[1].equals("1")) {
                task.markAsDone();
            }
            this.tasks.add(task);
        }
        System.out.println("Successfully loaded saved state");
    }

    private boolean isTaskValid(int index) {
        return index >= 0 && index < this.tasks.size();
    }
}
