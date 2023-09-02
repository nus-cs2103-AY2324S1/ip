public class Bareum {
    static TaskList taskList = new TaskList();

    public void run() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage();
        boolean isExit = false;

        storage.loadSavedTaskList(taskList);
        ui.showWelcomeMessage();

        while (!isExit) {
            try {
                ui.showLine();
                String input = ui.getUserInput();
                Command cmd = parser.parse(input);
                cmd.execute(ui, storage, taskList);
                isExit = cmd.isExit();
            } catch (BareumException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Bareum().run();
    }
}
