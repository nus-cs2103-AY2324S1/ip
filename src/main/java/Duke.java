public class Duke {

    Ui ui = new Ui();

    public void run () {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readInput();
            ui.showLine();
            Command command = Parser.parse(userInput);
            command.execute();
            isExit = command.isExit();
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
