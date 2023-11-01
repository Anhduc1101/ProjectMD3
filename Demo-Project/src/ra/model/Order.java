package ra.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int orderId;
    private int userId;
    private String name;
    private String phoneNumber;
    private String address;
    private double total;
    private OrderStatus orderStatus;
    private List<OrderDetails> orderDetails;
    private LocalDateTime orderAt;
    private LocalDateTime deliverAt;

    public Order() {
    }

    public Order(int orderId, int userId, String name, String phoneNumber, String address, double total, OrderStatus orderStatus, List<OrderDetails> orderDetails, LocalDateTime orderAt, LocalDateTime deliverAt) {
        this.orderId = orderId;
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.total = total;
        this.orderStatus = orderStatus;
        this.orderDetails = orderDetails;
        this.orderAt = orderAt;
        this.deliverAt = deliverAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", total=" + total +
                ", orderStatus=" + orderStatus +
                ", orderDetails=" + orderDetails +
                ", orderAt=" + orderAt +
                ", deliverAt=" + deliverAt +
                '}';
    }
}
