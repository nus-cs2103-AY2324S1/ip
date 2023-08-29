package duke;
public class Duke {

    private final CommandProcessor commandProcessor;
    private final Ui ui;
    private final Parser parser;

    public Duke() {
        commandProcessor = new CommandProcessor();
        ui = new Ui();
        parser = new Parser();
    }

    public void run() {
        ui.greeting();

        while (true) {
            // getting the input command
            String command = parser.getInput();

            //break out of the loop when it is "bye"
            if (command.equals("bye")) {
                ui.bye();
                break;
            }

            // process the command
            ui.message(commandProcessor.processCommand(command));


        }

        parser.closeParser();
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
