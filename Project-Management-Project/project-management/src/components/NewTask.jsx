import { useState, useRef } from "react";
import ErrorModal from "./ErrorModal";

export default function NewTask({ onAdd }) {
  const [enteredTask, setEnteredTask] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const modalRef = useRef();

  const handleTaskInputChange = (event) => {
    setEnteredTask(event.target.value);
  };

  const handleClick = () => {
    if (enteredTask.trim().length === 0) {
      modalRef.current.open();
      setErrorMessage(
        "Can't enter an empty task! Please enter a non-empty value."
      );
      setEnteredTask("");
      return;
    } else {
      onAdd(enteredTask);
      setEnteredTask("");
    }
  };

  return (
    <>
      <ErrorModal ref={modalRef} errorMessage={errorMessage} />
      <div className="flex items-center gap-4">
        <input
          type="text"
          value={enteredTask}
          onChange={handleTaskInputChange}
          className="w-64 px-2 py-1 rounded-sm bg-stone-200"
        />
        <button
          className="text-stone-700 hover:text-stone-950"
          onClick={handleClick}
        >
          Add Task
        </button>
      </div>
    </>
  );
}
