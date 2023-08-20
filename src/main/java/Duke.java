import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String exitMsg = "Bye. Hope to see you again soon!";
        String partition = "--------------------------------------";
        System.out.println(partition + "\n" + "Hello! I'm Rion");
        System.out.println("What can I do for you?\n" + partition);

        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while(!echo.equals("bye")) {
            System.out.println(partition + "\n   " + echo + "\n" + partition);
            echo = sc.nextLine();
        }
        System.out.println(partition + "\n   " + exitMsg + "\n" + partition);
    }
}
