package ratspeak;

import ratspeak.parser.CommandProcessor;

public class Duke {

    private final CommandProcessor commandProcessor;
    private final Ui ui;
    private final InputReceiver inputReceiver;

    public Duke() {
        commandProcessor = new CommandProcessor();
        ui = new Ui();
        inputReceiver = new InputReceiver();
    }

    public void run() {
        ui.greeting(commandProcessor.initialReminder());

        while (true) {
            String command = inputReceiver.getInput();

            //break out of the loop when it is "bye"
            if (command.equals("bye")) {
                ui.bye();
                break;
            }

            ui.message(commandProcessor.processCommand(command));
        }

        inputReceiver.closeParser();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    public String getResponse(String command) {
        return commandProcessor.processCommand(command);
    }
}
