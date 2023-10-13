import React from "react";
import "./index.css";
import AddUser from "./components/AddUser";
import UsersList from "./components/UsersList";
import { useState } from "react";

function App() {
  const [userInput, setUserInput] = useState([]);

  const calculateListHandler = (addedUser) => {
    setUserInput((prevList) => [...prevList, addedUser]);
  };

  return (
    <div>
      <AddUser onSubmitUser={calculateListHandler} />
      <UsersList data={userInput} />
    </div>
  );
}

export default App;
