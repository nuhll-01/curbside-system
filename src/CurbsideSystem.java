import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CurbsideSystem extends CustomerData {
    private Scanner scanner = new Scanner(System.in);
    private Inventory inventory = new Inventory();
    private OrderNumber orderNumber = new OrderNumber();
    private CustomerData customerData = new CustomerData();
    private boolean active = false; // the activate state of the program

    int menuSelection;

    public CurbsideSystem() {
        super();
    }

    /**
     * Initialize the curbside system application
     */
    public void start() {
        active = true;
        menu();
    }

    /**
     *  The main menu of the program.
     */
    public void menu() {
        System.out.println("\nWelcome!");
        if (!active) { throw new IllegalStateException("CRITICAL ERROR"); }
        
        System.out.println("\nMain Menu - Exit --> 1 | Search Order --> 2 | View Orders --> 3");
        System.out.print("Selection: ");

        menuSelection = scanner.nextInt();
        switch (menuSelection) {
            case 1:
                System.out.println("\nProgram terminated.");
                exit();

            case 2:
                int orderNumber;
                System.out.print("Enter Order Number: ");
                orderNumber = scanner.nextInt();
                search(orderNumber);
                break;

            case 3:
                System.out.println("List of Orders:\n");
                listOfOrderNumbers();
                break;

            default:
                System.out.println("Invalid Command.");
        }
    }

    /**
     *  A method to find the order and display the customer's details.
     *
     * @param order The order to search for.
     */
    private void search(int order) {
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
     *  Accesses the entire data of order numbers into scope.
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