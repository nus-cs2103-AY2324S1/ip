import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private static List<String> taskList = new ArrayList<>();


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
                System.out.println(Kniaz.taskListToString((taskList)));
                // print out if we are asked to list
            } else {
                // interpret everything else that isn't special as a task to add
                taskList.add(next);
                System.out.printf("added: %s%n",next);
            }
            System.out.println((Kniaz.SEPERATOR));


//            System.out.println(next);
//            next  = input.nextLine();

            // Functionality is just to echo -- Print what we got and wait to fetch next input
        }


        System.out.println(Kniaz.EXITMESSAGE);
        System.out.println(Kniaz.SEPERATOR);
    }

    /**
     * Formats a tasklist as a string.
     * @param taskList the list of tasks to format as a string
     * @return the list of tasks formatted in the right format
     */
    private static String taskListToString(List<? extends  String> taskList){
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++){
            String currTask = taskList.get(i);
            String newLine = String.format(
                    "%d. %s\n", i+1 , currTask);
            // User probably expects to start counting from 1 instead of 0
            // so need to add 1 here
            out.append(newLine);
        }
        return out.toString();
    }






}
