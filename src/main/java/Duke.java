import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "   ------------------------\n";
        System.out.println(horizontalLine
                + "     GREETINGS HUMAN! I AM QLATZ! □ \n"
                + "     I AM NOW A PARROT\n\n"
                + horizontalLine);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("bye"))
                break;
            text = "     " + text;
            System.out.println("\n" + horizontalLine + text + "\n" + horizontalLine);
        }

        System.out.println("     BYE!\n" + horizontalLine);
    }
}
