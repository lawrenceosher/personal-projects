import styles from "../styles/Button.module.css";

export default function Button(props) {
  return <button type={props.type || 'button'} onClick={props.onClick} className={styles.button}>{props.children}</button>;
}
