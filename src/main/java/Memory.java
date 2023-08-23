import java.util.ArrayList;
public class Memory {
    private final static ArrayList<Task> ls = new ArrayList<>();
    private final static ArrayList<Boolean> markList = new ArrayList<>();

    public void add(Task task) {
        System.out.println("Got it. I've added this task:");
        ls.add(task);
        System.out.println("added: " + task);
    }

    public void mark(int pos) {
        System.out.println("Nice! I've marked this task as done:");
        ls.get(pos - 1).toMark();
        System.out.println(ls.get(pos - 1));
    }

    public void unmark(int pos) {
        System.out.println("OK, I've marked this task as not done yet:");
        ls.get(pos - 1).toUnmark();
        System.out.println(ls.get(pos - 1));
    }

    public int size() {
        return ls.size();
    }

    public void print() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < ls.size(); i++) {
            int order = i + 1;
            Task cur = ls.get(i);
            System.out.println(order + ". "  + " " + cur);
        }
    }
}
