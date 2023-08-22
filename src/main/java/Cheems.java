import java.util.Scanner;

public class Cheems {
    private final UI ui = new UI();
    private final Database db = new Database();
    private final Scanner scanner = new Scanner(System.in);
    private final Parser parser = new Parser(this.db);

    public void run() {
        this.ui.showWelcomeMsg();
        String input = this.ui.getInput(this.scanner);

        // business logic
        while (!input.equals("bye")) {
            try {
                parser.parseAndExecute(input);
            } catch (RuntimeException e) {
                System.out.println(e.toString());
            }
            input = this.ui.getInput(this.scanner);
        }

        this.ui.showExitMsg();
    }

    public static void main(String[] args) {
        Cheems cheems = new Cheems();
        cheems.run();
    }
}
