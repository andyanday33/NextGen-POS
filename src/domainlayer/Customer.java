package domainlayer;

public class Customer {

    private int customerId;
    private int customerType; //1 = senior, 2 = regular
    private float percentage;

    public Customer(int customerId, int type, float percentage){
        this.customerId = customerId;
        this.customerType = type;
        this.percentage = percentage;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(int customerType) {
        this.customerType = customerType;
    }

    public float getPercentage(){return percentage;}

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}

