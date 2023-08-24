import java.util.Scanner;
public class Avalon {
    public static void main(String[] args) {
        System.out.println(
                "   ____________________________________________________________\n" +
                "    Hello! I'm Arthur Pendragon.\n" +
                "    What can I do for you?\n" +
                "   ____________________________________________________________\n"
        );

        Scanner scanner = new Scanner(System.in);
        
        while (true){
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")){
                System.out.println(
                        "   ____________________________________________________________\n" +
                                "    Farewell. We will meet again!\n" +
                                "   ____________________________________________________________"
                );
                break;
            }

            else {
                System.out.println(
                        "   ____________________________________________________________\n" +
                                "    " +
                                userInput +
                                "\n   ____________________________________________________________\n"
                );
            }
        }
    }
}
