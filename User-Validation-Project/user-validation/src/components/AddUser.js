import styles from "../styles/AddUser.module.css";
import Button from "./Button";
import Card from "./Card";
import ErrorModal from "./ErrorModal";
import { useState } from "react";

export default function AddUser(props) {
  const [enteredUsername, setEnteredUsername] = useState("");
  const [enteredAge, setEnteredAge] = useState("");

  const [error, setError] = useState("");
  const [showError, setShowError] = useState(false);

  const userInputChangeHandler = (event) => {
    setEnteredUsername(event.target.value);
  };

  const ageInputChangeHandler = (event) => {
    setEnteredAge(Number(event.target.value));
  };

  const validateInputOnClickHandler = (event) => {
    event.preventDefault();
    if (enteredUsername.trim().length === 0 || enteredAge.length === 0) {
      setError("Please enter a valid name and age (non-empty values).");
      setShowError(true);
      setEnteredAge("");
      setEnteredUsername("");
      return;
    }
    if (enteredAge < 0) {
      setError("Can't enter an age less than 0.");
      setShowError(true);
      setEnteredAge("");
      return;
    } else {
      props.onSubmitUser({
        username: enteredUsername,
        age: enteredAge,
        id: Math.random().toString(),
      });
      setEnteredAge("");
      setEnteredUsername("");
    }
  };

  return (
    <div>
      <ErrorModal
        shown={showError}
        onShowErrorModal={setShowError}
        errorMessage={error}
      ></ErrorModal>
      <Card className={styles.input}>
        <div>
          <label htmlFor="username">Username</label>
          <input
            type="text"
            id="username"
            value={enteredUsername}
            onChange={userInputChangeHandler}
          ></input>
          <label htmlFor="age">Age (Years)</label>
          <input
            type="number"
            id="age"
            value={enteredAge}
            onChange={ageInputChangeHandler}
          ></input>
          <Button type="submit" onClick={validateInputOnClickHandler}>
            Add User
          </Button>
        </div>
      </Card>
    </div>
  );
}
