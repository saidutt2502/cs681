package edu.umb.cs681.hw12;

public class Address {
    private final String street, city, state;
    private final int zipcode;

    public Address(String street, String city, String state, int zipcode) {
        this.street = street;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;

    }

    public final String getStreet() {
        return street;
    }
    
    public final String getCity() {
        return city;
    }
    
    public final String getState() {
        return state;
    }
    
    public final int getZip() {
        return zipcode;
    }


    public boolean equals(Address address) {
        if(address == this) {
            return true;
        }
        if(!(address instanceof Address)) {
            return false;
        }
        Address temp = (Address) address;
        return this.city.equals(temp.getCity())
                && this.street.equals(temp.getStreet())
                && this.state.equals(temp.getState())
                && Integer.compare(this.zipcode, temp.getZip()) == 0;
    }


    public String toString(){
        return street + "," + city + "," + state + "," + Integer.toString(zipcode);
    }

    public Address change(String street, String city, String state, int zipcode){
        return new Address(street, city, state, zipcode);
    }
}
