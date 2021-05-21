package edu.umb.cs681.hw12;

public class CustomerRunnable implements Runnable {
    Address addr1 = new Address("Central","Cambridge","Massachusetts",12139);
    Address addr2 = new Address("Washington Square","Pasadena","California",91104);

    public void run() {
        Customer customer = new Customer(addr1) ;
        System.out.println("Present Address:"+customer.getAddress());
        customer.setAddress(addr2);
        System.out.println("New Address:"+customer.getAddress());
        customer.setAddress(customer.getAddress().change("5th Avenue","New York City", "New York", 11656));
        System.out.println("Latest Address:"+customer.getAddress());

    }

    public static void main(String args[]) {
        Thread t1 = new Thread(new CustomerRunnable());
        Thread t2 = new Thread(new CustomerRunnable());


        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
