# Todoify User Guide

Welcome to **Todoify** - a simple CLI task tracking desktop app.

![Todoify Screenshot](Ui.png ':size=400')

Todoify is easy to use for users with experience of the CLI. It:
- has a chatbot-like interface
- available via your preferred [DE](https://en.wikipedia.org/wiki/Desktop_environment) or even your [terminal](https://en.wikipedia.org/wiki/Computer_terminal).
- uses Unix- and POSIX-like CLI syntax, so experienced users can pick it up quickly.

?> *Tip:* Use the **sidebar** on the left (if hidden, tap the menu button at the bottom left to reveal it) to browse the **Table of Contents**, or even **search for information**!

## Getting Started

1. Ensure you have **Java 11 or later installed** on your device.

2. **Check out** the [latest release](https://github.com/wxwern/ip/releases/latest).

3. **Download** the `.jar` file linked there, and move it to the *home folder* where you want to use Todoify.

4. **Launch** the JAR file by doing one of the following:
    - **Open a terminal** and **`cd` into the directory of the JAR file**. Then, **run `java -jar todoify-1.0.jar`**.
        - *Note:* The version number may not be the same. Change the version number if it's not the version you downloaded.

    - **Navigate to the folder containing the JAR file** in your file manager, then **double-click to open** it.
        - *Note:* Some systems might not correctly allow you from launching Todoify like this, hence you might need to use the terminal method above instead - see the notice at the [Launch Modes > Graphical User Interface](#graphical-user-interface-gui) section for information.

5. You will see [a GUI like above](#todoify-user-guide). See alternative [launch modes](#launch-modes-and-usage) if you prefer to use the TUI.

6. **Talk to Todoify** to manage your tasks!
    - To learn how to communicate with Todoify, simply send a `help` message to list available commands.

## Features

### Command-Line Interface (CLI)

Todoify uses a Command-Line Interface format similar to others on Unix and Linux, but it is not exactly the same. Additionally, you "send" your command to the Todoify chatbot, instead of a shell.

#### Example Command Format

```sh
commandname long input text --parameter1 first value --parameter2 second value
```

The command here is long. On small screens, you might need to scroll horizontally to see all of it.

This evaluates:
- the command `commandname`,
- with input `long input text`,
- while it sets `--parameter1` to `first value`,
- and sets `--parameter 2` to `second value`.

#### Detailed Command Formatting Notes

- The first word is always the **command name**.
- The second word till the last non-"`--`"-prefixed word is the **command input text**.
- **Parameters** are identified by name and with a `--` prefix. Order of different command parameters are not important, so `--from A --to B` and `--to B --from A` mean the same thing.
- Any text without the `--` prefix is not considered to be a parameter and is considered part of a contiguous text chunk, so no need to surround text with quotes like in standard shell commands.
- If duplicate parameters are present, the last value is considered.
- There are no methods (yet) to escape the `--` prefix. Hence, your content should not contain a word with said prefix.

#### Miscellaneous
- In help sheets, when a command format description contains stuff in `<>`, it refers to an inline explanatory description that should be replaced with actual command info.

### Launch Modes and Usage

For very advanced users, you can launch Todoify in both **GUI** and **TUI** modes. While the former requires a [desktop environment (DE)](https://en.wikipedia.org/wiki/Desktop_environment), the latter can operate on headless systems with no DE - like a server or a [headless Raspberry Pi](https://learn.sparkfun.com/tutorials/headless-raspberry-pi-setup/all).

#### Graphical User Interface (GUI)

**To launch in GUI mode**, either:

- Navigate to the directory of the JAR file in your terminal via `cd`, then run: `java -jar todoify-1.0.jar`.
- Navigate to where the JAR file is stored in your file manager, then double-click the JAR file.

You will see the following:

![Todoify Screenshot](first-launch.png ':size=400')

To interact with Todoify, click the text box at the bottom, type your message, and press Enter to Send.

!> As Todoify is not [code-signed](https://en.wikipedia.org/wiki/Code_signing), some platforms have security measures in place that prevent you from opening them via a simple double-click. You may thus need to temporarily bypass these protections to launch Todoify. ([Windows](https://support.microsoft.com/en-us/windows/add-an-exclusion-to-windows-security-811816c0-4dfd-af4a-47e4-c301afe13b26), [macOS](https://support.apple.com/en-gb/guide/mac-help/mh40616/mac))

!> Some Java versions may not open correctly via double-click, and some might also claim the JAR file is damaged. In which case, please try opening via the terminal method above instead.

?> *Note:* For terminal invocations, you might need to replace `todoify-1.0.jar` with a different name (specifically, the version number part) if it's not the same as the one you downloaded.

#### Text-only User Interface (TUI)

**To launch in TUI mode**, navigate to the directory of the JAR file in your terminal, then run:

- `java -jar todoify-1.0.jar --text-ui`, or
- `java -jar todoify-1.0.jar -t` as a shortcut.

You will see the following:

```shell
$ java -jar todoify-1.0.jar -t
[Todoify]:
  Hello! I'm Todoify, your friendly task helper.

[Todoify]:
  You have no tasks right now! :)

[Todoify]:
  What can I do for you?

[You]:
 >
```

To send a message, simply type your message and press Enter.

?> *Note:* You might need to replace `todoify-1.0.jar` with a different name (specifically, the version number part) if it's not the same as the one you downloaded.

## Commands

### Viewing Help: `help`, `tutorial`

It is good to know that Todoify allows you to directly check all available commands offline, and will be kept up-to-date on every new release.

All you need to do is to send `help` or `tutorial`, and you'll get all the info you need!

- Format: `help` or `tutorial`.

### Adding a Todo: `todo`

You can add a todo with the given title.

- Format: `todo <task title>`

### Adding a Deadline: `deadline`

You can add a task with a title and a deadline.

- Format: `deadline <task title> --by <task deadline in ISO8601>`

We use the [ISO8601 format specifications](https://en.wikipedia.org/wiki/ISO_8601) of `yyyy-mm-ddThh:mm:ss` without timezone. You must provide a valid date, though we allow you to **omit** any of the following:

- The entire time component (e.g., `hh:mm:ss`)
- The seconds component (e.g., `:ss`)

For example, these are valid inputs:

- `deadline CS2103T Project --by 2023-09-22T16:00`
- `deadline Christmas Prep --by 2023-12-23`
- `deadline Homework --by 2023-11-11T23:59:59`

Note that custom timezone inputs are not supported - we'll always use your system timezone. Existing tasks will however correctly show their displayed dates according to system timezone changes during the time of message response.

### Adding an Event: `event`

You can add an event with a title and a date range.

- Format: `event <title> --from <start date in ISO8601> --to <end date in ISO8601>`

We use the [ISO8601 format specifications](https://en.wikipedia.org/wiki/ISO_8601) of `yyyy-mm-ddThh:mm:ss` without timezone. You must provide a valid date, though we allow you to **omit** any of the following:

- The entire time component (e.g., `hh:mm:ss`)
- The seconds component (e.g., `:ss`)

For example, these are valid inputs:

- `event Birthday Party --from 2023-10-01T16:00 --to 2023-10-01T21:00`
- `event Holiday --from 2023-12-01 --to 2024-01-01`

Note that custom timezone inputs are not supported - we'll always use your system timezone. Existing tasks will however correctly show their displayed dates according to system timezone changes during the time of message response.

### List all tasks: `list`

Lists all your available tasks.

- Format: `list`

This gives you your full list of tasks in one place with a task number prefixed. The output is of the following format:

```
Task Number. <Type> [Completion State] Task Description (Extra Details)
```

Where:
- **Task Number** represents the number assigned to the task.
- **Type** represents the task type, e.g., `E` for Event, `D` for Deadline, `T` for Todo.
- **Completion State** represents whether it's marked as complete. It's complete if it's `[X]`, incomplete if `[ ]`.
- **Task Description** is the information you added to the task.
- **Extra Details** is any extra information associated with this task.

### Find tasks: `find`, `search`

You can search for tasks containing a specific substring, case-insensitive.

- Format: `find <substring>` or `search <substring>`

For example:

- `find Book` finds tasks with "book" in the title, ignoring case.
- `search Prep Work` finds tasks with "prep work" in the title, ignoring case.

The output is simply that of a filtered output from the `list` command.

### Completing a task: `mark`, `done`, `complete`

You can mark a task as complete by their **task number**. The task number is the one shown when using `list` or `find`.

- Format: `mark <task number>` or `done <task number>` or `complete <task number>`

For example:

- `mark 1` marks the task numbered 1 as completed.
- `done 3` marks the task numbered 3 as completed.
- `complete 5` marks the task numbered 5 as completed.

### Revert completing a task: `unmark`, `undone`, `incomplete`

You revert a completed task to the incomplete state by their **task number**. The task number is the one shown when using `list` or `find`.

- Format: `unmark <task number>` or `undone <task number>` or `incomplete <task number>`

For example:

- `unmark 1` marks the task numbered 1 as incomplete.
- `undone 3` marks the task numbered 3 as incomplete.
- `incomplete 5` marks the task numbered 5 as incomplete.

### Deleting a task: `delete`, `remove`

You can easily delete tasks by their **task number**. The task number is the one shown when using `list` or `find`.

- Format: `delete <task number>` or `remove <task number>`

For example:

- `delete 1` deletes the task numbered 1.
- `remove 3` removes the task numbered 3.

!> **Warning:** This action is permanent and cannot be undone.

!> **Important Note:** Deleting a task will change numbering for all tasks after it. Be careful when you are planning to deleting multiple tasks.

### Saving data: `save`

You can tell Todoify to immediately save your data.

?> *Note:* This is usually not needed as Todoify **autosaves data** after all commands that modify your data.

This may be useful in some situations, like retrying the save after fixing an issue causing a failed automatic save (such as insufficient permissions).

- Format: `save`

### Loading data: `load`

You can tell Todoify to immediately reload all data from storage.

?> *Note:* This is usually not needed by most users.

If the data files were updated by an external software while the app is open, Todoify may not realise that. Hence, this may be useful to reload all data from disk.

- Format: `load`

### Closing the conversation: `bye`, `exit`

This terminates the conversation with Todoify. Simply run the `bye` or `exit` command to close the conversation.

- Format: `bye` or `exit`

In the **GUI** (default), this halts further input. It does not close the app, so you can refer to a read-only transcript of your conversation. To restart, you must close and reopen the app.

If you are an advanced user using the **TUI**, this exits the program. You can use your terminal scrollback if you need to refer to the transcript.

## Command Summary

A summary of all commands is accessible via the `help` command within Todoify. A copy is reproduced here:

- `todo`
    - Adds a todo with the given title.
    - Format: `todo <task title>`
- `deadline`
    - Adds a deadline with the given title and due by date.
    - Format: `deadline <deadline title> --by <deadline in IS08601>`
- `event`
    - Adds a deadline with the given title and date range.
    - Format: `event <event title> --from <start date in IS08601> --to <end date in IS08601>`
- `delete`, `remove`
    - Deletes the referenced task.
    - Format: `delete <task number>`
- `mark`, `done`, `complete`
    - Marks the referenced task as complete.
    - Format: `mark <task number>`
- `unmark`, `undone`, `incomplete`
    - Sets the referenced task as incomplete.
    - Format: `unmark <task number>`
- `list`
    - Lists all available tasks.
    - Format: `list`
- `find`, `search`
    - Finds all available tasks with the given search term.
    - Format: `find <search terms>`
- `help`, `tutorial`
    - Shows the help sheet for all commands.
    - Format: `help`
- `save`
    - Saves your data to disk. Usually not necessary thanks to auto-save.
    - Format: `save`
- `load`
    - Loads the data from disk and replaces the contents in memory to it.
    - Format: `load`
- `bye`, `exit`
    - Stops the conversation.
    - Format: `bye`

