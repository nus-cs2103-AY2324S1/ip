import java.util.Scanner;

public class Dan {
    public static void main(String[] args) {
        hello();
        chat();
        goodbye();
    }


    private final static String greets = "\nDan: \n";
    private static void chat() {
        String text;
        do {
            text = new Scanner(System.in).nextLine();
            System.out.println(greets + text);
        } while (!text.toLowerCase().equals("bye"));
    }
    public static void hello() {
        System.out.println(
                greets +
                " Hello! I'm Dan!\n" +
                " What can I do for you?\n"
        );
    }
    public static void goodbye() {
        System.out.println(
                greets +
                " Bye. Hope to see you again soon!\n"
        );
    }
}
