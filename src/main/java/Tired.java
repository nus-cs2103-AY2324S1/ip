enum TaskType {
    TODO, DEADLINE, EVENT
}

public class Tired {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Tired(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } finally { }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isActive = true;
        while (isActive) {
            System.out.println("____________________________________________________________");
            String input = ui.readCommand().trim();
            try {
                isActive = Parser.parseCommand(input, tasks, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
            storage.saveToFile(tasks);
        }
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        String fileName = "duke.txt";
        new Tired(fileName).run();
    }
}