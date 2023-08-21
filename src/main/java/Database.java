import java.util.ArrayList;
public class Database {
    private ArrayList<Task> db = new ArrayList<>();
    private int total = 0;  // total indicates the first free slot

    public void addEvent(String ss) {
        this.db.add(new Task(ss));
        this.total++;
        UI.displayCheems();
        System.out.printf("I have added \"%s\" for you!%n", ss);
    }

    public void displayData() {
        UI.displayCheems();
        for (int i = 0; i < this.total; i++) {
            System.out.printf("%d.%s%n", i + 1, this.db.get(i));
        }
    }

    // for validity check later
    public int total() {
        return this.total;
    }

    public void markAsDone(String input) {
        String[] words = input.split(" ");
        int taskNo = Integer.parseInt(words[1]) - 1;
        this.db.get(taskNo).markAsDone();

        // display
        UI.displayCheems();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.db.get(taskNo));

    }

    public void markAsNotDone(String input) {
        String[] words = input.split(" ");
        int taskNo = Integer.parseInt(words[1]) - 1;
        this.db.get(taskNo).markAsNotDone();

        // display
        UI.displayCheems();
        System.out.println("Got it! I've unmarked it for you:");
        System.out.println(this.db.get(taskNo));
    }
}
