import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class SalaryTracker {
	private double daySalary;
	private double workDay;
	private double overTime;
	private double expenses;

	public SalaryTracker() {
		daySalary = 1700;
		workDay = 0;
		overTime = 0;
		expenses = getExpenses();
	}

	public void addDay() {
		workDay++;
	}

	public void addHalf() {
		workDay += 0.5;
	}

	public void addOver(double amount) {
		overTime += amount;
	}

	public double getWorkDay() {
		return workDay;
	}

	public double getTotal() {
		return (daySalary * workDay) - (daySalary * workDay * 0.05) + (overTime * (daySalary / 8) * 1.33);
	}

	public double getBalance() {
		return getTotal() - expenses;
	}

	public double getExpenses() {
		BufferedReader br = null;
		String line = null;
		double sum = 0;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream("../notes/NotesExp.txt"), "UTF-8"));

			while ((line = br.readLine()) != null) {
				String[] data = line.split(" : ");
				double exp = Double.parseDouble(data[1]);
				sum += exp;
			}

		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sum;
	}

	public double extra(double amount) {
		return this.expenses = getExpenses() + amount;
	}

}
