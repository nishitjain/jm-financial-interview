package com.moviedata;

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String substr = scan.next();
		System.out.println("Search results: ");
		Object[] titles = new Movie().getMovieTitles(substr);
		for(int i=0; i<titles.length; i++) {
			System.out.println((i+1)+". "+titles[i]);
		}
	}
}
