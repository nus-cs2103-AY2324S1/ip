import java.util.Scanner;

/**
 * The Duke class helps to manage all messages sent by the user to the chatbot accordingly.
 */
public class Duke {
    /**The list, which would be used to store all the tasks inputted by the user.*/
    private DukeList list = new DukeList();

    /**
     * Instantiates an instance of a Duke class.
     */
    public Duke() {}

    /**
     * The main class for which the chatbot runs.
     * @param args The inputs given by the user in the command prompt.
     */
    public static void main(String[] args) {
        Duke lati = new Duke();

        System.out.println("____________________________________________________________\n" +
                " Hellooooooooooo! I'm Lati!\n" +
                " What can I do for you? :3\n" +
                "____________________________________________________________");

        Scanner scan = new Scanner(System.in);
        String comd = scan.nextLine();

        while (!comd.equals("bye")) {
            lati.messageHandler(comd);
            comd = scan.nextLine();
        }

        System.out.println(" Byeeeeee. Hope to see you again soon~~\n" +
                "____________________________________________________________");
    }

    /**
     * Helps to manage the user's inputs. "list" shows the current list, "mark / unmark"
     * helps to mark or unmark the specific task in the list, and any other input is
     * added as a task to the list.
     *
     * @param tally takes in the input string.
     */
    public void messageHandler(String tally) {
        if (tally.equals("list")) {
            list.display();
        } else {
            String[] set = tally.split(" ");
            if (set[0].equals("mark")) {
                list.mark(Integer.parseInt(set[1]));
            } else if (set[0].equals("unmark")) {
                list.unmark(Integer.parseInt(set[1]));
            } else {
                list.store(tally);
            }
        }
    }

}
