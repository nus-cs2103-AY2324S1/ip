public class Duke {
    public static void main(String[] args) {
        String name = "Faiz";
        String line = "------------------------\n";
        String greeting = line + "Hello! I'm " + name + "\n" + "What can I do for you?" + "\n";
        String goodbye = line + "Bye. Hope to see you again soon!" + "\n" + line;
        String logo = greeting + goodbye;
        System.out.println(logo);
    }
}
