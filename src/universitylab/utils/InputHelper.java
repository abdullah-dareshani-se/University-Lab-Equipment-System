
package universitylab.utils;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class InputHelper {

    public static int readInt(Scanner sc) {

        while (true) {
            try {
                int number = sc.nextInt();
                sc.nextLine();
                return number;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
            }
        }
    }

    public static String readString(Scanner sc) {

        while (true) {
            String input = sc.nextLine();

            if (!input.trim().isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty! Try again:");
        }
    }

    public static String readDate(Scanner sc) {

        while (true) {
            try {
                System.out.print("Enter Booking Date (YYYY-MM-DD): ");

                String date = sc.nextLine();

                LocalDate validDate = LocalDate.parse(date);

                if (validDate.isBefore(LocalDate.now())) {
                    System.out.println("Past dates are not allowed!");
                    continue;
                }

                return date;

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date! Please use YYYY-MM-DD format.");
            }
        }
    }

    public static String readTime(Scanner sc) {

        while (true) {
            try {

                String time = sc.nextLine();

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("HH:mm")
                                .withResolverStyle(ResolverStyle.STRICT);

                LocalTime.parse(time, formatter);

                return time;

            } catch (DateTimeParseException e) {
                System.out.println("Invalid time! Use HH:mm format.");
            }
        }
    }
}
