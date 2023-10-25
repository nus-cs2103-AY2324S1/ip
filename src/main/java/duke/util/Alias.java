package duke.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Alias class is used to store and manage aliases. An <code>Alias</code> object
 * represents a list of aliases. There are three different kinds of commands for alias:
 * <ul>
 *     <li>add alias     (<code>e.g. alias t todo</code>)</li>
 *     <li>remove alias  (<code>e.g. alias t</code>)</li>
 *     <li>list alias    (<code>e.g. alias</code>)</li>
 * </ul>
 */
public class Alias {

    private static final HashMap<String, String> aliasMap = new HashMap<>();

    /**
     * Initializes the alias list, with pre-defined aliases.
     * <p><br>Note that this method is called only when there are no user-defined aliases.
     * (i.e., the alias file is not found or corrupted)</p>
     */
    public static void initAlias() {
        aliasMap.clear();
        aliasMap.put("t", "todo");
        aliasMap.put("e", "event");
        aliasMap.put("d", "deadline");
        aliasMap.put("del", "delete");
        aliasMap.put("f", "find");
        aliasMap.put("ls", "list");
        aliasMap.put("ld", "load");
        aliasMap.put("m", "mark");
        aliasMap.put("um", "unmark");
        aliasMap.put("pd", "print_date");
        aliasMap.put("s", "sort");
    }

    /**
     * Adds an alias to the alias list.
     *
     * @param alias   The alias.
     * @param command The string that the alias represents.
     */
    public static void addAlias(String alias, String command) {
        aliasMap.put(alias, command);
    }

    /**
     * Checks if the alias is present in the alias list.
     *
     * @param alias The alias.
     * @return     True if the alias is present in the alias list.
     */
    public static boolean isAliasPresent(String alias) {
        return aliasMap.containsKey(alias);
    }

    /**
     * Gets the string that the alias represents.
     *
     * @param alias The alias.
     * @return      The string that the alias represents.
     */
    public static String getAlias(String alias) {
        return aliasMap.get(alias);
    }

    /**
     * Removes the alias from the alias list.
     *
     * @param alias The alias.
     */
    public static void removeAlias(String alias) {
        aliasMap.remove(alias);
    }

    /**
     * Saves the alias list to a list of strings, with each string representing an alias.
     * The list is also sorted in alphabetical order.
     *
     * @return The list of strings, with each string representing an alias.
     */
    public static List<String> saveAliasFormat() {
        List<String> aliasFormat = new ArrayList<>();
        for (String alias : aliasMap.keySet()) {
            aliasFormat.add(alias + " -> " + aliasMap.get(alias) + "\n");
        }
        aliasFormat.sort(String::compareTo);
        return aliasFormat;
    }

    /**
     * Replaces the alias in the command body with the string that the alias represents.
     *
     * @param commandBody The command body.
     * @return            The command body with the alias replaced.
     */
    public static String replaceAlias(String commandBody) {
        String[] split = commandBody.split(" ");
        for (int i = 0; i < split.length; i++) {
            if (isAliasPresent(split[i])) {
                split[i] = getAlias(split[i]);
            }
        }
        return String.join(" ", split);
    }
}
