import java.io.FileWriter;
import java.util.ArrayList;

public class DukeList {

    private ArrayList<Task> listOfTexts;
    
    public DukeList() {
        this.listOfTexts = new ArrayList<>();
    }

    /**
     * Function to store text written by user into a list.
     * @param task
     */
    public void addItem(Task task) {
        this.listOfTexts.add(task);
        System.out.printf("Got it. I've added this task:%n %s%nNow you have %d tasks in the list.%n",
                task.printTask(), this.listOfTexts.size());
    }

    public void addItemFromStorage(Task task, String isDone) {
        this.listOfTexts.add(task);
        if (isDone.equals("1 ")) {
            task.toggleDoneFromStorage("mark");
        }
    }

    /**
     * Function to display list back to user when requested.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.listOfTexts.size(); i++) {
            System.out.printf("%d.%s%n", i+1, this.listOfTexts.get(i).printTask());
        }
    }

    /**
     * Function to toggle the done status of the Task.
     * @param id
     * @param keyword
     */
    public void toggleDone(int id, String keyword) {
        this.listOfTexts.get(id - 1).toggleDone(keyword);
    }

    /**
     * Function to remove item from list.
     * @param id
     */
    public void removeItem(int id) {
        Task task = this.listOfTexts.remove(id - 1);
        System.out.printf("Noted. I've removed this task:%n %s%nNow you have %d tasks in the list.%n"
                , task.printTask(), this.listOfTexts.size());
    }

    public void saveList() {
        try {
            FileWriter fileWriter = new FileWriter("./data/duke.txt");
            for (int i = 0; i < this.listOfTexts.size(); i++) {
                fileWriter.write(this.listOfTexts.get(i).addToStorage());
            }
            fileWriter.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
