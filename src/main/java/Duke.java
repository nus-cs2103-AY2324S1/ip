public class Duke {
    public static void main(String[] args) {
        String line = "---------------------------------------";
        String name = "ForsakenX";
        String helloGreeting = String.format("Hello! I'm %s\nWhat can I do for you?", name);
        String byeGreeting = "Bye. Hope to see you again soon!";

        System.out.println(String.format("%s\n%s\n%s\n%s\n%s",
                line, helloGreeting, line, byeGreeting, line));
    }
}
