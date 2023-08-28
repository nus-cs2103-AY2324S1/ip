import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "./data/state.txt";
    private static final String DATETIME_INPUT_FORMAT = "yyyy-MM-dd HHmm";
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);

    public TaskList() {
        loadState();
    }

    public void add(Task task) {
        this.tasks.add(task);
        this.printTaskAddedMessages(task);
    }

    public void remove(int index) throws InvalidCommandException {
        if (!isTaskValid(index)) {
            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
        }
        Task task = this.tasks.remove(index);
        this.printTaskDeletedMessage(task);
    }

    public void printContents() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + "." + this.tasks.get(i));
        }
    }

    public void printTasksOn(LocalDate date) {
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).isOnDate(date)) {
                System.out.println((i + 1) + "." + this.tasks.get(i));
            }
        }
    }

    public void mark(int index) throws InvalidCommandException {
        if (!isTaskValid(index)) {
            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
        }
        Task task = this.tasks.get(index);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void unmark(int index) throws InvalidCommandException {
        if (!isTaskValid(index)) {
            throw new InvalidCommandException("☹ OOPS!!! The task index in invalid");
        }
        Task task = this.tasks.get(index);
        task.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void saveState() {
        try {
            FileWriter fw = new FileWriter(TaskList.FILE_PATH);
            for (int i = 0; i < this.tasks.size(); i++) {
                fw.write(this.tasks.get(i).toSaveStateString() + "\n");
            }
            fw.close();
            System.out.println("Sucessfully saved state");
        } catch (IOException e) {
            System.out.println("Failed to save state");
        }
    }

    private void loadState() {
        try {
            File f = new File(TaskList.FILE_PATH);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] taskArray = s.nextLine().split(" / ");
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
        } catch (FileNotFoundException e) {
            System.out.println("File to save state cannot be found");
        }
    }

    private boolean isTaskValid(int index) {
        return index >= 0 && index < this.tasks.size();
    }

    private void printTaskAddedMessages(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        this.printTaskCount();
    }

    private void printTaskDeletedMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        this.printTaskCount();
    }

    private void printTaskCount() {
        int tasksCount = this.tasks.size();
        System.out.println("Now you have " + tasksCount + (tasksCount == 1 ? " task" : " tasks") + " in the list.");
    }
}
