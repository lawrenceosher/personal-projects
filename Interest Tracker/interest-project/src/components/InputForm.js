import styles from "../styles/InputForm.module.css";
import { useState } from "react";

export default function InputForm(props) {
  const [enteredCurrentSavings, setEnteredCurrentSavings] = useState(10000);
  const [enteredYearlySavings, setEnteredYearlySavings] = useState(1200);
  const [enteredExpectedInterest, setEnteredExpectedInterest] = useState(5);
  const [enteredInvestmentDuration, setEnteredInvestmentDuration] =
    useState(15);

  const currentSavingsInputHandler = (event) => {
    setEnteredCurrentSavings(Number(event.target.value));
  };

  const yearlySavingsInputHandler = (event) => {
    setEnteredYearlySavings(Number(event.target.value));
  };

  const expectedInterestInputHandler = (event) => {
    setEnteredExpectedInterest(Number(event.target.value));
  };

  const investmentDurationInputHandler = (event) => {
    setEnteredInvestmentDuration(Number(event.target.value));
  };

  const onResetHandle = () => {
    props.onSubmitInputs({})
    setEnteredCurrentSavings(10000);
    setEnteredYearlySavings(1200);
    setEnteredExpectedInterest(5);
    setEnteredInvestmentDuration(15);
  };

  const submitHandler = (event) => {
    event.preventDefault();

    props.onSubmitInputs({
      "current-savings": enteredCurrentSavings,
      "yearly-contribution": enteredYearlySavings,
      "expected-return": enteredExpectedInterest,
      duration: enteredInvestmentDuration,
    });
  };

  return (
    <form onSubmit={submitHandler} className={styles.form}>
      <div className={styles["input-group"]}>
        <p>
          <label htmlFor="current-savings">Current Savings ($)</label>
          <input
            onChange={currentSavingsInputHandler}
            value={enteredCurrentSavings}
            type="number"
            id="current-savings"
          />
        </p>
        <p>
          <label htmlFor="yearly-contribution">Yearly Savings ($)</label>
          <input
            onChange={yearlySavingsInputHandler}
            value={enteredYearlySavings}
            type="number"
            id="yearly-contribution"
          />
        </p>
      </div>
      <div className={styles["input-group"]}>
        <p>
          <label htmlFor="expected-return">
            Expected Interest (%, per year)
          </label>
          <input
            onChange={expectedInterestInputHandler}
            value={enteredExpectedInterest}
            type="number"
            id="expected-return"
          />
        </p>
        <p>
          <label htmlFor="duration">Investment Duration (years)</label>
          <input
            onChange={investmentDurationInputHandler}
            value={enteredInvestmentDuration}
            type="number"
            id="duration"
          />
        </p>
      </div>
      <p className={styles.actions}>
        <button
          type="reset"
          onClick={onResetHandle}
          className={styles.buttonAlt}
        >
          Reset
        </button>
        <button type="submit" className={styles.button}>
          Calculate
        </button>
      </p>
    </form>
  );
}
