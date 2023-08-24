import java.util.Scanner;
public class Duke {

    static boolean allowNext = true;

    public static void handleExit(String s) {
        String exit = "Bye. See you soon! :)\n";


        if (s.equals("bye")) {
            System.out.println(exit);
            allowNext = false;
        }

    }

    public static void main(String[] args) {
        String greet = "Hi! I'm Uke\n" + "What can I do for you?\n";
        System.out.println(greet);

        Scanner input = new Scanner(System.in);

        while (allowNext) {
            String str = input.nextLine();
            handleExit(str);

            if (allowNext) {
                System.out.println(str);
            }
        }
    }
}
