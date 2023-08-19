public class Duke {

    public static void horiLine(){
        System.out.println("---------------------");
    }
    public static void greet() {
        horiLine();
        System.out.println("Hello! I'm AJbot\n" +
                " What can I do for you?");
        horiLine();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        horiLine();
    }
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        greet();
        exit();
    }
}
