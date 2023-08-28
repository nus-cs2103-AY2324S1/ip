import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }


    public void getTasksOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getByDate().isEqual(date)) {
                    tasksOnDate.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getFromDate().isEqual(date.atStartOfDay())) {
                    tasksOnDate.add(task);
                }
            }
        }
        if (tasksOnDate.isEmpty()) {
            System.out.println("You have no tasks.\n");
        } else {
            System.out.println(tasksOnDate.size() +" tasks on " + date + ": ");
            for (int i = 0; i < tasksOnDate.size(); i++) {
                Task task = tasksOnDate.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
        String message = String.format("Got it. I've added this task:\n  "
                + task
                + "\nNow you have %s tasks in the list\n", taskList.size());
        System.out.println(message);
    }

    public void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks.\n");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task);
            }
        }
    }

    public List<Task> returnTaskList() {
        return taskList;
    }

    public void deleteTask(int i) throws TaskException{
        if (i > taskList.size()) {
            throw new TaskException("Invalid task index. Valid indexes from 1 to " + taskList.size());
        }
        Task taskToDekete = taskList.get(i - 1);
        taskList.remove(i - 1);
        System.out.println("Noted. I've removed this task:" + "\n  " + taskToDekete + "\n");
    }

    public void mark(int i) throws TaskException {
        if (i > taskList.size() || i <= 0) {
            throw new TaskException("Invalid task index. Valid indexes from 1 to " + taskList.size());
        }
        Task taskToMark = taskList.get(i - 1);
        if (taskToMark.isMarked) {
            System.out.println("Already marked!\n");
        } else {
            taskToMark.isMarked = true;
            System.out.println("Nice! I've marked this task as done:\n  " + taskToMark + "\n");
        }
    }

    public void unMark(int i) throws TaskException{
        if (i > taskList.size() || i <= 0) {
            throw new TaskException("Invalid task index. Valid indexes from 1 to " + taskList.size());
        }
        Task taskToMark = taskList.get(i - 1);
        if (taskToMark.isMarked) {
            taskToMark.isMarked = false;
            System.out.println("I've unmarked this task:\n  " + taskToMark + "\n");
        } else {
            System.out.println("Task already unmarked\n");
        }
    }
}
