public class Duke {

    private Ui ui = new Ui();
    private TaskList tasks = new TaskList();

    public void run () {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.readInput();
            ui.showLine();
            Command command = Parser.parse(userInput);
            isExit =  command.execute(this.tasks,ui);;
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
