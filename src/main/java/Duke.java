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
     * The main class for which the chatbot runs.
     * @param args The inputs given by the user in the command prompt.
     */
    public static void main(String[] args) {
        Duke lati = new Duke();

        lati.greet();

        Scanner scan = new Scanner(System.in);
        String comd = scan.nextLine();

        while (!comd.equals("bye")) {
            lati.messageHandler(comd);
            comd = scan.nextLine();
        }

        lati.bye();
    }

    /**
     * Sends a greeting message to the user.
     */
    public void greet() {
        System.out.println("____________________________________________________________\n" +
                " Hellooooooooooo! I'm Lati!\n" +
                " What can I do for you? :3\n" +
                "____________________________________________________________");
    }

    /**
     * Sends a goodbye message to the user.
     */
    public void bye() {
        System.out.println(" Byeeeeee. Hope to see you again soon~~\n" +
                "____________________________________________________________");
    }

    /**
     * Helps to parse and manage the user's inputs. "list" shows the current list, "mark / unmark"
     * helps to mark or unmark the specific task in the list, and todo/deadline/event
     * adds todo/deadline/event tasks respectively.
     *
     * @param tally takes in the input string.
     */
    public void messageHandler(String tally) {
        String comd = tally.split(" ")[0];

        switch (comd) {
            case "list":
                list.display();
                break;
            case "todo":
                Pattern todocmd = Pattern.compile("todo (.+)");
                Matcher mt = todocmd.matcher(tally);
                if (this.groupRun(mt, 1)) {
                    list.store(mt.group(1));
                } else {
                    throw new IllegalArgumentException("Whoops, wrong command! Type todo <task>");
                }
                break;
            case "deadline":
                Pattern deadlinecmd = Pattern.compile("deadline (.+) /by (.+)");
                Matcher md = deadlinecmd.matcher(tally);
                if (this.groupRun(md, 2)) {
                    list.store(md.group(1), md.group(2));
                } else {
                    throw new IllegalArgumentException("Whoops, wrong command! Type deadline <task> /by <date>");
                }
                break;
            case "event":
                Pattern eventcmd = Pattern.compile("event (.+) /from (.+) /to (.+)");
                Matcher ml = eventcmd.matcher(tally);
                if (this.groupRun(ml, 3)) {
                    list.store(ml.group(1), ml.group(2), ml.group(3));
                } else {
                    throw new IllegalArgumentException("Whoops, wrong command! Type event <task> /" +
                            "from <date> /to <date>");
                }
                break;
            case "mark":
                try {
                    String indexmark = tally.split(" ")[1];
                    list.mark(Integer.parseInt(indexmark));
                } catch (NullPointerException e) {
                    throw new IllegalArgumentException("Ehh? What do you want to mark? Type mark <index>");
                }
                break;
            case "unmark":
                try {
                    String indexunmark = tally.split(" ")[1];
                    list.mark(Integer.parseInt(indexunmark));
                } catch (NullPointerException e) {
                    throw new IllegalArgumentException("Ehh? What do you want to mark? Type unmark <index>");
                }
                break;
            default:
                throw new IllegalArgumentException("Error, unknown command.");
        }

    }

    /**
     * Recursively runs the matches() method on a given matcher n times.
     * @param m The matcher object
     * @param n number of times it has to run
     * @return the boolean value of all matches() run recursively.
     */
    private boolean groupRun(Matcher m, int n) {
        return n == 0 ? false : n == 1 ? m.matches() : m.matches() && groupRun(m, n - 1);
    }


}
