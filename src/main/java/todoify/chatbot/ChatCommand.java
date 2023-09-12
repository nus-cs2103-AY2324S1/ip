package todoify.chatbot;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstraction for an instruction command derived from a chat message.
 *
 * <p>
 * This class represents a simple command structure with name, data, parameters. It also provides convenience parser
 * methods to automatically parse an instruction String into a command, and enum lookups for known command operation
 * types.
 * </p>
 */
public class ChatCommand {

    /**
     * The prefix used to denote a parameter.
     */
    public static final String PARAMETER_PREFIX = "--";

    /**
     * A command operation, representing an identified operation for a command.
     */
    public enum Operation {
        ADD_TODO,
        ADD_DEADLINE,
        ADD_EVENT,
        DELETE,

        MARK_COMPLETE,
        UNMARK_COMPLETE,

        LIST,
        SEARCH,
        EXIT,

        UNKNOWN
    }

    private final String name;
    private Operation type = null;
    private final String data;
    private final Map<String, String> params = new HashMap<>();

    /**
     * Constructs a command with the given properties.
     *
     * @param name   The name of the command. Cannot be null.
     * @param data   The data of the command. Cannot be null; supply empty string if no data.
     * @param params The parameters of the command. May be null.
     */
    public ChatCommand(String name, String data, Map<String, String> params) {
        this.name = name;
        this.data = data;

        if (params != null) {
            this.params.putAll(params);
        }

        if (name == null || data == null) {
            throw new NullPointerException("Name and data of a command cannot be null.");
        }
    }

    /**
     * Parses a string instruction as a command.
     *
     * <p>
     * This expects the format: "commandName Some long data text --key1 value1 --key2 value2".
     * </p>
     *
     * @param instruction The string instruction to parse.
     * @return The resulting command.
     */
    public static ChatCommand parse(String instruction) {
        String[] words = extractWordsFromInstruction(instruction);

        int dataEndIndex = computeDataEndIndex(words);

        var name = extractCommandNameString(words);
        var data = extractCommandDataComponentString(words, dataEndIndex);
        var params = extractCommandParametersMap(words, dataEndIndex);

        return new ChatCommand(name, data, params);
    }

    /**
     * Parses a string instruction's command name.
     *
     * <p>
     * This expects the format: "commandName Some long data text --key1 value1 --key2 value2". It only returns the
     * commandName component, and does not attempt to read anything beyond that.
     * </p>
     *
     * @param instruction The string instruction to parse.
     * @return The command name retrieved as a non-null string.
     */
    public static String parseCommandName(String instruction) {
        String[] words = extractWordsFromInstruction(instruction);
        return extractCommandNameString(words);
    }


    /**
     * Internal method to extract individual words separated by whitespace from an instruction.
     *
     * @param instruction The original string instruction to extract words from.
     * @return The array of words.
     */
    private static String[] extractWordsFromInstruction(String instruction) {
        String[] parts = instruction.trim().split("[ \t\r\n]");
        return parts;
    }

    /**
     * Internal method to compute the first index of which is no longer the data component.
     *
     * @param words A reference to the original partitioned words.
     * @return The first index in the words array of which is no longer part of the data.
     */
    private static int computeDataEndIndex(String[] words) {
        int dataEndIndex = 0;
        while (dataEndIndex < words.length) {
            String part = words[dataEndIndex];
            if (part.startsWith(PARAMETER_PREFIX)) {
                break;
            }
            dataEndIndex++;
        }
        return dataEndIndex;
    }

    /**
     * Internal method to extract the command name string.
     *
     * @param words A reference to the original partitioned words.
     * @return A string representing the command name.
     */
    private static String extractCommandNameString(String[] words) {
        if (words.length == 0 || words[0].startsWith(PARAMETER_PREFIX)) {
            return "";
        }
        return words[0];
    }

    /**
     * Internal method to extract the data string from the word parts, given the index to end the search once reached.
     *
     * @param words A reference to the original partitioned words.
     * @param dataEndIndex The index that marks the end of the data component, exclusive.
     * @return A string representing the full data component.
     */
    private static String extractCommandDataComponentString(String[] words, int dataEndIndex) {
        StringBuilder data = new StringBuilder();

        // Merge the data components into a string.
        for (int i = 1; i < dataEndIndex; i++) {
            if (i > 1) {
                data.append(' ');
            }
            data.append(words[i]);
        }
        return data.toString();
    }

