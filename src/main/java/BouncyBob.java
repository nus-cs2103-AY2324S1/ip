import java.util.Scanner;

public class BouncyBob {
    private static final String TOP_BORDER = "╔══════════════════════════════════════════════╗";
    private static final String MIDDLE_BORDER = "║                                              ║";
    private static final String BOTTOM_BORDER = "╚══════════════════════════════════════════════╝";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(TOP_BORDER);
        System.out.println(MIDDLE_BORDER);
        System.out.println("║                   Hey there! 🎈              ║");
        System.out.println("║      I'm BouncyBob, your bubbly chatbot!     ║");
        System.out.println("║ Wow you look very round today, like a ball!  ║");
        System.out.println(MIDDLE_BORDER);
        System.out.println(BOTTOM_BORDER);

        while (true) {
            System.out.println("Enter something: ");
            String userInput = scanner.nextLine();
            System.out.println(TOP_BORDER);
            if (userInput.equals("bye")) {
                System.out.println(MIDDLE_BORDER);
                System.out.println("║        BouncyBob: Bye! Bounce back soon!     ║");
                System.out.println(MIDDLE_BORDER);
                System.out.println(BOTTOM_BORDER);
                break;
            } else {
                System.out.println(userInput);
                System.out.println(BOTTOM_BORDER);
            }
        }
    }
}
