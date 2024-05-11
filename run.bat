# start the grid with 2 chrome containers
docker-compose -f grid.yaml up --scale chrome=2 -d

# run test suites with chrome
set BROWSER=chrome
docker-compose up

# bring down all
docker-compose -f grid.yaml down
docker-compose down