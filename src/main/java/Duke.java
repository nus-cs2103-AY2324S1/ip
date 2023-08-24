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
                String[] splitMark = s.split(" ");
                String[] splitTask = s.split(" ", 2);

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
                    boolean condition1 = splitMark[0].equals("mark") || splitMark[0].equals("unmark"); //first word is mark or unmark

                    if (splitMark.length == 2 && condition1 ) {
                        Integer index = Integer.parseInt(splitMark[1]);

                        if (index > 0 && arrayList[index-1] != null) {
                            if (splitMark[0].equals("mark")) {
                                arrayList[index - 1].mark();

                                response = "    ____________________________________________________________\n" +
                                        "     Nice! I've marked this task as done:" + "\n" +
                                        "       " + arrayList[index-1].toString() + "\n" +
                                        "    ____________________________________________________________\n";
                            }
                            if (splitMark[0].equals("unmark")) {
                                arrayList[index - 1].unmark();

                                response = "    ____________________________________________________________\n" +
                                        "     OK, I've marked this task as not done yet:" + "\n" +
                                        "       " + arrayList[index-1].toString() + "\n" +
                                        "    ____________________________________________________________\n";
                            }
                        }
                    } else {
                        int i = 0;
                        for (i = 0; i < 100; i++) {
                            if (arrayList[i] == null & splitTask.length == 2) {
                                arrayList[i] = Task.createTaskType(splitTask[0], splitTask[1]);
                                break;
                            } else if (arrayList[i] == null & splitTask.length == 1){
                                arrayList[i] = Task.createTaskType(splitTask[0], splitTask[0]);
                                break;
                            }
                        }

                        response = "    ____________________________________________________________\n" +
                                "     Got it. I've added this task:\n" +
                                "      " + arrayList[i].toString() + "\n" +
                                "     Now you have " + (i+1) + " tasks in the list." + "\n" +
                                "    ____________________________________________________________\n";
                    }
                }

                System.out.println(response);
                s = null;
            }
        }
    }
}
