import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm FRIDAY!\n" +
                "What can I do for you?");

        echo();
    }

    public static void echo() {
        String task = "";
        while (task != "bye") {
            Scanner newInput = new Scanner(System.in);
            task = newInput.nextLine();
            System.out.println(task);
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

}
