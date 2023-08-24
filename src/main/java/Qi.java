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

            if (str.startsWith("mark ")) {
                int taskIdx = Integer.parseInt(str.substring(5));
                qi.markTask(taskIdx);
                continue;
            }

            if (str.startsWith("unmark ")) {
                int taskIdx = Integer.parseInt(str.substring(7));
                qi.unmarkTask(taskIdx);
                continue;
            }

            if (str.startsWith("todo ")) {
                String task = str.substring(5);
                qi.addTask(task);
                continue;
            }

            if (str.startsWith("deadline ")) {
                // find the first slash
                int idx = 9;
                while (str.charAt(idx) != '/') {
                    idx++;
                }

                String task = str.substring(9, idx - 1);
                String deadline = str.substring(idx + 4);

                qi.addTask(task, deadline);
                continue;
            }

            if (str.startsWith("event ")) {
                // find the first slash
                int idx1 = 6;
                while (str.charAt(idx1) != '/') {
                    idx1++;
                }

                // find the second slash
                int idx2 = idx1 + 1;
                while (str.charAt(idx2) != '/') {
                    idx2++;
                }

                String task = str.substring(6, idx1 - 1);
                String startTime = str.substring(idx1 + 6, idx2 - 1);
                String endTime = str.substring(idx2 + 4);
                qi.addTask(task, startTime, endTime);
            }
        }
        qi.goodBye();
    }
}
