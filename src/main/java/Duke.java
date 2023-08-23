public class Duke {
    private static String NAME = "Moira";
    private static String SPACER = "--------------------------------------------------------------------------";

    public static void main(String[] args) {
        greet();
        exit();
    }

    private static void greet() {
        System.out.println(SPACER);
        System.out.println("Howdy, I'm + " + NAME + ", your friendly personal assistant!");
        System.out.println("What can I do for you today?");
        System.out.println(SPACER);
    }

    private static void exit() {
        System.out.println(SPACER);
        System.out.println("See ya later, alligator! I'm waiting here if you need anything :>");
        System.out.println(SPACER);
    }
}
