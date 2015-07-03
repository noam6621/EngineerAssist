using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(EngineerAssistWebApp.Startup))]
namespace EngineerAssistWebApp
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            ConfigureAuth(app);
        }
    }
}
