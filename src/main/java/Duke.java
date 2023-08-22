import java.util.*;

public class Duke {
    public static String separator = "____________________________________________________________";
    public static ArrayList<String> list = new ArrayList<String>(100);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| |_| | | | | |/ / _ \\\n"
                + "| ___/| |_| |    | __/\n"
                + "| |    \\__,_|_|\\_\\___|\n"
                + "|_|";
        Scanner sc = new Scanner(System.in);
        System.out.println("Salutations! I am\n" + logo + "\nAn exceedingly verbose conversation simulation program");
        System.out.println(separator);
        while (true) {
            String command = sc.nextLine();
            System.out.println(separator);
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                int i = 1;
                for (String s:list) {
                    System.out.println(String.format("%d. %s", i, s));
                    i++;
                }
                System.out.println(separator);
                continue;
            } else {
                list.add(command);
                System.out.println(String.format("added: %s", command));
                System.out.println(separator);
            }
        }
        System.out.println("It appears that the user has decided to close the program as indicated by the command of which this is the function being issued and therefore,\n" +
                "I shall bid thee farewell and wish thee great fortune in your future endeavors");
    }
}

