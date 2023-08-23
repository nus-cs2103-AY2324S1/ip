import java.util.ArrayList;
import java.util.Scanner;

public class Moss {
    static ArrayList<String> things = new ArrayList<>();
    public static void main(String[] args) {

        String greet = "____________________________________________________________\n"
                + "Hello! I'm Moss \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while (!message.equals("bye")) {
            if (message.equals("list")){
                System.out.println("____________________________________________________________");
                for (int i = 0; i < things.size(); i++) {
                    System.out.println(i+1 + ". " + things.get(i));
                }
                System.out.println("____________________________________________________________");
            }
            else {
                add(message);
            }
            message = sc.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void add(String message) {
        things.add(message);
        System.out.println("____________________________________________________________");
        System.out.println("added: " + message);
        System.out.println("____________________________________________________________");
    }
}
