import java.util.*;

public class Duke {
    static boolean isEnd = false;
    static List<Task> taskList = new ArrayList<>();
    static String greeting = "______________________________________\n"
            + "Hi, I'm Chatty\n"
            + "What do you need to do today?\n"
            + "______________________________________";
    static String goodbye = "______________________________________\n"
            + "Bye. Don't come back!\n"
            + "______________________________________";

    static void addToList(String str) {
        Task t = new Task(str);
        taskList.add(t);
        String returnLine = "______________________________________\n"
                + "added: " + str
                + "\n______________________________________\n";
        System.out.println(returnLine);
    }

    static void listTasks() {
        System.out.println("______________________________________");
        for (int i=1; i<=taskList.size(); i++) {
            Task t = taskList.get((i-1));
            System.out.format("%d. [%s] %s\n", i, t.getStatusIcon(), t.getDescription());
        }
        System.out.println("______________________________________\n");
    }

    public static void main(String[] args) {
        System.out.println(greeting);
        while (isEnd == false) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if (command.equals("bye")) {
                isEnd = true;
            } else if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("mark")) {
                int index = Integer.parseInt(command.substring(5));
                taskList.get(index-1).markAsDone();
            } else if (command.startsWith("unmark")) {
                int index = Integer.parseInt(command.substring(7));
                taskList.get(index-1).markAsUndone();
            } else {
                addToList(command);
            }
        }
        System.out.println(goodbye);
    }
}
