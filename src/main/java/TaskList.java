import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> fullList;
    private static String line = "------------------------------------";

    public TaskList() {
        this.fullList = new ArrayList<>();
    }

    public void addToList(Task task) {
        this.fullList.add(task);
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + fullList.size() + " tasks in the list.");
        System.out.println(line);
    };

    public void markItem(int index) {
        if (index >= 0 && index < fullList.size()) {
            Task curr = fullList.get(index);
            curr.markDone();

            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(curr.toString());
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("No such item exists");
            System.out.println(line);
        }
    }

    public void unMarkItem(int index) {
        if (index >= 0 && index < fullList.size()) {
            Task curr = fullList.get(index);
            curr.markNotDone();

            System.out.println(line);
            System.out.println("Ok, I've marked this task as not done yet:");
            System.out.println(curr.toString());
            System.out.println(line);
        } else {
            System.out.println(line);
            System.out.println("No such item exists");
            System.out.println(line);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fullList.size(); i++) {
            int index = i + 1;
            stringBuilder.append(index).append(". ")
                    .append(fullList.get(i).toString()).append("\n");
        }
        return line + "\n" + stringBuilder.toString() + "\n" + line;
    }
}
