package com.ipv4ipv6;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.validator.routines.InetAddressValidator;

public class IPv4IPv6 {
	
	public String isValidIP(String ip) {
		InetAddressValidator validator = InetAddressValidator.getInstance();
		if(validator.isValidInet4Address(ip))
			return "IPv4";
		if(validator.isValidInet6Address(ip))
			return "IPv6";
		return "Neither";
	}
	
	public static void main(String[] args) {
		int numberOfaddresses;
		String[] result, ipAddresses;
		Scanner scan = new Scanner(System.in);
		ipAddresses = new String[500];
		result = new String[500];
		numberOfaddresses = scan.nextInt();
		
		if(numberOfaddresses < 1  ||  numberOfaddresses > 50) {
			System.out.println("Invalid Values Entered! Aborting. . .");
			System.exit(0);
		}
		
		for(int i=0;i<numberOfaddresses;i++) {
			ipAddresses[i] = scan.next();
			if(ipAddresses[i].length() > 500 || ipAddresses[i].length() < 1) {
				System.out.println("Invalid Values Entered! Aborting. . .");
				System.exit(0);
			}
		}
		
		for(int i=0;i<numberOfaddresses;i++) {
			result[i] = new IPv4IPv6().isValidIP(ipAddresses[i]);
		}
		
		for(int i=0;i<numberOfaddresses;i++) {
			System.out.println(result[i]);
		}
			
		scan.close();
	}
}
