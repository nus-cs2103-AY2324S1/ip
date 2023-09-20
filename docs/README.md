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
The Mr. Bear Application is a chatbot designed to assist users with various tasks, made by [Zhu Yufan](https://www.linkedin.com/in/yufan-zhu-36024a225/) under the module CS2103T 2022/23 Sem 2.

This project, Mr. Bear, is based on [Project Duke](https://github.com/nus-cs2103-AY2223S2/ip) and is an educational tool that helps new software developers improve their skills through the gradual construction of the project and the discovery of various software engineering principles.

This is a simple user guide for the usage of Mr. Bear Application.

## Quick Start
1. Download the latest  **Duke.jar** file from [todo](https://github.com/Yiwen101/ip/releases)
2. Open terminal and go to the directory where you downloaded the **Duke.jar** file.
3. Make sure that when you launch it for the first time, there is not data/duke.txt file in the same directory. If there is, please delete it.
4. In your terminal enter the command:
        ```
        java -jar duke.jar
        ```
5. You should expect to see a window as shown below:
![welcome](welcome.png)

6. Have fun exploring its features!

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
All your modification to the tasks in the task list will be saved in the data/duke.txt file.
So please do not delete this file if you want to keep your tasks.
## FAQ
**Caution:** Avoid from modifying the program data in the data/duke.txt file.
