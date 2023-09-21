# AiChan - User Guide 🌟

AiChan is a desktop application to help you **manage your tasks**.

- [Quick Start](#quick-start)
- [Features](#features)
- [Usage](#usage)
- [Command Summary](#command-summary)

<!--Quick Start part inspired by https://se-education.org/addressbook-level3/UserGuide.html-->
## Quick Start

1. Ensure your computer have Java `11`.
2. Download the latest `AiChan.jar` from [here](https://github.com/KumChaiYin/ip/releases)
3. Copy the file to the folder you want to use as the home folder for AiChan.
4. Open a command terminal, `cd` into the folder in step 3, and use the `java -jar AiChan.jar` command to run AiChan application.
5. An AiChan GUI will appear.
6. Refer to the [Usage](#usage) below to see the commands.
7. Type the command and press Enter to execute it.

## Features 

### Task Management

You can add different types of task:
- todo
- deadline
- event

After adding the task, you can:
- mark a task as done
- mark a task as undone
- find a task
- delete a task
- delete all marked-as-done tasks

### Task Storage

After using, when you exit the application, AiChan will store your current tasks.
These tasks will be shown when you open the application again.

## Usage

### `todo` - Add a todo task

Adds a todo task with its description.

Format: `todo DESCRIPTION`

Example of usage: 

`todo read book`

Expected outcome:

```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list
```

### `deadline` - Add a deadline task

Adds a deadline task with its description and due date.

Format: `deadline DESCRIPTION /by DD/MM/YYYY HHmm`

Example of usage:

`deadline essay /by 25/9/2023 2359`

Expected outcome:

```
Got it. I've added this task:
  [D][ ] essay (by: Sep 25 2023 2359)
Now you have 2 tasks in the list
```

### `event` - Add an event task

Adds an event task with its description, start and due date.

Format: `event DESCRIPTION /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm`

Example of usage:

`event meeting /from 27/9/2023 1300 /to 27/9/2023 1500`

Expected outcome:

```
Got it. I've added this task:
  [E][ ] meeting (from: Sep 27 2023 1300 to: Sep 27 2023 1500)
Now you have 3 tasks in the list
```

### `list` - See all the tasks

Show all the tasks in the list, indicating its task type and whether it is done or not.

Format: `list`

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] essay (by: Sep 25 2023 2359)
3. [E][ ] meeting (from: Sep 27 2023 1300 to: Sep 27 2023 1500)
```

### `mark` - Mark task as done

Mark the task with the ID as done, show its details.

Format: `mark TASKID`

Example of usage:

`mark 2`

Expected outcome:

```
Nive! I've marked this task as done:
[D][X] essay (by: Sep 25 2023 2359)
```

### `unmark` - Mark task as undone

Mark the task with the ID as undone, show its details.

Format: `unmark TASKID`

Example of usage:

`unmark 2`

Expected outcome:

```
OK, I've marked this task as not done yet:
[D][ ] essay (by: Sep 25 2023 2359)
```

### `find` - Find tasks with keyword

Show tasks which description contains partial of keywords.

Format: `find KEYWORDS`

Example of usage:

`find read essay`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] essay (by: Sep 25 2023 2359)
```

### `delete` - Delete a task

Delete the task with the ID, show its details.

Format: `delete TASKID`

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
  [T][ ] read book
Now you have 2 tasks in the list
```

### `deleteMarked` - Delete all marked tasks

Delete all tasks marked as done, show the details of the task(s).

Format: `deleteMarked`

Example of usage:

`deleteMarked`

Expected outcome:

```
I have deleted the following tasks you done:
1. [T][X] read book
3. [E][X] meeting (from: Sep 27 2023 1300 to: Sep 27 2023 1500)
```
### `bye` - Exit the application

Exit the program.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

```
Ta-da! It's time to go~ Keep smiling till we reunite!
```

## Command Summary

| Action   | Format                                        |
|----------|----------------------------------------------|
| todo     | `todo DESCRIPTION`                            |
| deadline | `deadline DESCRIPTION /by DD/MM/YYYY HHmm`   |
| event    | `event DESCRIPTION /from DD/MM/YYYY HHmm /to DD/MM/YYYY HHmm` |
| list     | `list`                                       |
| mark     | `mark TASKID`                                |
| unmark   | `unmark TASKID`                              |
| find     | `find KEYWORDS`                              |
| delete   | `delete TASKID`                              |
| deleteMarked | `deleteMarked`                            |
| bye      | `bye`                                        |
