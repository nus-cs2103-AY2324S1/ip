import java.time.format.DateTimeFormatter;

public class Duke {

    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy - HHmm");
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

    public static void main(String[] args) {
        Duke max = new Duke("data/duke.txt");
        max.run();
    }

    public void run() {
        this.ui.greet();;
        try {
            this.ui.getUserInput(this.taskList, this.storage);
        } catch (DukeException e) {
            System.out.println("\n" + e.getMessage());
        } finally {
            this.ui.exit();
        }
    }
}
