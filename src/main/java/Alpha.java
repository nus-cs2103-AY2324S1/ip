import java.util.Scanner;

public class Alpha {

    public static void main(String[] args) {

        System.out.println("______________________________");
        System.out.println("Hello! I'm Alpha.");
        System.out.println("What can I do for you?");
        System.out.println("______________________________");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            System.out.println("______________________________");

            if (!userInput.equals("bye")) {
                System.out.println(userInput);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________");
                return;
            }
            System.out.println("______________________________");

        }
    }
}
