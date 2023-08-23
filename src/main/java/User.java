import java.util.Scanner;
public class User {
    private String text;

    public User(Scanner sc) {

        while (sc.hasNextLine()) {
            this.text = sc.nextLine();
            System.out.println(text);
            if (text.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Till the next time.");
                break;
            }
        }

    }
}
