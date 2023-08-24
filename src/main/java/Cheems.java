import java.util.Scanner;

public class Cheems {
    private final static UI ui = new UI();
    private final static Scanner scanner = new Scanner(System.in);
    private final Parser parser;
    private final Database db;
    public Cheems() {
        this.db = new Database();
        this.parser = new Parser(this.db);
    }

    public void run() {
        ui.showWelcomeMsg();
        String input = ui.getInput(scanner);

        // business logic
        while (!input.equals("bye")) {
            try {
                parser.parseAndExecute(input);
            } catch (RuntimeException e) {
                System.out.println(e.toString());
            }
            input = ui.getInput(scanner);
        }

        ui.showExitMsg();
    }

    public static void main(String[] args) {
        Cheems cheems = new Cheems();
        cheems.run();
    }
}
