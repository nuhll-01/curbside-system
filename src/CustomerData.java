import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CustomerData extends OrderNumber {
    private static Random random = new Random();
    private String first_name;
    private String last_name;
    private String billing_address;
    private String email;
    private String phone_number;

    public CustomerData() {
        this.first_name = generateFirstName();
        this.last_name = generateLastName();
        this.billing_address = generateAddress();
        this.email = generateEmail(first_name);
        this.phone_number = generateNumber();
    }

    /**
     * Generates a number to model a customer.
     *
     * @return String representation of the number.
     */
    private @NotNull String generateNumber() {
        int digitLength = 10; // Max length of the phone number.
        long generateNumber = (long) (Math.random() * Math.pow(10, digitLength)); // Generates a random number.
        String generatedNumber = String.valueOf(generateNumber); // Converts the long to a string.
        return generatedNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3"); // Formats the string.
    }

    /**
     * Generates and returns a random 'first' name for the customer.
     *
     * @return A random first name.
     */
    private @NotNull String generateFirstName() {
        int sizeOfArray = FIRST_NAMES.length - 1;
        int index = (int) (Math.random() * sizeOfArray);
        return FIRST_NAMES[index];
    }

    /**
     * Generates and returns a random 'last' name for the customer.
     *
     * @return Randomly generated name.
     */
    private @NotNull String generateLastName() {
        int sizeOfArray = LAST_NAMES.length - 1;
        int index = (int) (Math.random() * sizeOfArray);
        return LAST_NAMES[index];
    }

    /**
     * Generates and returns a random 'address' for the customer.
     *
     * @return Randomly generated address.
     */
    private @NotNull String generateAddress() {
        return houseNumber() + " " + cardinalDirection() + " " + streetNumber() + "St " + zipCode();
    }

    /**
     * Generates and returns a random cardinal direction.
     *
     * @return Randomly generated cardinal direction.
     */
    private @NotNull String cardinalDirection() {
        String[] cardinalDirection = {"N", "S", "E", "W"};
        int sizeOfArray = cardinalDirection.length - 1;
        int index = (int) (Math.random() * sizeOfArray);
        return cardinalDirection[index];
    }


    /**
     * Generates and returns a random street number.
     *
     * @return Randomly generated street number.
     */
    private @NotNull String streetNumber() {
        int streetNumber = random.nextInt(0, 100); // Generates a random number between 0 and 100.
        return String.valueOf(streetNumber);
    }

    /**
     * Generates and returns a random house number.
     *
     * @return Randomly generated house number.
     */
    private int houseNumber() {
        return random.nextInt(0, 10000);
    }

    /**
     * Generates and returns a random zip code.
     *
     * @return Randomly generated zip code.
     */
    private int zipCode() {
        return random.nextInt(0, 10000); // Generates a random number between 0 and 10000.
    }

    /**
     * Returns the email of the given customer.
     *
     * @param first_name the first name to be used when creating the email
     * @return The email of the given customer name.
     */
    public @NotNull String generateEmail(String first_name) {
        return first_name.toLowerCase() + "@example.com";
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    /**
     * Prints the customer information.
     */
    public void displayCustomerDetails() {
        System.out.println("Name: " + first_name + " " + last_name);
        System.out.println("Address: " + billing_address);
        System.out.println("Phone Number: " + phone_number);
        System.out.println("Email Address: " + email);
    }

    private static final String[] FIRST_NAMES = {
            "Amelia",
            "Benjamin",
            "Chloe",
            "David",
            "Eleanor",
            "Felix",
            "Grace",
            "Henry",
            "Isla",
            "Jack",
            "Keira",
            "LeMi",
            "Noah",
            "Olivia",
            "Pepe",
            "Quinn",
            "Ruby",
            "Samuel",
            "Tessa"
    };
    private static final String[] LAST_NAMES = {
            "Parker",
            "Garcia",
            "Patel",
            "Kim",
            "Singh",
            "Lopez",
            "Martin",
            "Bailey",
            "Nguyen",
            "Perez",
            "Robinson",
            "Torres",
            "Carter",
            "Gonzalez",
            "Phillips",
            "Wright",
            "Ramirez",
            "Stewart",
            "Davis",
    };
}
