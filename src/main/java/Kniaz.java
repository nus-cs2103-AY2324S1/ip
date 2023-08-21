

import java.util.Scanner;

import task.*;

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

            if (next.equals(Kniaz.EXITSTRING)) {

                break; // Used here instead of in the while because of timing of next
            }

            if (next.equals("list")) {

                System.out.println(taskList.toPrintString());
                // print out if we are asked to list
            } else if (next.startsWith("mark")) {

                // handle parsing which entry the user wants to mark here
                String entryAsString = Kniaz.getAfter(next,"mark");
                int entryAsInt = Integer.parseInt(entryAsString.strip());
                int entryToMark = entryAsInt - 1;

                // handle actual marking here
                taskList.get(entryToMark).markAsDone();

                // give user feedback
                System.out.println("As you say. The task has been marked as done.");
                System.out.println(taskList.get(entryToMark).toPrintString());

            } else if (next.startsWith("unmark")) {

                // handle parsing which entry user wants to unmark here
                String entryAsString = Kniaz.getAfter(next,"unmark");
                int entryAsInt = Integer.parseInt(entryAsString.strip());
                int entryToMark = entryAsInt - 1;

                // handle unmarking here
                Kniaz.taskList.get(entryToMark).markAsUndone();

                // give user feedback
                System.out.println("Ah, so you didn't actually finish it. Correcting your mistake.");
                System.out.println(taskList.get(entryToMark).toPrintString());

            } else if (next.startsWith(("todo"))) {

                String taskName = Kniaz.getAfter(next,"todo").strip();
                // interpret everything else that isn't special as a task to add
                Task taskToAdd = new ToDo((taskName));
                // next currently holds the name of this new task

                Kniaz.taskList.add(taskToAdd);

                System.out.printf("added: %s%n",taskToAdd.toPrintString());
            } else if (next.startsWith("deadline")) {

                // pull the args for this command
                String deadlineArgs = Kniaz.getAfter(next, "deadline");

                String[] tokenizedDlineArgs = deadlineArgs.split("/");
                // Split it up by the slash


                String deadlineName = tokenizedDlineArgs[0].strip();
                String deadlineTime = Kniaz.getAfter(tokenizedDlineArgs[1], "by").strip();
                // we getAfter("by"), to get all after the by, as that's the actual date/time
                // Of note, this means that if it's lacking "by", no time will be specified.
                // strip to remove excess whitespace

                Task taskToAdd = new Deadline(deadlineName,deadlineTime);
                Kniaz.taskList.add(taskToAdd);
                // make a new Deadline to add

                System.out.printf("added: %s%n",taskToAdd.toPrintString());
                // remember to print our message to the user!

            } else if (next.startsWith("event")) {
                String eventArgs = Kniaz.getAfter(next, "event");

                String[] tokenizedEventArgs = eventArgs.split("/");

                String eventName = tokenizedEventArgs[0].strip();
                String eventStart = Kniaz.getAfter(tokenizedEventArgs[1], "from").strip();
                String eventEnd = Kniaz.getAfter(tokenizedEventArgs[2], "to").strip();
                // we use getAfter here to get all after the "from" or "to", as that's the actual date/time
                // strip to remove excess whitespace

                Task taskToAdd = new Event(eventName, eventStart, eventEnd);
                Kniaz.taskList.add(taskToAdd);
                // add a new Event to the tasklist

                System.out.printf("added: %s%n",taskToAdd.toPrintString());
                // and remember to give feedback to user
            }

            // Each command input will invariable result in a seperator line being printed
            // Helps to keep it looking nice
            System.out.println((Kniaz.SEPERATOR));


        }


        System.out.println(Kniaz.EXITMESSAGE);
        System.out.println(Kniaz.SEPERATOR);
    }

    /**
     * Helper function to get the substring after a provided substring
     * @param fullString The whole string to scan
     * @param subString The substring to scan fullString for
     * @return the substring of everything after the provided subString
     */
    private static String getAfter(String fullString, String subString) {
        int indexOfSubString = fullString.indexOf(subString);
        return fullString.substring(indexOfSubString + subString.length());
        // add the substring length to skip to the end of it
    }






}
