package com.epam.utils.exceptions;

public class FileAccessException extends RuntimeException
{

	private static final long serialVersionUID = -5863121185094585568L;

	public FileAccessException()
	{
		super();
	}

	public FileAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileAccessException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public FileAccessException(String message)
	{
		super(message);
	}

	public FileAccessException(Throwable cause)
	{
		super(cause);
	}

}