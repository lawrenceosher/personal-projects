import styles from "../styles/UsersList.module.css";
import Card from "./Card";

export default function UsersList(props) {
  return (
    <Card className={styles.users}>
      <ul>
        {props.data.map((userInfo) => {
          return (
            <li key={userInfo.id}>
              {userInfo.username} ({userInfo.age} years old)
            </li>
          );
        })}
      </ul>
    </Card>
  );
}
