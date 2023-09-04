import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Potato {
    private static String LINE = "-----------------------------------------\n";
    Storage storage;
    ArrayList<Task> tasks;

    public Potato() {
        storage = new Storage("./Potato.txt");
        try {
            tasks = storage.loadTask();
            System.out.println("here's the saved list");
            for (Task t : tasks) {
                System.out.println(t);
            }
        } catch (IOException e) {
            System.out.println("No saved list");
            tasks = new ArrayList<>();
        }
    }

    public void hi() {
        System.out.println(LINE + "Hey GURL I'm Potato\n" + "How can I help ya?\n" + LINE);
    }

    public void bye() {
        System.out.println(LINE + "You're cancelled. Leave.\n" + LINE);
    }

    public void mark(String input, ArrayList<Task> tasks) throws IOException {
        int index = Integer.parseInt(input.substring(5)) - 1;
        tasks.get(index).setStatus(true);
        System.out.println(LINE + "Slayyy such efficiency we love it!\n"
                + "Let's take this off the list!\n"
                + tasks.get(index).toString() + "\n" + LINE);
        storage.saveTask(tasks);
    }

    public void unmark(String input, ArrayList<Task> tasks) throws IOException {
        int index = Integer.parseInt(input.substring(7)) - 1;
        tasks.get(index).setStatus(false);
        System.out.println(LINE + "Ohmah... not you lying to me??\n"
                + "This is the last time I'm adding it back for you!\n"
                + tasks.get(index).toString() + "\n" + LINE);
        storage.saveTask(tasks);
    }

    public void delete(String input, ArrayList<Task> tasks) throws IOException {
        int index = Integer.parseInt(input.substring(7)) - 1;
        System.out.println(LINE + "NAUR NOT THE DITCHING?!\n" + "It's a no do for\n"
                + tasks.get(index).toString() + "\n" + "Now there's only " + (tasks.size() - 1)
                + " tasks left sistar pls do them.\n" + LINE);
        tasks.remove(index);
        storage.saveTask(tasks);
    }

    public void list(ArrayList<Task> tasks) {
        int count = 0;
        System.out.println(LINE + "Ok look all you want but they literally won't do themselves?");

        for (Task t : tasks) {
            if (t == null) break;
            count++;
            System.out.println(String.valueOf(count) + "." +
                    t.toString());
        }

        System.out.println(LINE);
    }

    public void add(String input, ArrayList<Task> tasks) throws IOException {
        Task task = Task.parse(input);

        if (task == null) {
            new PotatoException(LINE +
                    "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    LINE);

        } else {
            tasks.add(task);
            storage.saveTask(tasks);
            int size = tasks.size();
            System.out.println(LINE + "Let's add this to the never ending pile...\n"
                    + tasks.get(size - 1).toString() + "\n"
                    + "Now you have " + size + " tasks in the list.\n" + LINE);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Potato potato = new Potato();
        ArrayList<Task> tasks = potato.tasks;

        potato.hi();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                potato.bye();
                break;

            } else if (input.startsWith("mark ")) {
                // not followed by number
                potato.mark(input, tasks);

            } else if (input.startsWith("unmark ")) {
                // not followed by number
                potato.unmark(input, tasks);

            } else if (input.startsWith("delete ")) {
                potato.delete(input, tasks);

            } else if (input.equals("list")) {
                potato.list(tasks);

            } else {
                potato.add(input, tasks);
            }
        }
    }
}