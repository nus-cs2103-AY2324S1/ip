import java.util.ArrayList;

public class Task {
    private String task;
    private TaskStatus status;
    private static ArrayList<Task> arr = new ArrayList<>();
    private static int counter = 0;
    public Task(String task) {
        this.task = task;
        this.status = TaskStatus.NOT_DONE;
        addTask(task);
    }

    @Override
    public String toString() {
        return status.toString() + " " + this.task;
    }

    public void addTask(String task) {
        if (!task.equals("")){
            arr.add(this);
            counter = counter + 1;
        }
    }

    public static int getCounter() {
        return counter;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public String getTask() {
        return this.task;
    }

    public void printList() {
        int count = 0;
        System.out.println(Duke.horizontalLine + "Here are the tasks in your list:");
        for (Task task : arr) {
            count++;
            System.out.println( count+". " + task.toString());
        }
        System.out.println(Duke.horizontalLine);
    }

    public void mark(int i) throws DukeException {
        if (i > arr.size() || i <= 0) {
            throw new DukeException(Duke.horizontalLine+ "OOPS!!! Invalid number :(\n" + Duke.horizontalLine);
        }
        Task markTask = arr.get(i - 1);
        markTask.status = TaskStatus.DONE;
        System.out.println(Duke.horizontalLine + "Nice! I've marked this task as done:\n"
        + markTask.toString() + "\n" + Duke.horizontalLine);
    }

    public void unmark(int i) throws DukeException {
        if (i > arr.size() || i <= 0) {
            throw new DukeException(Duke.horizontalLine+ "OOPS!!! Invalid number :(\n" + Duke.horizontalLine);
        }
        Task unmarkTask = arr.get(i - 1);
        unmarkTask.status = TaskStatus.NOT_DONE;
        System.out.println(Duke.horizontalLine + "Ok, I've marked this task as not done yet:\n"
                + unmarkTask.toString() + "\n" + Duke.horizontalLine);
    }

    public void delete(int i) throws DukeException {
        if (i > arr.size() || i <= 0) {
            throw new DukeException(Duke.horizontalLine+ "OOPS!!! Invalid number :(\n" + Duke.horizontalLine);
        }
        Task deleteTask = arr.get(i - 1);
        counter = counter - 1;
        arr.remove(i - 1);
        System.out.println(Duke.horizontalLine + "Noted. I've removed this task:\n" + deleteTask.toString()
        + "\n" + String.format("Now you have %d tasks in the list\n", counter) + Duke.horizontalLine );
    }
}
