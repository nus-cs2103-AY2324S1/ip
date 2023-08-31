import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> enteredText = new ArrayList();

    public void add(Task task) {
        enteredText.add(task);
    }

    public int size() {
        return enteredText.size();
    }

    public void remove(Task task) {
        enteredText.remove(task);
    }

    public void printList() {
        for (int i = 0; i < enteredText.size(); i++) {
            System.out.printf("%d. %s \n", i + 1, enteredText.get(i).toString());
        }
    }

    public Task retrieve(int index) {
        return enteredText.get(index);
    }
}
