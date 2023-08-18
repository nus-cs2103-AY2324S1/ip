import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "ForsakenX";
        String helloGreeting = String.format("Hello! I'm %s\nWhat can I do for you?", name);
        String byeGreeting = "Bye. Hope to see you again soon! o(╥﹏╥)o";
        Scanner sc = new Scanner(System.in);
        String[] textList = new String[100];
        int itemCount = 0;

        System.out.print(output(helloGreeting));
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.print(output(byeGreeting));
                break;
            } else if (command.equals("list")) {
                StringBuilder msg = new StringBuilder();
                for (int i = 1; i <= itemCount; i++) {
                    msg.append(String.format("%d. %s", i, textList[i - 1]));
                    if (i != itemCount) {
                        msg.append("\n");
                    }
                }
                System.out.print(output(msg.toString()));
            } else {
                textList[itemCount] = command;
                itemCount++;
                System.out.print(output("added: " + command));
            }
        }
    }

    static String output(String msg) {
        String line = "----------------------------(≧▽≦)----------------------------";
        return String.format("%s\n%s\n%s\n",
                line, msg, line);
    }
}
