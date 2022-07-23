package Model;

public class PurchasesProduct {
    private long id;
    private long userId;
    private long ProductId;

    public PurchasesProduct(long id, long userId, long productId) {
        this.id = id;
        this.userId = userId;
        ProductId = productId;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", userId=" + userId +
                ", ProductId=" + ProductId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }
}
