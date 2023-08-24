import java.util.Scanner;

public class JermBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm JermBot");
        System.out.println("What can I do for you?");

        String[] storage = new String[100];
        int numOfItems = 0;
        Scanner sc = new Scanner(System.in);
        String currStr = sc.nextLine();
        while (true) {
            if (currStr.equals("bye")) {
                break;
            }

            if (currStr.equals("list")) {
                for (int i = 0; i < numOfItems; i++) {
                    System.out.print(i + 1);
                    System.out.print(". " + storage[i] + "\n");
                }
            } else {
                storage[numOfItems] = currStr;
                numOfItems++;
                System.out.println("added: " + currStr);
            }

            currStr = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
