import { useRef, forwardRef, useImperativeHandle } from "react";
import { createPortal } from "react-dom";
import Button from "./Button";

const ErrorModal = forwardRef(function ErrorModal(props, ref) {
  const dialogRef = useRef();

  useImperativeHandle(ref, () => {
    return {
      open() {
        dialogRef.current.showModal();
      },
    };
  });

  return createPortal(
    <>
      <dialog
        ref={dialogRef}
        className="backdrop:bg-stone-900/90 p-4 rounded-md shadow-md"
      >
        <h2 className="text-xl font-bold text-stone-700 my-4">Invalid Input</h2>
        <p className="text-stone-600 mb-4">{props.errorMessage}</p>
        <form method="dialog" className="mt-4 text-right">
          <Button>Close</Button>
        </form>
      </dialog>
    </>,
    document.getElementById("modal-root")
  );
});

export default ErrorModal;
