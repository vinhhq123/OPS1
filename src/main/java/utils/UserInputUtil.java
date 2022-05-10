package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputUtil {

	public static String validateDate() {
		Scanner sc = new Scanner(System.in);
		boolean success = false;
		String date = "";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		while (!success) {
			try {
				date = sc.nextLine();
				if (checkFormatDate(date) == true) {
					dateFormat.parse(date);
					if (checkDateAfterCurrentDate(date) == true) {
						success = true;
					} else {
						System.out.println("Enter order date: (dd/MM/yyy)");
					}
				} else {
					System.out.println("Please enter correct date with format [dd/mm/yyyy] : ");
				}
			} catch (ParseException e) {
				System.out.println("Please enter correct date with format [dd/mm/yyyy] : ");
			}
		}
		return parseDate(date);
	}

	public static boolean checkDateAfterCurrentDate(String date) {

		boolean check = false;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		// format.format(new Date());
		try {
			Date df1 = dateFormat.parse(date);
			Date df2 = dateFormat.parse(currentDate);

			if (df1.after(df2)) {
				check = true;
			} else {
				System.out.println("Error !!!");
				System.out.println("The Order Date cannot be less than Current Date !!! ");
			}
		} catch (ParseException ex) {
			System.out.println("Error !!!");
			System.out.println("Parse Exception !!");
		}
		return check;

	}

	public static boolean checkFormatDate(String input) {
		boolean checkFormat = false;
		if (input.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			checkFormat = true;
		} else {
			checkFormat = false;
		}
		return checkFormat;
	}

	public static String validateString() {
		Scanner sc = new Scanner(System.in);
		String enter_String = "";
		while (true) {
			enter_String = sc.nextLine();
			if (enter_String == null || enter_String.trim().isEmpty()) {
				System.out.println("Cannot be null or empty");
				System.out.println("Please re enter");
			} else if (enter_String.length() >= 200) {
				System.out.println("Cannot be more than 200 characters !");
				System.out.println("Please re enter");
			} else {
				break;
			}
		}
		return enter_String;
	}

	public static int validatePhone() {
		int parsePhone = 0;
		Scanner sc = new Scanner(System.in);
		String enter_String = "";
		String newString = "";
		Pattern pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$" + "|^\\d{10}$");
		while (true) {
			enter_String = sc.nextLine();
			if (enter_String == null || enter_String.trim().isEmpty()) {
				System.out.println("Cannot be null or empty");
				System.out.println("Please re enter");
			} else {
				Matcher matcher = pattern.matcher(enter_String);
				boolean validPhone = matcher.matches();
				if (!validPhone) {
					System.out.println("Phone must be in correct format");
					System.out.println("For example : 0987654321 or 098.765.4321");
					System.out.println("Please re enter");
				} else {
					String numberOnly = enter_String.replaceAll("[^0-9]", "");
					parsePhone = Integer.parseInt(numberOnly);
					break;
				}
			}
		}

		return parsePhone;
	}

	public static int validateNumber() {

		Scanner sc = new Scanner(System.in);
		int number = 0;
		while (true) {
			while (!sc.hasNextInt()) {
				System.out.println("Must enter digit only");
				System.out.println("Please re enter : ");
				sc.next();
			}

			number = sc.nextInt();

			if (number <= 0) {
				System.out.println("Must be a positive number");
				System.out.println("Please re enter : ");
			} else {
				break;
			}
		}
		return number;
	}

	public static String parseDate(String date) {
		String newDate = "";
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date1 = dateFormat.parse(date);
			dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			newDate = dateFormat.format(date1);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return newDate;
	}

}
