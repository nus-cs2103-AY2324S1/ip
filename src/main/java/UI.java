import java.util.List;

public class UI {
    public void display_lines() {
        for (int i = 0; i < 20; i++) {
            System.out.print((i == 0 ? "-" : " -"));
        }
        System.out.println();
    }

    public void displayGreeting() {
        String logo = "                ;~~,__,\n" + ":-….,———-‘`----/   ._.*\n" + " `-,,,   BRUNO   ,’\n"
                + "     ;   ,~.——;  /\n" + "     :  |     :  |\n" + "     `_ ’     `_ ‘";
        System.out.println(logo);
        String name = "Bruno";
        System.out.println("Woof Woof! I'm " + name + " 🐾");
        System.out.println("How can I help you?");
    }

    public void displayBye() {
        System.out.print("\t");
        System.out.println("Bye Bye! Hope to see you again soon! 🐶");
    }

    public void displayMessage(String s) {
        System.out.println(s);
    }
}
