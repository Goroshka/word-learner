# word-learner
Simple application for learning new language.  
You can add, see list and repeat words.  
  
In-memory database is used for storing words.  
At start a file containing words is read from filesystem.  
After shutting down application words are written to file on filesystem.

To run with docker, use commands:
```
docker build -t word-learner .
docker volume create word-learner-db
docker run --rm -d --name wordlearner -p 8080:8080 -v word-learner-db:/tmp/.word_learner_application/DB/WordDB/ word-learner
```
