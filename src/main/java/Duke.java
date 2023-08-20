import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Muddy\n" + "What can I do for you?");
        int number = 0;
        String[] list = new String[100];

        while (true) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equalsIgnoreCase("list")) {
                if (number == 0) {
                    System.out.println("List is empty");
                    continue;
                } else {
                    System.out.println("List:");
                    for (int i = 0; i < number; i++) {
                        String item = list[i];
                        System.out.println((i + 1) + ". " + item);
                    }
                }
            } else {
                list[number] = input;
                number++;
                System.out.println("added: " + input);
            }
        }
        in.close();
    }
}
