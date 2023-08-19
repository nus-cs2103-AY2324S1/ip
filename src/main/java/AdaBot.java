import java.util.Scanner;

public class AdaBot {
    private static final String helloString = "Hello! I'm AdaBot.\nWhat do you want to do today?";
    private static final String byeString = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(helloString);
        String response = input.nextLine();

        if (response.equals("bye")) {
            System.out.println(byeString);
        }
        input.close();
    }
}
