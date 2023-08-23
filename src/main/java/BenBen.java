import java.util.Scanner;

public class BenBen {
    public static void main(String[] args) {
        //initial
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        //level 0
        String line ="_______________________________________\n";
        System.out.println(line);
        System.out.println("Hello! I'm BenBen.\n" +
                "What can I do for you?");
        System.out.println(line);
//        System.out.println(line);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String next = sc.nextLine();

            if (next.equals("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!\n" + line);
                break;
            }
            System.out.println(line);
            System.out.println(next);
            System.out.println(line);
        }
        System.exit(0);
    }
}
