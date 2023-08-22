import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Chatty\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String[] ls = new String[100];
        int i = 0;

        while (true) {
            String userinput = scanner.nextLine();
            if (userinput.equals("list")) {
                for (int j = 0; j < i; j++) {
                    int index = j + 1;
                    System.out.println(index + ". " + ls[j]);
                }
            } else if (userinput.equals("bye")) {
                break;
            } else {
                ls[i] = userinput;
                i++;
                System.out.println("added: " + userinput);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
