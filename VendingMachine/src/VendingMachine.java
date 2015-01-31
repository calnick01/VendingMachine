/**
 * Author: Nicholas A. Webber
 * Date: 01/24/2015
 * Unit 1 Assignment: Vending Machine
 */
import java.util.Scanner; //Allows for keyboard input
import java.text.NumberFormat; //Formats currency
import java.math.BigDecimal; //Resolves double rounding errors
import java.math.RoundingMode; //Used in collaboration with BigDecimal for rounding.  Not sure what half.even is but it is most commonly used.

public class VendingMachine {
	public static void main(String[] args) throws InterruptedException {
		double price[] = { 0.50, 1.00, 1.25, 1.50, 2.00 };// 1d array for vending machine item prices.
		// double price[]=new double[5]; //Included to remind myself methods of
		// initializing arrays.
		// price[0]=0.50;
		// price[1]=1.00;
		// price[2]=1.25;
		// price[3]=1.50;
		// price[4]=2.00;
		String product[] = { "Gum", "Chips", "Pencil", "Sprite", "Coffee" };// 1d array for vending machine goods.

		double moneyIn = 0;
		int quarters, dimes, nickels, pennies;
		String proceed;
		byte exit = 1;// boolean would've been a better choice

		Scanner scanner = new Scanner(System.in);// keyboard input
		NumberFormat formatter = NumberFormat.getCurrencyInstance();// format currency
		System.out.println("This vending machine contains the following:");
		Thread.sleep(1000);

		System.out.println("\n\n\n#\tProduct\tPrice");// This line and the following loop display the Vending Machine menu
		for (int counter = 0; counter < price.length; counter++) {
			System.out.println((counter + 1) + "\t" + product[counter] + "\t" + formatter.format(price[counter]));
		}
		Thread.sleep(1000);

		System.out.println("\n\nThis machine accepts 5 dollar bills, 1 dollar bills, "
						+ "quarters, dimes, nickels, and pennies. "
						+ "\n\nEnter 0 when finished inserting money.\n\nPlease insert money.");

		while (exit > 0) 
		{
			// This loop controls the insertion of money to the vending machine.
			//The loop ends once the user enters 0.
			double demonination = scanner.nextDouble();
			
			if (demonination != 5 && demonination != 1 && demonination != 0.25
					&& demonination != 0.1 && demonination != 0.05
					&& demonination != 0.01 && demonination != 0) 
			{
				System.out.println("You must enter 5, 1, 0.25, 0.10, 0.05, or 0.01");
				//The if statement controls for profane values.
			} else if (demonination == 5) {
				moneyIn = 5 + moneyIn;
				System.out.println("Total credit: " + formatter.format(moneyIn)
						+ "\nEnter 0 when finished inserting money.\n");
			}//$5 input statement 
			else if (demonination == 1) {
				moneyIn = 1 + moneyIn;
				System.out.println("Total credit: " + formatter.format(moneyIn)
						+ "\nEnter 0 when finished inserting money.\n");
			}//$1 input statement
			else if (demonination == 0.25) {
				moneyIn = 0.25 + moneyIn;
				System.out.println("Total credit: " + formatter.format(moneyIn)
						+ "\nEnter 0 when finished inserting money.\n");
			} //25¢ input statement
			else if (demonination == 0.10) {
				moneyIn = 0.10 + moneyIn;
				System.out.println("Total credit: " + formatter.format(moneyIn)
						+ "\nEnter 0 when finished inserting money.\n");
			} //10¢ input statement
			else if (demonination == 0.05) {
				moneyIn = 0.05 + moneyIn;
				System.out.println("Total credit: " + formatter.format(moneyIn)
						+ "\nEnter 0 when finished inserting money.\n");
			} //5¢ input statement
			else if (demonination == 0.01) {
				moneyIn = 0.01 + moneyIn;
				System.out.println("Total credit: " + formatter.format(moneyIn)
						+ "\nEnter 0 when finished inserting money.\n");
			} //1¢ input statement
			
			else if (demonination == 0) //Controls for user not entering any funds, or requesting to exit the loop. 
			{
				exit = 0;
				System.out.println("Total credit: " + formatter.format(moneyIn));
				if (moneyIn == 0) //In the event the user didn't insert funds at all. 
				{
					System.out.println("****You didn't input any money.****");
				} else {
					break;
				}
			}
		}
		Thread.sleep(1000);
		System.out.println("\n\n\n\n\n");

		do
			{
			if (moneyIn > 0.5) { //Enters product selection, given the user has enough money to make a purchase.
				System.out.println("Please make your selection now.\n");
				System.out.println("#\tProduct\tPrice");
				for (int counter = 0; counter < price.length; counter++) {
					// This loop displays the goods contained in the vending
					// machine.
					System.out.println((counter + 1) + "\t" + product[counter]
							+ "\t" + formatter.format(price[counter]));
				}
				
				byte selection = scanner.nextByte();
				// This variable selects the good to be purchased.
				
				if (moneyIn > price[selection - 1]) {
					System.out.println("\n\n\nYou have "
							+ formatter.format(moneyIn) + " and you selected "
							+ product[selection - 1] + ". That item costs "
							+ formatter.format(price[selection - 1]) + ".");
					moneyIn = moneyIn - price[selection - 1];
					if(moneyIn >= 0.5) {
							System.out.println("\nYou have another "
									+ formatter.format(moneyIn)
									+ " left to spend. \n\nWould you like to purchase another item?");
					}
					else {
						System.out.println("You don't have enough credit to purchase more items.\n\n");
						Thread.sleep(1000);
						break;
					}
				} 
				else { // Insufficient funds statement.
					System.out.println("I'm sorry you didn't insert enough money. Would you like to make another selection?");
				}
				if(moneyIn>=0.5){
					proceed = scanner.next();// Yes or other input.
				}
				else{
					proceed="no";
				}
			}
			else 
			{
				if(moneyIn == 0)
				{ // No money for purchase. Insufficient funds statement.}
					System.out.println("You haven't inserted any money.  Please restart the program.");
					proceed="no";
				}
				else 
				{
					System.out.println("You don't have enough money to make a purchase.");
					proceed="no";
				}
			}
			
		} while (proceed.contains("Y") || proceed.contains("y"));
			
		System.out.println("Dispensing " + formatter.format(moneyIn));
		BigDecimal bgRate = new BigDecimal(Double.toString(moneyIn));
		bgRate = bgRate.setScale(2, RoundingMode.HALF_EVEN);
		bgRate = bgRate.multiply(new BigDecimal("100"));
		int i = bgRate.intValue();
		quarters = i / 25;
		i = i % 25;
		dimes = i / 10;
		i = i % 10;
		nickels = i / 5;
		i = i % 5;
		pennies = i / 1;
		if (quarters == 1) {
			System.out.println(quarters + " quarter");
		} else {
			System.out.println(quarters + " quarters");
		}
		if (dimes == 1) {
			System.out.println(dimes + " dime");
		} else {
			System.out.println(dimes + " dimes");
		}
		if (nickels == 1) {
			System.out.println(nickels + " nickel");
		}
		if (i == 1) {
			System.out.println(pennies + " penny");
		} else {
			System.out.println(pennies + " penny(s)");
		}
		scanner.close();
	}
}
