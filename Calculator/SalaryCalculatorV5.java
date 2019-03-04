

public class SalaryCalculatorV5 {

	public static void main(String[] args) {
		Salary sal = new Salary();
		double wallet = sal.calculate();
		System.out.println("Your paycheck: " + wallet);
		
		Spent spt = new Spent();
		int out = spt.sum();
		System.out.println("Your monthly expenses: " + out);
		
		double account = wallet - out;
		System.out.println("Your account remainder: " + account);

	}

}
