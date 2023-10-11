import styles from "../styles/OutputTable.module.css";

export default function OutputTable(props) {

  const formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  });
   
  if (props.interests.length !== 0) {
    return (
      <table className={styles.result}>
        <thead>
          <tr>
            <th>Year</th>
            <th>Total Savings</th>
            <th>Interest (Year)</th>
            <th>Total Interest</th>
            <th>Invested Capital</th>
          </tr>
        </thead>
        <tbody>
          {props.interests.map((yearResult) => {
            return (
              <tr key={yearResult.year}>
                <td>{yearResult.year}</td>
                <td>{formatter.format(yearResult.savingsEndOfYear)}</td>
                <td>{formatter.format(yearResult.yearlyInterest)}</td>
                <td>{formatter.format(yearResult.savingsEndOfYear - props.initialInvestment - yearResult.yearlyContribution * yearResult.year)}</td>
                <td>{formatter.format(props.initialInvestment + yearResult.yearlyContribution * yearResult.year)}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    );
  } else {
    return <p style={{textAlign: 'center'}}>No interests have been calculated yet.</p>;
  }
}
