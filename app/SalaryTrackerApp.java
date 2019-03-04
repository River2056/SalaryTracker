import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SalaryTrackerApp {
	public static final int READ = 0;
	public static final int WRITE = 1;

	public void start() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please choose mode: 0. read, 1. write");
		int mode = scanner.nextInt();
		ExecutorService threadPool = Executors.newSingleThreadExecutor();

		switch (mode) {
		case READ:
			Reader read = new Reader();
			threadPool.execute(read);
			break;
		case WRITE:
			Writer write = new Writer();
			threadPool.execute(write);
			break;
		}
		threadPool.shutdown();
	}

	public static void main(String[] args) {
		SalaryTrackerApp note = new SalaryTrackerApp();
		note.start();
	}

	private class Writer implements Runnable {

		public void run() {
			Scanner scanner = new Scanner(System.in);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = cal.getTime();
			System.out.println("Write in which entry? current time: " + sdf.format(date));
			String name = scanner.nextLine();
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(
						new OutputStreamWriter(new FileOutputStream("../notes/Notes" + name + ".txt", true), "UTF-8"),
						true);
				System.out.println(
						"Your notes input here: (Format: 0204, full (or half), place of work(with who in chinese))");
				String line = scanner.nextLine();
				pw.println(line + ", " + date);
				System.out.println("Done writing!");

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (pw != null) {
					pw.close();
				}
			}
		}
	}

	private class Reader implements Runnable {

		public void run() {
			SalaryTracker sal = new SalaryTracker();
			Scanner scanner = new Scanner(System.in);
			System.out.println("Which entry do you want to read? (e.g. Jan)");
			String name = scanner.nextLine();
			while (true) {
				BufferedReader br = null;
				try {

					br = new BufferedReader(
							new InputStreamReader(new FileInputStream("../notes/Notes" + name + ".txt"), "UTF-8"));
					String line = null;

					while ((line = br.readLine()) != null) {
						String[] data = line.split(", ");
						if ("full".equals(data[1])) {
							sal.addDay();
						} else if ("half".equals(data[1])) {
							sal.addHalf();
						}
						System.out.println(line);
					}
					System.out.println();
					System.out.println("Total work days this month: " + sal.getWorkDay());
					System.out.println(
							"Paycheck this month: " + sal.getTotal() + ", account balance: " + sal.getBalance());
					System.out.println("Done reading all records!");
					break;

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
