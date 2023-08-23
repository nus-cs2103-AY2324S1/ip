import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " _______________ \n"
                + "Eh, I'm Scarlet \n"
                + "No, I don't really want to know who you are \n\n"
                + " _______________ \n";
        System.out.println(logo);
        Duke duke = new Duke();
        duke.inputsForList();

    }

    public void inputsForList() {
        Scanner sc = new Scanner(System.in);
        List lst = new List();
        while (true) {
            String entry = sc.nextLine();

            if (entry.equals("bye")) {
                break;
            } else if (entry.equals("list")) {
                String condemn = " _______________ \n\n "
                        + " What a terrible day to be alive. \n"
                        +  String.format("%s \n\n  _______________ \n\n ", lst.toString());
                System.out.println(condemn);
            } else {
                lst.input(entry);
                System.out.println(" _______________ \n\n "
                        + "I'm totally not judging...  "
                        + entry
                        + " ... added to the list \n\n  _______________ \n ");
            }
        }
        String logo = " _______________ \n\n"
                + "finally.  \n"
                + " _______________ \n";
        System.out.println(logo);
    }

}
