import java.util.Scanner;
public class Qi {
    public static void main(String[] args) {
        Bot qi = new Bot("Qi");
        qi.greeting();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            if (str.equals("bye")) {
                break;
            }

            if (str.equals("list")) {
                qi.showTask();
                continue;
            }

            if (str.length() >= 6 && str.substring(0, 4).equals("mark")) {
                int taskIdx = Integer.parseInt(str.substring(5, str.length()));
                qi.markTask(taskIdx);
                continue;
            }

            if (str.length() >= 8 && str.substring(0, 6).equals("unmark")) {
                int taskIdx = Integer.parseInt(str.substring(7, str.length()));
                qi.unmarkTask(taskIdx);
                continue;
            }

            qi.addTask(str);
        }
        qi.goodBye();
    }
}
