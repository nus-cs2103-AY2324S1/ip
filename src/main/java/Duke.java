import java.util.Objects;
import java.util.Scanner;

public class Duke {
    private static final String SEPARATOR_LINE = "____________________________________________________________";
    private static TaskList listContainer = new TaskList();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println(SEPARATOR_LINE);
        String entranceMsg = "Hello! I'm Elon Musk.\n" +
                "What can I do for you?";
        System.out.println(entranceMsg);
        System.out.println(SEPARATOR_LINE);


        String inputString = "";

        Scanner keyboard = new Scanner(System.in);

        while (!Objects.equals(inputString, "bye")) {
            inputString = keyboard.nextLine();

//            System.out.println(inputString);
            System.out.println(SEPARATOR_LINE);

            if (inputString.equals("list")) {
                // output the list
                System.out.println(listContainer.toString());
            } else if (inputString.startsWith("mark ")) {
                // mark the index
                int index = Integer.parseInt(inputString.split(" ")[1]);
                listContainer.markAsDone(index);

            } else if (inputString.startsWith("unmark ")) {
                // unmark the index
                int index = Integer.parseInt(inputString.split(" ")[1]);
                listContainer.markAsUnDone(index);
            } else {
                // add to the list
                listContainer.addToList(inputString);
            }
            System.out.println(SEPARATOR_LINE);
        }


        String exitMsg = "Bye! Hope to see you again soon.";
        System.out.println(exitMsg);
        System.out.println(SEPARATOR_LINE);


    }
}
