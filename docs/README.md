# User Guide

## Project ~~Duke~~ Bloop
This is a project template for a greenfield Java project.
It's named after my favourite whale shark called Bloop 🐳😄.
Given below are instructions on how to use it.

Hello from
```

██████╗░██╗░░░░░░█████╗░░█████╗░██████╗░
██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔══██╗
██████╦╝██║░░░░░██║░░██║██║░░██║██████╔╝
██╔══██╗██║░░░░░██║░░██║██║░░██║██╔═══╝░
██████╦╝███████╗╚█████╔╝╚█████╔╝██║░░░░░
╚═════╝░╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░
```

## Features of Bloop Bloop 🐳🐳
- [x] Echo
- [x] Todo
- [x] DeadLine
- [x] Event
- [x] List
- [x] Mark
- [x] Unmark
- [x] Stats
- [x] Help
- [ ] Stay tuned for more!!😄

# SneakPeek
![alt text](Ui.png)

# Commands & Uses (At a Glance 👀)

## Echo 🔊:
> Bloop Bloop will echo back whatever you said.<br>
> Use the command: **echo** _input_
>
> **Example**:
>> You: echo I love you <br>
>> Bloop: "Echo: I love you"


## Todo ✏️:
> Bloop Bloop will help you track the task you want to complete <br>
> Use the command: **todo** _taskName_
>
> **Example**:
>> You: todo laundry
>> Bloop: Got it. I've added this task: <br>
>>        [T][ ] laundry <br>
>>        Now you have X tasks in the list


## DeadLine ⏰:
> Bloop Bloop will help you track a time-sensitive task! <br>
> Use the command: **deadline** _taskName_ /by _date/time_ <br>
>
> **Example**:
>> You: deadline homework /by 20/09/2023 1234 <br>
>> Bloop: Got it. I've added this task: <br>
>>        [D][ ] homework (by: 20 Sep 2023 12:34) <br>
>>        Now you have X tasks in the list <br>


## Event 📅:
> Bloop Bloop will help you track an important event! <br>
> Use the command: **event** /from _date/time_ /to _date/time_<br>
> 
> **Example**:
>> You: event spa camp /from 22/09/2023 1200 /to 25/09/2023 1500<br>
>> Bloop: Got it. I've added this task<br>
>>        [E][ ] spa camp (from: 22 Sep 2023 12:00 to: 25 Sep 2023 15:00)<br>
>>        Now you have X tasks in the list


## List 📝:
> Bloop Bloop will list down all your tasks!!<br>
> Use the command: **list**<br>
> 
> **Example**:
>> 1. [T][ ] laundry<br>
>> 2. [D][ ] homework (by: 20 Sep 2023 12:34)<br>
>> 3. [E][ ] spa camp (from: 22 Sep 2023 12:00 to: 25 Sep 2023 15:00)


## Mark/Unmark/Stats ✔️❌:
> Bloop Bloop will help you track your progress! <br>
> Use the command: **mark** _taskNumber_ (to mark as done)<br>
> Use the command: **unmark** _taskNumber_ (to mark as undone)<br>
> Use the command: **stats** (to see your progress)<br>
> **Example**:
>>**mark 1**
>>> 1. [T][X] laundry<br>
>
>> **mark2** <br>
>> **mark3** 
>>> 2. [D][X] homework (by: 20 Sep 2023 12:34)<br>
>>> 3. [E][X] spa camp (from: 22 Sep 2023 12:00 to: 25 Sep 2023 15:00)
>
>> **unmark 1**
>>> 1. [T][ ] laundry<br>
>
>> **list** <br> 
>> 1. [T][ ] laundry<br>
>> 2. [D][X] homework (by: 20 Sep 2023 12:34)<br>
>> 3. [E][X] spa camp (from: 22 Sep 2023 12:00 to: 25 Sep 2023
>
> **Stats**
>> Here are the task statistics: <br>
>> Tasks completed this week: 2<br>
>> 66.67% tasks completed this week<br>
>> Total tasks completed: 2<br>
>> 66.67% total tasks completed

## Help 💁:
> Can't remember all the commands? 
> Dont worry!!! <br>
> Use the command: **help** <br>
> Bloop Bloop will assist you and list out all available commands!


## Acknowledgements:
> Javadocs were written by ChatGPT :) <br>
> The extra Javadocs html were generated using IntelleJ software :)
