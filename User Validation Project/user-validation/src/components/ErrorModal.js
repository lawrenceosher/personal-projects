import styles from "../styles/ErrorModal.module.css";
import Button from "./Button";
import Card from "./Card";

export default function ErrorModal(props) {
  return (
    <div className={props.shown ? "" : styles.hidden}>
      <div
        onClick={() => props.onShowErrorModal(false)}
        className={styles.backdrop}
      />
      <Card className={styles.modal}>
        <header className={styles.header}>
          <h2>Invalid input</h2>
        </header>
        <div className={styles.content}>
          <p>{props.errorMessage}</p>
        </div>
        <footer className={styles.actions}>
          <Button onClick={() => props.onShowErrorModal(false)}>Close</Button>
        </footer>
      </Card>
    </div>
  );
}
