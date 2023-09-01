package duke;

public class Duke {
    private TaskList taskList;
    private Parser parser;

    public Duke(String filepath) {
        this.parser = new Parser();
        this.taskList = Storage.load(filepath, parser);
    }

    public void run() {
        Ui.printGreetings();
        while(true) {
            String input = Ui.scanInput();
            parser.handleInput(input, taskList, false);
            if (parser.isExit) {
                Ui.printBYE();
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
