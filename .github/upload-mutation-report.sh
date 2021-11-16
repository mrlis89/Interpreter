#!/usr/bin/env bash
set -Euo pipefail

# Whenever something goes wrong, clean up the temporary file
trap "rm mutation-testing-report.json" ERR

# Find the report.js file
reportJsLocation=$(find . -name "report.js")
echo Found report.js at ${reportJsLocation}
sleep 10
# Read the file
reportJsContent=$(<${reportJsLocation})
# Strip off the first 60 characters - yes, this is brittle :-)
report="${reportJsContent:60}"
# Store the data in a temporary file
echo "${report}" > mutation-testing-report.json

BASE_URL="https://dashboard.stryker-mutator.io"
PROJECT="github.com/${GITHUB_REPOSITORY}"
VERSION=${GITHUB_REF#refs/heads/}

# Finally, upload the data using the API key that we got
echo Uploading mutation-testing-report.json to ${BASE_URL}/api/reports/${PROJECT}/master
curl -X PUT \
  ${BASE_URL}/api/reports/${PROJECT}/${VERSION} \
  -H "Content-Type: application/json" \
  -H "Host: dashboard.stryker-mutator.io" \
  -H "X-Api-Key: ${API_KEY}" \
  -d @mutation-testing-report.json

rm mutation-testing-report.json
