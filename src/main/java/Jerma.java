import java.util.Scanner;

public class Jerma {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Jerma.");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye"))
                break;
            else
                System.out.println(input);
        }
        scanner.close();

        System.out.println("See ya soon!");
    }
}
