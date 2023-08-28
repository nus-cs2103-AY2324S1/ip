public class Duke {
    private final Parser parser;
    private final TaskList list;
    private final Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.list = new TaskList(filePath);
        this.parser = new Parser(this.list, this.ui);
    }
    public void run() {
        boolean run = true;
        this.ui.greet();
        while (run) {
            try {
                String input = this.ui.readCommand();
                this.parser.parse(input);
                run = this.parser.status();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
