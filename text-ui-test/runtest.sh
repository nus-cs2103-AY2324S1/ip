#!/usr/bin/env bash

# delete output from previous run
if [ -e "text-ui-test/ACTUAL.TXT" ]
then
    rm text-ui-test/ACTUAL.TXT
fi

# delete save file from previous run
if [ -e "data/ekud.txt" ]
then
    rm data/ekud.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! ./gradlew build
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -jar build/libs/Ekud.jar < text-ui-test/input.txt > text-ui-test/ACTUAL.TXT

# compare the output to the expected output
diff text-ui-test/ACTUAL.TXT text-ui-test/EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi