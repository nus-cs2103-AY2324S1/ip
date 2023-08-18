import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "ForsakenX";
        String helloGreeting = String.format("Hello! I'm %s\nWhat can I do for you?", name);
        String byeGreeting = "Bye. Hope to see you again soon! o(╥﹏╥)o";
        Scanner sc = new Scanner(System.in);

        System.out.print(output(helloGreeting));
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.print(output(byeGreeting));
                break;
            } else {
                System.out.print(output(command));
            }
        }
    }

    static String output(String msg) {
        String line = "----------------------------(≧▽≦)----------------------------";
        return String.format("%s\n%s\n%s\n",
                line, msg, line);
    }
}
