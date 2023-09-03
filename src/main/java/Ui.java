/**
 * deals with interactions with the user
 */
public class Ui {
    public static String line = "--------------------------------------------------------------------";

    public void greeting() {
        String intro = "(｡･o･｡)ﾉ Hey there! I'm BUTTER.\n" +
                "How can I help you today?\n";
        System.out.println(line + "\n" + intro + line);
    }

    public void bye() {
        String bye = "彡໒(⊙ᴗ⊙)७彡 Signing off, see you later!\n";
        System.out.println(bye + line);
    }

    public void newTaskAdded(Task task) {
        System.out.println("(｀･ω･´)ﾉ New task added:\n" + task);
    }

    //get the size by calling storage.getListSize() later in Duke
    public void getNumberOfTasks(int size) {
        System.out.println(size == 1
                ? "Now you have " + size + " task in the list!\n" + line
                : "Now you have " + size + " tasks in the list!\n" + line);
    }

    public void showInvalidTaskID() {
        System.out.println("(・´з`・) Uh oh... invalid taskID\n" + line);
    }

    public void showNoTaskID() {
        System.out.println("(・´з`・) Uh oh... please provide a taskID\n" + line);
    }

    public void showInvalidDateFormat() {
        System.out.println("(・´з`・) Uh oh...dates must be of YYYY-MM-DD HH:mm format");
        System.out.println(line);
    }

    public void showInvalidEventFormat() {
        System.out.println("(・´з`・) Uh oh...improper event format!\n" + line);
    }

    public void showInvalidStartEnd() {
        System.out.println("(・´з`・) Uh oh...start must be after end!\n" + line);
    }
    public void showLoadingError() {
        System.out.println("(・´з`・) Uh oh... error loading BUTTER");
    }
}
