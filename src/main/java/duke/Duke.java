package duke;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) {
        this.ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidInputException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui ui = new Ui();
        System.out.println(ui.greet());

        while (true) {
            try {
                //activates scanner
                String input = ui.getCommand();

                //handle the input from user
                Command command = Parser.parse(input);
                command.execute(tasks, ui, storage);
                if (command.isExit()) {
                    break;
                }
            } catch (InvalidInputException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(ui.byeGreetings());
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
