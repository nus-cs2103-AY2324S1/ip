package com.cloud.chatbot.item;

import java.util.ArrayList;
import java.util.List;



/**
 * Manages all Items while the bot is running.
 */
public class ItemManager {
    private List<Item> items = new ArrayList<>();

    /**
     * Adds the specified Item.
     *
     * @param item The Item to add.
     */
    public void add(Item item) {
        this.items.add(item);
    }

    /**
     * Returns the Item for the specified number.
     *
     * @param int The Item's number.
     */
    public Item get(int number) {
        return this.items.get(number - 1);
    }

    /**
     * Removes and returns the Item of the specified number.
     *
     * @param int The Item's number.
     */
    public Item remove(int number) {
        return this.items.remove(number - 1);
    }

    /**
     * Returns the total number of Items.
     */
    public int getCount() {
        return this.items.size();
    }
}
