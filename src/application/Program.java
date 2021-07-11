package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args){
		Locale.setDefault(Locale.US);
		
		Scanner scan = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			System.out.print("Room number:");
			int number = scan.nextInt();
			
			System.out.print("Check-in date (dd/MM/yyyy)");
			Date checkIn = sdf.parse(scan.next());
			
			System.out.print("Check-out date (dd/MM/yyyy)");
			Date checkOut = sdf.parse(scan.next());
			
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation.toString());
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			
			System.out.print("Check-in date (dd/MM/yyyy)");
			checkIn = sdf.parse(scan.next());
			
			System.out.print("Check-out date (dd/MM/yyyy)");
			checkOut = sdf.parse(scan.next());
			
			reservation.updateDates(checkIn, checkOut);
			System.out.println("Reservation: " + reservation.toString());
		}
		catch(ParseException e) {
			System.out.println("Invalid date format!");
		}
		catch(DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());
		}
		catch(RuntimeException e) {
			System.out.println("Unexpected error");
		}
		finally {
			scan.close();
		}
	}
}
