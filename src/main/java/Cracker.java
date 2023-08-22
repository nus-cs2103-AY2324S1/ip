public class Cracker {
    private static String line = "____________________________________________________________";
    public static void main(String[] args) {

        System.out.println(line);
        System.out.println("Hello! I'm Cracker");
        query();
        close();

        
    }

    public static void query(){
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
    public static void close(){
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
