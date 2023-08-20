import java.util.Scanner;

public class Sally {
    public static void main(String[] args) {
        System.out.println("Hey! It's Sally here!\n" + "How can I help you today?\n \n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye! See you again next time.");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
