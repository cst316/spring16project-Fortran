package example;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author tlroche
 * @version $Id: CelsiusConverterStrings.java 1407 2004-12-31 04:49:48Z tlroche $
 */
public class CelsiusConverterStrings {
	private static final String BUNDLE_NAME = "example.CelsiusConverter";//$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private CelsiusConverterStrings() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
