package duke.alias;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class represents a map of aliases, where each alias is associated with a full command.
 */
public class AliasMap implements Iterable<Map.Entry<String, String>> {
    private final HashMap<String, String> aliasMap;

    /**
     * Constructs an empty AliasMap.
     */
    public AliasMap() {
        this.aliasMap = new HashMap<>();
    }

    /**
     * Constructs a new instance of AliasMap, with aliasMap being
     * set to the HashMap argument.
     * @param aliasMap
     */
    public AliasMap(HashMap<String, String> aliasMap) {
        this.aliasMap = aliasMap;
    }

    /**
     * Adds an alias with its corresponding full command to the map.
     *
     * @param alias       The alias name.
     * @param fullCommand The full command associated with the alias.
     */
    public void addAlias(String alias, String fullCommand) {
        aliasMap.put(alias, fullCommand);
    }

    /**
     * Retrieves the full command associated with the given alias.
     *
     * @param alias The alias name.
     * @return The full command, or null if the alias does not exist.
     */
    public String getFullCommand(String alias) {
        return aliasMap.get(alias);
    }

    /**
     * Removes an alias from the map.
     *
     * @param alias The alias name to be removed.
     */
    public void removeAlias(String alias) {
        aliasMap.remove(alias);
    }

    /**
     * Returns a string representation of the AliasMap.
     *
     * @return A string containing all aliases and their corresponding full commands.
     */
    @Override
    public String toString() {
        return aliasMap.toString();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        return aliasMap.entrySet().iterator();
    }
}
