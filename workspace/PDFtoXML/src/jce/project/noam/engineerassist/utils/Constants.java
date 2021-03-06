package jce.project.noam.engineerassist.utils;

/**
 * Constants for the PDFtoXML project, part of the Engineer Assist project.
 * 
 * @author Noam S.
 */
public class Constants {
	public static final String COMMANDS = "commands";
	public static final String GLOBAL_VARIABLES = "global_variables";
	public static final String DESIGN_METRICS = "design_metrics";
	
	public static final String COMMAND_PARAMETERS = "Parameters";
	public static final String COMMAND_PARAMETER = "Parameter";
	public static final String GLOBAL_VARIABLE_TYPE = "Type: ";
	public static final String GLOBAL_VARIABLE_TYPE2 = "Type : ";
	public static final String GLOBAL_VARIABLE_DEFAULT = "Default: ";
	public static final String GLOBAL_VARIABLE_DEFAULT2 = "Default : ";
	public static final String EXAMPLES = "Examples";
	public static final String EXAMPLE = "Example";
	public static final String RELATED_GLOBALS = "Related Globals";
	public static final String RELATED_GLOBAL = "Related Global";
	public static final String RELATED_GLOBAL_VARIABLES = "Related Global Variables";
	public static final String RELATED_GLOBAL_VARIABLE = "Related Global Variable";
	public static final String RELATED_COMMANDS = "Related Commands";
	public static final String RELATED_COMMAND = "Related Command";
	public static final String RELATED_TOPICS = "Related Topics";
	public static final String RELATED_TOPIC = "Related Topic";
	
	public static final String FIELD_ID = "id";
	public static final String FIELD_COMMAND_NAME = "command_name";
	public static final String FIELD_COMMAND_SYNTAX = "command_syntax";
	public static final String FIELD_COMMAND_DESCRIPTION = "command_description";
	public static final String FIELD_COMMAND_PARAMETERS = "command_parameters";
	
	public static final String FIELD_GLOBAL_VARIABLE_NAME = "global_variable_name";
	public static final String FIELD_GLOBAL_VARIABLE_SYNTAX = "global_variable_syntax";
	public static final String FIELD_GLOBAL_VARIABLE_TYPE = "global_variable_type";
	public static final String FIELD_GLOBAL_VARIABLE_DEFAULT = "global_variable_default";
	public static final String FIELD_GLOBAL_VARIABLE_DESCRIPTION = "global_variable_description";
	
	public static final String FIELD_EXAMPLES = "examples";
	public static final String FIELD_RELATED_GLOBAL = "related_global";
	public static final String FIELD_RELATED_COMMAND = "related_command";
	public static final String FIELD_RELATED_TOPICS = "related_topics";
		
	public static final int START_SECTION = 0;
	public static final int COMMAND_PARAMETERS_SECTION = 1;
	public static final int EXAMPLES_SECTION = 2;
	public static final int RELATED_GLOBALS_SECTION = 3;
	public static final int RELATED_COMMANDS_SECTION = 4;
	public static final int RELATED_TOPICS_SECTION = 5;
	public static final int GLOBAL_VARIABLE_NAME_SECTION = 6;
	public static final int GLOBAL_VARIABLE_SYNTAX_SECTION = 7;
	public static final int GLOBAL_VARIABLE_TYPE_SECTION = 8;
	public static final int GLOBAL_VARIABLE_DEFAULT_SECTION = 9;
	public static final int GLOBAL_VARIABLE_DESCRIPTION_SECTION = 10;
}
