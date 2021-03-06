package jce.project.noam.engineerassist.utils;

/**
 * Represents an exception when we encounter an error while extracting text.
 * 
 * @author Noam S.
 *
 */
public class TextExtractionError extends Exception {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 4529523929153431294L;
	
	/**
	 * Constructor for general purpose
	 */
	public TextExtractionError() {
		super();
	}
	
	/**
	 * Constructor with a message option
	 * 
	 * @param message - The message that the exception will carry
	 */
	public TextExtractionError(String message) {
		super(message);
	}
}
