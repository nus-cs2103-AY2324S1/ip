import java.util.Scanner;

public class Duke {
    private static String chatBotName = "Doctor101";

    public static void Greets() {
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
    }

    public static void Bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
       Greets();

       Scanner scanner = new Scanner(System.in);
       String[] store = new String[100];

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                Bye();
                scanner.close();
                break;
            }
            if (input.equals("list")) {
                for (int i = 0; i < store.length; i++) {
                    if (store[i] != null) {
                        System.out.println(i + 1 + ". " + store[i]);
                    }
                }
            }
            else {
                System.out.println("added: " + input);
                for (int i = 0; i < store.length; i++) {
                    if (store[i] == null) {
                        store[i] = input;
                        break;
                    }
                }
            }
        }

         
    }

}
