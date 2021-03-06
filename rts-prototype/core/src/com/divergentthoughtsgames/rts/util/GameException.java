package com.divergentthoughtsgames.rts.util;

/**
 * Base class for Game Exceptions.
 * @author Christopher D. Canfield
 */
public class GameException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a GameException object with the specified message. 
	 * @param message the exception's detail message.
	 */
	public GameException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructs a GameException object from the specified exception.
	 * @param exception the exception to wrap.
	 */
	public GameException(Throwable exception)
	{
		super(exception);
	}
}
