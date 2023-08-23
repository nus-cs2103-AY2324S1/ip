import java.util.ArrayList;

public class List {
    private ArrayList<Task> list;

    public List() {
        this.list = new ArrayList<>();
    }

    public void addTask(String type, String input) {
        Task task = null;
        switch (type) {
            case "todo":
                task = new ToDo(input);
                break;
            case "deadline":
                task = new Deadline(input);
                break;
            case "event":
                task = new Event(input);
                break;
        }
        list.add(task);
        System.out.println(ChatterChicken.LINE
            + "Got it. I've added this task: \n"
            + ChatterChicken.INDENT_BIG + task.getTask() + "\n"
            + ChatterChicken.INDENT + "Now you have " + list.size() + " tasks in the list."
            + ChatterChicken.LINE);
    }

    public void markTask(String input) {
        int index = input.charAt(input.length() - 1) - '0' - 1;
        Task task = list.get(index);
        System.out.println(ChatterChicken.LINE + "Nice! I've marked this task as done:");
        task.markDone();
        System.out.println(ChatterChicken.LINE);
    }

    public void unmarkTask(String input) {
        int index = input.charAt(input.length() - 1) - '0' - 1;
        Task task = list.get(index);
        System.out.println(ChatterChicken.LINE + "OK, I've marked this task as not done yet:");
        task.unmarkDone();
        System.out.println(ChatterChicken.LINE);
    }

    public void printList() {
        System.out.println(ChatterChicken.LINE);
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%s %d.%s\n", ChatterChicken.INDENT, i + 1, list.get(i).getTask());
        }
        System.out.println(ChatterChicken.LINE);
    }
}
