using EngineerAssistWebApp.Models;
using EngineerAssistWebApp.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace EngineerAssistWebApp.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index(string search)
        {
            /*if (Request.IsAjaxRequest())
            {*/
            /*if (search != null)
            {*/
                /*var model =
                from c in _commands
                orderby c.Id
                select c;*/
                /*List<CommandModels> model = SearchSolr.Search(search);
                return PartialView("Results", model);
            }*/
            return View();
        }

        public ActionResult About()
        {
            ViewBag.Message = "Your application description page.";

            return View();
        }

        public ActionResult Contact()
        {
            ViewBag.Message = "Your contact page.";

            return View();
        }

        /*static List<CommandModels> _commands = new List<CommandModels>
        {
            new CommandModels
            {
                Id = "saveOaBlackboxes",
                CommandName = "saveOaBlackboxes",
                CommandSyntax = @"saveOaBlackboxes 
-lib libName 
-cell list_of_cells 
[-view viewName ] ",
                CommandDescription = @"Saves the specified blackbox abstracts, using the OpenAccess database format.
You can use the saveOaBlackboxes command at any time after importing the design.",
                CommandParameters = @"-cell
list_of_cells
Specifies one or more blackboxes to save. If you specify more than one blackbox,
you must enclose them in either double quotation marks or curly braces, and
separate them with an empty space.
-lib libName Specifies the OpenAccess library to use to save the blackbox abstracts. If an
OpenAccess library with the same name already exists, the software creates a
cell view in that library.
If the library does not exist, the software creates the library in the working directory,
with the library name and the path name being the same. If a new library is
created, the technology file is copied from the technology library to the new library.
-view
viewName
Specifies the cell view to save.
Default: Uses the view name abstract
 ",
                Examples = @"The following command saves the blackbox myBlackbox in view abstract : 
saveOaBlackboxes -lib myLib -cell myBlackbox -view abstract
The following command saves the blackboxes myBlackbox1 and myBlackbox2 in view abstract : 
saveOaBlackboxes -lib myLib -cell ""myBlackbox1 myBlackbox2"" -view abstract
The following command also saves the blackboxes myBlackbox1 and myBlackbox2 in view abstract
: 
saveOaBlackboxes -lib myLib -cell {myBlackbox1 myBlackbox2} -view abstract",
                RelatedGlobal = @"",
                RelatedCommand = @"",
                RelatedTopics = @""
            },
            new CommandModels
            {
                Id = "create_ecsm_extension",
                CommandName = "create_ecsm_extension",
                CommandSyntax = @"create_ecsm_extension 
[-help] 
[-cell_list list ] 
-spice_subCkt file_list 
[-spice_model {{ model1 cornerx cornery } { model2 cornerz } .... } ] 
-timing_library lib 
[-ecsm_slews slew_table ] 
[-ecsm_load load_table ] 
[-output_dir dir ] 
[-vdd_rail power_rail_name ] 
[-gnd_rail ground_rail_name ] 
",
                CommandDescription = @"Enables you to characterize the power and ground current waveforms into an existing Liberty ( .lib )
file.
To analyze the power-grid behavior during dynamic analysis in a more accurate way, the power
usage of individual cells attached to the power grid need to be studied. Determining the actual current
drawn from the power grid at any given time for any given cell enables the analysis tool to provide a
more accurate picture of the general power grid behavior. The create_ecsm_extension command
enables you to characterize the power and ground current waveforms into an existing Liberty ( .lib )
file. The existing power and timing data in the .lib file are retained and only the Power Dynamic
Current (PDC) and Ground Dynamic Current (GDC) waveforms are added corresponding to the
values in the power table for each arc.
The characterization tool, create_ecsm_extension command measures and generates this data for
standard cells and obtains the current profiles for the supply and ground pins for all transitioning arcs
of the cell, under different input slew and output load combinations, and under different sensitizations
of side input. The power/ground dynamic current waveforms in the .lib have SPICE level accuracy
and read in by power analysis for dynamic current calculation. PowerMeter interpolates the
waveforms if loads and slews do not match the index values of .lib . The dynamic current files
outputted by PowerMeter is then used in VoltageStorm ® Dynamic Gate (VSDG) for dynamic IR drop
analysis.
 
",
                CommandParameters = @" 
-cell_list
list
Specifies a list of cells.
-ecsm_load load_table
 Specifies the load table
-ecsm_slews slew_table
 Specifies the ECSM slew table.
-gnd_rail Specifies the name of the ground rail.
-help Outputs a brief description that includes the type and default information for each
gcreate_escm_extension parameter.
For a detailed description of the command and all of its parameters, use the man
command man create_escm_extension .
-
output_dir
dir
Specifies the output directory
-spice_model {{ model1 cornerx cornery } { model2 cornerz }.... }
 Specifies files with SPICE models and corners.
-spice_subckt file_list
 Specifies the SPICE subcircuit files.
-timing_library lib
 Specifies the timing library.
-vdd_rail Specifies the name of the power rail.
 
",
                Examples = @" The following command characterizes the power and ground current waveforms into the
existing Liberty( .lib ) file: 
create_ecsm_extension \ 
-cell_list cell_list \ 
-spice_subckt cln65lp_lk_v1d0.l \ 
-spice_model { { nvt.spi SS MID_DIO } { mod.typ} } \ 
-timing_library LIB/tcbn65lp_c050428wc.lib \ 
-vdd_rail VDD \ 
-gnd_rail VSS \ 
-ecsm_slews {0.010 0.025 0.050 0.100 0.200 0.500 1.00} \ 
-ecsm_load {0.010 0.025 0.050 0.100 0.200 0.500 1.00} \ 
-output_dir dpm_nvt
",
                RelatedGlobal = @"",
                RelatedCommand = @"",
                RelatedTopics = @" Dynamic Power and IRDrop Analysis "" in the  Voltus  User Guide
"
            },
            new CommandModels
            {
                Id = "lib_build_timing_cond_default_arc",
                GlobalVariableName = "lib_build_timing_cond_default_arc",
                GlobalVariableSyntax = @"lib_build_timing_cond_default_arc 
{true | false} ",
                GlobalVariableType = @"",
                GlobalVariableDefault = @" true
",
                GlobalVariableDescription = @"Controls the creation of the default timing arc. When set to true , the default timing arc is created from
the conditional timing arc delays specified in the library. The created default timing arc does not have
the WHEN condition. When set to false , the default timing arc from the library is disabled.
To set this global variable, use the set_global command.
",
                Examples = @"",
                RelatedGlobal = @"",
                RelatedCommand = @"",
                RelatedTopics = @""
            },
            new CommandModels
            {
                Id = "setAnalyzeProtoMode",
                CommandName = "setAnalyzeProtoMode",
                CommandSyntax = @"setAnalyzeProtoMode 
[-help] 
[-reset] 
[-load_timing_debug_report {true | false}] 
[-placeDesign {true | false}] 
[-timeDesign {true | false}] 
[-trialRoute {off | default | timing_aware | timing_aware_incr}] 
[-trialRoute_max_iteration val ]
",
                CommandDescription = @"Enables or disables certain steps during floorplan analysis (performed using the analyzeFloorplan
command). When you use this command, the settings affect the prototyping flow only. The
 
parameters of the setAnalyzeProtoMode command will be honored by the analyzeFloorplan command
within a FlexModel design.
",
                CommandParameters = @"-help Prints out the command usage.
-load_timing_debug_report
{true | false} 
Invokes timing debugger.
Default : true
-placeDesign {true | false} Runs cell placement.
Default : true
-reset Resets all or specified options to default value.
-timeDesign {true | false} Invokes prototype timing analysis.
Default : true
-trialRoute &lt;off | default | timing_aware | timing_aware_incr&gt;
 Routes the design with specific
  Default : off
-trialRoute_max_iteration val Specifies number of timing driven trialRoute iterations.
Default : 4,
Min : 0, Max : 100
Note: If –trialRoute is set to off , the -trialRoute_max_iteration
option cannot be used.
",
                Examples = @"Following are the required settings for just running the timing driven trialRoute 
setAnalyzeProtoMode –placeDesign false -trialRoute timing_aware
",
                RelatedGlobal = @"",
                RelatedCommand = @"",
                RelatedTopics = @""
            },
            new CommandModels
            {
                Id = "defOutCompressVia",
                GlobalVariableName = "defOutCompressVia",
                GlobalVariableSyntax = @"defOutCompressVia   {0 | 1} 
",
                GlobalVariableType = @"Integer
",
                GlobalVariableDefault = @"0
",
                GlobalVariableDescription = @"Compresses the via statement in SPECIALNETS section. This variable controls defOut  to sort vias
with two dimension array, so it can use DO numX BY numY STEP stepX stepY  statement.
 
",
                Examples = @"",
                RelatedGlobal = @"",
                RelatedCommand = @"defOut
",
                RelatedTopics = @""
            },
        };*/
    }
}