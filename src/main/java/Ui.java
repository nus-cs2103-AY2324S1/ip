public class Ui {
    static String welcome = "Hello! I'm Eddie\n" +
            "What can I do for you?";
    public static void welcome() {
        System.out.println(welcome);
    }

    public static void exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
    }

    public static void removeTask(String task, int size) {
        System.out.println("Noted. I've removed this task: \n" +
                task + "\n" +
                "Now you have " + size + " tasks in the list.");
    }

    public static void listTask(int num, String name) {
        System.out.println(num + ". " + name);
    }

    public static void addTask(String task, int size) {
        System.out.println("Got it. I've added this task:\n "
                + task + "\n"
                + "Now you have " + size + " tasks in the list.");
    }

    public static void clear(){
        System.out.println("List Cleared!");
    }
}
