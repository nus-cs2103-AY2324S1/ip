import java.util.*;

public class Duke {
    static boolean isEnd = false;
    static List<String> taskList = new ArrayList<>();
    static String greeting = "______________________________________\n"
            + "Hi, I'm Chatty\n"
            + "What do you need to do today?\n"
            + "______________________________________";
    static String goodbye = "______________________________________\n"
            + "Bye. Don't come back!\n"
            + "______________________________________";

    static void addToList(String str) {
        taskList.add(str);
        String returnLine = "______________________________________\n"
                + "added: " + str
                + "\n______________________________________\n";
        System.out.println(returnLine);
    }

    static void listTasks() {
        System.out.println("______________________________________");
        for (int i=1; i<=taskList.size(); i++) {
            System.out.format("%d. %s\n", i, taskList.get((i-1)));
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
            } else {
                addToList(command);
            }
        }
        System.out.println(goodbye);
    }
}
