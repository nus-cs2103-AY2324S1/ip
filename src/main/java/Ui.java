import java.util.Scanner;

/**
 * 
 */
public class Ui {

    //Scanner used to see user input
    private static final Scanner sc = new Scanner(System.in);

    //String representing a border.
    private static final String BORDER = "____________________________________________________________\n";

    /**
     * Prints the inputs out for the user.
     * 
     * @param inputs
     */
    public void print(String[] inputs) {
        System.out.println(BORDER);
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] != null) {
            System.out.println(inputs[i]);
            }
        }
        System.out.println(BORDER);
    }
    

    /**
     * Prints an introduction.
     */
    public void intro() {
        print(new String[] {"Hello! I am Bobby Wasabi", 
            "What can I do for you today?"});
    }

    /**
     * Prints a goodbye message.
     */
    public void bye() {
        print(new String[] {"Bye. Have a bad day you doofus."});
    }

    public String readInput() {
        return sc.nextLine();
    }

    public void showError(Exception e) {
        System.out.println("Error! " + e.getMessage());
    }

}
