import java.util.Scanner;

public class Homepage {
    public static void main(String[] args) {
        System.out.println(
                "____________________________________________________________\n"
                + "Hello! I'm Bobi >.< , your trusted companion.\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n"
        );

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();

        while (true) {
            if (answer.equalsIgnoreCase("bye")) {
                break;
            } else {
                System.out.println(
                        "____________________________________________________________\n"
                        + answer + "\n"
                        + "____________________________________________________________\n");
                answer = sc.nextLine();
            }
        }

        System.out.println(
                "____________________________________________________________\n"
                + "Bye! Hope you have a good day today :)\n"
                + "____________________________________________________________\n"
        );
        sc.close();
        return;
    }
}
