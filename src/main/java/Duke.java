import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println(line + " Hello! I'm Alcazar\n" +
                " What can I do for you?\n" +
                line);
        String prompt = Duke.inputText();
        while (!prompt.equals("bye")) {
            System.out.println(line + prompt + "\n" + line);
            prompt = Duke.inputText();
        }
        System.out.println(line +
                " Bye. Hope to see you again soon!\n" +
                line);
    }
    public static String inputText() {
        Scanner sc =  new Scanner(System.in);
        String inp = sc.nextLine();
        return inp;
    }
}
