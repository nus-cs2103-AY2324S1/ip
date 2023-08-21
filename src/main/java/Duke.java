import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static class StoreList {
        ArrayList<Task> list = new ArrayList<>();
        StoreList() {
        }

        String add(Commands type, String item) {
            Task task = Task.create(type, item);
            list.add(task);
            return String.format(
                    "added: %s\nYou have %d tasks.",
                    task,
                    list.size()
                    );
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
        Reading: while (true) {
            String line = sc.nextLine();
            String[] instruction = line.split(" ", 2);
            Commands cmd = Commands.valueOf(instruction[0]);
            switch (cmd) {
                case bye:
                    printer("Goodbye.");
                    sc.close();
                    break Reading;
                case list:
                    printer(list.toString());
                    continue Reading;
                case deadline:
                case todo:
                case event:
                    String taskResponse = list.add(cmd, instruction[1]);
                    printer(taskResponse);
                    continue Reading;
                case mark:
                    int markIndex = Integer.parseInt(instruction[1]) - 1;
                    String markedResponse = list.markDone(markIndex);
                    printer(markedResponse);
                    continue Reading;
                case unmark:
                    int unmarkIndex = Integer.parseInt(instruction[1]) - 1;
                    String response = list.markUndone(unmarkIndex);
                    printer(response);
                    continue Reading;

            }
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
