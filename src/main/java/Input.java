import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);
    public static String getInput() {
        String output = Input.scanner.nextLine();
        Input.scanner.close();
        return output;
    }

    public static void echo() {
        while (Input.scanner.hasNext()) {
            String input = Input.scanner.nextLine();
            if (input.equals("bye")) {
                Printing.bye();
                return;
            }
            Printing.printBlock();
            Printing.print(input);
            Printing.printBlock();
        }

    }
}
