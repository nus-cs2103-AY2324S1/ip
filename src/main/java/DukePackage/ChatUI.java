package DukePackage;

public class ChatUI {
    String horizontalLine = "   ------------------------------------------------------------------------";
    String intro = "    Hello! I'm iPbot \n" +
            "    What can I do for you?";
    String outro = "    Bye. Hope to see you again soon!";

    public ChatUI() {
        // Constructor body (if needed)
    }

    public void divider() {
        System.out.println(horizontalLine);
    }

    public void intro() {
        System.out.println(horizontalLine);
        System.out.println(intro);
        System.out.println(horizontalLine);
    }

    public void outro() {
        System.out.println(outro);
    }
}
