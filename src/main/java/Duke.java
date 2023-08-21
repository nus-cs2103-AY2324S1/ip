import java.util.*;
public class Duke {
    public void listen() {

    }
    public static void main(String[] args) {
        String horizontal_line = "____________________________________________________________\n";
        System.out.println(horizontal_line
                            + "Hello! I'm ChadBob.\n"
                            + "What can I do for you?\n"
                            + horizontal_line) ;
        boolean botInUse = true;

        while(botInUse) {
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            if (Objects.equals(input, "bye")) {
                botInUse=false;
                System.out.println(horizontal_line
                                    +  "Bye. Hope to see you again soon!\n"
                                    + horizontal_line);
            } else {
                System.out.println(horizontal_line + input +"\n" + horizontal_line);
            }
        }

    }


}
