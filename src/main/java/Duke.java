import java.util.Scanner;



/**
 * The Duke class helps to manage all messages sent by the user to the chatbot accordingly.
 */
public class Duke {

    private Parser parser;


    /**
     * Instantiates an instance of the Duke bot.
     * @param path The filepath to the data.s file.
     */
    public Duke(String path) {
        DukeList list = new DukeList(path);
        this.parser = new Parser(list);
    }

    /**
     * The main class for which the chatbot runs.
     * @param args The inputs given by the user in the command prompt.
     */
    public static void main(String[] args) {
        final String DATA_DIRECTORY = "data";
        String projectRoot = System.getProperty("user.dir");
        String dataFilePath = projectRoot + "/" + DATA_DIRECTORY + "/tasks.s";

        Duke lati = new Duke(dataFilePath);

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
        this.parser.close();
        System.out.println(" Byeeeeee. Hope to see you again soon~~\n" +
                "____________________________________________________________");
    }

    /**
     * Parses and manages the user's inputs using the parser.
     *
     * @param tally takes in the input string.
     */
    public void messageHandler(String tally) {
        this.parser.messageHandler(tally);
    }


}
