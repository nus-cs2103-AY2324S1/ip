package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    /**
     * Constructor for Ui class
     * Create a Scanner object
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Get the next line from the user
     *
     * @return the user input
     */
    public String getInput() {
        return this.scanner.nextLine();
    }

    /**
     * FInd and return the keywords found in the list
     *
     * @param keyword the keyword to find
     * @return the list that contains the keyword
     */
    public String find(String keyword) {
        String result = " ";
        TaskList.findKeyword(keyword);

        if(TaskList.getKeywordList().isEmpty()) {
            result =  Messages.findNothing();
        } else {
            result = Messages.keywordFound();
            int listSize = TaskList.getKeywordList().size();
            for (int i = 0; i < listSize; i++) {
                int count = i + 1;
                result = result + count + "." + TaskList.getKeywordList().get(i) + "\n";
            }
        }
        return result;
    }
}
