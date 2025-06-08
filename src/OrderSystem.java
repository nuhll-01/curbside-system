import java.util.HashMap;
import java.util.Map;

public class OrderSystem {
    private CustomerData customerData =  new CustomerData();
    private OrderNumber orderNumber =  new OrderNumber();
    private Map<OrderNumber, CustomerData> orderMap =  new HashMap<>();

    private void orders() {
        orderNumber.listOfOrderNumbers();
        
    }

}
