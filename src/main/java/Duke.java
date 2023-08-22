import java.util.*;

public class Duke {

    private static ArrayList<Task> lst = new ArrayList<>();

    private static String stringFormat(String[] strArray) {
        String content = "";
        for (String s : strArray) {
            content += "\t " + s + "\n";
        }
        return   "\t_______________________________________________\n"
                + content
                + "\t_______________________________________________";
    }

    private static String greet() {
        return stringFormat(new String[]{"Hi there! I'm Bob", "How can I help?"});
    }

    private static String exit() {
        return stringFormat(new String[]{"See you soon!"});
    }

    private static String echo(String input) {
        return stringFormat(new String[]{input});
    }

    private static String addToList(String taskName) {
        Task taskObj = new Task(taskName);
        lst.add(taskObj);
        return stringFormat(new String[]{"added: " + taskName});
    }

    private static String displayList() {
        String[] tasks = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            tasks[i] = (i + 1) + ". " + lst.get(i).toString();
        }
        return stringFormat(tasks);
    }

    private static String markDoneOrNot(int index, boolean doneOrNot) {
        lst.get(index - 1).SetDoneOrNot(doneOrNot);
        String statement = doneOrNot ? "Nice! You completed a task!" : "... This is now undone.";
        return stringFormat(new String[]{statement, lst.get(index - 1).toString()});
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greet());

        Scanner sc = new Scanner(System.in);
        while (true) {
            String nextLine = sc.nextLine();

            if (nextLine.equals("list")) {
                System.out.println(displayList());

            } else if (nextLine.contains("mark")) {
                String[] markIndex = nextLine.split(" ");
                int index = Integer.parseInt(markIndex[1]);
                boolean doneOrNot = true;
                if (nextLine.contains("unmark")) {
                    doneOrNot = false;
                }
                System.out.println(markDoneOrNot(index, doneOrNot));

            } else if (nextLine.equals("bye")) {
                System.out.println(exit());
                sc.close();
                break;

            } else {
                System.out.println(addToList(nextLine));
            }
        }
    }
}
