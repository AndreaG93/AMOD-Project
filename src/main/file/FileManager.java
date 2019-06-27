package file;

import java.io.File;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

class FileManager {

	/**
	 * This method is used to get a {@code File} object.
	 * 
	 * @param validExtension
	 *            - Represents a {@code String} object.
	 * @return A {@code File} object.
	 */
	static File getFile(String validExtension) throws Exception {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(validExtension, validExtension);

		chooser.setFileFilter(filter);
		chooser.setCurrentDirectory(new File(Paths.get("").toAbsolutePath().toString()));

		// Retrieve return state of the file chooser...
		int returnVal = chooser.showOpenDialog(null);

		// Retrieve file object...
		File myFile = chooser.getSelectedFile();

		// Check validity of selected file object...
		if (returnVal == JFileChooser.APPROVE_OPTION && isValidFile(myFile, validExtension))
			return myFile;

		throw new Exception("Invalid input.");
	}

	/**
	 * This method is used to check validity of a specified file.
	 * 
	 * @param myFile
	 *            - Represents a {@code File} object.
	 * @param requiredExtensions
	 *            - Represents a {@code String[]} object.
	 * @return A {@code boolean}.
	 */
	static boolean isValidFile(File myFile, String... requiredExtensions) throws Exception {

		if (myFile.exists() && myFile.isFile()) {

			boolean isValidExtension = false;
			
			// Get chosen file extension...
			String extension = getFileExtension(myFile);

			// Check validity of extension...
			for (String var0 : requiredExtensions) 
		    {
				if(extension.equals(var0))
				{
					isValidExtension = true;
					break;
				}
		    }
			
			if (extension != null && isValidExtension)
				return true;
		}
		return false;
	}
	
	/**
	 * This method is used to get extension of a specified {@code File} object.
	 * 
	 * @param arg0
	 *            - Represents a {@code File} object.
	 * @return A {@code String} object.
	 */
	private static String getFileExtension(File arg0) {
		String ext = null;
		String s = arg0.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1)
			ext = s.substring(i + 1).toLowerCase();

		return ext;
	}
}
