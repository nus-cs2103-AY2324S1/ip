import java.util.Scanner;

public class Duke {

    public static Scanner sc = new Scanner(System.in);
    public static String[] listArr = new String[100];
    public static void add(String s) {
        for (int i = 0; i < 100; i++) {
            if (listArr[i] == null) {
                listArr[i] = s;
                System.out.println("-------------------------------\n"
                        + "added: " + s
                        + "\n-------------------------------\n");
                break;
            }
        }
    }

    public static void listOut(String[] arr) {
        System.out.println("-------------------------------");
        for (int i = 0; i < 100; i++) {
            if (arr[i] != null) {
                int num = i + 1;
                System.out.println(num + ". " + arr[i]);
            }
        }
        System.out.println("-------------------------------\n");
    }

    public static void main(String[] args) {

        String greeting = "-------------------------------\n"
                + "Hello! I'm Skog.\n"
                + "What can I do for you?\n"
                + "-------------------------------\n";

        String exit = "-------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-------------------------------\n";

        System.out.println(greeting);

        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                System.out.println(exit);
            } else if (str.equals("list")) {
                listOut(listArr);
            } else {
                add(str);
            }
        }
    }
}
