//Name : Isaac Zhi Sern Ng
//Student ID : S3611452


package s3611452_S2A2;

import java.io.PrintWriter;
import java.util.Scanner;

public class OversizedVehicle extends Vehicle {

	//instance 
	private double userHeight;
	private double weight;
	String category = "N/A";

	//constant
	private static final double CLEARANCE_HEIGHT = 3.00;
	private static final double LIGHT_VEHICLE_CHARGE = 10.00;
	private static final double MEDIUM_VEHICLE_CHARGE = 20.00;
	private static final double HEAVY_VEHICLE_CHARGE = 50.00;

	//creating an object for OversizedVehicle
	public OversizedVehicle(String regNo, String make, int year, String description, double userHeight) {
		super(regNo, make, year, description);
		this.userHeight = userHeight;

	}
	
	private void recordWeight(double weight) {
		//sorting out vehicle's weight into category
		this.weight = weight;
		if (weight <= 3.0) {
			category = "N/A";
		} else if (weight > 3.1 && weight <= 4.5) {
			category = "LIGHT";
		} else if (weight > 4.5 && weight <= 8.0) {
			category = "MEDIUM";
		} else if (weight > 8.0) {
			category = "HEAVY";
		}

	}

	public double book(int numPassengers, DateTime argsDT, double weight) throws VehicleException {
		//an overwrite to vehicle's book for oversized vehicles.
		
		if (userHeight <= CLEARANCE_HEIGHT) {
			super.book(numPassengers, argsDT);

			recordWeight(weight);
			
			//calculating the extra cost based on category
			if (category == "LIGHT") {
				cost = cost + ((weight - 3.0) * LIGHT_VEHICLE_CHARGE);
			} else if (category == "MEDIUM") {
				cost = cost + ((weight - 3.0) * MEDIUM_VEHICLE_CHARGE);
			} else if (category == "HEAVY") {
				cost = cost + ((weight - 3.0) * HEAVY_VEHICLE_CHARGE);
			}

		} else {
			throw new VehicleException("Vehicle is too high! - booking cancelled");
		}

		return cost;

	}

	public String getDetails() {
		//overides vehicle method and prints out all the details
		
		String ninthLine = String.format("%-20s %s\n", "category :", category);
		String tenthLine = String.format("%-20s %s\n", "weight :", weight);
		String eleventhLine = String.format("%-20s %s\n", "Height :", userHeight);

		if (weight == 0.0) {
			return super.getDetails() + eleventhLine;
		} else {
			return super.getDetails() + ninthLine + tenthLine + eleventhLine;
		}

	}

	public void writeToFile(PrintWriter output) {
		// a method to write to file.
		super.writeToFile(output);

		if (userHeight > 1 && userHeight < 3) {
			output.println(category);
			output.println(weight);
			output.println(userHeight);
			output.println();
		}
	}
}
