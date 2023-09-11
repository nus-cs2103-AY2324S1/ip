package com.cloud.chatbot.item;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.cloud.chatbot.file.FileManager;



/**
 * Manages all Items while the bot is running.
 */
public class ItemManager {
    private FileManager fileManager = new FileManager();
    private List<Item> items = new ArrayList<>();

    private void save() {
        List<JSONObject> jsons = items
                .stream()
                .map(Item::export)
                .collect(Collectors.toList());

        JSONObject json = new JSONObject();
        json.put("items", jsons);

        this.fileManager.save(json);
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
     * Returns the String representation for the Item of the specified number.
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
