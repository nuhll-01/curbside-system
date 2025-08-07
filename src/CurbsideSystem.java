import org.jetbrains.annotations.Contract;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CurbsideSystem extends CustomerData {
    private Scanner scanner = new Scanner(System.in);
    private Inventory inventory = new Inventory();
    private OrderNumber orderNumber = new OrderNumber();
    private CustomerData customerData = new CustomerData();

    int option = 0;

    public CurbsideSystem() {
        super();
    }

    /**
     * Starts the curbside system application
     */
    public void start()  {
        mainMenu();
    }

    /**
     *  The main menu of the program.
     */
    private void mainMenu() {
        greetingMessage();
        switch (getOption()) {
            case 0:
                terminate();
            case 1:
                // TODO - implement 'search()' method
                int orderNumber;
                System.out.print("Enter Order Number: ");
                orderNumber = scanner.nextInt();
                search(orderNumber);
                break;
            case 2:
                System.out.println("List of Orders:\n");
                listOfOrderNumbers();
                break;
            default:
                System.out.println("Invalid Command.");
        }
    }

    /**
     * Used for terminating the application
     */
    private void terminate() {
        System.out.println("\nClosing Application");
        exit();
    }

    /**
     * A simple greeting message.
     */
    @Contract(pure = true)
    private void greetingMessage() {
        System.out.println("Curbside System Application");
    }

    /**
     * Get the user selected option
     * @return Menu option
     */
    private int getOption() {
        int option = 0;
        System.out.println("\nMenu:\n - Exit --> 0\n - Search --> 1\n - Overview --> 2");
        System.out.print("\nOption: ");
        option = scanner.nextInt();
        return option;
    }

    /**
     * A method to find the order and display the customer's details.
     * @param order The order to search for.
     */
    private void search(int order) {
        // int orderNumber;
        // System.out.print("Enter Order Number: ");

        importOrderNumbers();
        int orders = orderNumber.size();
        for (int i = 0; i < orders; i++) {
            if (order == orderNumber.getOrder(i)) {
                System.out.println("\nOrder Found!");
                displayResults(i);
            }
        }
    }

    /**
     * Accesses the entire data of order numbers into scope.
     */
    private void importOrderNumbers() {
        orderNumber.listOfNumbers();
    }

    private void displayResults(int index) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = dateTime.format(formatter);
        System.out.println("\n============================ Order Summary =============================");
        System.out.println("\nDate: " + formattedDate);
        System.out.println("Order Number: " + orderNumber.getOrder(index));
        customerData.displayCustomerDetails();
        inventory.displayDetails(index);
        System.out.println("\n========================================================================");
    }

    private void listOfOrders() {
        importOrderNumbers();
    }

    public boolean checkLength(int input) {
        String inputNumber = String.valueOf(input); // Converts the input to a String.
        String inputLength = Integer.toString(inputNumber.length()); // Converts the length of the input to a String.
        int inputLengthInt = Integer.parseInt(inputLength); // Converts the length of the input to an Integer.
        return inputLengthInt != 6;
    }

    public void exit() {
        System.exit(0);
    }
}