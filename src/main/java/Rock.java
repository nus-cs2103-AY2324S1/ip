public class Rock {
    private static String LINE_BREAK = "____________________________________________________________\n";
    private static void onReady() {
        String introString = LINE_BREAK +
        "Hello! I'm Rockman.\n" +
        "What can I do for you?\n" + 
        LINE_BREAK;
        System.out.println(introString);
    }
    private static void onTerminate() {
        String exitString = "Bye. Hope to see you again soon\n" + LINE_BREAK;
        System.out.println(exitString);
    }
    public static void main(String[] args) {
        onReady();
        onTerminate();
    }
}
