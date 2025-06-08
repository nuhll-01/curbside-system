import java.util.ArrayList;

public class OrderNumber {
    private final ArrayList<Integer> ORDER_NUMBER = new ArrayList<>(); // ArrayList of order numbers.

    public OrderNumber() {
        super();
    }

    protected void listOfNumbers() {
        ORDER_NUMBER.add(956487);
        ORDER_NUMBER.add(412835);
        ORDER_NUMBER.add(507898);
        ORDER_NUMBER.add(345511);
    }

    public int size() {
        return ORDER_NUMBER.size();
    }

    public int getOrder(int index) {
        if (index < 0 || index >= ORDER_NUMBER.size())
            throw new IllegalArgumentException("invalid index");
        return ORDER_NUMBER.get(index);
    }

    public void listOfOrderNumbers() {
        listOfNumbers();
        for (Integer integer : ORDER_NUMBER) {
            System.out.println("Order Number: " + integer);
        }
    }
}
