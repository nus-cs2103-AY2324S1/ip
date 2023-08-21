import java.util.Scanner;

public class Cheems {
    private UI ui = new UI();
    private Database db = new Database();

    public void run() {
        this.ui.showWelcomeMsg();
        Scanner scanner = new Scanner(System.in);

        // first message
        String input = this.ui.getInput(scanner);

        // echo
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                this.db.displayData();
            } else {
                this.db.addEvent(input);
            }
            input = this.ui.getInput(scanner);
        }

        this.ui.showExitMsg();
    }

    public static void main(String[] args) {
        Cheems cheems = new Cheems();
        cheems.run();
    }
}
