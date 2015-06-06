package jce.project.noam.engineerassist.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Handles writing to XML file.
 * 
 * @author Noam S.
 */
public class XMLWriter {
	
	private BufferedWriter xmlOut;
	
	/**
	 * Constructor for initializing the XML writer.
	 * 
	 * @param path - The path to the result XML file.
	 * @throws IOException If there was an error creating or writing to the XML file.
	 */
	public XMLWriter(String path) throws IOException {
		if (path.endsWith(".pdf")) {
			xmlOut = Files.newBufferedWriter(Paths.get(path.substring(0, path.length() - ".pdf".length()) + ".xml"), Charset.forName("UTF-8"));
		}
		else if (path.endsWith(".xml")) {
			xmlOut = Files.newBufferedWriter(Paths.get(path), Charset.forName("UTF-8"));
		}
		else {
			xmlOut = Files.newBufferedWriter(Paths.get(path + "\\out.xml"), Charset.forName("UTF-8"));
		}
	}

	/**
	 * Writes to file the specified text
	 * 
	 * @param text - The text to write to file.
	 * @throws IOException If there was an error writing to the XML file.
	 */
	private void write(String text) throws IOException {
		xmlOut.write(text);
		xmlOut.newLine();
	}

	/**
	 * Writes to file the start of the XML file.
	 * 
	 * @throws IOException If there was an error writing to the XML file.
	 */
	public void writeXMLStart() throws IOException {
		write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		write("<add>");
	}

	/**
	 * Writes to file the end of the XML file.
	 * 
	 * @throws IOException If there was an error writing to the XML file.
	 */
	public void writeXMLEnd() throws IOException {
		write("</add>");
		xmlOut.flush();
	}
	
	/**
	 * Writes to file the start of a DOC segment.
	 * 
	 * @throws IOException If there was an error writing to the XML file.
	 */
	public void writeDocStart() throws IOException {
		write("<doc>");
	}

	/**
	 * Writes to file the end of a DOC segment.
	 * 
	 * @throws IOException If there was an error writing to the XML file.
	 */
	public void writeDocEnd() throws IOException {
		write("</doc>");
	}

	/**
	 * Writes to file a new field of type 'type' with the contents 'text'. 
	 * 
	 * @param type - The type of the field being written.
	 * @param text - The text that the field contains.
	 * @throws IOException If there was an error writing to the XML file.
	 */
	public void writeField(String type, String text) throws IOException {
		write("<field name=\"" + type + "\">" + text + "</field>");
	}

}
