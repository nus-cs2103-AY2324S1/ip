import java.util.Scanner;

public class Ren {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String welcomeMsg = "____________________________________________________________\n" +
                " Hello! I'm Ren\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String goodbyeMsg = "____________________________________________________________\n" +
                " Bye! Pleasure speaking with you :) \n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMsg);
        String inputStr = input.next();
        while (!inputStr.equals("bye")) {
            System.out.println("____________________________________________________________\n" +
                    String.format("%s\n", inputStr) +
                    "____________________________________________________________\n");
            inputStr = input.next();
        }

        System.out.println(goodbyeMsg);
    }
}
