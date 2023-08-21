import java.util.Scanner;

public class Cheems {
    private UI ui;
    public Cheems() {
        this.ui = new UI();
    }
    public void run() {
        this.ui.showWelcomeMsg();
        Scanner scanner = new Scanner(System.in);
        // first message
        System.out.print("You: ");
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.print("Cheems: " + input + "\n");
            System.out.print("You: ");
            input = scanner.nextLine();
        }

        this.ui.showExitMsg();
    }
    public static void main(String[] args) {
        Cheems cheems = new Cheems();
        cheems.run();
    }
}
