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
        createDatabase();
        menu();
    }

    /**
     *  Display the main menu.
     */
    private void menu() {
        greetingMessage();
        switch (mainSelection()) {
            case 0:
                terminateApplication();
            case 1:
                OMM();
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
    private void OMM() {
        switch (ommSelection()) {
            case 0:
                mainSelection();
            case 1:

                // TODO - implement 'search()' method



                int orderNumber;
                System.out.print("Enter Order Number: ");
                orderNumber = scanner.nextInt();
                search(orderNumber);
                break;





            default:
                System.out.println("Invalid Command.");
        }
    }

    /**
     * Get the user option for the main menu
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
     * Get the user option for the OMM
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
     * Terminate the running program
     */
    private void terminateApplication() {
        System.out.println("\nClosing Application");
        exit();
    }

    /**
     * A simple greeting message.
     */
    @Contract(pure = true)
    private void greetingMessage() {
        System.out.println("Curbside Order Management System");
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

    /**
     * Initialize the order database
     */
    private void createDatabase() {
        DatabaseManager.initializeDatabase();
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