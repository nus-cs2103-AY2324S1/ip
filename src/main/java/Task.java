import java.util.ArrayList;

public class Task {
    private String task;
    private Boolean done;
    private String doneSymbol;
    private static ArrayList<Task> arr = new ArrayList<>();
    private static int counter = 0;
    public Task(String task) {
        this.task = task;
        this.done = false;
        addTask(task);
    }

    public String toString() {
        return this.doneSymbol + " " + this.task;
    }

    public void addTask(String task) {
        if (!task.equals("")){
            arr.add(this);
            this.doneSymbol = "[ ]";
            counter = counter + 1;
        }
    }

    public static int getCounter() {
        return counter;
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

    private void setDone() {
        this.doneSymbol = "[X]";
    }

    private void setNotDone() {
        this.doneSymbol = "[ ]";
    }

    public void mark(int i) throws DukeException {
        if (i > arr.size() || i <= 0) {
            throw new DukeException(Duke.horizontalLine+ "☹ OOPS!!! Invalid number :(\n" + Duke.horizontalLine);
        }
        Task markTask = arr.get(i - 1);
        markTask.setDone();
        System.out.println(Duke.horizontalLine + "Nice! I've marked this task as done:\n"
        + markTask.toString() + "\n" + Duke.horizontalLine);
    }

    public void unmark(int i) throws DukeException {
        if (i > arr.size() || i <= 0) {
            throw new DukeException(Duke.horizontalLine+ "☹ OOPS!!! Invalid number :(\n" + Duke.horizontalLine);
        }
        Task unmarkTask = arr.get(i - 1);
        unmarkTask.setNotDone();
        System.out.println(Duke.horizontalLine + "Ok, I've marked this task as not done yet:\n"
                + unmarkTask.toString() + "\n" + Duke.horizontalLine);
    }

    public void delete(int i) throws DukeException {
        if (i > arr.size() || i <= 0) {
            throw new DukeException(Duke.horizontalLine+ "☹ OOPS!!! Invalid number :(\n" + Duke.horizontalLine);
        }
        Task deleteTask = arr.get(i - 1);
        counter = counter - 1;
        arr.remove(i - 1);
        System.out.println(Duke.horizontalLine + "Noted. I've removed this task:\n" + deleteTask.toString()
        + "\n" + String.format("Now you have %d tasks in the list\n", counter) + Duke.horizontalLine );
    }
}