    /**
     * Internal method to extract a map of the parameters from the word parts of an instruction, given the starting
     * index to search from.
     *
     * @param words A reference to the original partitioned words.
     * @param parameterStartIndex The index to start the search from, inclusive.
     * @return A map reflecting the newly extracted parameters as key-value pairs.
     */
    private static Map<String, String> extractCommandParametersMap(String[] words, int parameterStartIndex) {
        Map<String, String> params = new HashMap<>();

        String currParamKey = "";
        StringBuilder currParamValue = new StringBuilder();

        // Scan through each word within the parameter index range.
        for (int i = parameterStartIndex; i < words.length; i++) {

            String part = words[i].trim();
            if (part.startsWith(PARAMETER_PREFIX)) {
                // We reached a new key value pair.
                // If it was tracking an old key, we should add the old key-value pair in before setting the new one.
                if (!currParamKey.isEmpty()) {
                    params.put(currParamKey, currParamValue.toString().trim());
                }

                // Now that we are done, track the new key value pair.
                currParamKey = part;
                currParamValue = new StringBuilder();

            } else {
                currParamValue.append(words[i]);
                currParamValue.append(' ');
            }

        }

        // There's an ending case that we might have missed out on, since it only adds upon new key detection.
        // Check if there's anything left out and add the pair if so.
        if (!currParamKey.isEmpty()) {
            params.put(currParamKey, currParamValue.toString().trim());
        }

        // We're done!
        return params;
    }



    /**
     * Obtains the name of the given command.
     *
     * @return The name of the given command as a non-null string.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Obtains the operation of the given command by comparing the name against one of the standard operations.
     *
     * @return The operation of the given command.
     */
    public Operation getOperation() {
        if (this.type != null) {
            return this.type;
        }

        switch (this.name) {
        case "todo":
            return this.type = Operation.ADD_TODO;
        case "event":
            return this.type = Operation.ADD_EVENT;
        case "deadline":
            return this.type = Operation.ADD_DEADLINE;

        case "delete":
            return this.type = Operation.DELETE;

        case "mark":
            return this.type = Operation.MARK_COMPLETE;
        case "unmark":
            return this.type = Operation.UNMARK_COMPLETE;

        case "list":
            return this.type = Operation.LIST;

        case "find":
        case "search":
            return this.type = Operation.SEARCH;

        case "bye":
        case "exit":
            return this.type = Operation.EXIT;

        default:
            break;
        }

        return this.type = Operation.UNKNOWN;
    }

    /**
     * Obtains the data of the given command.
     *
     * @return The data of the given command as a non-null string.
     */
    public String getData() {
        return this.data;
    }

    /**
     * Obtains the value of the given parameter in the command.
     *
     * @param key the parameter, also known as the key.
     * @return The value of this param, or null if unset.
     */
    public String getParam(String key) {
        if (!key.startsWith(PARAMETER_PREFIX)) {
            key = PARAMETER_PREFIX + key;
        }
        return this.params.get(key);
    }

    /**
     * Checks whether the command has parameters.
     *
     * @return `true` if there is at least one parameter, `false` otherwise.
     */
    public boolean hasParams() {
        return !this.params.isEmpty();
    }

    /**
     * Checks whether the given parameter was initialised.
     *
     * @param key the parameter, also known as the key.
     * @return `true` if it was initialised, `false` otherwise.
     */
    public boolean hasParam(String key) {
        if (!key.startsWith(PARAMETER_PREFIX)) {
            key = PARAMETER_PREFIX + key;
        }
        return this.params.containsKey(key);
    }


    /**
     * Checks whether the given parameter has any <i>useful</i> value.
     *
     * @param key the parameter, also known as the key.
     * @return `true` if it has any non-empty, non-whitespace value, `false` otherwise.
     */
    public boolean hasParamWithUsefulValue(String key) {
        if (!key.startsWith(PARAMETER_PREFIX)) {
            key = PARAMETER_PREFIX + key;
        }
        String value = this.params.get(key);
        if (value == null) {
            value = "";
        }
        return !value.isBlank();
    }
}
