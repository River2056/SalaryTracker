
import java.util.Scanner;

public class Salary {
	Scanner scan = new Scanner(System.in);
	int daySalary;
	int workDays;
	int overTime;
	
	Salary(){
		System.out.println("Please enter your day salary: ");
		this.daySalary = scan.nextInt();
		System.out.println("How many days of work: ");
		this.workDays = scan.nextInt();
		System.out.println("Any overtime? ");
		this.overTime = scan.nextInt();
	}
	
	double calculate(){
		double salary = (daySalary * workDays) - (daySalary * workDays * 0.05);
		double overWork = overTime * (daySalary / 8) * 1.33;
		double trueSalary = salary + overWork;
		return trueSalary;
	}
}
