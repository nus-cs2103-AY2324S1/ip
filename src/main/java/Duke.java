import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Mathan";
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm "+name+"\n" +
                " What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        while (!str.equals("bye")){
            System.out.println("____________________________________________________________\n" +
                    str+"\n" +
                    "____________________________________________________________");
            str = scanner.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
