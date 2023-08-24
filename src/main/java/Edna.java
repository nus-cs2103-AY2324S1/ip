import java.util.Scanner;

public class Edna {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        String partition = "\n----------------------------------------\n";

        System.out.println(partition + "Hello! I'm Edna.");
        System.out.print("What can I do for you?" + partition);
        input = sc.nextLine();

        while(input.equals("bye") == false) {
            System.out.print(input + partition);
            input = sc.nextLine();
        }

        System.out.print("Bye. Hope to see you again soon!" + partition);
    }
}
