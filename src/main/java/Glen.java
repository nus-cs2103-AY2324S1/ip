import java.util.Scanner;

/**
 * Glen is a chatbot that helps you keep track of your tasks.
 */
public class Glen {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;

    /**
     * Creates a new Glen object.
     *
     * @param filePath The path to the file where the tasks are stored.
     */
    public Glen(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.read();
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        System.out.println(ui.intro());

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.toLowerCase().equals("bye")) {
            String output = new Parser(storage, tasks).parseInput(input);
            System.out.println(output);
            input = scan.nextLine();
        }

        System.out.println(ui.exit());
        scan.close();
    }

    /**
     * Runs the chatbot.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Glen("./data/tasks.txt").run();
    }
}
