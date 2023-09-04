public class Thea {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Thea(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        try {
            this.tasks = new TaskList(storage.retrieveTasks());
        } catch (FileCorruptedException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readNextLine();
            try {
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit;
            } catch (EmptyDescriptionException | WrongCommandException
                     | WrongDateTimeFormatException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Thea("thea.txt").run();
    }
}
