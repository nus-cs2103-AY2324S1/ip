import java.util.ArrayList;
import java.util.Scanner;

public class Smolbrain {
    public static void main(String[] args) {

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Smolbrain\nWhat can I do for you?");
        System.out.println("____________________________________________________________\n");

        Scanner sc = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<>();

        while(true) {
            String input = sc.nextLine();
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________\n");
                break;
            }

            switch (input) {
                case "list":
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println((i+1) + ". " + data.get(i));
                    }
                    break;

                default:
                    data.add(input);
                    System.out.println("added: " + input);
                    break;
            }
            System.out.println("____________________________________________________________\n");
        }

    }
}
