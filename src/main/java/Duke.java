import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);

        String greet = "   ____________________________________________________________\n" +
                "    Hello! I'm Siri\n" +
                "    What can I do for you?\n" +
                "   ____________________________________________________________\n";
        System.out.println(greet);

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        boolean bye = false;
        while (!bye) {
            if (s == null) {
                s = in.nextLine();
            }

            if (s.equals("bye")) {
                bye = true;

                String exit = "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________";
                System.out.println(exit);
            } else {

                String response = "    ____________________________________________________________\n" +
                        "     " + s + "\n" +
                        "    ____________________________________________________________\n";
                System.out.println(response);
                s = null;
            }
        }
    }
}
