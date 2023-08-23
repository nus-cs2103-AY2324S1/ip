public class DukeList {
    private Task[] listOfTexts;
    private int count;
    public DukeList() {
        this.listOfTexts = new Task[100];
        this.count = 0;
    }

    /**
     * Function to store text written by user into a list.
     * @param task
     */
    public void addItem(Task task) {
        this.listOfTexts[count] = task;
        this.count++;
        System.out.printf("Got it. I've added this task:%n %s%nNow you have %d tasks in the list.",
                this.listOfTexts[count - 1].printTask(), this.count);
    }

    /**
     * Function to display list back to user when requested.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.count; i++) {
            System.out.printf("%d.%s%n", i+1, this.listOfTexts[i].printTask());
        }
    }

    /**
     * Function to toggle the done status of the Task.
     * @param id
     * @param keyword
     */
    public void toggleDone(int id, String keyword) {
        this.listOfTexts[id - 1].toggleDone(keyword);
    }
}
