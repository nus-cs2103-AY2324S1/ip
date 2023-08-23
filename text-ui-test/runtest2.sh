#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

java -classpath ../bin Duke < input2.txt > ACTUAL2.TXT

cp EXPECTED2.TXT EXPECTED2-UNIX.TXT
dos2unix ACTUAL2.TXT EXPECTED2-UNIX.TXT
# compare the output to the expected output
diff ACTUAL2.TXT EXPECTED2-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test2 result: PASSED"
    exit 0
else
    echo "Test2 result: FAILED"
    exit 1
fi




