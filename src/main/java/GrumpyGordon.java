import java.util.*;
public class GrumpyGordon {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Oi! I'm Grumpy Gordon.");
        System.out.println("    Why are you bothering me?");
        System.out.println("    ____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        while (!str.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + str);
            System.out.println("    ____________________________________________________________");
            str = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to never see you again.");
        System.out.println("    ____________________________________________________________");
    }
}
