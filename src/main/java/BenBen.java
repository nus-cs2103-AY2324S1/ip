import java.util.Scanner;

public class BenBen {
    private static String[] arr;
    private static String line ="_______________________________________\n";
    private static int counter = 0;

    public static void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!\n" + line);
        System.exit(0);
    }

    public static void add(String task) {
        arr[counter] = task;
        counter++;
        System.out.println(line);
        System.out.println("added: " + task);
        System.out.println(line);
    }

    public static void iterList() {
        System.out.println(line);
      for(int i = 0; i < counter; i++) {
          System.out.println(i + ". " + arr[i]);
      }
        System.out.println(line);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        arr = new String[100];
        System.out.println(line);
        System.out.println("Hello! I'm BenBen.\n" +
                "What can I do for you?");
        System.out.println(line);;

        while(sc.hasNext()) {
            String next = sc.nextLine();

            if (next.equals("bye")) {
                exit();
                break;
            }

            if (next.equals("list")) {
                iterList();
                continue;
            }

            add(next);
        }
        System.exit(0);
    }
}
