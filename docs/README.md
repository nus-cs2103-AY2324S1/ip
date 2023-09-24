# Eggbot User Guide
## About

Eggbot is a simple chatbot with an intuitive Graphical User Interface (GUI) that helps you track your tasks.

Eggbot is also capable of remembering your added tasks, even when the GUI is closed.


If you're new here, you can start with [Getting Started](#getting-started).

If you have used Eggbot before, you can jump staright to Eggbot's [Features and Commands](#features-and-commands).

<img src="Ui.png" width="40%" display='block' margin-left="auto" margin-right="auto"/>

**Figure 1**: How Eggbot's GUI appears on the Windows operating system.

## Getting Started

[//]: # ( <h1 style="color:purple;">Hello World</h1>)

Ensure you have Java 11 installed on your Computer.

Download the latest EGGBOT-v0.3.jar from [here](https://github.com/oeggy03/ip/releases).

Copy the file to the folder you want to use as the home folder for your Eggbot.

Open a command terminal, cd into the folder you put the jar file in, and use the java -jar EGGBOT-v0.3.jar command to run the application.

## Features and Commands 

### `help` - List all commands

To list all the commands you can give to Eggbot, you can type `help`.

**Example of usage:** 

`help`

**Expected outcome:**

Eggbot will reply with a list of commands that you can give it.

```
Hello! I'm EGGBOT!
>>> Adding of tasks 
1. ToDo task: 'todo [Task]'
2. Deadline task: 'deadline [Task /Deadline (dd-mm-yyyy hhmm)]'
3. Event task: 'event [Task /Start Date (dd-mm-yyyy hhmm)/End Date (dd-mm-yyyy hhmm)]'

etc...
```
---
### `todo` - Add a todo task

To add a todo task to your task list, you can type `todo [TASK_NAME]`

**Example of usage:**

`todo Fill in the midterm course review`

**Expected outcome:**

Eggbot will confirm when the task is added successfully, as well as tell you your total number of tasks.

```
Got it, I've added this task:
[T][ ] Fill in the midterm course review
You now have 1 task(s) in the list.
```
---
### `deadline` - Add a deadline task

To add a task with a deadline to your task list, you can type `todo [TASK_NAME]/[DEADLINE]`

Please note that the format for DEADLINE is 'DD-MM-YYYY hhmm'

**Example of usage:**

`deadline Finish ip/ 22-09-2023 2359`

**Expected outcome:**

Eggbot will confirm when the task is added successfully, as well as tell you your total number of tasks.

```
Got it, I've added this task:
[D][ ] Finish ip (By: 22 Sep 2023 11:59 PM)
You now have 2 task(s) in the list.
```
---
### `event` - Add an event task

To add an event task with a start and end time to your task list, you can type `todo [TASK_NAME]/[START_DATE_TIME]/[END_DATE_TIME]`

Please note that the format for both START_DATE_TIME and END_DATE_TIME is 'DD-MM-YYYY hhmm'

**Example of usage:**

`event Award Ceremony/ 26-09-2023 1600/ 26-09-2023 2100`

**Expected outcome:**

Eggbot will confirm when the task is added successfully, as well as tell you your total number of tasks.

```
Got it, I've added this task:
[E][ ] Award Ceremony (From: 26 Sep 2023 4:00 PM to 26 Sep 2023 9:00 PM)
You now have 3 task(s) in the list.
```
---
### `list` - View all tasks 

To view all the tasks you have added so far, you can type `list`

**Example of usage:**

`list`

**Expected outcome:**

Eggbot show you all your tasks.

```
Your tasks:
1. [T][ ] Fill in the midterm course review
2. [D][ ] Finish ip (By: 22 Sep 2023 11:59 PM)
3. [E][ ] Award Ceremony (From: 26 Sep 2023 4:00 PM to 26 Sep 2023 9:00 PM)
```
---
### `mark` - Mark a task as done

To mark a task as done, you can type `mark [TASK_INDEX]`

**Example of usage:**

`list`, followed by `mark 2`

**Expected outcome:**

Eggbot first shows you all your tasks. For instance, you are done with task 2 - you can mark it as done with `mark 2`.

```
Your tasks:
1. [T][ ] Fill in the midterm course review
2. [D][ ] Finish ip (By: 22 Sep 2023 11:59 PM)
3. [E][ ] Award Ceremony (From: 26 Sep 2023 4:00 PM to 26 Sep 2023 9:00 PM)
```

Then, Eggbot marks task 2 as done, and tells you so. A cross appears in the second [X] to mark the task.

```
Nice! I've marked this task as done:
[D][X] Finish ip (By: 22 Sep 2023 11:59 PM)
```
---
### `unmark` - "Unmark" a task as not done

To mark a task as not done, you can type `unmark [TASK_INDEX]`

**Example of usage:**

`list`, followed by `unmark 2`

**Expected outcome:**

Eggbot first shows you all your tasks. As indicated by the cross, task 2 is marked as done. You can mark this back to "not done".

```
Your tasks:
1. [T][ ] Fill in the midterm course review
2. [D][X] Finish ip (By: 22 Sep 2023 11:59 PM)
3. [E][ ] Award Ceremony (From: 26 Sep 2023 4:00 PM to 26 Sep 2023 9:00 PM)
```

Then, Eggbot "unmarks" task 2 as not done, and tells you so. The cross disappears.

```
OK, I've marked this task as not done yet:
[D][ ] Finish ip (By: 22 Sep 2023 11:59 PM)
```
---
### `delete` - Delete a task

To delete a task, you can type `delete [TASK_INDEX]`

**Example of usage:**

`list`, followed by `delete 1`

**Expected outcome:**

Eggbot first shows you all your tasks. For example, we want to delete task 1.

```
Your tasks:
1. [T][ ] Fill in the midterm course review
2. [D][X] Finish ip (By: 22 Sep 2023 11:59 PM)
3. [E][ ] Award Ceremony (From: 26 Sep 2023 4:00 PM to 26 Sep 2023 9:00 PM)
```

Eggbot then deletes task 1.

```
Noted, I've removed this task:
[T][ ] Fill in the midterm course review
```
---
### `find` - Find a task

To find a task, you can type `find [A_WORD_IN_THE_TASK]`

A_WORD_IN_THE_TASK refers to any word contained within the task, and is not case-sensitive.

**Example of usage:**

`find Award`

**Expected outcome:**

Eggbot shows you all your tasks with the word "award" in them.

```
2. [E][ ] Award Ceremony (From: 26 Sep 2023 4:00 PM to 26 Sep 2023 9:00 PM)
```
---
### `bye` - Exit the bot

Going so soon? :(

To exit the bot, you can type `exit`

**Example of usage:**

`exit`

**Expected outcome:**

Eggbot saves all your added tasks and the GUI closes.