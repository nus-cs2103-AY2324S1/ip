# Zean Chatbot User Guide

Zean is a **desktop chatbot app for managing all your tasks effectively**, 
so you can stay organized and get things done. It features a 
Graphical User Interface (GUI), making it easy for everyone to use.

## Quick Start

1. Ensure you have `Java 11` or above installed in your Computer.
2. Download the latest zean.jar from [here](https://github.com/zhonghan721/ip/releases).
3. Copy the file to the folder you want to use as the *home folder* for Zean chatbot.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the
`java -jar zean.jar` command to run the application.
5. Refer to the Feature and Usage sections below for details of what you can achieve
with Zean.

## Features 

### Add tasks

Add todo, deadline, event tasks.

### List tasks

List all the tasks that have been added and stored.

### Mark tasks as complete/incomplete

Tasks can be marked as completed/incomplete for you to keep track of tasks.

### Update tasks

Relevant task details such as descriptions and dates can be updated.

### Find tasks

Find all the tasks with description that matches the keyword.

### Delete tasks

Delete tasks that you no longer need to keep track of.

## Usage

### `todo` - Add a todo task

Add a todo task to your task list.

Example of usage: 

`todo buy birthday cake`

Expected outcome:

The todo task "buy birthday cake" is added to your list.

```
Got it. I've added this task:
[T][] buy birthday cake
Now you have 1 task in the list. 
```

### `deadline` - Add a deadline task

Add a deadline task to your task list.

Example of usage:

`deadline assignment /by 2023-09-29`

Expected outcome:

The deadline task "assignment" with deadline 29 Sep 2023 is added to your list.

```
Got it. I've added this task:
[D][] assignment (by: Sep 29 2023)
Now you have 2 tasks in the list. 
```

### `event` - Add an event

Add an event to your task list.

Example of usage:

`event recess week /from 2023-09-23 /to 2023-10-01`

Expected outcome:

The event "recess week" from 23 Sep 2023 to 1 Oct 2023 is added to your list.

```
Got it. I've added this task:
[E][] recess week (from: Sep 23 2023 to: Oct 01 2023)
Now you have 3 tasks in the list. 
```

### `list` - List all the tasks

List all the tasks in your task list.

Example of usage:

`list`

Expected outcome:

The list of your tasks is displayed.

```
As requested, here are the tasks in your list:
1.[T][] buy birthday cake
2.[D][] assignment (by: Sep 29 2023)
3.[E][] recess week (from: Sep 23 2023 to: Oct 01 2023)
```

### `mark` - Mark task

Mark the specified task as completed.

Example of usage:

`mark 1`

Expected outcome:

The first task (`[T][] buy birthday cake` in the above example) is marked as completed.

```
Nice! I've marked this task as done:
[T][X] buy birthday cake
```

### `unmark` - Unmark task

Mark the specified task as not completed yet.

Example of usage:

`unmark 1`

Expected outcome:

The first task (`[T][] buy birthday cake` in the above example) is marked as incomplete.

```
Nice! I've marked this task as not done yet:
[T][] buy birthday cake
```

### `update` - Update task details

Update the relevant details of the specified task.

Format: `update INDEX [/description DESCRIPTION] 
[/by DEADLINE] [/from START] [/to END]`

- Words in `UPPER_CASE` are the parameters to be supplied by the user.
- Items in square brackets are optional.
- At least one of the optional fields have to be provided.
- When parameters not belonging to the tasks are provided, 
it will result in an error. (i.e. providing `/from` or `/to` 
for tasks other than event, and providing `/by` for tasks other than deadline tasks)

Example of usage:

`update 2 /description assignment 1 /by 2023-09-24`

Expected outcome:

The second task (`[D][] assignment (by: Sep 29 2023)` in the above example) is updated with 
the new description and deadline date.

```
Successfully updated your task!
```

### `find` - Find tasks with keyword

Find the tasks that matches the keyword.

Example of usage:

`find week`

Expected outcome:

A list of the tasks with description containing "week".

```
Here are the matching tasks in your list:
1.[E][] recess week (from: Sep 23 2023 to: Oct 01 2023)
```

### `delete` - Delete tasks

Find the tasks that matches the keyword.

Example of usage:

`delete 2`

Expected outcome:

The second task is deleted.

```
Noted. I've removed this task.
Now you have 2 tasks in the list.
```
