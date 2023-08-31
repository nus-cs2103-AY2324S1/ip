import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "_______________ \n"
                + "Eh, I'm Scarlet \n"
                + "No, I don't really want to know who you are \n\n"
                + "_______________ \n";
        System.out.println(logo);
        Duke bot = new Duke();
        bot.inputsForList();

    }

    public void inputsForList() {
        Scanner sc = new Scanner(System.in);
        Tasks lst = new Tasks();
        boolean running = true;
        while (running) {
            String entry = sc.nextLine();
            String[] words = entry.split(" ");
            if (entry.equals("bye")) {
                lst.saveTasks();
                String logo = "_______________ \n\n"
                        + "finally.  \n"
                        + "_______________ \n";
                System.out.println(logo);
                running = false;
            } else if (entry.equals("list")) {
                String condemn = "_______________ \n\n"
                        + "What a terrible day to be alive. \n"
                        + String.format("%s \n_______________ \n", lst);
                System.out.println(condemn);
            } else if (words[0].equals("mark")) {
                int index = Integer.parseInt(words[1]);
                lst.checkItem(index);
                System.out.println("_______________ \n\n"
                        + "A proud moment of your life I am sure... \n  "
                        + lst.showThisTask(index) + " \n" + "_______________\n");
            } else if (words[0].equals("unmark")) {
                int index = Integer.parseInt(words[1]);
                lst.notDoneItem(index);
                System.out.println("_______________ \n\n"
                        + "I'm not juding at all... \n"
                        + lst.showThisTask(index) + " \n" + "_______________\n");
            } else if (words[0].equals("delete")) {
                int index = Integer.parseInt(words[1]);
                String placeholder = lst.showThisTask(index);
                lst.deleteIndex(index);
                System.out.println("_______________ \n\n"
                        + "Not another mistake I hope... \n"
                        + placeholder + " \n\n" + "_______________");
            } else {
                boolean exceptionOccured = false;
                try {
                    lst.input(entry);
                } catch (IncompleteInput e) {
                    exceptionOccured = true;
                    System.out.println("_______________ \n\n" +
                            "Come on now... don't be shy, go on \n" +
                            "_______________ \n");
                } catch (InvalidInput e) {
                    exceptionOccured = true;
                    System.out.println("_______________ \n\n" +
                            "Someone should have paid attention in school... try again \n" +
                            "_______________ \n");
                } finally {
                    if (!exceptionOccured) {
                        System.out.println("_______________ \n\n"
                                + "I'm totally not judging...  "
                                + lst.showThisTask(lst.numOfItems())
                                + " ... added to the list \n"
                                + "I wonder how you'll mess up this... " + lst.numOfItems() + "\n"
                                + "_______________ \n");
                    }
                }
            }
        }
    }

}
