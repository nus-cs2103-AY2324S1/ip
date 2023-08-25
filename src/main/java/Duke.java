import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "______________________________________________";

        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine +
                "\nHello i'm ChatterBuddy\n" +
                "Is there anything I can assist you with today?\n" +
                horizontalLine);

        String userInput;

        do {
            userInput = scanner.nextLine();
            System.out.println(userInput + "\n" + horizontalLine);
        } while (!userInput.equalsIgnoreCase("bye"));

        System.out.println("Goodbye. Catch you later!" + "\n" + horizontalLine);
        scanner.close();
    }
}
