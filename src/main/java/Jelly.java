import java.lang.reflect.Array;
import java.util.Scanner;

public class Jelly {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] storage = new String[100];
        int index = 0;
        System.out.println("Hello! I'm Jelly");
        System.out.println("What can I do for you?");
        while (sc.hasNextLine()) {
            String temp = sc.nextLine();
            if (temp.equals("bye")) {
                System.out.println("Bye, have a nice day!");
                return;
            } else if (temp.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + "." + storage[i]);
                }
            } else {
                    storage[index] = temp;
                    index++;
                    System.out.println("added: " + temp);
                }
            }
        }
}
