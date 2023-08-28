import java.util.ArrayList;

public class Duke {
    private final Parser parser;

    private final Ui ui;

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }

    public Duke() {
        Storage storage = new Storage("./data/data.txt");
        ArrayList<Task> taskList = storage.read();
        this.ui = new Ui();
        this.parser = new Parser(storage, taskList);
    }

    public void run() {

        ui.showLine();
        ui.greet();
        ui.showLine();

        while (true) {
            try {
                String input = ui.readInput();
                if (input.equals("bye")) {
                    ui.showLine();
                    ui.bye();
                    ui.showLine();
                    break;
                } else {
                    ui.showLine();
                    parser.parse(input);
                    ui.showLine();
                }
            } catch (DukeException e) {
                System.out.println(e);
                ui.showLine();
            }
        }
    }
}