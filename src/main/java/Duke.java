public class Duke {
    private TaskList taskList;
    private Parser parser;

    public Duke(Storage storage) {
        try {
            this.taskList = new TaskList(storage);
        } catch (GlubException ex) {
            Ui.printError(ex.getMessage());
        }
        this.parser = new Parser(taskList, storage);
    }

    public void run() {
        parser.listen();
    }

    public static void main(String[] args) {
        Duke duke = new Duke(new Storage("tasks.txt"));
        Ui.greet();
        duke.run();
    }

}
