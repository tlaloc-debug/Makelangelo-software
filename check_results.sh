#!/bin/bash

# Read the results of the two runs from the results file
RESULT_FILE="results.log"

if [[ ! -f "$RESULT_FILE" ]]; then
    echo "The results file '$RESULT_FILE' does not exist."
    exit 1
fi

# Read the results of each run
RUN1_RESULT=$(sed -n '1p' "$RESULT_FILE")
RUN2_RESULT=$(sed -n '2p' "$RESULT_FILE")

# Evaluate the conclusion based on the results of the two runs
if [[ "$RUN1_RESULT" == "success" && "$RUN2_RESULT" == "success" ]]; then
    echo "Conclusion: The software is likely to run on both architectures."
elif [[ "$RUN1_RESULT" == "failure" && "$RUN2_RESULT" == "failure" ]]; then
    echo "Conclusion: Both builds failed. Try increasing the cache size for the 64-bit architecture."
else
    echo "Conclusion: The software is likely to run only on 64-bit architectures."
fi
