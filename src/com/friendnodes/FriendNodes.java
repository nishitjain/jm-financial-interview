package com.friendnodes;

import java.util.Scanner;

public class FriendNodes {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int friend_nodes, friend_edges;
		friend_nodes = scan.nextInt();
		friend_edges = scan.nextInt();
		
		if(friend_nodes <2 || friend_nodes > 100 || friend_edges < 1 || friend_edges > Math.min(200,(friend_nodes * (friend_nodes-1))/2)) {
			System.out.println("Invalid Values Entered! Aborting. . .");
			System.exit(0);
		}
		
		int[] friends_from, friends_to, friends_weight;
		friends_from = new int[friend_edges];
		friends_to = new int[friend_edges];
		friends_weight = new int[friend_edges];
		
		for(int j=0;j<friend_edges;j++)
			friends_weight[j] = 0;
		
		for(int i=0;i<friend_edges;i++) {
			friends_from[i] = scan.nextInt();
			friends_to[i] = scan.nextInt();
			friends_weight[i] = scan.nextInt();
			if(friends_from[i] > friend_nodes || friends_to[i] > friend_nodes || 
					friends_from[i] < 1 || friends_to[i] < 1 || friends_weight[i] <1 || friends_weight[i] > 100 ||
					friends_weight[i] > friend_edges || friends_from[i] == friends_to[i]) {
				System.out.println("Invalid Values Entered! Aborting. . .");
				System.exit(0);
			}
		}
		
		for(int i=0;i<friend_edges;i++) {
			for(int j=i+1;j<friend_edges;j++) {
				if(friends_from[i] == friends_from[j] && friends_to[i] == friends_to[j]) {
					friends_weight[i] += friends_weight[j];
					friends_from[j] = friends_to[j] = friends_weight[j] = 0;
				}
			}
		}
		
		int maximum_weight = friends_weight[0];
		int index = 0;
		
		for(int i=0;i<friend_edges;i++) {
			if(maximum_weight < friends_weight[i]) {
				maximum_weight = friends_weight[i];
				index = i;
			}
		}
		
		System.out.println(friends_from[index] * friends_to[index]);
	}
}
