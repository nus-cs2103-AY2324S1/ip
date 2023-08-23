import java.util.Scanner;
public class Sae {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Sae\nWhat can I do for you?");

        while (true) {
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            if (str.equals("bye")) {
                break;
            }
            else {
                System.out.println(str);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
