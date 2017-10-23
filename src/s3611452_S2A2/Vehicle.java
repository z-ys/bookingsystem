//Name : Isaac Zhi Sern Ng
//Student ID : S3611452

package s3611452_S2A2;

import java.io.PrintWriter;

public class Vehicle extends DateTime {

	// instance variable
	private String regNo;
	private String make;
	private int year;
	private String description;
	private String bookingID;
	private int numPassengers;
	private DateTime bookingDate;
	public double cost;

	// constant variable
	protected static final int BOOKING_FEE = 100;
	protected static final int PASSENGER_SURCHARGE = 20;

	// a constructor for vehicle object
	public Vehicle(String regNo, String make, int year, String description) {

		this.regNo = regNo;
		this.make = make;
		this.year = year;
		this.description = description;
		this.bookingID = "N/A";

	}

	public String getregNo() {
		// getter
		return regNo;
	}

	private boolean recPass(int numPassengers) {
		// method to validate if passenger limit

		if (numPassengers < 1 || numPassengers > 6) {

			return false;
		} else {
			return true;
		}

	}

	// method to book a car
	public int book(int numPassengers, DateTime date) {

		if (recPass(numPassengers)) {
			this.numPassengers=numPassengers;
			// calling current date from DateTime class.
			DateTime i = new DateTime();
			DateTime curr_date = new DateTime(Integer.parseInt(i.toString().substring(9, 10)),
					Integer.parseInt(i.toString().substring(6, 7)), Integer.parseInt(i.toString().substring(0, 4)));

			// validates if booking date is in future.
			if (DateTime.diffDays(date, curr_date) > 0) {
				bookingID = getregNo() + date.getEightDigitDate();
				bookingDate = date;

				cost = BOOKING_FEE + (numPassengers * PASSENGER_SURCHARGE);
			
			} else {
				return -2;
			}
		} else {
			return -1;
		}
		return numPassengers;
	}

	public double cost() {
		// a method to calculate cost
		
		cost = BOOKING_FEE + (numPassengers * PASSENGER_SURCHARGE);
		return cost;
	}

	public String getDetails() {
		
		// this method prints out details.
		String firstLine = String.format("%-20s %s\n", "Reg Num:", regNo);
		String secondLine = String.format("%-20s %s\n", "Make:", make);
		String thirdLine = String.format("%-20s %s\n", "Year:", year);
		String fourthLine = String.format("%-20s %s\n", "Description:", description);
		String fifthLine = String.format("%-20s %s\n", "Booking ref:", bookingID);
		String sixthLine = String.format("%-20s %s\n", "Booking Date:", bookingDate);
		String seventhLine = String.format("%-20s %s\n", "Num Passengers:", numPassengers);
		String eighthLine = String.format("%-20s %s\n\n", "Fee:", cost);

		// to ensure it wont return information that are not required.
		if (bookingID.equals("N/A")) {
			return firstLine + secondLine + thirdLine + fourthLine + fifthLine;
		} else {
			return firstLine + secondLine + thirdLine + fourthLine + fifthLine + sixthLine + seventhLine + eighthLine;

		}
	}

	public void writeToFile(PrintWriter output) {
		// a method to write to file.
		output.println(regNo);
		output.println(make);
		output.println(year);
		output.println(description);
		output.println(bookingID);
		
		//checks if there is a booking date and prints date
		if (bookingID.equals("N/A") == false) {
			output.println(bookingDate.getEightDigitDate().substring(0, 2));
			output.println(bookingDate.getEightDigitDate().substring(2, 4));
			output.println(bookingDate.getEightDigitDate().substring(4, 8));

			output.println(numPassengers);
		}
		output.println();
	}
}
