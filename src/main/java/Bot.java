import java.util.Scanner;

public class Bot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm the trash gremlin Caelus!\nWhat can I do for you?");
        while (true) {
            String str = sc.next();
            if (str.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println(str);
        }
        System.out.println("Bye. You can find me at the nearest trash can!");
    }
}
