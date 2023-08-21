import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static class StoreList {
        ArrayList<String> list = new ArrayList<>();
        StoreList() {
        }

        String add(String item) {
            list.add(item);
            return String.format("added: %s", item);
        }
        @Override
        public String toString() {
            String result = "";
            for (int i = 0; i < list.size(); i++) {
                result += String.format("    %d. %s", i + 1, list.get(i));
            }
            return result;
        }
    }

    enum commands {
        list,
        bye
    }

    StoreList list = new StoreList();
    public static void main(String[] args) {
        String logo = "Nino!";
        System.out.println("Hello, my name is " + logo);
        System.out.println(wrapper("What can I do for you?"));
        reader();
    }

    private static void reader() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println(wrapper("Goodbye."));
                sc.close();
                break;
            }
            System.out.println(wrapper(line));
        }
    }

    private static String wrapper(String line) {
        String frame = "=====================";
        return String.format("%s\n%s", line, frame);
    }
}
