/**
 * DukeList stores tasks given by users when communicating with the main Duke chatbot.
 */
public class DukeList {
    /**The array used to store all tasks accordingly, up to a total of 100 tasks*/
    private String[] tasks = new String[100];
    /**The current number of tasks stored*/
    private int index;

    /**
     * Instantiates an instance of DukeList.
     */
    public DukeList() {
        this.index = 0;
    }

    /**
     * Helps to store user inputs into the list variable and show its
     * contents if the "list" command is inputted.
     *
     * @param task takes in the task given by the user.
     */
    public void store(String task) {
        this.tasks[index] = task;
        System.out.println("Added! You want to: " + task + "\n" +
                "____________________________________________________________");
        index++;
    }
    /**
     * Displays all contents of the list stored within an instance of DukeList.
     */
    public void display() {
        for (int i = 0; i < index; i++) {
            int curr = i + 1;
            System.out.println(curr + ". " + tasks[i] +"\n");
        }

        System.out.println("____________________________________________________________");
    }
}
