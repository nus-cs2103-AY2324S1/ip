import java.lang.reflect.Array;
import java.util.Scanner;

public class ChatbotAlain {
    public static void main(String[] args) {
        String[] list = new String[200];
        int pos = 0;
        String renameAndGreeting = "____________________________________________________________\n"
                + " Hello! I'm Alain\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(renameAndGreeting);
        while (true) {
            Scanner s = new Scanner(System.in);
            String text = new String();
            text = s.nextLine();
            if (text.equals("bye")) {
                break;
            } else if (! text.equals("list")){
                list[pos] = text;
                pos ++;
                String output = "____________________________________________________________\n"
                        + " " + text + "\n"
                        + "____________________________________________________________\n";
                System.out.println(output);

            } else {
                String output = "";
                output += "____________________________________________________________\n";
                for (int i = 0; i < pos; i++ ) {
                    output += " " + (i + 1) + ". " + list[i] + "\n";
                }
                output += "____________________________________________________________\n";
                System.out.println(output);
            }
        }
        String endingLine = "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        
        System.out.println(endingLine);
    }
}
