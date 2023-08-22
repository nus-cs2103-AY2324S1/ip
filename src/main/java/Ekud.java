public class Ekud {
    public static void main(String[] args) {
        String horizontalLine = "------------------------------------";
        String intro = "Hello there! I'm Ekud.\nWhat can I do for you?";
        String outro = "Hope to see you again soon, goodbye!";
        System.out.println(
                String.format("%s\n%s\n%s\n%s\n%s",
                        horizontalLine,
                        intro,
                        horizontalLine,
                        outro,
                        horizontalLine)
        );
    }
}
