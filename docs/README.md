# Ruiz Chatbot User Guide

## Features 

### Creation of tasks

You can create different tasks in the form of "Dealines", "Events" and "Todos" and Ruiz
will remember them for you.

### Marking and Unmarking your tasks
You can mark tasks that you have completed and Ruiz will help you keep track of them.

### Search your tasks

You can search for a keyword within all of your tasks and Ruiz will return you the list
of tasks containing the keyword.

## Usage

### `todo` - adds a Todo 

adds a Todo to your list of tasks

Format: `todo <description> /at <location>`

Example of usage: 

`todo eat /at home`

Expected outcome:

A message showing a Todo has successfully been added and how many tasks you have currently.

```
Got it. I've added this task: <task>.
Now you have <taskListSize> in the list"
```

### `deadline` - adds a Deadline 

adds a deadline to your list of tasks

Format: `deadline <description> /by <YYYY-MM-DD HHmm> /at <location>`

Example of usage: 

`deadline complete assignment 1 /by 2023-11-11 1111 /at home`

Expected outcome:

A message showing a Deadline has successfully been added and how many tasks you have currently.

```
Got it. I've added this task: <task>.
Now you have <taskListSize> in the list"
```

### `event` - adds an Event 

adds an event to your list of tasks

Format: `event <description> /from <YYYY-MM-DD HHmm> /to <YYYY-MM-DD HHmm> /at <location>`

Example of usage: 

`event john's birthday /from 2023-11-11 1111 /to 2023-11-11 1112 /at john's house`

Expected outcome:

A message showing an Event has successfully been added and how many tasks you have currently.

```
Got it. I've added this task: <task>.
Now you have <taskListSize> in the list"
```

### `list` - shows the task list 

Shows you your list of tasks

Format: `list`

Example of usage: 

`list`

Expected outcome:

A list of your current tasks.

```
1. [T][ ] eat at: my house
2. [E][ ] project meeting (from: Nov 11 2023 1200 to: Nov 11 2023 1300) at: school
3. [D][ ] do IP (by: Nov 11 2023 1100) at: school
4. [T][X] sign up for careerfair at: computing club's page
```

### `mark` - marks a task 

marks the specified task as displayed in the list.

Format: `mark <task index>`

Example of usage: 

`mark 1`

Expected outcome:

A message showing Task has successfully been marked.

```
Nice! I've marked this task as done
[T][X] eat at: my house
```

### `unmark` - unmarks a task 

unmarks the specified task as displayed in the list.

Format: `unmark <task index>`

Example of usage: 

`unmark 1`

Expected outcome:

A message showing Task has successfully been unmarked.

```
OK, I've unmarked this task as not done yet
[T][ ] eat at: my house
```

### `find` - Find Tasks containing the keyword

Shows a list of tasks containing the given keyword

Format: `find <keyword>`

Example of usage: 

`find eat`

Expected outcome:

A message showing the list of Tasks that contain the keyword.

```
1. [T][ ] eat at: my house
```

### `bye` - Closes the Ruiz ChatBot

Ends Ruiz

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

A goodbye message from Ruiz and the application closes itself after 3 seconds.

```
Bye! Comeback soon!
```
