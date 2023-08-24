import java.util.Scanner;

public class JermBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm JermBot");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String currStr = "";
        while (!currStr.equals("bye")) {
            if (currStr != "") {
                System.out.println(currStr);
            }
            currStr = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
