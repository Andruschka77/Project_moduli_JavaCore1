import org.example.Testing.Order;
import org.example.Testing.OrderRepository;
import org.example.Testing.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private OrderRepository orderRepositoryMock;
    private OrderService orderService;

    @BeforeEach
    void helper() {
        orderRepositoryMock = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepositoryMock);
    }

    @Test
    void testSuccessfulOrderProcessing() {
        Order order = new Order(1, "Apple", 2, 50.0);
        when(orderRepositoryMock.saveOrder(order)).thenReturn(order.getId());

        String result = orderService.processOrder(order);

        assertEquals("Order processed successfully", result);
        verify(orderRepositoryMock, times(1)).saveOrder(order);
    }

    @Test
    void testFailedOrderProcessing() {
        Order order = new Order(-5, "Pear", 7, 110.0);
        when(orderRepositoryMock.saveOrder(order)).thenReturn(order.getId());

        String result = orderService.processOrder(order);

        assertEquals("Order processing failed", result);
        verify(orderRepositoryMock, times(1)).saveOrder(order);
    }

    @Test
    void testSuccessfulCostCalculation() {
        Order order = new Order(2, "Tomato", 3, 100.0);
        when(orderRepositoryMock.getOrderById(order.getId())).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(order.getId());

        assertEquals(300.0, result);
        verify(orderRepositoryMock, times(1)).getOrderById(order.getId());
    }

    @Test
    void testOrderNotFound() {
        when(orderRepositoryMock.getOrderById(3)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> orderService.calculateTotal(3));
        verify(orderRepositoryMock, times(1)).getOrderById(3);
    }

    @Test
    void testCorrectCalculationWithZeroCost() {
        Order order = new Order(75, "Package", 190, 0.0);
        when(orderRepositoryMock.getOrderById(75)).thenReturn(Optional.of(order));

        double result = orderService.calculateTotal(order.getId());

        assertEquals(0.0, result);
        verify(orderRepositoryMock, times(1)).getOrderById(order.getId());
    }
}