package com.cloud.chatbot.item;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cloud.chatbot.Ui;
import com.cloud.chatbot.annotation.Nullable;
import com.cloud.chatbot.exception.IllegalTimestampException;
import com.cloud.chatbot.file.FileStorage;
import com.cloud.chatbot.file.Key;



/**
 * Manages all Items while the bot is running.
 */
public class ItemManager {
    private FileStorage fileManager = new FileStorage();
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
            long endEpoch = json.getLong(Key.END.string);
            item = new Deadline(
                description,
                Instant.ofEpochMilli(endEpoch)
            );
            break;
        }
        case EVENT: {
            long startEpoch = json.getLong(Key.START.string);
            long endEpoch = json.getLong(Key.END.string);

            try {
                item = new Event(
                    description,
                    Instant.ofEpochMilli(startEpoch),
                    Instant.ofEpochMilli(endEpoch)
                );
            } catch (IllegalTimestampException e) {
                Ui.error(
                    "Could not import JSON event with illegal timestamp",
                    json
                );
                return;
            }

            break;
        }
        default: {
            Ui.error(
                "Could not import JSON item with unknown type",
                json
            );
            return;
        }
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
