import java.util.Scanner;

// Solution below inspired by https://stackoverflow.com/questions/47150081/while-loop-for-multiple-inputs
// Solution below inspired by ChatGPT, to solve the issue in the else block of incrementing the num_items counter to add the new item subsequently.
/* Solution below inspired by ChatGPT, to solve the issue for the command list, to show the separators only at the start and end, by moving
the statement outside the for loop
 */

public class Duke {
    public static void main(String[] args) {
        String separators = "____________________________________________________________";
        String text1 = " Hello! I'm Novo\n"
                + " What can I do for you?\n" + separators
                + "\n";
        String text2 = " Bye. Hope to see you again soon!";
        System.out.println(separators + "\n" + text1);

        Scanner sc = new Scanner(System.in);
        String user_text = sc.nextLine();
        String[] stringArray = new String[100];
        int num_items = 0;

        while (!user_text.isEmpty()) {
            if (user_text.equals("bye")) {
                System.out.println(separators + "\n" + text2 + "\n" + separators + "\n");
                break;
            } else if (user_text.equals("list")) {
                System.out.println(separators);
                for (int i = 0; i < num_items; i++) {
                    System.out.println((i + 1) + ". " + " " + stringArray[i]);
                }
                System.out.println(separators);
            } else {
                stringArray[num_items] = user_text;
                num_items++;
                System.out.println(separators + "\n" + "added: " + user_text + "\n" + separators + "\n");
            }
            user_text = sc.nextLine();
        }
    }
}
