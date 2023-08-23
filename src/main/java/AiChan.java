import java.util.ArrayList;
import java.util.Scanner;
public class AiChan {
    public static void main(String[] args) {
        String line = "_______________________________________________________________________\n";
        String greet = "Hiya! I'm Ai-chan~ ✧◝(⁰▿⁰)◜✧\n" +
                "Hey there, dear viewer, what's on your mind?\n" +
                "Is there anything I can do to sprinkle some magic into your day?\n";
        String bye = "Ta-da! It's time to go~ Keep smiling till we reunite!\n";

        Scanner scn = new Scanner(System.in);
        ArrayList<String> arrStr = new ArrayList<>();

        System.out.println(line + greet + line);
        while (true) {
            String command = scn.nextLine();
            if (command.equals("bye")) {
                System.out.println(line + bye + line);
                break;
            } else if (command.equals("list")){
                System.out.print(line);
                int i = 1;
                for (String str : arrStr) {
                    System.out.println(i + ". " + str);
                    i++;
                }
                System.out.print(line);
            } else {
                arrStr.add(command);
                System.out.println(line + "added: " + command + "\n" + line);
            }
        }
    }
}
