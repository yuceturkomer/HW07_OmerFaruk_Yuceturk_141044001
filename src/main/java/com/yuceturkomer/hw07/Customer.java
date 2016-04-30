package com.yuceturkomer.hw07;

/**
 * Created by ömer on 27.04.2016.
 */
public class Customer implements Comparable<Customer> {
    private int hour;
    private int minute;
    private int serviceTime;
    private int customerType;
    private final int GOLD_CUSTOMER = 1;
    private final int SILVER_CUSTOMER = 2;
    private final int BRONZE_CUSTOMER = 3;


    public Customer(int hour, int minute, int serviceTime, int customerType) {
        this.hour = hour;
        this.minute = minute;
        this.serviceTime = serviceTime;
        this.customerType = customerType;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(int customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        //I thought it'd be better to use String.format(...) method to return string ordered style.
        return String.format("Arrival time:  %02d:%02d \tService Time: %-8s Customer Type %-1s", hour, minute, serviceTime, customerType);
    }

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

    @Override
    public int hashCode() {
        int result = getHour();
        result = 31 * result + getMinute();
        result = 31 * result + getServiceTime();
        result = 31 * result + getCustomerType();
        return result;
    }

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
