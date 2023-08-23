import java.util.Scanner;
public class Ally {

    private static final String line = "____________________________________________________________";
    private static final String greeting = "Hello! I'm ALLY\n What can I do for you?\n";
    private static final String bye = "Bye. Hope to see you again soon!";
    public static void start() {
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
    }

    public static void bye() {
        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        

        Scanner scanner = new Scanner(System.in);
        AllyList ally = new AllyList();
        start();
        String ipt;
        while(true) {
            ipt = scanner.nextLine();
            String[] split = ipt.split(" ");

            if (split[0].equals("bye")) {
                bye();
                break;
            } else if (split[0].equals("list")) {
                System.out.println(line);
                ally.printElements();
                System.out.println(line);
            } else if (split[0].equals("mark")) {
                int index = Integer.parseInt(split[1]) - 1;
                System.out.println(line);
                ally.markAsDone(index);
                System.out.println(line);
            } else if (split[0].equals("unmark")) {
                int index = Integer.parseInt(split[1]) - 1;
                System.out.println(line);
                ally.unMarkDone(index);
                System.out.println(line);
            }
            else {
                ally.addElements(ipt);
                System.out.println(line + "\n" + "added: " + ipt + "\n" + line);
            }
        }
        scanner.close();


    }
}
