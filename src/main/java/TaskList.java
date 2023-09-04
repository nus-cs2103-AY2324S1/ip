import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Locale;

public class TaskList {
    private ArrayList<Task> taskArrayList;

    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    public TaskList(ArrayList taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public Task getTask(int index) {
        return this.taskArrayList.get(index);
    }

    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    /*
    * deletes and returns deleted task - like pop
    * */
    public Task deleteTask(int index) {
        Task x = this.getTask(index);
        this.taskArrayList.remove(index);
        return x;
    }

    public int getSize() {
        return this.taskArrayList.size();
    }

    public String toString() {
        String botOutput = "";
        for (int i = 1; i <= this.taskArrayList.size(); i++) {
            botOutput = botOutput + i + "." + " " + this.taskArrayList.get(i-1) + "\n    ";
        }
        return botOutput;
    }

    public TaskList dueWithinWeek() {
        TaskList listWeek = new TaskList();
        for (Task t: this.taskArrayList) {
            LocalDateTime taskDueDate = t.getUrgencyDate();
            LocalDateTime currentDate = LocalDateTime.now();
            long daysDifference = ChronoUnit.DAYS.between(currentDate, taskDueDate);
            // Check if the task's due date is within one week of the current date (7 days)
            if (daysDifference >= 0 && daysDifference <= 7) {
                listWeek.addTask(t);
            }
        }
        return listWeek;
    }

    public TaskList dueWithinMonth() {
        TaskList monthWeek = new TaskList();
        for (Task t: this.taskArrayList) {
            LocalDateTime taskDueDate = t.getUrgencyDate();
            LocalDateTime currentDate = LocalDateTime.now();
            long daysDifference = ChronoUnit.DAYS.between(currentDate, taskDueDate);
            // Check if the task's due date is within one week of the current date (7 days)
            if (daysDifference >= 0 && daysDifference <= 30) {
                monthWeek.addTask(t);
            }
        }
        return monthWeek;
    }
}
