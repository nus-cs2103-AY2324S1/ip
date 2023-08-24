public class Duke {
    private static String horiLine = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(horiLine);
        System.out.println(greetFunction("Jack"));
        System.out.println(byeFunction());
    }

    public static String greetFunction(String name){
        String greetings = "Hello! I'm " + name + "\n"
                + "What can I do for you?\n"
                + horiLine;
        return greetings;
    }
    public static String byeFunction(){
        String byeword = "Bye. Hope to see you again soon";
        return byeword;
    }
}
