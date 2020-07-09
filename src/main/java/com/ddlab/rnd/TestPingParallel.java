package com.ddlab.rnd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * The Class TestPingParallel.
 * @author Debadatta Mishra
 */
public class TestPingParallel {

	/** The Constant IP_FILE. */
	private final static String IP_FILE = "ipaddress-73.txt";

	/**
	 * Ping in parallel.
	 *
	 * @param ipAddressList the ip address list
	 * @return the list
	 */
	private static List<Future<PingResponse>> pingInParallel(List<String> ipAddressList) {
		int totalIPList = ipAddressList.size();
		ExecutorService executor = Executors.newFixedThreadPool(totalIPList);
		List<Future<PingResponse>> responseList = new ArrayList<Future<PingResponse>>();
		Callable<PingResponse> callable = null;
		for (int i = 0; i < totalIPList; i++) {
			callable = new PingIPTask(ipAddressList.get(i));
			Future<PingResponse> future = executor.submit(callable);
			responseList.add(future);
		}
		executor.shutdown();
		return responseList;
	}

	/**
	 * Show IP ping result.
	 *
	 * @param responseList the response list
	 */
	private static void showIPPingResult(List<Future<PingResponse>> responseList) {
		for (Future<PingResponse> responseFuture : responseList) {
			try {
				PingResponse pingResult = responseFuture.get();
				String output = String.format("%16s : %b", pingResult.getIpAddress(), pingResult.isReachable());
				System.out.println(output);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Instant start = Instant.now();
		List<String> ipAddressList = null;
		List<Future<PingResponse>> responseList = null;
		try {
			ipAddressList = Files.readAllLines(Paths.get(IP_FILE));
			responseList = pingInParallel(ipAddressList);
			showIPPingResult(responseList);
			System.out.println("============= ALL TASK COMPLETED ===========");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		long timeInMilliseconds = timeElapsed.toMillis();
		long seconds = TimeUnit.MILLISECONDS.toSeconds(timeInMilliseconds);
		System.out.println("Total Time taken: " + seconds + " seconds");
		System.out.println("Total IPs to Ping: "+ipAddressList.size());
		System.out.println("Total IPs Pinged: "+responseList.size());
	}

}
