package ra.model;

import java.io.Serializable;

public class OrderDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean status;

    public OrderDetails() {
    }

    public OrderDetails(int catalogId, String catalogName, String description, boolean status) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
