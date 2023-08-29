import java.util.Arrays;
import java.util.HashMap;

/**
 * Handles making sense of the user command and outputing it in a programme readable way
 */
public class Parser {

    /**
     * Hashmap to map the flags to its corresponding string
     */
    private final HashMap<String, String> FLAG = new HashMap<>();

    /**
     * type of command
     */
    private final Commands COMMAND;

    /**
     * param that came with the command
     */
    private String param = "";

    /**
     * Index that came with the command
     */
    private int index = -1;


    /**
     * Constructor for the Parser
     *
     * @param input - the input string that needs to be parsed
     * @throws DukeBadInputException - if the input cannot be parsed properly
     * @throws NumberFormatException - if the input cannot be converted to an int
     */
    public Parser(String input) throws DukeBadInputException, NumberFormatException {
        String[] splitInput = input.split(" ");
        switch (splitInput[0]) {
            case "list":
                this.COMMAND = Commands.LIST;
                break;
            case "mark":
                this.COMMAND = Commands.MARK;
                this.index = this.findIndex(splitInput);
                break;
            case "unmark":
                this.COMMAND = Commands.UNMARK;
                this.index = this.findIndex(splitInput);
                break;
            case "delete":
                this.COMMAND = Commands.DELETE;
                this.index = this.findIndex(splitInput);
                break;
            case "todo":
                this.COMMAND = Commands.TODO;
                this.param = input.replace("todo ", "");
                if (this.param.equals("todo")) {
                    throw new DukeBadInputException(
                            "Quack doesn't understand an empty todo description, please provide one!!");
                }
                break;
            case "deadline":
                this.COMMAND = Commands.DEADLINE;
                this.findFlags(splitInput, "/by");
                break;
            case "event":
                this.COMMAND = Commands.EVENT;
                this.findFlags(splitInput, "/from", "/to");
                break;
            default:
                this.COMMAND = Commands.UNRECOGNISED;

        }
    }

    private int findIndex(String[] splitInput) throws DukeBadInputException, NumberFormatException {
        if (splitInput.length != 2) {
            throw new DukeBadInputException(
                    String.format("Quack requires exactly one number after the %s command", splitInput[0]));
        }
        int ret = Integer.parseInt(splitInput[1]);
        if (ret < 0) {
            throw new DukeBadInputException(
                    "Quack requires a positive number to help you manage tasks!");
        }
        return ret;
    }

    /**
     * function to find the flags and update both the flags and param field
     *
     * @param splitInputs - input string that has been split into words
     * @param flags       - the flags that needs to be found
     * @throws DukeBadInputException - if the flags cannot be found or without a
     *                               description
     */
    private void findFlags(String[] splitInputs, String... flags) throws DukeBadInputException {

        int[] flagIndex = this.find(splitInputs, flags);

        for (int i = 0; i < flagIndex.length - 1; i++) {

            if (flagIndex[i] == -1) {
                throw new DukeBadInputException(
                        "Quack cant find the required " + flags[i] + " flags, please provide quack with one please");
            }

            if (flagIndex[i + 1] == -1) {
                throw new DukeBadInputException(
                        "Quack cant find the required " + flags[i + 1]
                                + " flags, please provide quack with one please");
            }

            String value = String.join(" ", Arrays.copyOfRange(splitInputs, flagIndex[i] + 1, flagIndex[i + 1]));
            if (value.isBlank()) {
                throw new DukeBadInputException(
                        "Please provide quack a description for the " + flags[i] + " flag");
            }
            this.FLAG.put(splitInputs[flagIndex[i]], value);

        }
        this.param = String.join(" ", Arrays.copyOfRange(splitInputs, 1, flagIndex[0]));
        if (this.param.isBlank()) {
            throw new DukeBadInputException(
                    "Quack doesn't understand an empty description, please provide one!!");
        }
    }

    /**
     * Finds the required flags in the array of strings
     *
     * @param arr   - the array of strings that you want to find the flags from
     * @param items - the array of flags you want to find from the array
     * @return an array of the index of the flags
     * @throws DukeBadInputException - if the flags cannot be found or without a
     *                               description
     */
    private int[] find(String[] arr, String[] items) throws DukeBadInputException {
        int[] ret = new int[items.length + 1];
        // initialise values
        for (int i = 0; i < items.length + 1; i++) {
            // to capture all text in the flag
            if (i == items.length) {
                ret[i] = arr.length;
                continue;
            }
            ret[i] = -1;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < items.length; j++) {
                if (arr[i].equals(items[j])) {
                    if (ret[j] != -1) {
                        throw new DukeBadInputException(
                                "There are too many of the " + items[j] + " flag, please just provide one");
                    }
                    ret[j] = i;
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * getter for the type of command
     *
     * @return the type of command
     */
    public Commands getCommand() {
        return this.COMMAND;
    }

    /**
     * getter for the value of the flag
     *
     * @param key - the flag
     * @return the value associated with the key
     */
    public String getFlag(String key) {
        return this.FLAG.get(key);
    }

    /**
     * getter for the param of the input
     *
     * @return param
     */
    public String getParam() {
        return this.param;
    }

    /**
     * getter for the index of the input
     *
     * @return index
     */
    public int getIndex() {
        return this.index;
    }

}
