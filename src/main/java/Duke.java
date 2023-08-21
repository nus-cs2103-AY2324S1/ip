import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Botty";
        String tmp = "";
        String[] taskList = new String[100];
        int counter = 0;
        Scanner scanner = new Scanner(System.in);
        greet(name);
        while (true) {
            tmp = scanner.nextLine();
            if (tmp.equals("bye")) {
                break;
            } else if (tmp.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    tmp = taskList[i];
                    System.out.println((i + 1) + ". " + tmp);
                }
            } else {
                System.out.println("added " + tmp);
                taskList[counter] = tmp;
                counter++;
            }
        }
        bye();
    }
    public static void greet(String name) {
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?\n");
    }
    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }
}
