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
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        Ally ally = new Ally();
        ally.start();

    }
}
