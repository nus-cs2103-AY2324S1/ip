import java.util.ArrayList;
public class AllyList {
    ArrayList<Task> arr;
    public AllyList() {
        arr = new ArrayList<>(100);
    }

    public void addElements(String str) {
        Task task = new Task(str);
        arr.add(task);
    }

    public void markAsDone(int index) {
        Task task = arr.get(index);
        task.setMarked();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + task.addCheckBox());
    }
    
    public void unMarkDone(int index) {
        Task task = arr.get(index);
        task.notDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("\t" + task.addCheckBox());
    }
    public void printElements() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0, len = arr.size(); i < len; i++) {
            System.out.println((i + 1) +". " + arr.get(i).addCheckBox());
        }
    }

}
