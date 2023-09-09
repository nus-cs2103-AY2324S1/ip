package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Task {
    private String task;
    private TaskStatus status;

    Boolean isNotSaved;
    private static ArrayList<Task> arr = new ArrayList<>();
    private static int counter = 0;
    public Task(String task, Boolean isNotSaved) {
        this.task = task;
        this.status = TaskStatus.NOT_DONE;
        this.isNotSaved = isNotSaved;

        if (!task.isEmpty()) {
            addTask(this.task);
        }
    }

    public Task() {

    }

    @Override
    public String toString() {
        return status.toString() + " " + this.task;
    }

    public void addTask(String task) {
        if (!task.equals("")){
                if (!task.isEmpty()) {
                    Duke.taskList.addTask(this);
                    counter++;
                }

        }
    }

    public static int getCounter() {
        return counter;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public void setStatus(TaskStatus taskStatus) {
        this.status = taskStatus;
    }

    public String getTask() {
        return this.task;
    }

    public LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
            // Split the input string into date and time parts
            String[] parts = dateTimeString.split(" ", 2);

            // Check if there are exactly two parts (date and time)
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid date/time format: " + dateTimeString);
            }

            String datePart = parts[0];
            String timePart = parts[1];

            // Define a formatter for the date part, e.g., "dd/MM/yyyy"
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            // Parse the date part into a LocalDate object
            LocalDate date = LocalDate.parse(datePart, dateFormatter);

            // Define a formatter for the time part, e.g., "HHmm"
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
            // Parse the time part into a LocalTime object
            LocalTime time = LocalTime.parse(timePart, timeFormatter);

            // Combine the date and time into a LocalDateTime object
            return LocalDateTime.of(date, time);
    }

    public void printList() {
        System.out.println(Ui.horizontalLine + "Here are the tasks in your list:");
        for (int i = 0; i < Duke.taskList.getTasks().size(); i++) {
            Task task = Duke.taskList.getTasks().get(i);
            System.out.println((i + 1) + ". " + task.toString());
        }
        System.out.println(Ui.horizontalLine);
    }


    public String getTaskType() {
        // Your logic to determine the task type based on the instance's actual class
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else if (this instanceof Event) {
            return "E";
        } else {
            return ""; // Handle unknown task types or add appropriate logic
        }
    }


    public void mark(int i) throws DukeException {
        if (i > Duke.taskList.getTasks().size() || i <= 0) {
            throw new DukeException(Ui.horizontalLine + "OOPS!!! Invalid number :(\n" + Ui.horizontalLine);
        }
        Task markTask = Duke.taskList.getTasks().get(i - 1);
        markTask.status = TaskStatus.DONE;

        // Update the task description in the file
        Storage.updateLineInFile(i, markTask.generateStr());

        System.out.println(Ui.horizontalLine + "Nice! I've marked this task as done:\n"
                + markTask.toString() + "\n" + Ui.horizontalLine);
    }

    public void mark() {
        this.status = TaskStatus.DONE;
    }

    public void unmark() {
        this.status = TaskStatus.NOT_DONE;
    }

    public void delete() {
        Duke.taskList.deleteTask(this); // Remove the task from the list
    }

    public boolean contains(String keyword) {
        return task.contains(keyword);
    }




    public void unmark(int i) throws DukeException {
        if (i > Duke.taskList.size() || i <= 0) {
            throw new DukeException(Ui.horizontalLine+ "OOPS!!! Invalid number :(\n" + Ui.horizontalLine);
        }
        Task unmarkTask = Duke.taskList.getTasks().get(i - 1);
        unmarkTask.status = TaskStatus.NOT_DONE;
        Storage.updateLineInFile(i, unmarkTask.generateStr());
        System.out.println(Ui.horizontalLine + "Ok, I've marked this task as not done yet:\n"
                + unmarkTask.toString() + "\n" + Ui.horizontalLine);
    }

    public void delete(int i) throws DukeException {
        if (i > Duke.taskList.size() || i <= 0) {
            throw new DukeException(Ui.horizontalLine+ "OOPS!!! Invalid number :(\n" + Ui.horizontalLine);
        }
        Task deleteTask = Duke.taskList.getTasks().get(i - 1);
        counter = counter - 1;
        Duke.taskList.deleteTask(i - 1);
        Storage.deleteLineFromFile(i);
        System.out.println(Ui.horizontalLine + "Noted. I've removed this task:\n" + deleteTask.toString()
        + "\n" + String.format("Now you have %d tasks in the list\n", counter) + Ui.horizontalLine );
    }



    public String generateStr() {
        return task;
    }

    public void saveToFile() {
        return;
    }
}
