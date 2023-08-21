import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static class StoreList {
        ArrayList<Task> list = new ArrayList<>();
        StoreList() {
        }

        String add(String item) {
            Task task = Task.create();
            list.add(task);
            return String.format("    added: %s", item);
        }

        String markDone(int index) {
            Task task = list.get(index);
            task.markAsDone();
            return String.format("Nice! You have completed the task:\n    %s", task);
        }

        String markUndone(int index) {
            Task task = list.get(index);
            task.markAsNotDone();
            return String.format("Ok! Task marked undone:\n    %s", task);
        }
        @Override
        public String toString() {
            String result = "";
            for (int i = 0; i < list.size(); i++) {
                result += String.format("    %d. %s\n", i + 1, list.get(i));
            }
            return result;
        }
    }

    enum commands {
        list,
        bye,
        mark,
        unmark,
    }

    private static StoreList list = new StoreList();
    public static void main(String[] args) {
        String logo = "Nino!";
        System.out.println("Hello, my name is " + logo);
        System.out.println(wrapper("What can I do for you?"));
        reader();
    }

    private static void reader() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                printer("Goodbye.");
                sc.close();
                break;
            }
            if (line.equals("list")) {
                printer(list.toString());
                continue;
            }
            String[] instructions = line.split(" ");
            if (instructions[0].equals("mark")) {
                int index = Integer.parseInt(instructions[1]) - 1;
                String response = list.markDone(index);
                printer(response);
                continue;
            }
            if (instructions[0].equals("unmark")) {
                int index = Integer.parseInt(instructions[1]) - 1;
                String response = list.markUndone(index);
                printer(response);
                continue;
            }
            String response = list.add(line);
            printer(response);
        }
    }

    private static String wrapper(String line) {
        String frame = "=====================";
        return String.format("%s\n%s", line, frame);
    }

    private static void printer(String item) {
        System.out.println(wrapper(item));
    }
}
