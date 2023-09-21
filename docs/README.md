# User Guide for Cracker

## Summary

1. [Initial setup](#initial-setup)
2. [list - List out all of your current tasks](#list---list-out-all-of-your-current-tasks)
3. [find - Find a particular task by a keyword](#find---find-a-particular-task-by-a-keyword)
4. [mark - Mark a task as completed](#mark---mark-a-task-as-completed)
5. [unmark - Mark a task as incomplete](#unmark---mark-a-task-as-incomplete)
6. [delete - Delete a task](#delete---delete-a-task)
7. [clear - Delete all existing tasks](#clear---clear-all-existing-tasks)
8. [todo - Create a todo task](#todo---create-a-todo-task)
9. [deadline - Create a deadline task](#deadline---create-a-deadline-task)
10. [event - Create an event](#event---create-an-event-task)
11. [bye - End the program](#bye---end-the-program)

## Initial setup

1. Ensure Java 11 is installed in your computer.
2. Download the latest jar file from [here](https://github.com/antonTan96/ip/releases) and save it anywhere.
   1. Your created tasks will be stored in `data/duke.txt`.
3. Open a terminal/console and move to the preferred directory.
4. Run `java -jar duke.jar`.
5. Start chatting!

## Features

### `list` - List out all of your current tasks

Back to [Summary](#summary)

#### Input:

`list`

#### Sample Response:

```
Here are the tasks in your list:
1. [T][X] Assignment 1
2. [E][ ] Work (from: 01 Jan 2023 to: 21 Jan 2023)
```

### `find` - Find a particular task by a keyword

Back to [Summary](#summary)

#### Description:

Shows all your tasks with the description containing the specified keyword (case-sensitive).

#### Input:

`find <keyword>`

#### Sample Input:

`find Lect`

#### Sample Response:

```
Here are the matching tasks in your list:
1. [T][X] CS2103 Lecture Quiz
2. [T][ ] CS2101 Lecture Presentation
```

### `mark` - Mark a task as completed

Back to [Summary](#summary)

#### Input:

`mark <index of task>`

#### Requirements:

- Task list must be non-empty
- Index must range from 1 to the size of the task list

#### Sample Input:

`mark 1`

#### Sample Response:

```
Operation done. This is the current state of yout task:
[T][X] CS2101 Lecture Presentation
```

### `unmark` - Mark a task as incomplete

Back to [Summary](#summary)

#### Input:

`unmark <index of task>`

#### Requirements:

- Task list must be non-empty
- Index must range from 1 to the size of the task list

#### Sample Input:

`unmark 1`

#### Sample Response:

```
Operation done. This is the current state of yout task:
[T][ ] CS2101 Lecture Presentation
```

### `delete` - Delete a task

Back to [Summary](#summary)

#### Input:

`delete <index of task>`

#### Requirements:

- Task list must be non-empty
- Index must range from 1 to the size of the task list

#### Sample Input:

`delete 1`

#### Sample Response:

```
Got it. I've removed this task:
[T][X] CS2103 Lecture Quiz
Now you have 1 task(s) in the list.
```

### `Clear` - Delete all tasks

Back to [Summary](#summary)

#### Input:

`clear`

#### Sample Input:

`clear`

#### Sample Response:

```
All Tasks have been cleared!
```

### `todo` - Create a todo task

Back to [Summary](#summary)

#### Description:

Creates a todo task, containing a task description.

#### Input:

`todo <task description>`

#### Requirements:

- Task description must be non-empty

#### Sample Input:

`todo Assignment`

#### Sample Response:

```
Got it. I've added this task:
  [T][ ] Assignment
Now you have 1 task(s) in the list.
```

### `deadline` - Create a deadline task

Back to [Summary](#summary)

#### Description:

Creates a task that has a date as a deadline

#### Input:

`deadline <task description> /by <deadline date>`

#### Requirements:

- Task description must be non-empty
- Deadline date must be non-empty, with format: `yyyy-MM-dd`

#### Sample Input:

`deadline Assignment /by 2023-10-20`

#### Sample Response:

```
Got it. I've added this task:
  [D][ ] Assignment (by: 20 Oct 2023)
Now you have 2 task(s) in the list.
```

### `event` - Create an event task

Back to [Summary](#summary)

#### Description:

Creates a task that has a starting date and an ending date.

#### Input:

`event <task description> /from <start date> /to <end date>`

#### Requirements:

- Task description must be non-empty
- Start and end date must be non-empty, with format: `yyyy-MM-dd`

#### Sample Input:

`event CCA Camp /from 2023-09-20 /to 2023-09-24`

#### Sample Response:

```
Got it. I've added this task:
  [E][ ] CCA Camp (from: 20 Sep 2023 to:
24 Sep 2023)
Now you have 3 task(s) in the list.
```

### `bye` - End the program

Back to [Summary](#summary)

#### Description:

Signs off and prevents the user from adding any more input. Also saves the task list.

#### Input:

`bye`

#### Response:

```
Bye! Your tasks have been saved!
```
