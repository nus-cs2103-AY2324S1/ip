package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Parser {
    private static final String DELIMITER_COMMAND = " ";
    private static final String DELIMITER_DATE = " /";
    private static final String DELIMITER_INPUT = " / ";

    /**
     * Returns parsed input from the user.
     *
     * @param input Input from user.
     * @throws InvalidCommandException If the input is not of the recognised form.
     */
    public static ArrayList<String> parseUserInput(String input) throws InvalidCommandException {
        ArrayList<String> parsedInput= new ArrayList<>();

        String[] splitInputByDateDelimiter = input.split(DELIMITER_DATE);

        for (int i = 1; i < splitInputByDateDelimiter.length; i++) {
            String[] split = splitInputByDateDelimiter[i].split(DELIMITER_COMMAND);
            if (split.length <= 1) {
                throw new InvalidCommandException("Invalid input for date");
            }
            splitInputByDateDelimiter[i] = String.join(" ", Arrays.copyOfRange(split, 1, split.length));
        }

        String commandString = splitInputByDateDelimiter[0];
        String[] splitCommandStringByCommandDelimiter = commandString.split(DELIMITER_COMMAND);
        String command = splitCommandStringByCommandDelimiter[0];

        parsedInput.add(command);

        if (splitCommandStringByCommandDelimiter.length > 1) {
            String commandInput = String.join(" ", Arrays.copyOfRange(splitCommandStringByCommandDelimiter,
                    1, splitCommandStringByCommandDelimiter.length));
            parsedInput.add(commandInput);
        }

        Collections.addAll(parsedInput, Arrays.copyOfRange(splitInputByDateDelimiter, 1,
                splitInputByDateDelimiter.length));

        return parsedInput;
    }

    /**
     * Returns parsed input from the file.
     *
     * @param input Input from file.
     * @throws InvalidCommandException If the input is not of the recognised form.
     */
    public static ArrayList<String> parseFileInput(String input) throws InvalidCommandException {
      ArrayList<String> parsedInput = new ArrayList<>();
      String[] split = input.split(DELIMITER_INPUT);
      Collections.addAll(parsedInput, split);
      return parsedInput;
    }
}
