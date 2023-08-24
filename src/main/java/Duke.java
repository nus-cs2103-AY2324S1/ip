import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean bye = false;
        String[] arrayList = new String[100];

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greet = "   ____________________________________________________________\n" +
                "    Hello! I'm Siri\n" +
                "    What can I do for you?\n" +
                "   ____________________________________________________________\n";
        System.out.println(greet);

        String s = in.nextLine();
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
                String response = "";

                if (s.equals("list")) {
                    String items = "";

                    for (int i = 0; i < 100; i++) {
                        if (arrayList[i] == null) {
                            break;
                        }
                        items += "     " + (i+1) + ". " + arrayList[i] + "\n";
                    }
                    response = "    ____________________________________________________________\n" +
                            items +
                            "    ____________________________________________________________\n";

                } else {
                    for (int i = 0; i < 100; i++) {
                        if (arrayList[i] == null) {
                            arrayList[i] = s;
                            break;
                        }
                    }

                    response = "    ____________________________________________________________\n" +
                            "     added: " + s + "\n" +
                            "    ____________________________________________________________\n";
                }

                System.out.println(response);
                s = null;
            }
        }
    }
}
