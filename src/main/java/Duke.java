import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm JJ\n" +
                "What can I do for you?\n" + "\n");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        while (true) {
            if (cmd.equals("bye")) {
                sc.close();
                System.out.println("Bye. Hope to see you again soon!\n");
                System.exit(0);
            } else {
                System.out.println(cmd + "\n");
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
