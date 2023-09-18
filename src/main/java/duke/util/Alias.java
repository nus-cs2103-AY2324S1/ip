package duke.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Alias {

    public static HashMap<String, String> aliasMap = new HashMap<>();

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

    public static void addAlias(String alias, String command) {
        aliasMap.put(alias, command);
    }

    public static boolean isAliasPresent(String alias) {
        return aliasMap.containsKey(alias);
    }

    public static String getAlias(String alias) {
        return aliasMap.get(alias);
    }

    public static void removeAlias(String alias) {
        aliasMap.remove(alias);
    }

    public static List<String> saveAliasFormat() {
        List<String> aliasFormat = new ArrayList<>();
        for (String alias : aliasMap.keySet()) {
            aliasFormat.add(alias + " -> " + aliasMap.get(alias) + "\n");
        }
        aliasFormat.sort(String::compareTo);
        return aliasFormat;
    }

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
