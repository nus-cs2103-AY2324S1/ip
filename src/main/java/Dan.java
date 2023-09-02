public class Dan {
    public static void main(String[] args) {
        hello();
        goodbye();
    }

    private final static String greets = "Dan: \n";
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
