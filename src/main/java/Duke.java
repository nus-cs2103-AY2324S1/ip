import java.util.*;
public class Duke {
    public static String stringifyList(LinkedList<String> linkedList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < linkedList.size(); i++) {
            str.append(String.valueOf(i + 1)).append(". ").append(linkedList.get(i)).append("\n");
        }
        return str.toString();
    }
    public static void main(String[] args) {
        String horizontal_line = "____________________________________________________________\n";
        System.out.println(horizontal_line
                            + "Hello! I'm ChadBob.\n"
                            + "What can I do for you?\n"
                            + horizontal_line) ;
        boolean botInUse = true;
        LinkedList<String> storage = new LinkedList<>();
        while(botInUse) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (Objects.equals(input, "bye")) {
                botInUse=false;
                System.out.println(horizontal_line
                                    +  "Bye. Hope to see you again soon!\n"
                                    + horizontal_line);
            } else if (Objects.equals(input, "list")) {
                String outputList = Duke.stringifyList(storage);
                System.out.println(horizontal_line + outputList + horizontal_line);
            }
            else {
                storage.add(input);
                System.out.println(horizontal_line + "added: " + input +"\n" + horizontal_line);
            }
        }

    }


}
