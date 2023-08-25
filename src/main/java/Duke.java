import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "______________________________________________";

        Scanner scanner = new Scanner(System.in);

        System.out.println(horizontalLine +
                "\nHello i'm ChatterBuddy\n" +
                "Is there anything I can assist you with today?\n" +
                horizontalLine);

        ArrayList<String> allInputs = new ArrayList<>();
        String userInput;

        do {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < allInputs.size(); i++) {
                    System.out.println((i + 1) + ". " + allInputs.get(i));
                }
            } else {
                    System.out.println("added: " + userInput + "\n" + horizontalLine);
                    if (!userInput.equalsIgnoreCase("list")) {
                        allInputs.add(userInput);
                    }
                }

        } while (!userInput.equalsIgnoreCase("bye"));

        System.out.println("Goodbye. Catch you later!" + "\n" + horizontalLine);
        scanner.close();
    }
}
