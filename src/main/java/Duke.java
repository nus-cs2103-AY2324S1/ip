import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * helps to mark or unmark the specific task in the list, and todo/deadline/event
     * adds todo/deadline/event tasks respectively.
     *
     * @param tally takes in the input string.
     */
    public void messageHandler(String tally) {
        if (tally.equals("list")) {
            list.display();
        } else {
            String comd = tally.split(" ")[0];

            if (comd.equals("todo")) {
                Pattern todocmd = Pattern.compile("todo (.+)");
                Matcher m = todocmd.matcher(tally);
                m.matches();
                list.store(m.group(1));
            } else if (comd.equals("deadline")) {
                Pattern deadlinecmd = Pattern.compile("deadline (.+) /by (.+)");
                Matcher m = deadlinecmd.matcher(tally);
                m.matches();
                m.matches();
                list.store(m.group(1), m.group(2));
            } else if (comd.equals("event")) {
                Pattern eventcmd = Pattern.compile("event (.+) /from (.+) /to (.+)");
                Matcher m = eventcmd.matcher(tally);
                m.matches();
                m.matches();
                m.matches();
                list.store(m.group(1), m.group(2), m.group(3));
            } else if (comd.equals("mark")) {
                String index = tally.split(" ")[1];
                list.mark(Integer.parseInt(index));
            } else {
                String index = tally.split(" ")[1];
                list.unmark(Integer.parseInt(index));
            }
        }
    }

}
