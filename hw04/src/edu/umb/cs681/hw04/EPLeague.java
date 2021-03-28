package edu.umb.cs681.hw04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EPLeague {
	
	private String name;
	private int goals, assists, yellow, red;
	

	public EPLeague(String name,int goals, int assists, int  yellow, int red){
		this.name = name;
		this.goals = goals;
		this.assists = assists;
		this.yellow = yellow;
		this.red = red;
		
	}

	public String getName() {
		return name;
	}
	
	public int getGoals() {
		return goals;
	}

	public int getAssists() {
		return assists;
	}

	public int getYellow() {
		return yellow;
	}

	public int getRed() {
		return red;
	}

	@Override
    public String toString() {
    	
    	return this.name +" "+ this.goals+" "+ this.assists+" "+this.yellow+" "+this.red;
    }

	public static void main(String[] args) {
		
		List<EPLeague> list=new ArrayList<EPLeague>();
		list.add(new EPLeague("Harry Kane", 17, 40, 1, 0));
		list.add(new EPLeague("Mohamed Salah", 15, 10, 2, 1));
		list.add(new EPLeague("Marcus Rashford", 10, 20, 4, 0));
		list.add(new EPLeague("Raheem Sterling", 11, 30, 3, 2));
		
		int max_goals = list.stream().map((EPLeague player) ->player.getGoals() )
				.reduce(0, (result,goal)->result > goal ? result : goal);
		
		System.out.println("Maximum number of goals scored by a player: "+max_goals);
		
		int min_assists = list.stream().map((EPLeague player) ->player.getAssists() )
				.reduce(1000000000, (result, assist)->assist>result ? result : assist);
		
		System.out.println("Minimum number of assists by a player: "+min_assists);
		
		int index=0;
		int count = list.stream().map(x->index+1).reduce(0,(a,b)->a+b);
		System.out.println("Total number of players in list:"+count);
		
		
		System.out.println("Players sorted by number of yellow cards:");
		List<EPLeague> sortedByYellow=list.stream().sorted(Comparator.comparingInt(EPLeague::getYellow)).collect(Collectors.toList());
		sortedByYellow.forEach(System.out::println);
		
		
		System.out.println("Players sorted by number of red cards:");
		List<EPLeague> sortedByRed=list.stream().sorted(Comparator.comparingInt(EPLeague::getRed)).collect(Collectors.toList());
		sortedByRed.forEach(System.out::println);

	}
}
