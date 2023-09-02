import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.File;

public class Qi {
    public static void main(String[] args) {
        // Get the data for the list
        File file = new File("data//list.txt");
        File directory = file.getParentFile();

        // Create directory to the file if it does not exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Cannot create file!");
        }

        Bot qi = new Bot("Qi", file);
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
                int taskId = Integer.parseInt(str.substring(5));
                qi.markTask(taskId);
                continue;
            }

            if (str.startsWith("unmark ")) {
                int taskId = Integer.parseInt(str.substring(7));
                qi.unmarkTask(taskId);
                continue;
            }

            if (str.startsWith("todo")) {
                try {
                    String task = str.substring(5);
                    qi.addTask(task);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("    ____________________________________________________________");
                }
                continue;
            }

            if (str.startsWith("deadline")) {
                // find the first slash
                int idx = 9;
                while (idx < str.length() && str.charAt(idx) != '/') {
                    idx++;
                }

                try {
                    String task = str.substring(9, idx - 1);
                    String deadline = str.substring(idx + 4);
                    qi.addTask(task, LocalDate.parse(deadline));
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println("    ____________________________________________________________");
                }
                continue;
            }

            if (str.startsWith("event")) {
                // find the first slash
                int idx1 = 6;
                while (idx1 < str.length() && str.charAt(idx1) != '/') {
                    idx1++;
                }

                // find the second slash
                int idx2 = idx1 + 1;
                while (idx2 < str.length() && str.charAt(idx2) != '/') {
                    idx2++;
                }

                try {
                    String task = str.substring(6, idx1 - 1);
                    String startTime = str.substring(idx1 + 6, idx2 - 1);
                    String endTime = str.substring(idx2 + 4);
                    qi.addTask(task, startTime, endTime);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     ☹ OOPS!!! The description of an event cannot be empty.");
                    System.out.println("    ____________________________________________________________");
                }
                continue;
            }

            if (str.startsWith("delete")) {
                int taskId = Integer.parseInt(str.substring(7));
                qi.deleteTask(taskId);
                continue;
            }

            System.out.println("    ____________________________________________________________");
            System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("    ____________________________________________________________");
        }
        qi.goodBye();
    }
}
