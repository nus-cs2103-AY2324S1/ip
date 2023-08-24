import java.util.ArrayList;

public class Task {
    private String task;
    private Boolean done;
    private static ArrayList<String> arr = new ArrayList<>();
    public Task(String task) {
        this.task = task;
        this.done = false;
        arr.add(task);
        System.out.println(Duke.horizontalLine + "added: " + task + "\n" + Duke.horizontalLine);
    }

    public static void printList() {
        int count = 0;
        System.out.println(Duke.horizontalLine);
        for (String str : arr) {
            count++;
            System.out.println( count+"." + str);
        }
        System.out.println(Duke.horizontalLine);
    }

}
