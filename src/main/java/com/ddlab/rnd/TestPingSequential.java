package com.ddlab.rnd;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestPingSequential {

	/** The Constant IP_FILE. */
	private final static String IP_FILE = "ipaddress-500.txt";

	/** The Constant TIMEOUT. */
	private final static int TIMEOUT = 5000;

	public static void pingIP(String ipAddress) {
		InetAddress inet = null;
		try {
			inet = InetAddress.getByName(ipAddress);
			boolean response = inet.isReachable(TIMEOUT);
			String output = String.format("%16s : %b", ipAddress, response);
			System.out.println(output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Instant start = Instant.now();
		try {
			List<String> ipAddressList = Files.readAllLines(Paths.get(IP_FILE));
			ipAddressList.forEach(ipData -> pingIP(ipData));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		long timeInMilliseconds = timeElapsed.toMillis();
		long seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMilliseconds);
		System.out.println("Total Time taken: " + seconds + " seconds");
	}

}
