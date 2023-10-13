Second Practice Project associated with Section 9 of the Udemy Course: React - The Complete Guide 2023 (incl. React Router & Redux) by Maximilian Schwarzmüller. This project is a user validator that checks whether or not the inputs that the user enters for their username and age are both non-empty values, and that their age is a non-negative number.

The differences in my project and the instructor's final version were:
- Added user as an object in the AddUser component instead of in the App component 
- Added a .hidden CSS selector in the ErrorModal.module.css file. This was to help with hide-and-show logic. I also used state in the AddUser component for whether or not to show the ErrorModal component, and I passed that state through props to set it with onClick handlers in the ErrorModal component. 
- Used another state slice for setting the different error messages based on the input validation in the AddUser  component, and I passed that to the ErrorModal component through props

To run the project, open the src folder, type npm install in the CLI and then npm start to start the project on your local machine.