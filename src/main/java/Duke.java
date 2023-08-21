import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {


        System.out.println(
                "____________________________________________________________\n" +
                        "Hello! I'm Ding!\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(str);
            System.out.println("____________________________________________________________");
            str = sc.nextLine();
        }
        System.out.println(
                "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");

    }

}