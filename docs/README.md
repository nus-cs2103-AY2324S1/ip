# User Guide

**Cloud** is a chatbot that tracks work items. Here's a quick overview of how to use it.

Interaction is done by sending text commands. After starting the app, click on the text box at the bottom of the window to type your commands there. You may press <kbd>Enter</kbd> or click the "Send" button to send each command to the bot.

---

## Adding Items

There are 3 kinds of items you can create. The most basic is called a task, which just has a description.

Syntax: `add <description>`

Example: `add Complete task by creating more cool features.`

There are also deadlines and events, which can be created by specifying flags. A flag consists of a word with a `/` in front of it, followed by the flag's contents.

A deadline has a `/by` flag, while an event has both a `/from` and `/to` flag. These flags all expect a timestamp as their contents. Timestamps are given in the form `<day> <month> <year> <time>` - e.g. `18 6 23 1950` would represent 18 June 2023, 7:50pm.

Syntax: `add <description> /by <timestamp>`

Example: `add Submit quiz before the deadline! /by 22 9 23 2359`

Syntax: `add <description> /from <timestamp> /to <timestamp>`

Example: `add Attend open house event. /from 22 9 23 900 /to 22 9 23 1730`

## Listing Items

You can list all your items with the `list` command.

Each item has a number representing its position in the list (e.g. `#2`), as well as a letter representing its type - `T` for task, `D` for deadline, and `E` for event.

## Finding Items

You can search for items whose descriptions include a specified phrase. The searching is case sensitive.

Syntax: `find <phrase>`

Example: `find open house`

## Marking Items

Items can be marked (or unmarked) as complete by specifying their number. Completed items have an `X` in front of them.

Syntax: `mark <number>`

Example: `mark 1`

Syntax: `unmark <number>`

Example: `unmark 2`

## Deleting Items

You may also choose to delete items altogether instead of marking them.

Syntax: `delete <number>`

Example: `delete 3`

---

![](./Ui.png)
