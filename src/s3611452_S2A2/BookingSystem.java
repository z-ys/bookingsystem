//Name : Isaac Zhi Sern Ng
//Student ID : S3611452


package s3611452_S2A2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BookingSystem {

	// Array List to store and manage vehicles and over sized vehicle that are
	// added
	private static ArrayList<Vehicle> arrayList = new ArrayList<Vehicle>();

	// declaring filename
	public static final String fileName = "Storage.txt";
	public static final String backupName = "Backup.txt";

	public static void main(String[] args) throws FileNotFoundException {
		// main method to run the application
		try {
			startProgram();
		} catch (VehicleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		menu();

	}

	public static void menu() {

		Scanner user_input = new Scanner(System.in);

		// displaying the feature of the menu
		System.out.println("*** Vehicle Booking System Menu ***" + '\n');
		System.out.printf("%-25s %s\n", "Seed Data", "A");
		System.out.printf("%-25s %s\n", "Add Vehicle", "B");
		System.out.printf("%-25s %s\n", "Display Vehicle", "C");
		System.out.printf("%-25s %s\n", "Book Passage", "D");
		System.out.printf("%-25s %s\n", "Exit Program", "x");
		String option = user_input.next();

		// a switch statement to control which function to call
		switch (option.toUpperCase()) {
		case "A":
			try {
				seedData();
			} catch (VehicleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "B":
			addVehicle();

			break;
		case "C":
			for (Vehicle s : arrayList) {
				System.out.println("----------------------------");
				System.out.println(s.getDetails());
				System.out.println("----------------------------");
			}

			menu();
			break;
		case "D":
			bookPassage();
			break;
		case "X":
			ExitProgram();
			break;
		default:
			System.out.println("invalid key");
			menu();

			break;
		}
		user_input.close();
	}

	public static void seedData() throws VehicleException {

		// these vehicles are hard coded by creating and object and adding in
		// values.
		// booked vehicle
		Vehicle car1 = new Vehicle("SIM479", "Honda", 2007, "white Sedan");
		car1.book(5, new DateTime(28, 10, 2018));
		Vehicle car2 = new Vehicle("SCA888", "Maserati", 2017, "Midnight Black");
		car2.book(6, new DateTime(22, 04, 2018));

		// not book vehicle
		Vehicle car3 = new Vehicle("MUF122", "Volvo", 1969, "Burn Orange");
		Vehicle car4 = new Vehicle("RAM153", "Audi", 1969, "Cobalt Blue Sedan");

		// book Oversized Vehicle
		OversizedVehicle car5 = new OversizedVehicle("WOW555", "Toyota", 2010, "Black Sedan", 1.1);
		car5.book(6, new DateTime(28, 10, 2018), 3.2);
		OversizedVehicle car6 = new OversizedVehicle("MLM222", "honda", 2005, "Velvet Red Sedan", 1.5);
		car6.book(5, new DateTime(06, 11, 2018), 6.0);
		OversizedVehicle car7 = new OversizedVehicle("TOT444", "Mazda", 2000, "Silver", 2.0);
		car7.book(3, new DateTime(30, 11, 2018), 9.0);

		// not book Oversized Vehicle
		OversizedVehicle car8 = new OversizedVehicle("AKM499", "HONDA", 1996, "Brown", 2.0);
		OversizedVehicle car9 = new OversizedVehicle("SKS474", "Audi", 1998, "Midnight Black",1.3);
		OversizedVehicle car10 = new OversizedVehicle("MKM505", "KIA", 2000, "Grey", 2.0);

		// inputing all the new objects into the arraylist
		arrayList.add(car1);
		arrayList.add(car2);
		arrayList.add(car3);
		arrayList.add(car4);
		arrayList.add(car5);
		arrayList.add(car6);
		arrayList.add(car7);
		arrayList.add(car8);
		arrayList.add(car9);
		arrayList.add(car10);

		menu();

	}

	public static void addVehicle() {
		// this method allow user to add a vehicle
		try {
			double vehicle_Height;
			Scanner user_input = new Scanner(System.in);

			// making sure sale id input is not empty
			String vehicle_ID = null;
			do {
				System.out.printf("%-35s %s", "Enter vehicle registration:", "");
				vehicle_ID = user_input.nextLine();
			} while (isEmpty(vehicle_ID));

			// Checking if registration number exist
			boolean regNoExists = false;
			for (Vehicle j : arrayList) {
				if ((vehicle_ID.equals(j.getregNo()))) {
					regNoExists = true;
				}
			}

			if (regNoExists) {
				System.out.printf("Error - Registration  %s already exists in the system!%n", vehicle_ID);
				menu();

			}
			// input input for vehicle make
			String vehicle_Make = null;
			System.out.printf("%-35s %s", "Enter vehicle make:", "");
			vehicle_Make = user_input.nextLine();

			// input year and checking if input for vehicle year price is
			// numeric
			System.out.printf("%-35s %s", "Enter vehicle year:", "");
			int vehicle_Year = 0;
			while (true) {

				String test = user_input.next();
				if (!isInteger(test)) {
					System.out.printf("%-35s %s", "Please enter numeric value:", "");
				} else {
					vehicle_Year = Integer.parseInt(test);
					break;

				}

			}
			// input description of vehicle
			System.out.printf("%-35s %s", "Enter vehicle description:", "");
			user_input.nextLine();
			String vehicle_Des = user_input.nextLine();

			// input vehicle Height checks if its numeric
			System.out.printf("%-35s %s", "Please enter vehicle height:", "");
			while (true) {

				String test = user_input.next();
				if (!isDouble(test)) {
					System.out.printf("%-35s %s", "Please enter numeric value:", "");
				} else {
					vehicle_Height = Double.parseDouble(test);
					break;

				}

			}
			// to validate if a vehicle is considered as vehicle object or
			// OversizedVehicle object.
			if (vehicle_Height <= 1.0) {

				Vehicle newVehicle = new Vehicle(vehicle_ID, vehicle_Make, vehicle_Year, vehicle_Des);
				arrayList.add(newVehicle);

			} else {
				OversizedVehicle newOversized = new OversizedVehicle(vehicle_ID, vehicle_Make, vehicle_Year,
						vehicle_Des, vehicle_Height);
				arrayList.add(newOversized);
			}

			System.out.printf("New Vehicle added successfully for registration of %s !%n", vehicle_ID);

			menu();
			user_input.close();
		} catch (InputMismatchException e) {
			System.out.println("Error! - entred worng data type ");
		}

	}

	public static void bookPassage() {
		// a method for user to book desired vehicle

		int inputDay;
		int inputMonth;
		int inputYear;
		int noPassenger;
		double inputWeight = 0;
		Scanner user_input = new Scanner(System.in);

		// making sure sale id input is not empty
		String vehicle_ID = null;
		do {
			System.out.printf("%-35s %s", "Enter vehicle registration:", "");
			vehicle_ID = user_input.nextLine();
		} while (isEmpty(vehicle_ID));

		// Checking if registration id already exist
		boolean regNoExists = false;
		for (Vehicle j : arrayList) {
			if ((vehicle_ID.equals(j.getregNo()))) {
				regNoExists = true;

				// input day and check if its a numeric number
				System.out.printf("%-35s %s", "Enter day:", "");
				while (true) {

					String test = user_input.next();
					if (!isInteger(test)) {
						System.out.printf("%-35s %s", "Please enter numeric value:", "");
					} else {
						inputDay = Integer.parseInt(test);
						break;

					}

				}
				// input month and check if its a numeric number
				System.out.printf("%-35s %s", "Enter month:", "");
				while (true) {

					String test = user_input.next();
					if (!isInteger(test)) {
						System.out.printf("%-35s %s", "Please enter numeric value:", "");
					} else {
						inputMonth = Integer.parseInt(test);
						break;

					}

				}
				// input year and check if its a numeric number
				System.out.printf("%-35s %s", "Enter year:", "");
				while (true) {

					String test = user_input.next();
					if (!isInteger(test)) {
						System.out.printf("%-35s %s", "Please enter numeric value:", "");
					} else {
						inputYear = Integer.parseInt(test);
						break;

					}

				}

				// input passengers and check if its a numeric number
				System.out.printf("%-35s %s", "Enter passenger:", "");
				while (true) {

					String test = user_input.next();
					if (!isInteger(test)) {
						System.out.printf("%-35s %s", "Please enter numeric value:", "");
					} else {
						noPassenger = Integer.parseInt(test);
						break;

					}

				}

				// input weight if its and oversized vehicle
				// check if input a numeric number
				if (j instanceof OversizedVehicle) {

					System.out.printf("%-35s %s", "Enter weight:", "");
					while (true) {

						String test = user_input.next();
						if (!isDouble(test)) {
							System.out.printf("%-35s %s", "Please enter numeric value:", "");
						} else {
							inputWeight = Double.parseDouble(test);
							break;

						}

					}

				}

				// get the current date in dateTime class format.
				DateTime booking_date = new DateTime(inputDay, inputMonth, inputYear);

				// sort out pricing of a vehicle and over sized vehicle's extra
				// charge
				int booking_fee = j.book(noPassenger, booking_date);
				try {
					if (booking_fee > 0) {
						if (j instanceof OversizedVehicle) {
							OversizedVehicle temp;
							temp = (OversizedVehicle) j;
							temp.book(noPassenger, booking_date, inputWeight);
							System.out
									.println("Booking for " + vehicle_ID + " on " + booking_date + " was successful.");
							System.out.println("The total cost of the booking is: "
									+ temp.book(noPassenger, booking_date, inputWeight));

						} else {

							j.book(noPassenger, booking_date);
							System.out
									.println("Booking for " + vehicle_ID + " on " + booking_date + " was successful.");
							System.out.println("The total cost of the booking is: " + j.cost());

						}

						menu();

					} else if (booking_fee == -2.0) {

						throw new VehicleException("Error - date must be in the future");

					} else if (booking_fee == -1.0) {
						// no of pass error
						throw new VehicleException("Error - passenger count must be greater than 0!");

					}
				} catch (VehicleException veException) {
					System.out.println(veException.getMessage());
					menu();
				}

			}
		}
		if (!regNoExists)
		// if registration does not exist ouput this statement
		{
			System.out.printf("Error - Registration  %s does not exists in the system!%n", vehicle_ID);
			menu();

		}

		user_input.close();
	}

	private static void ExitProgram() {
		// this method prints whatever that is saved in the arraylist to a
		// file.txt
		// served as a save file

		Scanner user_input = new Scanner(System.in);
		try {

			// creating an object for the files
			PrintWriter output = new PrintWriter(new FileOutputStream(fileName));
			PrintWriter backup = new PrintWriter(new FileOutputStream(backupName));

			// going through the arraylist and prints out
			for (int i = 0; i < arrayList.size(); i++) {

				if (arrayList.get(i) instanceof OversizedVehicle) {
					output.println("OversizedVehicle");
					arrayList.get(i).writeToFile(output);
					backup.println("OversizedVehicle");
					arrayList.get(i).writeToFile(backup);
				} else if (arrayList.get(i) instanceof Vehicle) {
					output.println("Vehicle");
					arrayList.get(i).writeToFile(output);
					backup.println("Vehicle");
					arrayList.get(i).writeToFile(backup);

				}

			}
			output.close();
			backup.close();
			System.out.println("Program successfully saved and exited!");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.getMessage();
			System.out.println("Error! Could not save");

			menu();
		}
		user_input.close();
	}

	public static void startProgram() throws VehicleException {
		try {
			// creating the scanner that runs through the files
			Scanner input = new Scanner(new File(fileName));
			// loop that continues as long as the file has another line exist
			while (input.hasNextLine()) {
				// assigning either vehicle or overszied vehicle
				String catagory = input.nextLine();

				// checks if its a vehicle or an overszied vehicle
				if (catagory.equals("Vehicle")) {
					String regNo = input.next();
					String make = input.next();
					int madeyear = input.nextInt();
					input.nextLine();
					String des = input.nextLine();
					String bookingID = input.next();

					// checking if it is a book vehicle or not.
					if (!bookingID.equals("N/A")) {
						int day = input.nextInt();
						int month = input.nextInt();
						int year = input.nextInt();
						int passenger = input.nextInt();

						// creating an object vehicle and add it into the array
						// list.
						Vehicle newVehicle = new Vehicle(regNo, make, madeyear, des);
						newVehicle.book(passenger, new DateTime(day, month, year));
						arrayList.add(newVehicle);

					} else if (bookingID.equals("N/A")) {
						// creating an object vehicle and add it into the array
						// list.
						Vehicle newVehicle = new Vehicle(regNo, make, madeyear, des);
						arrayList.add(newVehicle);
					}
					// checks if its a vehicle or an overszied vehicle
				} else if (catagory.equals("OversizedVehicle")) {
					String regNo = input.next();
					String make = input.next();
					int madeyear = input.nextInt();
					input.nextLine();
					String des = input.nextLine();
					String bookingID = input.next();

					// checking if it is a book vehicle or not.
					if (!bookingID.equals("N/A")) {
						int day = input.nextInt();
						int month = input.nextInt();
						int year = input.nextInt();
						int passenger = input.nextInt();

						String emptyCategory = input.next();
						double weight = input.nextDouble();
						double height = input.nextDouble();

						// creating an object vehicle and add it into the array
						// list.
						OversizedVehicle newOVehicle = new OversizedVehicle(regNo, make, madeyear, des, height);
						newOVehicle.book(passenger, new DateTime(day, month, year), weight);
						arrayList.add(newOVehicle);

					} else if (bookingID.equals("N/A")) {
						String emptyCategory = input.next();
						double emptyWeight = input.nextDouble();
						double height2 = input.nextDouble();

						// creating an object vehicle and add it into the array
						// list.
						OversizedVehicle newOVehicle = new OversizedVehicle(regNo, make, madeyear, des, height2);
						arrayList.add(newOVehicle);
					}
				}
				
			}
			System.out.println("save file successfully loaded");

		} catch (FileNotFoundException e) {
			try {
				// creating the scanner that runs through the files
				Scanner input = new Scanner(new File(backupName));
				// loop that continues as long as the file has another line
				// exist
				while (input.hasNextLine()) {
					// assigning either vehicle or overszied vehicle
					String catagory = input.nextLine();

					// checks if its a vehicle or an overszied vehicle
					if (catagory.equals("Vehicle")) {
						String regNo = input.next();
						String make = input.next();
						int madeyear = input.nextInt();
						input.nextLine();
						String des = input.nextLine();
						String bookingID = input.next();

						// checking if it is a book vehicle or not.
						if (!bookingID.equals("N/A")) {
							int day = input.nextInt();
							int month = input.nextInt();
							int year = input.nextInt();
							int passenger = input.nextInt();

							// creating an object vehicle and add it into the
							// array
							// list.
							Vehicle newVehicle = new Vehicle(regNo, make, madeyear, des);
							newVehicle.book(passenger, new DateTime(day, month, year));
							arrayList.add(newVehicle);

						} else if (bookingID.equals("N/A")) {
							// creating an object vehicle and add it into the
							// array
							// list.
							Vehicle newVehicle = new Vehicle(regNo, make, madeyear, des);
							arrayList.add(newVehicle);
						}
						// checks if its a vehicle or an overszied vehicle
					} else if (catagory.equals("OversizedVehicle")) {
						String regNo = input.next();
						String make = input.next();
						int madeyear = input.nextInt();
						input.nextLine();
						String des = input.next();
						String bookingID = input.next();

						// checking if it is a book vehicle or not.
						if (!bookingID.equals("N/A")) {
							int day = input.nextInt();
							int month = input.nextInt();
							int year = input.nextInt();
							int passenger = input.nextInt();

							String emptyCategory = input.next();
							double weight = input.nextDouble();
							double height = input.nextDouble();

							// creating an object vehicle and add it into the
							// array
							// list.
							OversizedVehicle newOVehicle = new OversizedVehicle(regNo, make, madeyear, des, height);
							newOVehicle.book(passenger, new DateTime(day, month, year), weight);
							arrayList.add(newOVehicle);

						} else if (bookingID.equals("N/A")) {
							String emptyCategory = input.next();
							double emptyWeight = input.nextDouble();
							double height2 = input.nextDouble();

							// creating an object vehicle and add it into the
							// array
							// list.
							OversizedVehicle newOVehicle = new OversizedVehicle(regNo, make, madeyear, des, height2);
							arrayList.add(newOVehicle);
						}
					}
				
				}

				System.out.println("Save files from backup file restored");

			}

			catch (Exception d) {
				System.out.println("could not find file");
			}
		}

	}

	// Function to check if input is empty
	public static boolean isEmpty(String s) {
		try {
			if (s == null || s.isEmpty()) {
				System.out.println("Error: Empty Input.");
				return true;
			} else
				return false;

		} catch (NullPointerException e) {
			return true;

		}

	}

	// Function to check if input is integer
	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}

	// function to check if input is double
	public static boolean isDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;
	}
}
