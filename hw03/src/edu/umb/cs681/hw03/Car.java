package edu.umb.cs681.hw03;

import java.util.ArrayList;
import java.util.List;

public class Car {
	
	private String make, model;
	private int mileage, year;
	private int price;
	private int dominationCount;

	public Car(String make, String model, int year, int mileage, int price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
	}
	
	public int getDominationCount() {
		return this.dominationCount;
	}
	
	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public int getPrice() {
		return price;
	}

	public int getMileage() {
		return mileage;
	}

	public void setDominationCount(ArrayList<Car> cars) {
		for (Car car : cars) {
			if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
					&& (car.getYear() <= this.getYear())) {
				this.dominationCount++;
			}
		}
		this.dominationCount--; 	
	}
	
    @Override
    public String toString() {
    	return this.make +" "+ this.model+" "+ this.mileage+" "+this.year+" "+this.price;
    }

	public static void main(String[] args) {
		List<Car> list=new ArrayList<Car>();
		list.add(new Car("Mercedes", "C-Class", 2019, 15, 76000));
		list.add(new Car("Ford", "Mustang", 2014, 11, 90000));
		list.add(new Car("Audi", "A8", 2019, 9, 95000));
		list.add(new Car("BMW", "7-Series", 2020, 8, 670000));
		
		int y=0;
		int count = list.stream().map(x->y+1).reduce(0,(a,b)->a+b);
		System.out.println("Number of cars in the list: "+count);
		
		int min_cost = list.stream().map((Car car) ->car.getPrice() )
				.reduce(1000000000, (result, price)->price>result ? result : price);
		System.out.println("Cost of the cheapest car: $"+min_cost);
		
		int max_cost = list.stream().map((Car car) ->car.getPrice() )
				.reduce(0, (result,price)->result > price ? result : price);
		System.out.println("Cost of the most expensive car: $"+max_cost);
	}
}
