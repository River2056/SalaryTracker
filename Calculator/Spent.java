

public class Spent {
	//members of Expenses;
		int rent;
		int studentLoan;
		int iphoneBills;
		int eDating;
		int computerClass;
		int appleMusic;
		int iCloud;
		int dailyExpenses;
		int electricityBills;
		
		//initialize value to all members
		Spent(){ //5500, 2020, 1500, 1000, 5550, 150, 90, 250*30, 800
			rent = 5500;
			studentLoan = 2020;
			iphoneBills = 1500;
			eDating = 1000;
			computerClass = 5550;
			appleMusic = 150;
			iCloud = 90;
			dailyExpenses = 250 * 30;
			electricityBills = 800;
			
		}
		
		//methods for calculating expenses
		int sum(){
			int total = rent + studentLoan + iphoneBills + eDating + computerClass + 
					appleMusic + iCloud + dailyExpenses
					+ electricityBills;
			return total;
		}
}
