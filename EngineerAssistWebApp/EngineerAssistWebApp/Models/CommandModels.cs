using SolrNet.Attributes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace EngineerAssistWebApp.Models
{
    public class CommandModels
    {
        [SolrUniqueKey("id")]
        public string Id { get; set; }
        [SolrField("examples")]
        public string Examples { get; set; }
        [SolrField("related_global")]
        public string RelatedGlobal { get; set; }
        [SolrField("related_command")]
        public string RelatedCommand { get; set; }
        [SolrField("related_topics")]
        public string RelatedTopics { get; set; }
        [SolrField("command_name")]
        public string CommandName { get; set; }
        [SolrField("command_syntax")]
        public string CommandSyntax { get; set; }
        [SolrField("command_description")]
        public string CommandDescription { get; set; }
        [SolrField("command_parameters")]
        public string CommandParameters { get; set; }
        [SolrField("global_variable_name")]
        public string GlobalVariableName { get; set; }
        [SolrField("global_variable_syntax")]
        public string GlobalVariableSyntax { get; set; }
        [SolrField("global_variable_type")]
        public string GlobalVariableType { get; set; }
        [SolrField("global_variable_default")]
        public string GlobalVariableDefault { get; set; }
        [SolrField("global_variable_description")]
        public string GlobalVariableDescription { get; set; }
    }
}