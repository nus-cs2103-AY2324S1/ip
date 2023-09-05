import java.util.ArrayList;

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
                    Duke.allTasks.add(this);
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

    public void printList() {
        int count = 0;
        System.out.println(Duke.horizontalLine + "Here are the tasks in your list:");
        for (Task task : Duke.allTasks) {
            count++;
            System.out.println( count+". " + task.toString());
        }
        System.out.println(Duke.horizontalLine);
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
        if (i > Duke.allTasks.size() || i <= 0) {
            throw new DukeException(Duke.horizontalLine + "OOPS!!! Invalid number :(\n" + Duke.horizontalLine);
        }
        Task markTask = Duke.allTasks.get(i - 1);
        markTask.status = TaskStatus.DONE;

        // Update the task description in the file
        Duke.updateLineInFile(i, markTask.generateStr());

        System.out.println(Duke.horizontalLine + "Nice! I've marked this task as done:\n"
                + markTask.toString() + "\n" + Duke.horizontalLine);
    }

    public void mark() {
        this.status = TaskStatus.DONE;
    }

    public void unmark() {
        this.status = TaskStatus.NOT_DONE;
    }

    public void delete() {
        Duke.allTasks.remove(this); // Remove the task from the list
    }



    public void unmark(int i) throws DukeException {
        if (i > Duke.allTasks.size() || i <= 0) {
            throw new DukeException(Duke.horizontalLine+ "OOPS!!! Invalid number :(\n" + Duke.horizontalLine);
        }
        Task unmarkTask = Duke.allTasks.get(i - 1);
        unmarkTask.status = TaskStatus.NOT_DONE;
        Duke.updateLineInFile(i, unmarkTask.generateStr());
        System.out.println(Duke.horizontalLine + "Ok, I've marked this task as not done yet:\n"
                + unmarkTask.toString() + "\n" + Duke.horizontalLine);
    }

    public void delete(int i) throws DukeException {
        if (i > Duke.allTasks.size() || i <= 0) {
            throw new DukeException(Duke.horizontalLine+ "OOPS!!! Invalid number :(\n" + Duke.horizontalLine);
        }
        Task deleteTask = Duke.allTasks.get(i - 1);
        counter = counter - 1;
        Duke.allTasks.remove(i - 1);
        Duke.deleteLineFromFile(i);
        System.out.println(Duke.horizontalLine + "Noted. I've removed this task:\n" + deleteTask.toString()
        + "\n" + String.format("Now you have %d tasks in the list\n", counter) + Duke.horizontalLine );
    }

    public String generateStr() {
        return task;
    }

    public void saveToFile() {
        return;
    }
}
