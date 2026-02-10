package org.example.Testing;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public String processOrder(Order order) {
        if (orderRepository.saveOrder(order) > 0) {
            return "Order processed successfully";
        }
        else {
            return "Order processing failed";
        }
    }

    public double calculateTotal(int id) {
        Order order = orderRepository.getOrderById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return order.getTotalPrice();
    }
}
