import java.util.Scanner;
public class Duke {
    public static void display_lines() {
        for (int i = 0; i < 20; i++) {
            System.out.print("- ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        display_lines();
        String logo = "                ;~~,__,\n" +
                ":-….,———-‘`----/   ._.*\n" +
                " `-,,,   BRUNO   ,’\n" +
                "     ;   ,~.——;  /\n" +
                "     :  |     :  | \n" +
                "     `_ ’     `_ ‘";
        System.out.println(logo);
        String name = "Bruno";
        System.out.println("Woof Woof! I'm " + name + " 🐾");
        System.out.println("How can I help you?");
        display_lines();
        String s = "";
        do {
            s = sc.next();
            display_lines();
            if (!s.equals("bye")) {
                System.out.print("\t");
                System.out.println(s);
                display_lines();
            }
            else {
                System.out.println("Bye Bye! Hope to see you again soon! 🐶");
                display_lines();
                break;
            }
        } while (true);
    }
}
