package Duke;
import java.util.Scanner;

public class UserInterface {
    private Scanner userInput;
    private final String lineDivider = "---------------------------------------------";
    public UserInterface() {
        this.userInput = new Scanner(System.in);
    }

    public String input() {
        return userInput.nextLine();
    }
    public void output(String output){
        System.out.println(output);
        System.out.println(lineDivider);
    }

}
