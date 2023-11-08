import { useRef, useState } from "react";
import Input from "./Input";
import ErrorModal from "./ErrorModal";

export default function ProjectInput(props) {
  const titleInput = useRef();
  const descInput = useRef();
  const dateInput = useRef();

  const modalRef = useRef();

  const [errorMessage, setErrorMessage] = useState("");

  const handleSaveProjectInput = () => {
    const enteredTitle = titleInput.current.value;
    const enteredDesc = descInput.current.value;
    const enteredDate = dateInput.current.value;

    //add validation and show modal if invalid values are entered
    if (enteredTitle.trim().length === 0) {
      
      modalRef.current.open();
      setErrorMessage(
        "Can't enter an empty title! Please enter a non-empty title."
      );
      titleInput.current.value = "";
      return;
    } else if (enteredDesc.trim().length === 0) {
      
      modalRef.current.open();
      setErrorMessage(
        "Can't enter an empty description! Please enter a non-empty description."
      );
      descInput.current.value = "";
      return;
    } else if (enteredDate.trim().length === 0) {
      
      modalRef.current.open();
      setErrorMessage(
        "Can't enter an empty date! Please enter a non-empty date."
      );
      dateInput.current.value = "";
      return;
    } else {
      props.saveProject({
        title: enteredTitle,
        description: enteredDesc,
        date: enteredDate,
      });

      titleInput.current.value = "";
      descInput.current.value = "";
      dateInput.current.value = "";
    }
  };

  return (
    <>
      <ErrorModal ref={modalRef} errorMessage={errorMessage} />
      <div className="w-[35rem] mt-16">
        <menu className="flex flex-row items-center gap-4 justify-end my-4 ">
          <button
            className="text-stone-800 hover:text-stone-950"
            onClick={props.cancelProj}
          >
            Cancel
          </button>
          <button
            className="px-6 py-2 rounded-md bg-stone-800 text-stone-50 hover:bg-stone-950"
            onClick={handleSaveProjectInput}
          >
            Save
          </button>
        </menu>

        <div>
          <Input ref={titleInput} label="Title" isTextArea={false} id="title" />
          <Input
            ref={descInput}
            label="Description"
            isTextArea={true}
            id="desc"
          />
          <Input
            ref={dateInput}
            label="Due Date"
            type="date"
            isTextArea={false}
            id="date"
          />
        </div>
      </div>
    </>
  );
}
