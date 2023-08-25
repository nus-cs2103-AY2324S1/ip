import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    /**
     * method to run the code
     * @param arrayList the array containing the tasks
     * @return boolean to check bye or not
     * @throws DukeException throws DukeException
     */
    public static boolean runProgram(ArrayList<Task> arrayList) throws DukeException {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        if (s == null) {
            s = in.nextLine();
        }

        if (s.equals("bye")) { // if input is bye, terminate code

            String exit = "    ____________________________________________________________\n" +
                    "     Bye. Hope to see you again soon!\n" +
                    "    ____________________________________________________________";
            System.out.println(exit);
            return false;
        } else {
            String response = "";
            String[] splitMark = s.split(" ");
            String[] splitTask = s.split(" ", 2);

            if (s.equals("list")) {
                String items = "";

                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i) == null) {
                        break;
                    }
                    items += "     " + (i + 1) + "." + arrayList.get(i).toString() + "\n";
                }
                response = "    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:\n" +
                        items +
                        "    ____________________________________________________________\n";

            } else if (splitMark[0].equals("delete") && splitMark.length == 2 ) {
                int index;

                try {
                    index = Integer.parseInt(splitMark[1]);
                } catch (NumberFormatException e) {
                    throw new DukeInvalidDeleteException(splitMark[0]);
                }

                if (index > 0 && arrayList.get(index - 1) != null) {
                    response = "    ____________________________________________________________\n" +
                            "     Noted. I've removed this task:" + "\n" +
                            "       " + arrayList.get(index-1).toString() + "\n" +
                            "     Now you have " + (arrayList.size() - 1) + " tasks in the list." + "\n" +
                            "    ____________________________________________________________\n";
                    arrayList.remove(index - 1);

                }

            } else {
                boolean condition1 = splitMark[0].equals("mark") || splitMark[0].equals("unmark"); //first word is mark or unmark

                if (splitMark.length == 2 && condition1) {
                    int index = 0;
                    try {
                        index = Integer.parseInt(splitMark[1]);
                    } catch (NumberFormatException e) {
                        throw new DukeInvalidMarkException(splitMark[0]);
                    }

                    if (index > 0 && arrayList.get(index - 1) != null) {
                        if (splitMark[0].equals("mark")) {
                            arrayList.get(index - 1).mark();

                            response = "    ____________________________________________________________\n" +
                                    "     Nice! I've marked this task as done:" + "\n" +
                                    "       " + arrayList.get(index - 1).toString() + "\n" +
                                    "    ____________________________________________________________\n";
                        }
                        if (splitMark[0].equals("unmark")) {
                            arrayList.get(index - 1).unmark();

                            response = "    ____________________________________________________________\n" +
                                    "     OK, I've marked this task as not done yet:" + "\n" +
                                    "       " + arrayList.get(index - 1).toString() + "\n" +
                                    "    ____________________________________________________________\n";
                        }
                    }
                } else {
                    Task newTask = Task.createTaskType(splitTask);
                    arrayList.add(newTask);
                    int i = arrayList.indexOf(newTask);

                    response = "    ____________________________________________________________\n" +
                            "     Got it. I've added this task:\n" +
                            "      " + newTask + "\n" +
                            "     Now you have " + (i + 1) + " tasks in the list." + "\n" +
                            "    ____________________________________________________________\n";
                }
            }

            System.out.println(response);
            s = null;
            return true;
        }
    }

    public static void main(String[] args) {
        /**
         * array of task
         */
        //Task[] arrayList = new Task[100];
        ArrayList<Task> arrayList = new ArrayList<>();

        String greet = "   ____________________________________________________________\n" +
                "    Hello! I'm Siri\n" +
                "    What can I do for you?\n" +
                "   ____________________________________________________________\n";
        System.out.println(greet);


        while (true) {
            try {
                if (!Duke.runProgram(arrayList)) {
                    break;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }


        }
    }
}
