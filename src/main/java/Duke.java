import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String horizontalLine = "_".repeat(35) + "\n";
    public static void printList(ArrayList<String> arr) {
        int count = 0;
        System.out.println(horizontalLine);
        for (String str : arr) {
            count++;
            System.out.println( count+"." + str);
        }
        System.out.println(horizontalLine);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String task = "";
        ArrayList<String> arr = new ArrayList<>();


        System.out.println("Hello! I'm Bot\n" + "What can I do for you?\n" + horizontalLine);
        while (true) {
            task = sc.nextLine();
            if (task.equals("bye")) {
                break;
            }
            if (task.equals("list")) {
                Duke.printList(arr);
                task = "";
                continue;
            }
            arr.add(task);
            System.out.println(horizontalLine + "added: " + task + "\n" + horizontalLine);
        }
        System.out.println(horizontalLine+ "Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
