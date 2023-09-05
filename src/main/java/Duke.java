import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;

    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
            tasks = new TaskList();
        }
        this.parser = new Parser(ui, storage, tasks);
    }

    public void run() {
        ui.intromsg();

        Scanner scan = new Scanner(System.in);

        String userinput = scan.nextLine();
        while (!userinput.equalsIgnoreCase("bye")) {
            try {
                parser.parse(userinput);
            } catch (DukeException e) {
                System.out.println(e);
            }
            userinput = scan.nextLine();
        }
        ui.byemsg();
    }


    public static void inputChecker(Task task) throws DukeException {
        if (task.toString().trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a " + task + " cannot be empty.");
        }
    }

    public static void cansplit(String input, String splitter) throws DukeException {
        if (input.split(splitter).length == 1) {
            throw new DukeException("☹ OOPS!!!");
        }
    }
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
