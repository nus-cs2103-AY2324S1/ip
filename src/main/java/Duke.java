import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Siri");
        System.out.println("What can I do for you?");

        String[] store = new String[100];
        int size = 0;
        size = echo(store, size);
        exit();
    }

    private static int echo(String[] store, int size) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            String word = sc.nextLine();
            if (word.equals("bye")) {
                break;
            } else  if (word.equals("list")) {
                for (int i = 0; i < size; ++i) {
                    System.out.println(i + 1 + ". " + store[i]);
                }
            } else {
                store[size++] = word;
                System.out.println("added: " + word);
            }
        }
        return size;
    }

    private static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
