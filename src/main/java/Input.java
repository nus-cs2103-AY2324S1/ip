import java.util.Scanner;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static void echo() {
        while (Input.scanner.hasNext()) {
            String input = Input.scanner.nextLine();

            if (input.equals("bye")) {
                Printing.bye();
                return;
            } else if (input.equals("list")) {
                Printing.list();
            } else if (input.startsWith("mark")) {
                int index = Character.getNumericValue(input.charAt(5)) - 1;
                Storage.markAsDone(index);
                Printing.printMarkAsDone(index);
            } else if (input.startsWith("unmark")) {
                int index = Character.getNumericValue(input.charAt(7)) - 1;
                Storage.unmark(index);
                Printing.printUnmark(index);
            } else {
                Printing.printBlock();
                Storage.addToStorage(input);
                Printing.print("added: " + input);
                Printing.printBlock();
            }
        }

    }
}
