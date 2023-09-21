# User Guide
###  For Bob Chatbot

## Features 

### Create Tasks

The chatbot allows you to create tasks, which include todos,
events and deadlines

### Mark Tasks

The chatbot allows you to mark whether tasks are done or undone 

### Tag Tasks

The chatbot allows you to give tasks a hashtag

### List Tasks

The chatbot allows you to list all available tasks

### Delete Tasks

The chatbot allows you to delete tasks you don't want

## Usage

### `List` - Describe action

List all available tasks

Example of usage: 

`list`

Expected outcome:

All your tasks will be listed

```
1. [T] [X] hi #hi
2. [D] [X] hellow  #heyy (by: 10 October 2023)
3. [T] [ ] hi
4. [T] [X] bye
5. [E] [ ] exams  (from: 10 October 2023 to: 11 November 2023)

```

### `Add Todo` - Describe action

Adds a todo task

Example of usage:

`todo cook lunch`

Expected outcome:

Your todo task will be added to the list

```
Got it. I've added this task:
        [T] [ ] cook lunch
Now you have 8 tasks in the list.

```

### `Add Deadline` - Describe action

Adds a deadline task

Example of usage:

`deadline cook dinner /by 10/10/2023`

Expected outcome:

Your deadline task will be added to the list

```
Got it. I've added this task:
        [D] [ ] cook dinner  (by: 10 October 2023)
Now you have 9 tasks in the list.

```

### `Add Event` - Describe action

Adds an event task

Example of usage:

`event cook supper /from 10/10/2023 /to 11/10/2023`

Expected outcome:

Your event task will be added to the list

```
Got it. I've added this task:
        [E] [ ] cook supper  (from: 10 October 2023 to: 11 October 2023)
Now you have 10 tasks in the list.

```

### `Delete task` - Describe action

Deletes an existing task based on key

Example of usage:

`delete 1`

Expected outcome:

Your specified task will be deleted

```
Noted. I've removed this task:
        [T] [X] hi #hi
 Now you have 9 tasks in the list.

```

### `Find tasks` - Describe action

Finds tasks based on keyword

Example of usage:

`find cook`

Expected outcome:

The tasks with the keyword will be listed.

```
Here are the matching tasks in your list:
1. [T] [ ] cook lunch
2. [T] [ ] cook lunch
3. [T] [ ] cook lunch
4. [D] [ ] cook dinner  (by: 10 October 2023)
5. [E] [ ] cook supper  (from: 10 October 2023 to: 11 October 2023)
```

### `Mark tasks` - Describe action

Mark task done based on key

Example of usage:

`mark 1`

Expected outcome:

The task will be marked as done

```
Nice! I've marked this task as done:
        [D] [X] hellow  #heyy (by: 10 October 2023)
```

### `Unmark tasks` - Describe action

Unmark task done based on key

Example of usage:

`unmark 1`

Expected outcome:

The task will be unmarked

```
OK, I've marked this task as not done yet:
        [D] [ ] hellow  #heyy (by: 10 October 2023)
```

### `Tag tasks` - Describe action

Assign a hashtag to a task based on key

Example of usage:

`tag 1 #cool`

Expected outcome:

The task will be tagged

```
Noted. I've tagged this task:
        [D] [ ] hellow  #cool (by: 10 October 2023)
```

### `Exit` - Describe action

Exit the chatbot

Example of usage:

`bye`

Expected outcome:

The application will exit
