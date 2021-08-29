
Install Docker Desktop for your particular OS: 
https://docs.docker.com/engine/install/
https://docs.docker.com/desktop/mac/install/
https://docs.docker.com/desktop/windows/install/

Run the following commands from the root directory of this project:
```
docker build -t musicalartistprocessor .

docker run --name musical-artist-processor musicalartistprocessor

docker cp musical-artist-processor:/output.csv output.csv
```

output.csv will contain the artist pairs that show up 50 or more times in artist_lists_small.csv