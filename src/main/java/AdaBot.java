import java.util.ArrayList;
import java.util.Scanner;

public class AdaBot {
    private static final String helloString = "Hello! I'm AdaBot.\nWhat do you want to do today?";
    private static final String byeString = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(helloString);
        String response = "";
        ArrayList<String> tasks = new ArrayList<String>();

        while (true) {
            response = input.nextLine();
            if (response.equals("bye")) {
                break;
            } else if (response.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
                }
            } else {
                tasks.add(response);
                System.out.println("added: " + response);
            }
        }
        System.out.println(byeString);
        input.close();
    }
}
