# Bus Schedule Management System

A web-based administrator module for BlueBus.com, built using Java Servlets, JDBC, and Oracle Database, enabling staff to manage bus schedules.

## Features
- Add new bus schedules
- View all bus schedules
- Update existing schedule details
- Delete bus schedule records

## Tech Stack
- Java
- Java Servlets
- JDBC
- Oracle Database
- Apache Tomcat

## Typical Project Structure
src/
├── bean/        → Entity classes (e.g., BusSchedule.java)
├── dao/         → JDBC data access classes
├── servlets/    → Controller servlets (Add/View/Update/Delete)
├── util/        → DB connection utility
WebContent/
├── *.jsp / *.html → Admin forms and result pages

## How to Run
1. Clone the repository
git clone https://github.com/shruthiramasamy5/Bus-Schedule-Management-System---Java-Servlet-Project.git
2. Set up the Oracle database and update DB connection details
3. Build and deploy as a WAR to Apache Tomcat
4. Access via browser:
http://localhost:8080/<project-name>/

## Author
Shruthika R
