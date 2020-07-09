package com.ddlab.rnd;

import java.net.InetAddress;
import java.util.concurrent.Callable;

/**
 * The Class PingIPTask.
 * @author Debadatta Mishra
 */
public class PingIPTask implements Callable<PingResponse> {

	/** The Constant TIMEOUT. */
	private final static int TIMEOUT = 5000;
	
	/** The ip address. */
	private String ipAddress;

	/**
	 * Instantiates a new ping IP task.
	 *
	 * @param ipAddress the ip address
	 */
	public PingIPTask(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Call.
	 *
	 * @return the ping response
	 */
	@Override
	public PingResponse call() {
		InetAddress inet = null;
		try {
			inet = InetAddress.getByName(ipAddress);
			boolean response = inet.isReachable(TIMEOUT);
			return new PingResponse(ipAddress, response);
		} catch (Exception e) {
			e.printStackTrace();
			return new PingResponse(ipAddress, false);
		}
	}
}