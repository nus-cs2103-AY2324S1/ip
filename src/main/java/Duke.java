import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final String line = "_____________________________________________________";
    private final Parser parser;

    private final Ui ui;

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.run();
    }

    public Duke() {
        Storage storage = new Storage("./data/data.txt");
        ArrayList<Task> taskList = storage.read();
        this.ui = new Ui();
        this.parser = new Parser(storage, taskList);
    }

    public void run() {

        ui.greet();

        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    ui.bye();
                    break;
                } else {
                    parser.parse(input);
                }
            } catch (DukeException e) {
                System.out.println(line);
                System.out.println(e);
                System.out.println(line);
            }
        }
    }
}