/**
 * DukeList stores tasks given by users when communicating with the main Duke chatbot.
 */
public class DukeList {
    /**The array used to store all tasks accordingly, up to a total of 100 tasks*/
    private Task[] tasks = new Task[100];
    /**The current number of tasks stored*/
    private int index;

    /**
     * Instantiates an instance of DukeList.
     */
    public DukeList() {
        this.index = 0;
    }

    /**
     * Helps to store user todo tasks into the list.
     *
     * @param task takes in the task given by the user.
     */
    public void store(String task) {
        this.tasks[index] = new ToDo(task);
        System.out.println("Added! You want to: " + task + "\n" +
                "Now you have " + (index + 1) + (index > 0 ? " tasks!" : " task!") + "\n" +
                "____________________________________________________________");
        index++;
    }

    /**
     * Helps to store user deadline tasks and deadlines into the list.
     *
     * @param task takes in the task given by the user.
     * @param by takes in the deadline of the deadline task.
     */
    public void store(String task, String by) {
        this.tasks[index] = new Deadline(task, by);
        System.out.println("Added! You want to: " + task + "\n" +
                "Now you have " + (index + 1) + (index > 0 ? " tasks!" : " task!") + "\n" +
                "____________________________________________________________");
        index++;
    }

    /**
     * Helps to store user event tasks and start / end dates into the list.
     *
     * @param task takes in the task given by the user.
     * @param start takes in the start of the deadline task.
     * @param end takes in the end date of the store task.
     */
    public void store(String task, String start, String end) {
        this.tasks[index] = new Event(task, start, end);
        System.out.println("Added! You want to: " + task + "\n" +
                "Now you have " + (index + 1) + (index > 0 ? " tasks!" : " task!") + "\n" +
                "____________________________________________________________");
        index++;
    }
    /**
     * Displays all contents of the list stored within an instance of DukeList.
     */
    public void display() {
        for (int i = 0; i < index; i++) {
            int curr = i + 1;
            System.out.println(curr + ". " + tasks[i].toString() +"\n");
        }

        System.out.println("____________________________________________________________");
    }

    /**
     * Marks the i-th task as done.
     * @param i takes in the index of the task to be set as done.
     */
    public void mark(int i) {
        tasks[i - 1].done();

        System.out.println("NICEEEEE. Good job on completing the task!\n" +
                tasks[i - 1].toString() + "\n" +
                "____________________________________________________________");
    }

    /**
     * Marks the i-th task as undone.
     * @param i takes in the index of the task to be set as undone.
     */
    public void unmark(int i) {
        tasks[i - 1].undo();

        System.out.println("Ohhh... uhm, okay, task undone!\n" +
                tasks[i - 1].toString() + "\n" +
                "____________________________________________________________");
    }
}
