package jce.project.noam.engineerassist;

import java.io.IOException;
import java.util.regex.Pattern;

import jce.project.noam.engineerassist.utils.Constants;
import jce.project.noam.engineerassist.utils.TextExtractionError;

import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.apache.pdfbox.pdmodel.encryption.StandardDecryptionMaterial;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * This will extract all the text elements from a PDF titled 'EDI System Text Command Reference' of the Encounter EDI System v14.20.
 * It will export it to XML for importing into SOLR search system according to the Engineer Assist project schema.
 * 
 * @author Noam S.
 */
public class PDFExtractHandler {
	private static int docCount = 0;

	/**
	 * Main method that will parse the PDF titled 'EDI System Text Command Reference' of the Encounter EDI System v14.20. for indexing in the SOLR system via an XML output.
	 * 
	 * @param args - The command line arguments.
	 */
	public static void main(String[] args) {
		PDDocument doc = null;
		TextParser parser = null;
		boolean hasOutputPath = false;

		if (args.length != 1 && args.length != 2) {
			usage();
			System.exit(0);
		}
		if (args.length == 2) {
			hasOutputPath = true;
		}
		try {
			doc = PDDocument.load(args[0]);
			if (doc.isEncrypted())
			{
				StandardDecryptionMaterial sdm = new StandardDecryptionMaterial("");
				doc.openProtection(sdm);
			}
		}
		catch (IOException e) {
			System.err.println("Error loading PDF file");
			System.exit(0);
		}
		catch (BadSecurityHandlerException e) {
			e.printStackTrace();
			System.exit(0);
		}
		catch (CryptographyException e) {
			e.printStackTrace();
			System.exit(0);
		}
		try {
			parser = new TextParser(hasOutputPath? args[1]: args[0]);
		}
		catch (IOException e) {
			System.err.println("Error creating XML output file");
			System.exit(0);
		}

		PDDocumentOutline outlineRoot = doc.getDocumentCatalog().getDocumentOutline();
		PDOutlineItem parentItem = outlineRoot.getFirstChild();
		String parentTitleName = "";
		boolean childrenWereParsed = false;

		docCount = 0;
		try {
			while (parentItem != null) {
				childrenWereParsed = false;
				parentTitleName = parentItem.getTitle();
				System.out.println("Proccesing parent: " + parentTitleName);
				if (parentTitleName.equals("Prototyping Commands")) {
					PDOutlineItem childItem = parentItem.getFirstChild();
					if (Pattern.matches(".*Commands", childItem.getTitle())) {
						parseAllChildren(doc, childItem, childItem = childItem.getNextSibling(), parser, Constants.COMMANDS);
						childrenWereParsed = true;
					}
					if (Pattern.matches(".*Commands", childItem.getTitle())) {
						parseAllChildren(doc, childItem, parentItem = parentItem.getNextSibling(), parser, Constants.COMMANDS);
						childrenWereParsed = true;
					}
				}
				else if (Pattern.matches(".*Commands", parentTitleName)) {
					parseAllChildren(doc, parentItem, parentItem = parentItem.getNextSibling(), parser, Constants.COMMANDS);
					childrenWereParsed = true;
				}
				else if (Pattern.matches(".*Commands and Global Variables", parentTitleName)) {
					PDOutlineItem childItem = parentItem.getFirstChild();
					if (Pattern.matches(".*Commands", childItem.getTitle())) {
						parseAllChildren(doc, childItem, childItem = childItem.getNextSibling(), parser, Constants.COMMANDS);
						childrenWereParsed = true;
					}
					if (Pattern.matches(".*Global Variables", childItem.getTitle())) {
						parseAllChildren(doc, childItem, parentItem = parentItem.getNextSibling(), parser, Constants.GLOBAL_VARIABLES);
						childrenWereParsed = true;
					}
				}
				else if (Pattern.matches(".*Global Variables", parentTitleName)) {
					parseAllChildren(doc, parentItem, parentItem = parentItem.getNextSibling(), parser, Constants.GLOBAL_VARIABLES);
					childrenWereParsed = true;
				}
				else if (Pattern.matches("Design Metrics", parentTitleName)) {
					parseAllChildren(doc, parentItem, parentItem = parentItem.getNextSibling(), parser, Constants.DESIGN_METRICS);
					childrenWereParsed = true;
				}
				if (!childrenWereParsed) {
					parentItem = parentItem.getNextSibling();
				}
			}
			parser.writeXMLEnd();

			System.out.println("Writing to XML Completed.");
			System.out.println("Proccesed " + docCount + " commands.");
			doc.close();
		}
		catch (TextExtractionError e) {
			System.err.println(e.getMessage());
		}
		catch (IOException e) {
			System.err.println("Error writing to XML output file");
		}
	}

