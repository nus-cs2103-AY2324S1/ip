import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {
        System.out.println("Hello! I'm JJ\n" +
                "What can I do for you?\n" + "\n");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        List<String> ls = new ArrayList<>();

        while (true) {
            if (cmd.equals("bye")) {
                sc.close();
                System.out.println("Bye. Hope to see you again soon!\n");
                System.exit(0);
            } else if (cmd.equals("list")) {
                int size = ls.size();
                for(int i = 0; i < size;i++) {
                    int j = i + 1;
                    System.out.println(j + ". " + ls.get(i));
                }
                cmd = sc.nextLine();
            } else {
                ls.add(cmd);
                System.out.println("added: " + cmd + "\n");
                cmd = sc.nextLine();
            }
        }
    }
}
//    String logo = " ____        _        \n"
//            + "|  _ \\ _   _| | _____ \n"
//            + "| | | | | | | |/ / _ \\\n"
//            + "| |_| | |_| |   <  __/\n"
//            + "|____/ \\__,_|_|\\_\\___|\n";
