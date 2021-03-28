
package edu.umb.cs681.hw02;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class Car {

	private String make, model;
	private int mileage, year;
	private float price;
	private int dominationCount;

	public Car(String make, String model, int year, int mileage, float price) {
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
	
	public float getPrice() {
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
		
		System.out.println("Sorted by Domination Count:");
		List<Car> sortedByDomCount=list.stream().sorted(Comparator.comparingInt(Car::getDominationCount)).collect(Collectors.toList());
		sortedByDomCount.forEach(System.out::println);
		
		System.out.println("Sorted by Year:");
		List<Car> sortedByYear=list.stream().sorted(Comparator.comparingInt(Car::getYear)).collect(Collectors.toList());
		sortedByYear.forEach(System.out::println);
		
		System.out.println("Sorted by Mileage:");
		List<Car> sortedByMileage=list.stream().sorted(Comparator.comparingInt(Car::getMileage)).collect(Collectors.toList());
		sortedByMileage.forEach(System.out::println);
		
		System.out.println("Sorted by Price:");
		List<Car> sortedByPrice=list.stream().sorted(Comparator.comparingDouble(Car::getPrice)).collect(Collectors.toList());
		sortedByPrice.forEach(System.out::println);
	}
}

