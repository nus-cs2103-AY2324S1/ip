import java.security.SecureRandom;
import java.util.*;

public class Duke {

    private static ArrayList<String> lst = new ArrayList<>();

    private static String StringFormat(String[] strArray) {
        String content = "";
        for (String s : strArray) {
            content += "\t " + s + "\n";
        }
        return   "\t_______________________________________________\n"
                + content
                + "\t_______________________________________________";
    }

    private static String Greet() {
        return StringFormat(new String[]{"Hi there! I'm Bob", "How can I help?"});
    }

    private static String Exit() {
        return StringFormat(new String[]{"See you soon!"});
    }

    private static String Echo(String input) {
        return StringFormat(new String[]{input});
    }

    private static String AddToList(String task) {
        lst.add(task);
        return StringFormat(new String[]{"added: " + task});
    }

    private static String DisplayList() {
        String[] tasks = new String[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            tasks[i] = (i + 1) + ". " + lst.get(i);
        }
        return StringFormat(tasks);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Greet());

        Scanner sc = new Scanner(System.in);
        while (true) {
            String nextLine = sc.nextLine();
            if (nextLine.equals("list")) {
                System.out.println(DisplayList());
            } else if (nextLine.equals("bye")) {
                System.out.println(Exit());
                sc.close();
                break;
            } else {
                System.out.println(AddToList(nextLine));
            }
        }
    }
}
