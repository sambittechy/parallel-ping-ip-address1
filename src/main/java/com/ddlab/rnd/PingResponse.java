package com.ddlab.rnd;

/**
 * The Class PingResponse.
 * @author Debadatta Mishra
 */
public class PingResponse {

	/** The ip address. */
	private String ipAddress;
	
	/** The reachable. */
	private boolean reachable;

	/**
	 * Instantiates a new ping response.
	 *
	 * @param ipAddress the ip address
	 * @param reachable the reachable
	 */
	public PingResponse(String ipAddress, boolean reachable) {
		super();
		this.ipAddress = ipAddress;
		this.reachable = reachable;
	}

	/**
	 * Gets the ip address.
	 *
	 * @return the ip address
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Checks if is reachable.
	 *
	 * @return true, if is reachable
	 */
	public boolean isReachable() {
		return reachable;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + (reachable ? 1231 : 1237);
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PingResponse other = (PingResponse) obj;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (reachable != other.reachable)
			return false;
		return true;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PingResult [ipAddress=" + ipAddress + ", reachable=" + reachable + "]";
	}

}