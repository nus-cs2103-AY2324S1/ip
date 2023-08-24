import java.util.Scanner;
public class Potato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] store = new String[100];
        int size = 0;

        System.out.println("-----------------------------------------\n" +
                "Hello! I'm Potato\n" + "What can I do for you?\n" +
                "-----------------------------------------\n");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("-----------------------------------------\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "-----------------------------------------\n");
                break;
            } else if (input.equals("list")) {
                int count = 0;
                System.out.println("-----------------------------------------");
                for (String s : store) {
                    if (s == null) break;
                    count ++;
                    System.out.println(String.valueOf(count) + ". " + s);
                }
                System.out.println("-----------------------------------------\n");
            } else {
                store[size] = input;
                size++;
                System.out.println("-----------------------------------------\n" +
                        "added: " + input + "\n" +
                        "-----------------------------------------\n");
            }
        }
    }
}
