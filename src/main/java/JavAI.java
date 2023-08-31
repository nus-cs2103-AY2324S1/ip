import java.util.Scanner;

/**
 * JavAI is a simple chatbot that allows users to add, mark, unmark, delete,
 * and list tasks.
 */
public class JavAI {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public JavAI(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                storage.taskListWriter(tasks.getTasksAsArrayList());
                ui.exit();
                break;
            } else {
                try {
                    parser.parse(input, tasks, ui);
                } catch (Exception e) {
                    ui.showLoadingError(e);
                }
            }
        }
    }
    /**
     * The main method that starts the JavAI chatbot.
     *
     * @param args Command-line arguments.
     * @throws JavAIException If there's an exception in the JavAI program.
     * @throws Exception If there's an exception in the JavAI program.
     */
    public static void main(String[] args) {
        new JavAI("./src/main/txtFolder/JavAI.txt").run();
    }
}