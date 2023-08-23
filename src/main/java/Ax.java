import java.util.Scanner;

public class Ax {

    private static void hoLine() {
        System.out.println();
        System.out.println("=^..^=   =^..^=   =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=   =^..^=   =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    ");
    }

    private static void greet() {
        System.out.println("\uD83D\uDC4B Greetings!\n" +
                "\n" +
                "Ax at your service! I'm not just any chatbot; I'm Ax – your knowledgeable and engaging virtual companion. Whether you're seeking answers, a friendly chat, or a bit of fun, I'm here to make your experience enjoyable and insightful. Don't hesitate to share your thoughts or questions with me. Let's dive in and start our conversation! How can I assist you today, my friend?");
        hoLine();
    }

    private static void bye() {
        System.out.println("Thank you for your time and I hope you found what you needed!! 🥰");
        hoLine();
    }

    private static boolean getInput(Scanner scanner) {
        String input = scanner.nextLine();
        System.out.println(input);
        System.out.println();
        hoLine();
        if (input.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String newLogo = "                \n" +
                "                \n" +
                "                \n" +
                "    ##          \n" +
                "   ###          \n" +
                "  ## ##  ##  ## \n" +
                "  ## ##   ####  \n" +
                " ##  ##    ##   \n" +
                " ######   ###   \n" +
                "##   ##  ## ##  \n" +
                "##   ## ##  ##  \n" +
                "                \n" +
                "                \n";

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello from\n" + newLogo);
        greet();
        while (true) {
            Boolean done = getInput(scanner);
            if (done) {
                break;
            }
        }
        bye();
    }
}
