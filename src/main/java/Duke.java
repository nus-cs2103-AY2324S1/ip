public class Duke {

    private static char horizontal_line = '\u2500';
    private static String name = "Chewbacca";

    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);

        start();
        end();
    }

    private static String drawLine() {
        String line = "";
        for (int i = 0; i < 50; i++)
            line += horizontal_line;
        return line;
    }
    private static void start() {

        System.out.println(drawLine());
        System.out.println("Rrrruuuurrr, I am " + name + ", son of Attichitcuk");
        System.out.println("How can Chewie help?\n");

    }

    private static void end() {
        System.out.println(drawLine());
        System.out.println("Chewie is going home now.\nBye bye.\n");
        System.out.println(drawLine());

    }
}
