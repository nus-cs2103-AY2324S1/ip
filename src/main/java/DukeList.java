import java.util.ArrayList;

/**
 * DukeList stores tasks given by users when communicating with the main Duke chatbot.
 */
public class DukeList {
    /**The ArrayList used to store all tasks accordingly, up to a total of 100 tasks*/
    private ArrayList<Task> tasks = new ArrayList<>();


    /**
     * Helps to store user todo tasks into the list.
     *
     * @param task takes in the task given by the user.
     */
    public void store(String task) {
        Task todo = new ToDo(task);
        tasks.add(todo);
        int index = tasks.size();
        System.out.println("Added! You want to: " + task + "\n" +
                "Now you have " + index + (index > 1 ? " tasks!" : " task!") + "\n" +
                "____________________________________________________________");
    }

    /**
     * Helps to store user deadline tasks and deadlines into the list.
     *
     * @param task takes in the task given by the user.
     * @param by takes in the deadline of the deadline task.
     */
    public void store(String task, String by) {
        Task todo = new Deadline(task, by);
        tasks.add(todo);
        int index = tasks.size();
        System.out.println("Added! You want to: " + task + "\n" +
                "Now you have " + index + (index > 1 ? " tasks!" : " task!") + "\n" +
                "____________________________________________________________");
    }

    /**
     * Helps to store user event tasks and start / end dates into the list.
     *
     * @param task takes in the task given by the user.
     * @param start takes in the start of the deadline task.
     * @param end takes in the end date of the store task.
     */
    public void store(String task, String start, String end) {
        Task todo = new Event(task, start, end);
        tasks.add(todo);
        int index = tasks.size();
        System.out.println("Added! You want to: " + task + "\n" +
                "Now you have " + index + (index > 1 ? " tasks!" : " task!") + "\n" +
                "____________________________________________________________");
    }
    /**
     * Displays all contents of the list stored within an instance of DukeList.
     */
    public void display() {
        for (int i = 0; i < tasks.size(); i++) {
            int curr = i + 1;
            System.out.println(curr + ". " + tasks.get(i).toString() +"\n");
        }

        System.out.println("____________________________________________________________");
    }

    /**
     * Marks the i-th task as done.
     * @param i takes in the index of the task to be set as done.
     */
    public void mark(int i) {
        tasks.get(i - 1).done();

        System.out.println("NICEEEEE. Good job on completing the task!\n" +
                tasks.get(i - 1).toString() + "\n" +
                "____________________________________________________________");
    }

    /**
     * Marks the i-th task as undone.
     * @param i takes in the index of the task to be set as undone.
     */
    public void unmark(int i) {
        tasks.get(i - 1).undo();

        System.out.println("Ohhh... uhm, okay, task undone!\n" +
                tasks.get(i - 1).toString() + "\n" +
                "____________________________________________________________");
    }

    /**
     * Removes the i-th.
     * @param i takes in the index of the task to be removed.
     */
    public void delete(int i) {
        Task task = tasks.remove(i - 1);
        int index = tasks.size();

        System.out.println("Guess you don't want to do that anymore: " + task.toString() + "\n" +
                "Now you have " + index + (index > 1 ? " tasks!" : " task!") + "\n" +
                "____________________________________________________________");
    }
}
