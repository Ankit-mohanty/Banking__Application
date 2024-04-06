# Banking Application 🏦

## Overview

The **Banking System** project aims to provide a basic understanding of bank account operations. Some of the key functionalities implemented include:

1. **Account Creation**: Users can create new bank accounts. 💰
2. **Transactions**: Users can perform transactions between two accounts. 💸
3. **Transaction History**: Users can retrieve their transaction history. 📊

### Prerequisites 🛠️

Before setting up the application, ensure you have the following prerequisites:

- **Java 17** ☕
- **Maven 3.x** 📦
- **npm** 🌐

### Backend Setup 🚀

1. **Clone the Repository**:

   - Start by cloning the project repository using the following command:
     ```bash
     git clone https://github.com/AbhayKatharotiya/BankingSystem.git
     ```

2. **Navigate to the Backend Directory**:

   - Move to the root directory of the backend part of the application.

3. **Build the Project**:

   - Build the project using Maven:
     ```bash
     mvn clean install
     ```

4. **Run the Project**:

   - Execute the following command to run the backend application:
     ```bash
     java -jar target/backend-0.0.1-SNAPSHOT.jar
     ```

5. **Explore Backend APIs**:
   - The application defines the following APIs:
     - `POST /api/v1/accounts/signup`: Create a new user account.
     - `POST /api/v1/accounts/user`: Login.
     - `PUT /api/v1/accounts/`: Update user information.
     - `PATCH /api/v1/accounts/`: Deposite Money.
     - `PATCH /api/v1/accounts/`: Withdraw money.
     - `GET /api/v1/accounts/`: Retrieve account details.
     - `PATCH /api/v1/accounts/`: Perform a fund transfer.
     - `GET /api//v1/accounts/`: Get account detail by email id.

### Frontend Setup 🎨

1. **Navigate to the Frontend Directory**:

   - Move to the frontend directory.

2. **Install Dependencies**:

   - Install the required packages using npm:
     ```bash
     npm install
     ```

3. **Run the Frontend Project**:

   - Start the frontend application:
     ```bash
     npm start
     ```

4. **Access the Application**:
   - Open the URL in your browser: [http://localhost:4200/](http://localhost:4200/)

### Future Extension 🚀

If given the opportunity, I would further enhance this project by leveraging my knowledge of **Angular 17** and **Spring Boot** to create a more creative and impactful solution for society.

Feel free to explore the application, and let me know if you have any other questions or need further assistance! 😊🌟
