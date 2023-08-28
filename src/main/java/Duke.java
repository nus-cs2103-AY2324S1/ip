import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class Duke {

    /** name of ChatBot */
    private final String name = "Ken";

    /** store user Input in Task array */
    private TaskList tasks;

    /**
     * Initialize the fixed sized array.
     */
    public Duke() {

        storage = new Storage();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private Storage storage;
    /**
     * Return the name of the Duke ChatBot.
     *
     * @return this.name.
     */
    private String getName() {
        return this.name;
    }

    private TaskList getTaskList() {
        return this.tasks;
    }

    public static void main(String[] args) {

        Duke chatBot = new Duke();

        String horLine = "____________________________________________________________";
        String userInput = "";
        Scanner input = new Scanner(System.in);

        System.out.println(horLine);
        System.out.println("Hello! I'm " + chatBot.getName() + "!");
        System.out.println("What can I do for you?");
        System.out.println(horLine);

        while (!userInput.equals("bye")) {
            userInput = input.nextLine();
            System.out.println(horLine);
            System.out.println(Parser.replyUser(userInput, chatBot.getTaskList()));
            System.out.println(horLine);
            chatBot.exit();
        }
    }

    private void exit() {
        try {
            storage.save(tasks.getTasks());
        } catch (DukeException e) {
            System.out.println(e);
        }

    }
}
