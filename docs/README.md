# Dude User Guide

Dude is a personal assistant Java application that helps you keep track of your tasks, events, and deadlines.
You can interact with Dude through a graphical user interface (GUI) similar to a chatbot.

<img height="480" alt="Screenshot of Dude GUI" src="./Ui.png">

## Quick Start

Dude requires Java 11 to be installed on your computer.

1. Download the latest (top-most) `dude.jar` file from the [Dude releases page](https://github.com/xenosf/ip/releases).
2. Create a new folder and move the `.jar` file into it.
3. In the terminal/command prompt, navigate to the folder and run `java -jar release.jar`.
4. Enjoy Dude!

You can refer to the [features](#features) section of this guide to learn the various commands.

## Features

### `todo` - Add a to-do task

Adds a to-do task to the task list.

Format: `todo DESCRIPTION`

* Adds a task with the given description [`DESCRIPTION`].

Example:

`todo Read book`

### `deadline` - Add a deadline task

Adds a task with a deadline to the task list.

Format: `deadline DESCRIPTION /by DEADLINE`

* Adds a task with the given description [`DESCRIPTION`] and deadline [`DEADLINE`].
* `DEADLINE` must be in the format `DD/MM/YYYY hhmm`.
    * `DD/MM/YYYY` - Date of deadline in day/month/year format. Year must be typed in full (`2023`, *not* `23`).
    * `hhmm` - Time in 24-hour format. Example: 11:59PM is `2359`

Example:

`deadline Assignment quiz (online) /by 22/09/2023 2359`

### `event` - Add an event task

Adds a task with a start and end time to the task list.

Format: `event DESCRIPTION /from START /to END`

* Adds a task with the given description [`DESCRIPTION`], start date and time [`START`], and end date and time [`END`].
* `START` and `END` must be in the format `DD/MM/YYYY hhmm`.
    * `DD/MM/YYYY` - Date of deadline in day/month/year format. Year must be typed in full (`2023`, *not* `23`).
    * `hhmm` - Time in 24-hour format. Example: 11:59PM is `2359`

Example:

`event Project meeting zoom call w/ prof /from 1/2/2023 1000 /to 1/2/2023 1130`

### `list` - View list of tasks

Displays list of tasks and their associated information:

* **Index**: Each task has an index number that is shown at the start of each list item.
* **Done/not done**: Tasks that are done are marked with `[X]`, and marked with `[ ]` if not done.
* **Type**: To-do tasks are marked with `<T>`, deadline tasks `<D>`, event tasks `<E>`.

Format: `list`

Example output:

```
Here's your tasks list:
1-<E>[ ] CS2103 tP meeting (FROM: 22 Sep 2023, 4:00 PM TO: 22 Sep 2023, 5:00 PM)
2-<D>[ ] CS2103 iP (BY: 22 Sep 2023, 11:59 PM)
3-<T>[ ] Readings
4-<T>[X] Week 7 quiz
5-<E>[X] Project meeting zoom call w/ prof (FROM: 1 Feb 2023, 10:00 AM | TO: 1 Feb 2023, 11:30 AM)
```

### `mark`, `unmark` - Mark task as done/not done

Mark a task as done (using `mark`) or not done (using `unmark`).

Format: `mark INDEX` for mark, `unmark INDEX` for unmark.

* Marks the task with the given index number [`INDEX`] as done/not done.

Example:

`mark 1`

* Marks task number `1` as done.

`unmark 4`

* Marks task number `4` as not done.

### `remove`/`delete` - Remove task from list

Removes a task from the list.

Format: `remove INDEX`/`delete INDEX` (both do the same thing).

Example:

`delete 2`

* Deletes task number `2` from the list.

### `find` - Search tasks

Searches the list of tasks for those that match the given search text.

Format: `find SEARCH_QUERY`.

* Finds and lists all tasks with descriptions that contain the search query [`SEARCH_QUERY`].
* `SEARCH_QUERY` can contain spaces and is **case-insensitive**.

Examples:

`find cs2103`

* Finds all tasks that have descriptions containing `cs2103` (case-insensitive).
* **Will match**: "CS2103T project meeting with group", "cs2103 ip"
* **Will not match**: "CS2100 quiz", "Meeting w/ prof"

`find meeting w`

* Finds all tasks that have descriptions containing `meeting w` (case-insensitive).
* **Will match**: "CS2103T project meeting with group", "Meeting w/ prof"
* **Will not match**: "CS2100 quiz", "cs2103 ip"

### `sort` - Sort list of tasks

Sorts the task list and displays the newly sorted list.

* This sorting will change the index number of tasks.

Format: `sort METHOD [/order ORDER]` (The part in square brackets is **optional**).

* Sorts the list using the specified method [`METHOD`] and order [`ORDER`].
* `METHOD` must be one of the following:
  * `date` - Sorts chronologically by **deadline** (for deadline tasks) or start date and time (for event tasks).
    Tasks without dates (to-do tasks) are always at the **end** of the sorted list.
  * `description` - Sorts alphabetically by task **description**.
  * `type` - Sorts alphabetically by task **type**.
* `ORDER` is optional. If specified, it must be one of the following:
  * `a`/`asc`/`ascending` - Ascending order.
  * `d`/`desc`/`descending` - Descending order.
  * If not specified, it defaults to `ascending`.

Example:

`sort date /order d`

* Sorts tasks in reverse chronological order.

`sort description`

* Sorts tasks in ascending alphabetical order based on the descriptions.

`sort type /order ascending`

* Sorts tasks by type, with Deadline tasks first, followed by Event tasks, then To-do tasks.

# Credits

The format of this user guide was inspired by the very clear
[SE-EDU AddressBook user guide](https://se-education.org/addressbook-level3/UserGuide.html).
