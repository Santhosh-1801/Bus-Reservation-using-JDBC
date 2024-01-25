package BusReservation;

import java.util.Scanner; //nested package/ hierarchical package


public class BusDemo {

	public static void main(String[] args) throws Exception {
		BusDAO busdao=new BusDAO();
		busdao.displayInfo();

		int userChoice=1;
		Scanner sc=new Scanner(System.in);

		while(userChoice==1){
			System.out.println("Enter 1 to book and 2 to exit");
			userChoice=sc.nextInt();
			if(userChoice==1){
				Booking booking=new Booking();
				if(booking.isAvailable()){
					BookingDAO bookingDAO=new BookingDAO();
					bookingDAO.addBooking(booking);
					System.out.println("Your booking is confirmed");
				}
				else{
					System.out.println("Sorry. Bus is full. Try another bus or date.");
				}
			}
		}

	}

}
