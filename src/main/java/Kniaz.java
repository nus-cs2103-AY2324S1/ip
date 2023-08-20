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

        String next = input.nextLine(); //Initialise the input
        while (!next.equals(Kniaz.EXITSTRING)) {
            System.out.println(next);
            next  = input.nextLine();
            // Functionality is just to echo -- Print what we got and wait to fetch next input
        }


        System.out.println(Kniaz.EXITMESSAGE);
        System.out.println(Kniaz.SEPERATOR);
    }








}
