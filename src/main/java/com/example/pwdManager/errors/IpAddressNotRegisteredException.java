package com.example.pwdManager.errors;
public class IpAddressNotRegisteredException extends RuntimeException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IpAddressNotRegisteredException(String message) {
        super(message);
    }

    public IpAddressNotRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }
}
