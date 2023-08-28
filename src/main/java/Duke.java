
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

            ui.setAnswerBorder();
            // process the command
            commandProcessor.processCommand(command);
            ui.setAnswerBorder();


        }

        parser.closeParser();
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
