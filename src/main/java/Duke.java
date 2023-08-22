import java.util.Scanner;

public class Duke {

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
            if (nextLine.equals("bye")) {
                System.out.println(Exit());
                sc.close();
                break;
            }
            System.out.println(Echo(nextLine));
        }
    }
}
