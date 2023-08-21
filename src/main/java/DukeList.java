public class DukeList {
    private Task[] listOfTexts;
    private int count;
    public DukeList() {
        this.listOfTexts = new Task[100];
        this.count = 0;
    }

    /**
     * Function to store text written by user into a list.
     * @param text
     */
    public void addItem(String text) {
        this.listOfTexts[count] = new Task(text);
        this.count++;
        System.out.printf("added: %s%n", text);
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
