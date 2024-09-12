# User Guide

## Details of Bot
**Name:** ChampionSOS

**Date of Birth:** 18 August 2023

**List of contents** <br>
1. [Features](#features) <br>
2. [Usage](#usage)

## Features 

### 1) Listing tasks

Show all the tasks stored in the ChampionSOS bot.

### 2) Marking tasks

Mark a task as complete in the ChampionSOS bot.

### 3) Unmarking tasks

Mark a task as **not** complete in the ChampionSOS bot.

### 4) Finding tasks

Find a list of tasks that contains  a given keyword.

### 5) Reminding user of tasks

Shows the list of tasks that are due in 10 days or less to remind users.

### 6) Adding Todo tasks

Adds a todo task.

### 7) Adding Deadline tasks

Adds a deadline task.

### 8) Adding Event tasks

Adds an event task.

### 9) Deleting tasks

Delete a task from the existing task list.

### 10) Exit the Bot

Closes the ChampionSOS bot.

<br>
<br>

## Usage

### `list` - List down all tasks

List all the tasks stored in the ChampionSOS bot.

Format: `list`

Example of usage: 

`list`

Expected outcome:

A list of all the tasks and its details will be shown.

```
Here are the tasks in your list:
1.[D][] submit work (by: 28 September 2023)
2.[E][] attend fiesta (from: 25 September 2023 to: 26 September 2023)
3.[T][] eat lunch
```

### `mark` - Mark a task as complete

Mark a task as complete in the ChampionSOS bot.

Format: `mark [Task number]`

Example of usage:

`mark 2`

Expected outcome:

The stated task (Eg. 2) will be marked as complete with an [X].

```
Nice! I've marked this task as done:
 [E][X] attend fiesta (from: 25 September 2023 to: 26 September 2023)
```

### `unmark` - Mark a task as NOT complete

Mark a task as **not** complete in the ChampionSOS bot.

Format: `unmark [Task number]`

Example of usage:

`unmark 2`

Expected outcome:

The stated task (Eg. 2) will be marked as **not** complete by removing the [X].

```
OK, I've marked this task as not done yet:
 [E][] attend fiesta (from: 25 September 2023 to: 26 September 2023)
```

### `find` - Find tasks with a matching keyword

Find a list tasks that contains the given keyword.

Format: `find [keyword]`

Example of usage:

`find eat`

Expected outcome:

The list of tasks that has a matching keyword will be shown.

```
Here are the matching tasks in your list:
1.[T][] eat lunch
2.[D][] eat dinner (by: 20 September 2023)
```

### `remind` - Remind user of tasks due soon

Shows the list of tasks that are due in 10 days or less to remind users.

Format: `remind`

Example of usage:

`remind`

Expected outcome:

The relevant tasks due soon will be shown.

```
Here are the tasks in your list DUE SOON:
1.[D][] submit work (by: 28 September 2023)
2.[E][] attend fiesta (from: 25 September 2023 to: 26 September 2023)
3.[D][] eat dinner (by: 20 September 2023)
```

### `todo` - Add Todo task

Adds a task of the Todo type into the list.

Format: `todo [task description]`

Example of usage:

`todo borrow book`

Expected outcome:

The todo task and details will be added and stored into the list.

```
Got it. I've added this task:
 [T][] borrow book
Now you have 5 tasks in the list.
```

### `deadline` - Add deadline task

Adds a task of the Deadline type into the list.

Format: `deadline [task description] /by [YYYY-MM-DD]`

Example of usage:

`deadline return book /by 2023-12-12`

Expected outcome:

The deadline task and details will be added and stored into the list.

```
Got it. I've added this task:
 [D][] return book (by: 12 December 2023)
Now you have 5 tasks in the list.
```

### `event` - Add event task

Adds a task of the Event type into the list.

Format: `event [task description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]`

Example of usage:

`event IG Convention Fair /from 2023-09-25 /to 2023-09-26`

Expected outcome:

The event task and details will be added and stored into the list.

```
Got it. I've added this task:
 [E][] IG Convention Fair (from: 25 September 2023 to: 26 September 2023)
Now you have 6 tasks in the list.
```

### `delete` - Delete task

Delete and remove task from the list and storage.

Format: `delete [task number]`

Example of usage:

`delete 6`

Expected outcome:

The task and its details will be deleted and removed from the list.

```
Noted. I've removed this task:
 [E][] IG Convention Fair (from: 25 September 2023 to: 26 September 2023)
Now you have 5 tasks in the list.
```

### `bye` - Close the bot

Exit and close the bot.

Format: `bye`

Example of usage:

`bye`

Expected outcome:

The bot will exit and close.