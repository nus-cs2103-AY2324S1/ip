import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Task.TaskList;
import Task.Task;
public class Kniaz {

    /**
     * Seperator to make things a bit prettier
     */
    private static final String SEPERATOR = "_".repeat(20) + "\n";

    /**
     * Logo to use to represent this chatbot in the command line
     */
    private static final String LOGO = "KNIAZ";
    // Placeholder for now, intend to make prettier later
    // Kniaz is a rough equivalent for Duke in eastern europe - get it?

    /**
     * Message to print when exiting.
     */
    private static final String EXITMESSAGE = "Bye. Hope to see you again soon!";
    /**
     * String that, when entered into the command line interface of Kniaz, result in Kniaz exiting, and
     * printing the exit message.
     */
    public static final String EXITSTRING = "bye";


    /**
     * All tasks stored by Kniaz.
     */
    private static TaskList taskList = new TaskList();


    /**
     * Main function of Kniaz, that is intended as entry point into the program.
     * @param args arguments to be entered into Kniaz when it is called from command line
     */
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner input = new Scanner(System.in);
        // initialise a scanner object to read from input

        System.out.println("Hello from\n" + Kniaz.LOGO);
        System.out.println(Kniaz.SEPERATOR);

        System.out.println("What can I do for you?");
        System.out.println(Kniaz.SEPERATOR);

        String next = ""; //Initialise the input
        while (true) { // I find this a bit icky but we rely on guard clauses to break instead

            next = input.nextLine();

            if (next.equals(Kniaz.EXITSTRING)){

                break; // Used here instead of in the while because of timing of next
            }

            if (next.equals("list")){

                System.out.println(taskList.toString());
                // print out if we are asked to list
            } else if (next.startsWith("mark")) {

                String entryAsString = Kniaz.getAfter(next,"mark");
                int entryAsInt = Integer.parseInt(entryAsString.strip());
                int entryToMark = entryAsInt - 1;

                taskList.get(entryToMark).markAsDone();

                System.out.println("As you say. The task has been marked as done :");
                System.out.println(taskList.get(entryToMark).toString());

            } else if (next.startsWith("unmark")){

                String entryAsString = Kniaz.getAfter(next,"unmark");
                int entryAsInt = Integer.parseInt(entryAsString.strip());
                int entryToMark = entryAsInt - 1;

                taskList.get(entryToMark).markAsUndone();

                System.out.println("Ah, so you didn't actually finish it. Correcting your mistake.");
                System.out.println(taskList.get(entryToMark).toString());

            } else {

                // interpret everything else that isn't special as a task to add
                Task taskToAdd = Task.newTask((next));
                // next currently holds the name of this new task

                taskList.add(taskToAdd);

                System.out.printf("added: %s%n",taskToAdd.toString());
            }
            System.out.println((Kniaz.SEPERATOR));


        }


        System.out.println(Kniaz.EXITMESSAGE);
        System.out.println(Kniaz.SEPERATOR);
    }

    private static String getAfter(String fullString, String subString){
        int indexOfSubString = fullString.indexOf(subString);
        return fullString.substring(indexOfSubString + subString.length());
        // add the substring length to skip to the end of it
    }






}
