import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class Ax {

    private static List<ListItem> listItems = new ArrayList<>();

    /**
     * Displays a horizontal line.
     */
    private static void hoLine() {
        System.out.println();
        System.out.println(
                "=^..^=   =^..^=   =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    =^..^=   =^..^=   =^..^=    =^..^=    =^..^=    =^..^=    =^..^=    "
        );
    }

    /**
     * Displays a greeting message.
     */
    private static void greet() {
        String newLogo =
                "                \n" +
                        "                \n" +
                        "                \n" +
                        "    ##          \n" +
                        "   ###          \n" +
                        "  ## ##  ##  ## \n" +
                        "  ## ##   ####  \n" +
                        " ##  ##    ##   \n" +
                        " ######   ###   \n" +
                        "##   ##  ## ##  \n" +
                        "##   ## ##  ##  \n" +
                        "                \n" +
                        "                \n";

        System.out.println("Hello from\n" + newLogo);
        System.out.println(
                "\uD83D\uDC4B Greetings!\n" +
                        "\n" +
                        "Ax at your service! I'm not just any chatbot; I'm Ax – your knowledgeable and engaging virtual companion. Whether you're seeking answers, a friendly chat, or a bit of fun, I'm here to make your experience enjoyable and insightful. Don't hesitate to share your thoughts or questions with me. Let's dive in and start our conversation! How can I assist you today, my friend?"
        );
        hoLine();
    }

    /**
     * Displays a farewell message.
     */
    private static void bye() {
        System.out.println(
                "Thank you for your time and I hope you found what you needed!! 🥰"
        );
        hoLine();
    }

    /**
     * Displays the current to-do list.
     */
    private static void listTheList() {
        for (int i = 1; i < listItems.size() + 1; i++) {
            System.out.printf("%d. %s%n", i, listItems.get(i - 1));
        }
        hoLine();
    }

    /**
     * Gets input from the user and performs the corresponding action.
     * If the input is "list", displays the current to-do list.
     * If the input is "add", prompts the user for a new item to add to the list.
     * If the input is "delete", prompts the user for the index of the item to delete from the list.
     * If the input is anything else, throws a NoSuchMethodException.
     * If the input is missing an argument, throws a MissingFormatArgumentException.
     *
     * @param scanner the scanner to use for input
     * @return true if the user wants to exit, false otherwise
     */
    private static boolean getInput(Scanner scanner) {
        try {
            String input = scanner.nextLine(); // get next input
            System.out.println(input); // repeat the input
            System.out.println();
            hoLine();
            String[] inputs = input.split(" ", 2);
            String[] dates = input.split("/(by|from|to) ");
            if (input.equals("bye")) { // check if it is bye, then return true, so it will exit the loop
                // delete existing file
                Path saveFile = Paths.get("data/save.txt");
                System.out.println(saveFile.toAbsolutePath());
                if (saveFile.toFile().exists()) {
                    Files.delete(saveFile);
                }
                boolean success = saveFile.toFile().createNewFile();
                // write new file
                FileWriter fileWriter = new FileWriter(saveFile.toFile());
                PrintWriter printWriter = new PrintWriter(fileWriter);
                for (ListItem listItem : listItems) {
                    System.out.println(listItem);
                    printWriter.println(listItem);
                }

                return true;
            } else if (input.equals("list")) {
                // call the list function
                listTheList();
            } else if (input.startsWith("mark")) {
                if (inputs.length > 1) {
                    ListItem task = listItems.get(Integer.parseInt(inputs[1]) - 1);
                    task.setDone(true);
                } else {
                    throw new MissingFormatArgumentException("no arg");
                }
            } else if (input.startsWith("unmark")) {
                if (inputs.length > 1) {
                    ListItem task = listItems.get(Integer.parseInt(inputs[1]) - 1);
                    task.setDone(false);
                } else {
                    throw new MissingFormatArgumentException("no arg");
                }
            } else if (input.startsWith("todo")) {
                if (inputs.length > 1) {
                    listItems.add(new Todos(inputs[1]));
                } else {
                    throw new MissingFormatArgumentException("no arg");
                }
            } else if (input.startsWith("deadline")) {
                if (inputs.length > 1 && dates.length > 1) {
                    listItems.add((new Deadlines(inputs[1].split("/")[0], dates[1])));
                } else {
                    throw new MissingFormatArgumentException("no arg");
                }
            } else if (input.startsWith("event")) {
                if (inputs.length > 1 && dates.length > 2) {
                    listItems.add(
                            (new Events(inputs[1].split("/")[0], dates[1], dates[2]))
                    );
                } else {
                    throw new MissingFormatArgumentException("no arg");
                }
            } else if (input.startsWith("delete")) {
                if (inputs.length > 1) {
                    listItems.remove(Integer.parseInt(inputs[1]) - 1);
                } else {
                    throw new MissingFormatArgumentException("no arg");
                }
            } else {
                throw new NoSuchMethodException("no method");
            }
        } catch (MissingFormatArgumentException e) {
            System.out.println(
                    "You're missing some arguments 😭😭 I'm smart but I can't read minds!"
            );
        } catch (NoSuchMethodException e) {
            System.out.println(
                    "Oh nos what is this foreign language, I haven't learnt that yet 🧑‍🎓"
            );
        } catch (Exception e) {
            System.out.println("I DONT KNOW WHATS HAPPENING!!! SAVE ME 🆘");
            System.out.println("But seriously, its this ");
            System.out.print("error = " + e);
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        greet();
        while (true) {
            boolean done = getInput(scanner);
            if (done) {
                break;
            }
        }
        bye();
    }
}
