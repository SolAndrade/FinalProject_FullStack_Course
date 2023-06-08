This is a ticketing management web application for a cinema. It allows users to view a list of movies being displayed, check movie details, and purchase tickets. Additionally, administrators have access to create and edit movies.

**Backend**

**Technologies Used:**
- Java
- Spring Framework
- JUnit
- Mockito
- Maven
- Models
    - Movie: Represents a movie with attributes like title, description, genre, duration, rating, minimum age, and image URL.
    - Ticket: Represents a ticket with information about the movie, user, and other details.
    - User: Represents a user of the system with their information.
    - LoginRequest: Represents the request object for user login.
    - LoginResponse: Represents the response object for user login.

**Controllers**

The controllers are responsible for handling the REST API endpoints and implementing CRUD actions for the different models.

**Testing**

The project includes unit testing using JUnit and Mockito to ensure the correctness and reliability of the implemented functionality.

**Frontend**

**Technologies Used**
- HTML
- CSS
- Angular

The frontend of the project is developed using modern web technologies like HTML, CSS, and Angular framework.

**Project Functionality**

*Users* can view all the movies being displayed and access detailed information about each movie.

*Users* can purchase tickets for the available movies.
Administrators have access to the /admin endpoint, where they can create new movies and edit existing ones.
Running the Application

To run the application, follow the steps below:

- Start the backend server by running the necessary commands or executing the server application.
Use the provided REST API endpoints to add movies to the database.
- Start the frontend server by running the necessary commands or executing the frontend application.
Open http://localhost:4200/home in a web browser to access the application.

*Note: Please make sure to have both the backend and frontend running simultaneously for the complete functionality of the application.*

**Enjoy using the ticketing management web application for the cinema!**