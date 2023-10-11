import Header from "./components/Header"
import InputForm from "./components/InputForm"
import OutputTable from "./components/OutputTable";
import { useState } from "react";

function App() {

  const [yearlyResults, setYearlyResults] = useState([]);
  const [initialInvestmentAmount, setInitialInvesmentAmount] = useState(0);

  const calculateHandler = (userInput) => {
    const yearlyData = []; 

    setInitialInvesmentAmount(userInput['current-savings']);

    let currentSavings = +userInput['current-savings'];
    const yearlyContribution = +userInput['yearly-contribution']; 
    const expectedReturn = +userInput['expected-return'] / 100;
    const duration = +userInput['duration'];

    for (let i = 0; i < duration; i++) {
      const yearlyInterest = currentSavings * expectedReturn;
      currentSavings += yearlyInterest + yearlyContribution;
      yearlyData.push({
        year: i + 1,
        yearlyInterest: yearlyInterest,
        savingsEndOfYear: currentSavings,
        yearlyContribution: yearlyContribution,
      });
    }

    setYearlyResults(yearlyData);

  };

  return (
    <div>
      <Header />
      <InputForm onSubmitInputs={calculateHandler} />
      <OutputTable interests={yearlyResults} initialInvestment={initialInvestmentAmount} />
    </div>
  );
}

export default App;
