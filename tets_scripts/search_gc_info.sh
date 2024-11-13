#!/bin/bash

LOG_FILE="gc.log"

if [[ ! -f "$LOG_FILE" ]]; then
    echo "File '$LOG_FILE' doesnt exist."
    exit 1
fi

echo "Getting info from log file..."

while IFS= read -r line
do
    if [[ "$line" == *"garbage-first heap"* ]]; then
        total=$(echo "$line" | awk '{print $5}' | sed 's/..$//')   
        used=$(echo "$line" | awk '{print $7}' | sed 's/.$//')     

        if [[ $total -ne 0 ]]; then
            ratio=$(echo "scale=4; $used / $total" | bc)   
            echo "Total: $total, Used: $used, Ratio: $ratio"
        else
            echo "Bad format."
        fi
    fi
done < "$LOG_FILE"
