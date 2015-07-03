using EngineerAssistWebApp.Models;
using EngineerAssistWebApp.Utils;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace EngineerAssistWebApp.Controllers
{
    public class SearchController : Controller
    {
        public ActionResult Search(string query)
        {
            ViewBag.QueryString = query;
            List<CommandModels> model = SearchSolr.Search(query);
            return View(model);
        }

        public ActionResult Details(string id)
        {
            List<CommandModels> model = SearchSolr.Search(id);
            return View(model.ElementAt<CommandModels>(0));
        }
    }
}