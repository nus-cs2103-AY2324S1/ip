public class Duke {

    private final String name = "Ken";

    private String getName() {
        return this.name;
    }
    public static void main(String[] args) {

        Duke chatBot = new Duke();
        String horLine = "____________________________________________________________";
        String userInput = "";

        System.out.println(horLine);
        System.out.println("Hello! I'm " + chatBot.getName() + "!");
        System.out.println("What can I do for you?");
        System.out.println(horLine);
        System.out.println("Bye. hope to see you again soon!");
        System.out.println(horLine);
    }
}
