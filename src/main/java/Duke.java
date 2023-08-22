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

    private static String addToList(String description) {
        // Split by the first " " into type, and task details
        String taskType = description.split(" ", 2)[0];
        String taskDetails = description.split(" ", 2)[1];
        Task taskObj;
        if (taskType.equals("deadline")) {
            taskObj = new Deadline(taskDetails);
        } else if (taskType.equals("event")) {
            taskObj = new Event(taskDetails);
        } else {
            taskObj = new Todo(taskDetails);
        }

        lst.add(taskObj);
        return stringFormat(new String[]{"New task added: ", "\t" + taskObj.toString(),
                "You now have " + lst.size() + (lst.size() == 1 ? " task!" : " tasks!")});
    }

    private static String displayList() {
        String[] tasks = new String[lst.size() + 1];
        if (lst.isEmpty()) {
            tasks[0] = "You currently have no tasks.";
        } else {
            tasks[0] = "Here are your tasks!";
        }

        for (int i = 0; i < lst.size(); i++) {
            tasks[i + 1] = (i + 1) + ". " + lst.get(i).toString();
        }
        return stringFormat(tasks);
    }

    private static String markDoneOrNot(int index, boolean doneOrNot) {
        lst.get(index - 1).SetDoneOrNot(doneOrNot);
        String statement = doneOrNot ? "Nice! You completed a task!" : "... This is now undone.";
        return stringFormat(new String[]{statement, "\t" + lst.get(index - 1).toString()});
    }

    public static void main(String[] args) {
        System.out.println(greet());

        Scanner sc = new Scanner(System.in);
        while (true) {
            String nextLine = sc.nextLine();

            if (nextLine.equals("list")) {
                System.out.println(displayList());

            } else if (nextLine.contains("mark")) { // if command is to mark or unmark
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

            } else { // if command is to add tasks
                System.out.println(addToList(nextLine));
            }
        }
    }
}
