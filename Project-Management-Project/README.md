This is one of the practice projects in the updated course (as of 10/23/23) of Udemy Course: React - The Complete Guide 2023 (incl. React Router & Redux) by Maximilian Schwarzmüller. This practice project is a project management app that keeps track of multiple projects for a user with a title, description, and due date, and also allows the user to create tasks for each of their projects to stay organized.

I followed along with his walkthrough for the most part, but there were slight changes in my project from his.

Differences:
- Way I manage state in the app component into separate state slices, where he has it all in one. It was easier for me to conceptualize this way.
- I show more distinct error messages for the modal that change depending on which input is invalid, where he doesn't
- Added an ErrorModal to the NewTask component for more input validation

To run the project, open the src folder, type npm install in the CLI and then npm run dev to start the project on your local machine.