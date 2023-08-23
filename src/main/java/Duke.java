import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Hello! I am Nila");
        System.out.println("What can I do for you?");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -\n");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] list = new String[100];
        int i = 0;

        while (!str.equals("bye") && !str.equals("Bye")) {
            if (!str.equals("list") && !str.equals("List")) {
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.println("Added: " + str);
                list[i] = str;
                i++;
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            } else {
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                for (int j = 0; j < i; j++){
                    System.out.println(j+1 +". " + list[j]);
                }
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
                str = sc.nextLine();
            }
        }

        sc.close();
        System.out.println("\nBye. Hope to see you again soon!");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - -");
    }
}
