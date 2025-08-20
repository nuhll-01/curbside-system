import org.jetbrains.annotations.Contract;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CurbsideSystem extends CustomerData {
    public static Scanner scanner = new Scanner(System.in);
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
    public void start() throws SQLException {
        menu();
    }

    /**
     *  Display the main menu.
     */
    private void menu() throws SQLException {
        greetingMessage();
        switch (mainSelection()) {
            case 0:
                scanner.close();
                terminate();
            case 1:
                OMM();
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
     *  Display the order-management menu (OMM).
     */
    private void OMM() throws SQLException {
        switch (ommSelection()) {
            case 0:
                menu();
            case 1:
                search();
                break;
            default:
                System.out.println("Invalid Command.");
        }
    }

    /**
     * Handles the user option selection operation
     * @return Menu option
     */
    private int mainSelection() {
        int option;
        System.out.print("""
                \nMenu
                \t- Order Management (1)
                \t- Overview         (2)
                \t- Exit             (0)
                """);
        System.out.print("Option: ");
        option = scanner.nextInt();
        return option;
    }

    /**
     * Handles the user option selection operation
     * @return Menu option
     */
    private int ommSelection() {
        int option;
        System.out.print("""
                \nOrder-Management Menu
                \t- Search Order     (1)
                \t- Return           (0)
                """);
        System.out.print("Option: ");
        option = scanner.nextInt();
        return option;
    }



    /**
     * Find the customer's order number and display important details.
     */
    private void search() throws SQLException {
        System.out.print("Search Order Number: ");
        String order_number = scanner.next();

        // Operation #1 : Search for the ID
        DatabaseManager.searchIDs(order_number);

        // Operation #2 : Check if ID was found
        // If found, display order summary
        // if not found, continue




    }

    /**
     * Generate a greeting message.
     */
    @Contract(pure = true)
    private void greetingMessage() {
        System.out.println("Curbside Order Management System");
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

    /**
     * Terminate the process
     */
    private void terminate() {
        System.out.println("\nClosing Application");
        System.exit(0);
    }
}