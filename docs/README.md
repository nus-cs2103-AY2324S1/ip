# User Guide

Desolute is designed to be **an easy-to-use chatbot for individuals to use to keep track of their daily tasks**.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Task Management](#task-management)
- [Usage](#usage)
  - [todo](#todo---creates-a-todo-task)
  - [deadline](#deadline---creates-a-deadline-task)
  - [event](#event---creates-a-event-task)
  - [delete](#delete---deletes-a-task-from-tasklist)
  - [mark](#mark---marks-a-task-as-completed)
  - [unmark](#unmark---unmark-the-task-as-uncompleted)
  - [list](#list---list-the-tasks-in-the-tasklist)
  - [find](#find---finds-tasks-in-the-tasklist)
  - [update](#update---updates-a-task-information)
  - [help](#help---shows-help)
  - [bye](#bye---exits-the-application)

<!--Quick Started portion inspired by https://se-education.org/addressbook-level3/UserGuide.html --->
## Quick Start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `chatbot.jar` from [here](https://github.com/rayshawntan/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for the chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use `java -jar chatbot.jar` command to 
run the application.
5. Start using Desolute!

## Features 

### Task Management

Desolute helps to keep track of your tasks and displays them to you whenever you decide to view them. It also stores
them for you in local file system so whenever you decide to boot up Desolute, you can always access what tasks you have.

Desolute features the ability to:
1. **Create** a task of a specific type (todo, deadline or event)
2. **Delete** a task in the tasklist that you no longer want to keep track of.
3. **Mark** a task to show that it has been completed.
4. **Unmark** a task to show that it has not been completed.
5. **List** the tasks you have at hand.
6. **Find** tasks based on matching words.
7. **Update** a task information that you have keyed incorrectly.
8. **Help** you navigate the chatbot.
9. **Exit** the application.

## Usage

### `todo` - Creates a *todo* task

Creates a *todo* task and adds it into your tasklist and displays the result of creating it.

Format: `todo DESCRIPTION`

- Creates a *todo* task with the given `DESCRIPTION`.

Example of usage:

`todo homework`

Expected outcome:

```
Got it. I've added this task:
 [T][ ] homework
You have 1 tasks in the list.
```

### `deadline` - Creates a *deadline* task

Creates a *deadline* task and adds it into your tasklist and displays the result of creating it.

Format: `deadline DESCRIPTION /by DATE`

- Creates a *deadline* task with the given `DESCRIPTION` and `DATE`.
- Accepted format for `DATE`: YYYY-MM-DD

Example of usage:

`deadline homework /by 2023-09-20`

Expected outcome:

```
Got it. I've added this task:
 [D][ ] homework (by: Sep 20 2023)
You have 2 tasks in the list.
```

### `event` - Creates a `event` task

Creates a *event* task and adds it into your tasklist and displays the result of creating it.

Format: `event DESCRIPTION /from DATE TIME /to TIME`

- Creates a *deadline* task with the given `DESCRIPTION`, `DATE` and `TIME`.
- Accepted format for:
  - `DATE`: YYYY-MM-DD
  - `TIME`: HH(AM/PM)

Example of usage:

`event party /from 2023-09-20 8PM /to 10PM`

Expected outcome:

```
Got it. I've added this task:
 [E][ ] party (from: 2023-09-20 8 PM /to 10 PM)
You have 3 tasks in the list.
```

### `delete` - Deletes a task from tasklist

Deletes a task from your tasklist and displays the task deleted.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`. This index is referred from the number shown in the tasklist.

Example of usage:

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
 [T][ ] homework
```

### `mark` - Marks a task as completed

Marks a task in tasklist as completed and displays the task marked.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as completed. This index is referred from the number shown in the tasklist. 

Example of usage:

`mark 1`

Expected outcome:

Description of the outcome.

```
Nice! I've marked this task as done:
 [D][X] homework (by: Sep 20 2023)
```

### `unmark` - Unmark the task as uncompleted

Marks a task in tasklist as not completed and displays the task unmarked.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as not completed. This index is referred from the number shown in the 
tasklist.

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task not done yet:
 [D][ ] homework (by: Sep 20 2023)
```

### `list` - List the tasks in the tasklist

List all the tasks that are in the tasklist.

Format: `list`

Expected outcome:

```
    1. [D][ ] homework (by: Sep 20 2023)
    2. [E][ ] party (from: 2023-09-20 8 PM to: 10 PM)
  
You have 2 tasks in the list.
```

### `find` - Finds tasks in the tasklist

Finds tasks in your tasklist and displays the result.

Format: `Find DESCRIPTION`
- The search is case-sensitive.
- Only the `DESCRIPTION` will be searched.

>[!IMPORTANT]
> Tasks with words that contains `DESCRIPTION` partially will also be displayed.

Example of usage:

`find homework`

Expected outcome:

```
    1. [D][ ] homework (by: Sep 20 2023)
   
You have 1 tasks in the list.
```

### `update` - Updates a task information

Updates a task information that maybe incorrect and displays the result.

Format: `update INDEX TYPE INFORMATION`
- Update the task at the specified `INDEX`. This index is referred from the number shown in the tasklist.
- `TYPE` is the type of information you want to update.
- `INFORMATION` is the new information that is to overwrite the previous one.

> [!IMPORTANT]
> *todo* tasks can only update description, *deadline* can only update description and date, *event* can update 
description, date, start and end.

Example of usage:

`update 2 description birthday`

Expected outcome:

```
I've update the description!
 [E][ ] birthday (from: 2023-09-20 8 PM to: 10 PM)
```

### `help` - Shows help

Shows the list commands and how to use them.

Format: `help`

Expected outcome:

```
todo: Adds a ToDo task to the task list.
Parameters: Description
Example: todo homework
deadline: Adds a Deadline task to the task list.
Parameters: Description /by YYYY-MM-DD
Example deadline homework /by 2023-08-28
event: Adds a Event task to the task list.
Parameters: Description /from YYYY-MM-DD HH(AM/PM) /to HH(AM/PM)
Example: event birthday party /from 2023-08-28 06PM /to 10PM
delete: Deletes the task identified by the index used shown in the task listing.
Parameters: INDEX
Example: delete 1
mark: Use to mark a task as completed
Parameters: INDEX
Example: mark 1
unmark: Use to unmark a task as not completed
Parameters: INDEX
Example: unmark 1
list: Displays all the tasks in the task list with index numbers.
Example: list
find: Finds all tasks whos description contain any of the specific keywords (case-sensitive) 
and displays them as a list with index numbers.
Parameters: Description
Example: find homework
update: Use to update a task description, date, start time or end time.
Parameters: INDEX description/date/start/end UpdatedInfo
Example: update 1 date 2023-09-17
** Description can be updated for all task type, however, date can only be updated for deadline and event tasks and 
start and end can only be updated for event task!
Date format: YYYY-MM-DD
Time format: HH(AM/PM)
Example: update 1 description party
help: Shows program usage instructions.
Example: help
bye: Exits the program
Example: bye
```

### `bye` - Exits the application

Exits the application.

Example of usage:

`bye`