# htmlCraweler
Crawling HTML content
Main functions:

1. Reads a list of web pages (HTTP URLs) and corresponding page content requirements from a configuration file.

- Created a JSON file for configuration WebComparisionConufig.json

2. Periodically makes an HTTP request to each page.

- Spring scheduler has been created - SchedulingConfig

3. Verifies that the page content received from the server matches the content requirements.

- A logic has been included to verify the content 

4. Measures the time it took for the web server to complete the whole request.

- The response time is calculated 

5. Writes a log file that shows the progress of the periodic checks.

- Log file has been written with periodic checks - myapp.log

6. (OPTIONAL) Implement a single-page HTTP server interface in the same process that shows (HTML) each monitored web site and their current (last check) status.

- UI implemented in angular to represent the status 
 
 
Deliverables:

The software is delivered with the full source code included. All used source code must be freely distributable.

If necessary, include readme.txt to describe the software and how it meets the requirements.


Springbootapplication and angular application is committed in Gitlab 

steps to import 

Springboot 
1. Import the application in intellij IDE
2. Run the HtmlCompareApplication.java
3. Verify the results in postman

Angular
1. download the application.
2. npm install in the folder
3. ng serve 
4. go to localhost:4200 it automatically redirects

Note : Cross origin has been applied
