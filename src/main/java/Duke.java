import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Duke {
    private String name;
    private ArrayList<Task> tasks;
    private String filepath;
    private Storage storage;

    public Duke(String name, String filepath) {
        this.name = name;
        this.filepath = filepath;
        this.storage = new Storage(this.filepath);
        try {
            this.tasks = storage.readFile();
        } catch (FileNotFoundException e) {
            this.tasks = new ArrayList<>();
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Ui.printGreeting(this.name);
        new ListCommand().execute(this.storage, this.tasks);

        while (true) {
            String fullCommand = scanner.nextLine();
            Ui.printLine();

            Parser parser = new Parser();
            Command command = parser.parse(fullCommand);
            if (command == null) {
                continue;
            }
            command.execute(this.storage, this.tasks);
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String name = "Beary";
        String filepath = "data/tasks.txt";
        Duke duke = new Duke(name, filepath);
        duke.run();
    }
}

