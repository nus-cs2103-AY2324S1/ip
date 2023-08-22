import java.util.ArrayList;

public class DukeList {
    ArrayList<Task> arr;

    public DukeList() {
        arr = new ArrayList<>(100);
    }

    public void addToList (String userInput) {
        Task task = new Task(userInput);
        arr.add(task);
        System.out.println("added: " + userInput);
    }

    public void printList() {
        int arrSize = arr.size();
        for (int i = 0; i < arrSize; i++) {
            int num = i + 1;
            Task chosenTask = arr.get(i);
            System.out.println(num + ". " + chosenTask.getDescription());
        }
    }

    public void setDone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + chosenTask.getDescription());
    }

    public void setUndone(int number) {
        Task chosenTask = arr.get(number);
        chosenTask.markUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + chosenTask.getDescription());
    }
}
