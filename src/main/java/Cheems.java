import java.util.Scanner;

public class Cheems {
    private final static Scanner scanner = new Scanner(System.in);
    private final Parser parser = new Parser();

    public void run() {
        UI.showWelcomeMsg();
        String input = UI.getInput(scanner);

        // business logic
        while (!input.equals("bye")) {
            try {
                parser.parseAndExecute(input);
            } catch (RuntimeException e) {
                System.out.println(e.toString());
            }
            input = UI.getInput(scanner);
        }

        UI.showExitMsg();
    }

    public static void main(String[] args) {
        Cheems cheems = new Cheems();
        cheems.run();
    }
}
