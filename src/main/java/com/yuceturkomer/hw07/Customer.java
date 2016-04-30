package com.yuceturkomer.hw07;

/**
 * The Customer Class which contains a customer's arrival hour and minute, service time and his type
 *
 * Customer 1 = GOLD_CUSTOMER
 * Customer 2 = SILVER_CUSTOMER
 * Customer 3 = BRONZE_CUSTOMER
 *
 */
public class Customer implements Comparable<Customer> {
    private int hour;
    private int minute;
    private int serviceTime;
    private int customerType;
    private final int GOLD_CUSTOMER = 1;
    private final int SILVER_CUSTOMER = 2;
    private final int BRONZE_CUSTOMER = 3;

    /**
     * There is only 1 constructor and that takes 4 paramters
     * @param hour Arrival hour
     * @param minute Arrival minute
     * @param serviceTime Service time
     * @param customerType Customer type
     */
    public Customer(int hour, int minute, int serviceTime, int customerType) {
        this.hour = hour;
        this.minute = minute;
        this.serviceTime = serviceTime;
        this.customerType = customerType;
    }

    /**
     * Returns hour
     * @return hour
     */
    public int getHour() {
        return hour;
    }

    /**
     * Returns minute
     * @return minute
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Returns serviceTime
     * @return serviceTime
     */
    public int getServiceTime() {
        return serviceTime;
    }


    /**
     * Returns customerType
     * @return customerType
     */
    public int getCustomerType() {
        return customerType;
    }

    /**
     * Overriden formatted toString method
     * @return String form of Customer's details
     */
    @Override
    public String toString() {
        //I thought it'd be better to use String.format(...) method to return string ordered style.
        return String.format("Arrival time:  %02d:%02d \tService Time: %-8s Customer Type %-1s", hour, minute, serviceTime, customerType);
    }

    /**
     * Overriden equals method
     * @param o Object to be compared if equal
     * @return true if equal. Else false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        return getHour() == customer.getHour()
                && getMinute() == customer.getMinute()
                && getServiceTime() == customer.getServiceTime()
                && getCustomerType() == customer.getCustomerType();

    }

    /**
     * Overriden hashCode method
     * @return hashCode
     */
    @Override
    public int hashCode() {
        int result = getHour();
        result = 31 * result + getMinute();
        result = 31 * result + getServiceTime();
        result = 31 * result + getCustomerType();
        return result;
    }

    /**
     * Compares two Customers firstly according to their type, if they are equal it looks at the hour, if these are
     * equal too, then it looks at the minute
     * @param obj
     * @return
     */
    public int compareTo(Customer obj) {
        if (obj == null)
            throw new NullPointerException();

        if (getCustomerType() < obj.getCustomerType())
            //This customer is prior
            return 1;
        else if (getCustomerType() > obj.getCustomerType())
            //Other customer is prior
            return -1;
        else {//Same customer type
            if (getHour() < obj.getHour())
                return 1;
            else if (getHour() > obj.getHour())
                return -1;
            else {//Same hour
                if (getMinute() < obj.getMinute())
                    return 1;
                else if (getMinute() > obj.getMinute())
                    return -1;
                else//Same minute
                    return 0;
            }
        }
    }
}
