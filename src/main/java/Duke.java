import java.io.IOException;
import java.util.Scanner;

//copy this into the actual Duke file later!1!!!!
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks(), ui);
    }

    public void run() {
        this.ui.greeting();
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (parser.isGoodbye(input)) {
                    break;
                } else {
                    parser.parseInput(input, storage, tasks, ui);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        this.ui.bye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt").run();
    }
}
