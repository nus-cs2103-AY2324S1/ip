---
layout: page
title: User Guide
---

Alice Task Manager (Alice) is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, she can get your tasks done faster than traditional GUI apps.

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `alice.jar` from [here](https://github.com/ncduy0303/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your application.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar alice.jar` command to run the application. A GUI similar to the below should appear in a few seconds. You can also run the CLI version by using the `--cli` flag.<br>
   ![Ui](./Ui.png)

5. Type the command in the command box and press Enter to execute it. Some example commands you can try:

    * `list` : Lists all tasks.

    * `deadline return book /by 2023-08-06T14:00:00` : Adds a return book task with the deadline of 6th August 2023, 2pm.

    * `delete 3` : Deletes the 3rd task shown in the current list.

    * `bye` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.

* Every parameter has to be in the order specified in the format and cannot be omitted.<br>
  e.g. if the format specifies `deadline DESCRIPTION /by DATETIME`, `deadline /by 2023-08-06T14:00:00 DESCRIPTION` and `deadline DESCRIPTION` will not be accepted as valid commands.

* Dates and times should be in the format `YYYY-MM-DD` and `HH:MM:SS` respectively.<br>
  e.g. `2021-08-06T14:00:00` will be interpreted as 6th August 2021, 2pm.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.<br>
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Listing all tasks : `list`

Shows a list of all tasks in the application.

Format: `list`

### Locating tasks by description: `find`

Finds tasks whose descriptions contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive and will match even if only a substring of the keyword(s) is matched.
  e.g. `Meet` will match `meeting`
* The order of the keywords does not matter. e.g. `meeting work` will match `work meeting`
* Tasks matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `meeting work` will return `work on my project` and `meeting with friends`

Examples:

  ![result for 'find meeting work'](./findMeetingWorkResult.png)

### Adding a task : `todo`/`deadline`/`event`

Adds a task to the list. There are 3 types of tasks: todo, deadline, and event.

Format: `todo DESCRIPTION` or `deadline DESCRIPTION /by DATETIME` or `event DESCRIPTION /from DATETIME /to DATETIME`

* Adds a task to the list.
* The task type is specified by the first word in the command: `todo`, `deadline`, or `event`.
* The description of the task is specified after the task type.
* For deadline tasks, the deadline is specified after the `/by` keyword.
* For event tasks, the start and end times are specified after the `/from` and `/to` keywords respectively.
  `DATETIME` must be in the format specified [above](#features).

Examples:
* `todo read book`
```markdown
Got it nyaa~! I added this taskie for you:
  [T][ ] read book []
Now you have 1 taskie uwu in the list, desu~!
```
* `deadline return book /by 2023-08-06T14:00:00`
```markdown
Got it nyaa~! I added this taskie for you:
  [D][ ] return book [] (by: 2:00 PM, Aug 6 2023)
Now you have 2 taskies uwu in the list, desu~! 
```
* `event project meeting /from 2021-08-06T14:00:00 /to 2021-08-06T16:00:00`
```markdown
Got it nyaa~! I added this taskie for you:
  [E][ ] project meeting [] (from: 2:00 PM, Aug 6 2021 to: 4:00 PM, Aug 6 2021)
Now you have 3 taskies uwu in the list, desu~!
```

### Deleting a task : `delete`

Deletes the specified task from the list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list by the `list` command.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the list.
```markdown
Nyaa~ noted! I removed this taskie for you:
  [D][ ] return book [] (by: 2:00 PM, Aug 6 2023)
Now you have 2 taskies uwu in the list, desu~!
```

### Marking a task as done/not done : `mark`/`unmark`

Marks the specified task from the list as done or not done.

Format: `mark INDEX` or `unmark INDEX`

* Marks the task at the specified `INDEX` as done or not done.
* The index refers to the index number shown in the displayed task list by the `list` command.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `mark 2` marks the 2nd task in the list as done.
```markdown
Nyan nyan~! I marked this taskie as done for you:
  [E][X] project meeting [] (from: 2:00 PM, Aug 6 2021 to: 4:00 PM, Aug 6 2021)
```
* `list` followed by `unmark 1` marks the 1st task in the list as not done.
```markdown
Okie dokie nyaa~! I marked this taskie as not done yet:
  [E][ ] project meeting [] (from: 2:00 PM, Aug 6 2021 to: 4:00 PM, Aug 6 2021)
```

### Tagging a task : `tag`

Tags the specified task from the list with the given tag.

Format: `tag INDEX TAG [MORE_TAGS]`

* Tags the task at the specified `INDEX` with the given tag(s).
* The index refers to the index number shown in the displayed task list by the `list` command.
* The index **must be a positive integer** 1, 2, 3, …​
* The tag(s) must be alphanumeric and **cannot contain whitespace**.

Examples:
* `list` followed by `tag 2 important work` tags the 2nd task in the list with the tags `important` and `work`.
```markdown
Nyan~! I added some cute tags to this taskie:
  [E][ ] project meeting [important, work] (from: 2:00 PM, Aug 6 2021 to: 4:00 PM, Aug 6 2021)
```

### Untagging a task : `untag`

Clears all tags from the specified task from the list.

Format: `untag INDEX`

* Clears all tags from the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list by the `list` command.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `untag 2` clears all tags from the 2nd task in the list.
```markdown
Nyaa~, I cleared all the tags for this taskie:
  [E][ ] project meeting [] (from: 2:00 PM, Aug 6 2021 to: 4:00 PM, Aug 6 2021)
```

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Alice's data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Alice data are saved automatically as `[JAR file location]/alice.txt`. Advanced users are welcome to update data directly by editing that data file.

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Alice home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action              | Format, Examples                                                                                                                                                                                                                                                                                      |
|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **List**            | `list`                                                                                                                                                                                                                                                                                                |
| **Find**            | `find KEYWORD [MORE_KEYWORDS]` <br> e.g., `find meeting work`                                                                                                                                                                                                                                         |
| **Add**             | `todo DESCRIPTION` <br> e.g., `todo read book` <br> <br> `deadline DESCRIPTION /by DATETIME` <br> e.g., `deadline return book /by 2023-08-06T14:00:00` <br> <br> `event DESCRIPTION /from DATETIME /to DATETIME` <br> e.g., `event project meeting /from 2021-08-06T14:00:00 /to 2021-08-06T16:00:00` |
| **Delete**          | `delete INDEX` <br> e.g., `delete 2`                                                                                                                                                                                                                                                                  |
| **Mark**/**Unmark** | `mark INDEX` <br> e.g., `mark 2` <br> <br> `unmark INDEX` <br> e.g., `unmark 1`                                                                                                                                                                                                                       |
| **Tag**             | `tag INDEX TAG [MORE_TAGS]` <br> e.g., `tag 2 important work`                                                                                                                                                                                                                                         |
| **Untag**           | `untag INDEX` <br> e.g., `untag 2`                                                                                                                                                                                                                                                                    |
| **Exit**            | `exit`                                                                                                                                                                                                                                                                                                |
