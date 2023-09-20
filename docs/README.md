<!-- Inspired by https://yufannnn.github.io/ip/ for the structure and format of the user documentation. Reused its template, but fill in with my own content-->
# User Guide
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
- [Usage](#usage)
    * [Adds Todo Tasks](#adds-todo-tasks)
    * [Adds Deadline Tasks](#adds-deadline-tasks)
    * [Adds Event Tasks](#adds-event-tasks)
    * [Lists All Tasks](#lists-all-tasks)
    * [Mark Tasks as Done](#mark-tasks-as-done)
    * [Unmark Tasks](#unmark-tasks)
    * [Tag Tasks](#tag-tasks)
    * [Untag Tasks](#untag-tasks)
    * [Find Tasks with keywords](#find-tasks-with-keywords)
    * [Find Tasks with tags](#find-tasks-with-tags)
    * [Delete Tasks](#delete-tasks)
    * [Bye](#bye)
- [FAQ](#faq)

## Introduction
This app is designed to help the user with task management. It allow you to add, delete, mark, unmark, tag, untag, find tasks with keywords/tags and list all the tasks in the task list. It also supports saving the tasks in the task list to a file and loading the tasks from the file when you launch the app again.

## Quick Start
1. Download the latest  **Duke.jar** file from [todo](https://github.com/Yiwen101/ip/releases)
2. Open terminal and go to the directory where you downloaded the **Duke.jar** file.
3. Make sure that when you launch it for the first time, there is not data/duke.txt file in the same directory. If there is, please delete it.
4. In your terminal enter the command:
        ```
        java -jar duke.jar
        ```
5. You should expect to see a window as shown below:
![greeting](quickStart.png)
6. Have fun exploring its features!
![ui](Ui.png)

## Features

| Command  |                      Usage                      |                     Explaination                     |
|:--------:|:-----------------------------------------------:|:----------------------------------------------------:|
|   todo   |               todo <Description>                |                   add a todo task                    | 
| deadline |     deadline <Description> /by <YYYY-MM-DD>     |               add a task with deadline               | 
|  event   |    event <Description> /from <from> /to <to>    |            add a task with start/end time            |
|   list   |                      list                       |       displaying all the tasks in the list now       |
|   mark   |                  mark <index>                   |          mark the task at the index as done          |
|  unmark  |                 unmark <index>                  |         mark the task at the index as undone         |
|   tag    |                   tag <index>                   |        add a new tag to the task at the index        |
|  untag   |                  untag <index>                  |       delete the tag for the task at the index       |
|   find   |                 find <keyword>                  | find all the tasks with keyword in their description |
| findtag  |                  findtag <tag>                  |             find all tasks with the tag              |
|  delete  |                 delete <index>                  |      delete the task at the index from the list      |
|   bye    |                      bye                        |                     exit the app                     |

## Usage

### Adds Todo Tasks
The `todo <Description>` command allows you to add a task of given description to your task list. 
It takes in a single argument, which is the description of the task.

For example, `todo read book`,  would  return the following output:

>Got it. I've added this task:
> 
>[T][ ] read book (tags:todo)
> 
>Now you have 1 tasks in the list.

### Adds Deadline Tasks
The `deadline <Description> /by <YYYY-MM-DD>` command allows you to add a task with given deadline to your task list. 
It takes in two arguments, the description of the task and the the deadline of the task.

For example, `deadline return book /by 2023-10-07` would return the following output:

>Got it. I've added this task:
> 
>[D][ ] return book (tags: deadline) (by: Oct 07 2023)
> 
>Now you have 2 tasks in the list.

### Adds Event Tasks
The `event <Description> /from <from> /to <to>` command allows you to add task spanning a period of time to your task list. 
It takes in three arguments, the description of the task, the start time of the event and the end time of the event.

For example, running`event project meeting /from Aug 6th 2pm /to 4pm`, would return the following output

> Got it. I've added this task:
> 
> [E][ ] project meeting (tags: event) (from: Aug 6th 2pm to: 4pm)
> 
> Now you have 3 tasks in the list.

### Lists All Tasks
The `list` command allows you to view all tasks and their status in your task list. 

For example, `list` will return the following output:

>Here are the tasks in your list:
>
> 1. [T][ ] read book (tags:todo)
> 
> 2. [D][ ] return book (tags: deadline) (by: Oct 07 2023)
> 
> 3. [E][ ] project meeting (tags: event) (from: Aug 6th 2pm to: 4pm)

### Mark Tasks as Done
The `mark <TaskIndex>` command allows you to mark a task as done in your task list. 
It takes in one argument, the index of the task you want to mark as done.
For example,`mark 1`,  would return the following output:

> Nice! I've marked this task as done:
>
> [D][X] read book (tags:todo)


### Unmark Tasks
The `unmark <TaskIndex>` does the opposite of mark command.
It takes in a single argument which is the index of the task you want to unmark.
For example: `unmark 1`,  would return the following output:
> Ok, I've marked this task as not done yet:
>
> [D][ ] read book (tags:todo)

### Tag Tasks
The `tag <TaskIndex> <Tag>` command allows you to add a tag to a task in your task list.
It takes in two arguments, the index of the task you want to tag and the tag you want to add.
For example: `tag 1 important`,  would return the following output:
> Added tag to task:
> [T][ ] read book (tags:todo, important)

### Untag Tasks
The `untag <TaskIndex> <Tag>` command allows you to remove a tag from a task in your task list.
It takes in two arguments, the index of the task you want to untag and the tag you want to remove.
For example: `untag 1 todo`,  would return the following output:
> Deleted tag todo for task:
> [T][ ] read book (tags:important)

### Find Tasks with keywords
The `find <Keyword>` command allows you to find tasks whose description contains the keyword.
It takes in one or more arguments which are the keywords you want to search for,

For example, running the command `find project`, would return the following output:
> Here are the matching tasks in your list:
>
> 1. [E][ ] project meeting (tags: event) (from: Aug 6th 2pm to: 4pm)

And running the command `find daskjh`, would return the following output:
> You have no tasks with the keyword daskjh.

But please take note that, to perform operations like mark, unmark,tag, untag, you still need to input the index of the task when running list.

### Find Tasks with tags
The `findtag <Tag>` command allows you to find tasks with the given tag.
It takes in one arguments which is the tags you want to search for.
for example, running the command `findtag event`, would return the following output:
> Here are the matching tasks in your list:
> 
> 1. [E][ ] project meeting (tags: event) (from: Aug 6th 2pm to: 4pm)

And running the command `findtag daskjh`, would return the following output:
> You have no tasks with this tag!

But please take note that, to perform operations like mark, unmark,tag, untag, you still need to input the index of the task when running list.

### Delete Tasks
The `delete <TaskIndex>` command allows you to delete a task from your task list. 
It takes in a single argument which is the index of the task you want to delete.

For example: `delete 1`, would return the following output:

> Ok, I've removed this task:
>
> [D][ ] read book (tags:todo)
>
> Now you have 2 tasks in the list.

if you run `list` again, you will get the following output:

>Here are the tasks in your list:
>
> 1.  [D][ ] return book (tags: deadline) (by: Oct 07 2023)
>
> 2.  [E][ ] project meeting (tags: event) (from: Aug 6th 2pm to: 4pm)


### Bye
The `bye` command allows you to exit the task list application.
All your modification to the tasks in the task list will be saved, and you can continue from where you left off when you launch the app again.
## FAQ
Q: How do I save my tasks?

A: All your modification to the tasks in the task list will be saved in the data/duke.txt file.
So please do not delete this file if you want to keep your tasks.

Q: Why could I only see 4 tasks in the list after I added 5 tasks?

A: Due to the limitation of the window size, you can only see 4 tasks at a time. But I am working on a solution to this problem.

Q: Why deadline must take YYYY-MM-DD as the format of the date, but event can take any format?

A: Because most of the time the starting time and ending time of an event is going to be quite close and share quite some repetition in common, so I think it is more convenient to allow the user to input the date in any format. But for deadline, it is more likely that the deadline is on an exact day, so I think it is better to force the user to input the date in YYYY-MM-DD format.

