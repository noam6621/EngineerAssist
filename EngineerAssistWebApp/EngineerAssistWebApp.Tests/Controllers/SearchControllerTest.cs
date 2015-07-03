using System.Linq;
using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using EngineerAssistWebApp.Controllers;
using System.Web.Mvc;
using EngineerAssistWebApp.Models;
using System.Collections.Generic;
using SolrNet;

namespace EngineerAssistWebApp.Tests.Controllers
{
    [TestClass]
    public class SearchControllerTest
    {
        [TestMethod]
        public void SearchReturnsView()
        {
            // Arrange
            Startup.Init<CommandModels>("http://localhost:8983/solr");
            SearchController controller = new SearchController();

            // Act
            ActionResult result = controller.Search("class") as ActionResult;

            // Assert
            Assert.IsNotNull(result);
        }

        [TestMethod]
        public void DetailsReturnsView()
        {
            // Arrange
            SearchController controller = new SearchController();

            // Act
            ViewResult result = controller.Details("set_timing_derate") as ViewResult;

            // Assert
            Assert.IsNotNull(result);
        }

        [TestMethod]
        public void DetailsRecivesOneModel()
        {
            // Arrange
            SearchController controller = new SearchController();

            // Act
            ViewResult result = controller.Details("set_timing_derate") as ViewResult;

            // Assert
            Assert.AreEqual((result.Model as CommandModels).Id, "set_timing_derate", false);
        }
    }
}
