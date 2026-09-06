curl -X POST \
  -H "Content-Type: application/json" \
  --data @employee.avsc \
  http://localhost:8081/subjects/test-topic-value/versions


  curl -X POST \
    -H "Content-Type: application/json" \
    --data @user.avsc \
    http://localhost:8081/subjects/test-topic-value/versions