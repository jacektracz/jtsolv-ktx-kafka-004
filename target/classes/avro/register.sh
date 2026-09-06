curl -X POST \
  -H "Content-Type: application/json" \
  --data @employee.avsc \
  http://localhost:8081/subjects/test-topic-value/versions



  curl -X GET http://localhost:8081/subjects/t-1/versions/1