	/**
	 * Receives the bookmark item that represent parents from the PDF outline and extracts the text from the pages per child. Than it sends the text to be parsed.
	 * 
	 * @param doc - The document we are extracting from.
	 * @param parentItem - The PDOutlineItem that is the parent item, we want to parse all it's children.
	 * @param nextParentItem - the next parent PDOutlineItem, it will be used to detect the end of the last child's command text.
	 * @param parser - The initialized parser we are using.
	 * @param type - The type of commands we are processing.
	 * @throws IOException If there was an error writing to file.
	 * @throws TextExtractionError If we encounter any error while extracting text.
	 */
	private static void parseAllChildren(PDDocument doc, PDOutlineItem parentItem, PDOutlineItem nextParentItem, TextParser parser, String type) throws IOException, TextExtractionError {
		String currentChildTitleName;
		String nextChildTitleName;
		PDFTextStripper stripper = new PDFTextStripper();
		PDOutlineItem item = parentItem.getFirstChild();

		while (item != null) {
			currentChildTitleName = item.getTitle();
			//skipping over certain titles since they have problems that prohibit their detection.
			if (currentChildTitleName.equals("saveClockNets") || currentChildTitleName.equals("saveClockTreeSpec") || 
					currentChildTitleName.equals("setPGPinUseSignalRoute") || currentChildTitleName.equals("Syntax Conventions") || 
					currentChildTitleName.equals("Syntax Examples") || currentChildTitleName.equals("Path Exception Priorities") || 
					currentChildTitleName.equals("Bidirectional Pin Defaults") || currentChildTitleName.equals("Overview") || 
					currentChildTitleName.equals("db_rect") || currentChildTitleName.equals("deleteAllInstGroups") || 
					currentChildTitleName.equals("deleteAllPowerPreroutes") || currentChildTitleName.equals("deleteAllSignalPreroutes") || 
					currentChildTitleName.equals("Puts") || currentChildTitleName.equals("deleteDanglingNet") || 
					currentChildTitleName.equals("delaycal_prop_slew_for_constant_net") || currentChildTitleName.equals("delaycal_prop_slew_for_disable_timing_net") || 
					currentChildTitleName.equals("timing_disable_sdf_retain_arc_merging") || currentChildTitleName.equals("timing_disable_set_case_analysis") || 
					currentChildTitleName.equals("timing_enable_early_late_data_slews_for_setuphold_mode_checks") || 
					currentChildTitleName.equals("timing_enable_derating_for_pulsewidth_checks") || currentChildTitleName.equals("timing_propagate_latch_data_uncertainty") || 
					currentChildTitleName.equals("timing_property_clock_used_as_data_unconstrained_clock_source_paths") || 
					currentChildTitleName.equals("timing_report_enable_max_path_limit_crossed") || currentChildTitleName.equals("timing_write_sdf_no_escape_backslash_control") || 
					currentChildTitleName.equals("write_global_slack_report_time_unit_backward_compatible_mode")) {
				System.out.println("    Skipping child: " + currentChildTitleName);
				item = item.getNextSibling();
				continue;
			}
			System.out.println("    Proccesing child: " + currentChildTitleName);
			stripper.setStartBookmark(item);
			if ((item = item.getNextSibling()) == null) {

				nextChildTitleName = nextParentItem.getTitle();//no need to check null on next parent item because the expected DPF file build is known and 'null' won't happen;
				stripper.setEndBookmark(nextParentItem);
			}
			else {
				nextChildTitleName = item.getTitle();
				stripper.setEndBookmark(item);
			}
			parser.parseText(stripper.getText(doc), currentChildTitleName, nextChildTitleName, type);
			docCount++;
		}
	}

	/**
	 * Prints an explanation about how to execute this method.
	 */
	private static void usage() {
		System.err.println("Please enter the path to the `EDI System Text Command Reference` PDF file as the first argument."
				+ " Optionaly you can specify the output XML file as the second argument,"
				+ " it can contain the path or just the file name or both concatenated");
	}

}
