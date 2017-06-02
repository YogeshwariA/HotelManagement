package com.full.hotel.management;

import java.util.Scanner;

public class HotelManager {

	private static enum Hotel {
		PARK(90, 20, 5, 2), HYAAT(82, 12, 5, 3), RADDISSON(30, 18, 3, 3), RAINTREE(58, 15, 3, 2), ACCORD(41.55, 11.8, 1,
				3), FORTUNE(43, 14.8, 1, 4);

		private double costPerDay;
		private double discountPercentage;
		private int discountDays;
		private int star;

		private Hotel(double costPerDay, double discountPercentage, int star, int discountDays) {
			this.costPerDay = costPerDay;
			this.discountDays = discountDays;
			this.discountPercentage = discountPercentage;
			this.star = star;

		}

		public Double getCostPerDay() {
			return costPerDay;
		}

		public double getDiscountPercentage() {
			return discountPercentage;
		}

		public int getDiscountDays() {
			return discountDays;
		}

		public int getStar() {
			return star;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try(Scanner scanner = new Scanner(System.in)){
		System.out.println("Enter amount: ");
		int amount = scanner.nextInt();
		System.out.println("How many days u want to stay here?: ");
		int days = scanner.nextInt();
		System.out.println("What is your rating 1 or 3 or 5?.");
		int rating = scanner.nextInt();

		System.out.println(bookHotel(amount, days, rating));
	}
	}

	private static String bookHotel(double money, int days, int starRating) {

		Hotel[] hotels = Hotel.values();

		double minCost = 0;
		String hotelName = "";

		for (Hotel hotel : hotels) {
			if (starRating == hotel.getStar()) {

				double totalCost = getCostOfHotel(days, hotel);
				if (minCost == 0) {
					minCost = totalCost;
					hotelName = hotel.toString();

				} else if (totalCost < minCost) {
					hotelName = hotelName.toString();
					minCost = totalCost;
				}

			}

		}

		return isHotelBooked(money, days, starRating, minCost, hotelName);

	}

	private static String isHotelBooked(double money, int days, int starRating, double minCost, String hotelName) {
		if (money > minCost) {
			return "Hotel Booked for " + days + " days in " + starRating + " star Hotel " + hotelName
					+ " for the cost of " + minCost + " for hotel";
		} else {
			return "Sorry! you dont have sufficient amount to book any hotel";
		}
	}

	private static double getCostOfHotel(int noOfDays, Hotel hotel) {
		double totalCost = hotel.getCostPerDay() * noOfDays;

		if (noOfDays > hotel.getDiscountDays()) {
			totalCost = totalCost - ((totalCost / 100) * hotel.getDiscountPercentage());
		}

		return totalCost;
	}

}
