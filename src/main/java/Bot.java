import java.util.Scanner;

public class Bot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm [YOUR CHATBOT NAME]\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");
        String str = sc.nextLine();
        Storage<String> storage = new Storage<String>();
        while (!str.equals("bye")) {
            System.out.println("____________________________________________________________\n" +
                    str + "\n" +
                    "____________________________________________________________");
            str = sc.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
