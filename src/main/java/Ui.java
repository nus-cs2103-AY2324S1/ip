public class Ui {
    public static final String i4 = "    ";
    public static final String i5 = Ui.i4 + " ";
    public static final String i7 = Ui.i5 + "  ";
    public static final String line = Ui.i4 + "——————————————————————————————————————————————————————————————————";

    private String name;

    public Ui(String name) {
        this.name = name;
    }

    public static void line() {
        System.out.println(Ui.line);
    }

    public void greet() {
        Ui.line();
        System.out.println(Ui.i5 + "Hello! I'm " + this.name);
        System.out.println(Ui.i5 + "What can I do for you?");
        Ui.line();
    }

    public void exit() {
        System.out.println(Ui.i5 + "Bye. Hope to see you again soon!");
    }
}
