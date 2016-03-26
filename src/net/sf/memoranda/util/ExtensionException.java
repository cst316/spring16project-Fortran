package net.sf.memoranda.util;

/**
 * ExtensionException
 * <p>
 * Exception mad specifically for file extensions
 * @author Saul Lopez
 * @version Mar 23, 2016
 */
public class ExtensionException extends Exception{
	private static final long serialVersionUID = -3143993640609676549L;
	//constructor(s)
	public ExtensionException(){
		super("invalid file extension");
    }
    public ExtensionException(String message){
        super(message);
    }
    public ExtensionException(Throwable cause){
        super(cause);
    }
    public ExtensionException(String message, Throwable cause){
        super(message, cause);
    }
    public ExtensionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

