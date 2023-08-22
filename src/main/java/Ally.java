import java.util.Scanner;
public class Ally {

    private static final String line = "____________________________________________________________";
    private static final String greeting = "Hello! I'm ALLY\n What can I do for you?\n";
    private static final String bye = "Bye. Hope to see you again soon!";
    private void start() {
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
    }

    public void list() {
        System.out.println(line);
        System.out.println("\tlist");
        System.out.println(line);
    }

    public void blah() {
        System.out.println(line);
        System.out.println("\tblah");
        System.out.println(line);
    }

    public void bye() {
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Ally ally = new Ally();
        ally.start();
        Scanner scanner = new Scanner(System.in);
        String ipt;
        while(true) {
            ipt = scanner.nextLine();
            if (ipt.equals("bye")) {
                break;
            } else if (ipt.equals("list")){
                ally.list();
            } else if (ipt.equals("blah")) {
                ally.blah();
            }
        }
            ally.bye();
            scanner.close();

    }
}
