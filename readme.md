# Running Tests

TestNG parameters
Running a test suite
java -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml

Running a test suite with specific thread count
java -cp 'libs/*' org.testng.TestNG -threadcount 2 test-suites/flight-reservation.xml

TestNG by default creates test-output directory. You can change it with -d option.
java -cp 'libs/*' org.testng.TestNG -threadcount 2 -d result test-suites/flight-reservation.xml

System Properties
To pass the browser option
java -Dbrowser=chrome -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml

To run the tests using Selenium Grid
java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=localhost -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml

To run the tests using Selenium Grid with specific thread count
java -Dselenium.grid.enabled=true -Dselenium.grid.hubHost=localhost -cp 'libs/*' org.testng.TestNG test-suites/flight-reservation.xml -threadcount 2



run.bat
# start the grid with 2 chrome containers
docker-compose -f grid.yaml up --scale chrome=2 -d

# run test suites with chrome
set BROWSER=chrome
docker-compose up

# stop chrome and run firefox containers
docker-compose -f grid.yaml up --scale firefox=2 -d

# run test suites with firefox
set BROWSER=firefox
docker-compose up

# run test suites with edge
docker-compose -f grid.yaml up --scale edge=2 -d
set BROWSER=edge
docker-compose up

# bring down all
docker-compose -f grid.yaml down
docker-compose down