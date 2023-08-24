import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean bye = false;
        Task[] arrayList = new Task[100];

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

            if (s.equals("bye")) { // if inout is bye, terminate code
                bye = true;

                String exit = "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________";
                System.out.println(exit);
            } else {
                String response = "";
                String[] split = s.split(" ");

                if (s.equals("list")) {
                    String items = "";

                    for (int i = 0; i < 100; i++) {
                        if (arrayList[i] == null) {
                            break;
                        }
                        items += "     " + (i+1) + "." + arrayList[i].toString() + "\n";
                    }
                    response = "    ____________________________________________________________\n" +
                            "     Here are the tasks in your list: \n" +
                            items +
                            "    ____________________________________________________________\n";

                } else {
                    boolean condition1 = split[0].equals("mark") || split[0].equals("unmark"); //first word is mark or unmark

                    if (split.length == 2 && condition1 ) {
                        Integer index = Integer.parseInt(split[1]);

                        if (index > 0 && arrayList[index-1] != null) {
                            if (split[0].equals("mark")) {
                                arrayList[index - 1].mark();

                                response = "    ____________________________________________________________\n" +
                                        "     Nice! I've marked this task as done:" + "\n" +
                                        "       " + arrayList[index-1].toString() + "\n" +
                                        "    ____________________________________________________________\n";
                            }
                            if (split[0].equals("unmark")) {
                                arrayList[index - 1].unmark();

                                response = "    ____________________________________________________________\n" +
                                        "     OK, I've marked this task as not done yet:" + "\n" +
                                        "       " + arrayList[index-1].toString() + "\n" +
                                        "    ____________________________________________________________\n";
                            }
                        }
                    } else {
                        for (int i = 0; i < 100; i++) {
                            if (arrayList[i] == null) {
                                arrayList[i] = new Task(s);
                                break;
                            }
                        }

                        response = "    ____________________________________________________________\n" +
                                "     added: " + s + "\n" +
                                "    ____________________________________________________________\n";
                    }
                }

                System.out.println(response);
                s = null;
            }
        }
    }
}
