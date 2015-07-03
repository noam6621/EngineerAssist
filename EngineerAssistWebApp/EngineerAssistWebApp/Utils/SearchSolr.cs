using EngineerAssistWebApp.Models;
using Microsoft.Practices.ServiceLocation;
using SolrNet;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace EngineerAssistWebApp.Utils
{
    public class SearchSolr
    {
        public static List<CommandModels> Search(string query)
        {
            var solr = ServiceLocator.Current.GetInstance<ISolrOperations<CommandModels>>();
            return solr.Query(new SolrQuery(query));
        }
    }
}