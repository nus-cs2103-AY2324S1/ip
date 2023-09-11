package com.cloud.chatbot.item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cloud.chatbot.annotations.Nullable;
import com.cloud.chatbot.file.FileManager;
import com.cloud.chatbot.file.Key;



/**
 * Manages all Items while the bot is running.
 */
public class ItemManager {
    private FileManager fileManager = new FileManager();
    private List<Item> items = new ArrayList<>();

    /**
     * Initialises the ItemManager.
     */
    public ItemManager() {
        @Nullable JSONObject jsonAll = this.fileManager.read();
        if (jsonAll == null) {
            return;
        }

        JSONArray array = jsonAll.getJSONArray("items");
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonItem = array.getJSONObject(i);
            this.importJson(jsonItem);
        }
    }

    private void importJson(JSONObject json) {
        ItemType type = ItemType.fromString(
            json.getString(Key.TYPE.string)
        );
        String description = json.getString(Key.DESCRIPTION.string);
        boolean isComplete = json.getBoolean(Key.IS_COMPLETE.string);

        Item item;
        switch (type) {
        case TASK: {
            item = new Task(description);
            break;
        }
        case DEADLINE: {
            String end = json.getString(Key.END.string);
            item = new Deadline(description, end);
            break;
        }
        case EVENT: {
            String start = json.getString(Key.START.string);
            String end = json.getString(Key.END.string);
            item = new Event(description, start, end);
            break;
        }
        default:
            System.err.println(
                "ERR Could not import JSON item with unsupported type!"
            );
            System.err.println(json);
            return;
        }

        item.setComplete(isComplete);
        this.items.add(item);
    }

    private void save() {
        List<JSONObject> jsons = items
                .stream()
                .map(Item::export)
                .collect(Collectors.toList());

        JSONObject json = new JSONObject();
        json.put("items", jsons);

        this.fileManager.write(json);
    }

    /**
     * Adds the specified Item.
     *
     * @param item The Item to add.
     */
    public void add(Item item) {
        this.items.add(item);

        this.save();
    }

    /**
     * Returns the string representation for the Item of the specified number.
     *
     * @param int The Item's number.
     */
    public String getString(int number) {
        return this.items
                .get(number - 1)
                .toString(number);
    }

    /**
     * Sets the completion status for the Item of the specified number.
     *
     * @param int The Item's number.
     * @param isComplete The completion status.
     */
    public void setComplete(int number, boolean isComplete) {
        this.items
                .get(number - 1)
                .setComplete(isComplete);

        this.save();
    }

    /**
     * Removes and returns the Item of the specified number.
     *
     * @param int The Item's number.
     */
    public Item remove(int number) {
        Item item = this.items.remove(number - 1);

        this.save();

        return item;
    }

    /**
     * Returns the total number of Items.
     */
    public int getCount() {
        return this.items.size();
    }
}
