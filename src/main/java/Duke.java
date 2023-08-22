import java.util.Scanner;
public class Duke {

    String line = "~~*~~*~~*~~*~~*~~*~~*~~*~~*~~\n";
        public void chadGreet() {
            System.out.println(line);
            System.out.println("Yo! This is CHADbot\n");
            System.out.println("Need sum help?\n");
            System.out.println(line);
        }
        public void chadBye() {
            System.out.println(line);
            System.out.println("Cya l8r~\n");
            System.out.println(line);
        }

        public void chadOutput(String input) {
            System.out.println(line);
            System.out.println(input + "\n");
            System.out.println(line);
        }
    public static void main(String[] args) {
        Duke chad = new Duke();
        chad.chadGreet();

        Scanner scanObj = new Scanner(System.in);

        while(true) {
            String input = scanObj.nextLine();
            if (input.equals("bye")) {
                chad.chadBye();
                break;
            }
            chad.chadOutput(input);
        }
        scanObj.close();

    }
}
