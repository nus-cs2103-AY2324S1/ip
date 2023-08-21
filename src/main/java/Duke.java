import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        String[] items = new String[100];
        int itemCount = 0;

        System.out.println(
                "____________________________________________________________\n" +
                        "Hello! I'm Ding!\n" +
                        "I don't know what I'm supposed to do...\n" +
                        "____________________________________________________________\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(String.format("%d. %s", i+1, items[i]));
                }
            } else {
                items[itemCount] = str;
                if (itemCount < 100) {
                    itemCount++;
                }
                System.out.println(String.format("Ding: What does '%s' mean? I'll just add it anyway.", str));
            }
            System.out.println("\n____________________________________________________________\n");
            str = sc.nextLine();
        }
        System.out.println(
                "____________________________________________________________\n" +
                        "Bye. Hopefully I get to see you again soon!\n" +
                        "____________________________________________________________");

    }

}
