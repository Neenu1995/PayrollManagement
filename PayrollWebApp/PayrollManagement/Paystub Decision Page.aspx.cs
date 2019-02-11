using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace PayrollManagement
{
    public partial class Paystub_Decision_Page : System.Web.UI.Page
    {
        protected override void OnPreInit(EventArgs e)
        {
            //Checks which user is entering the system and chooses the master pages for them
            int postion = Model.MiscClass.position;
            if (postion == 3)
            {
                MasterPageFile = "~/MasterPageAdmin.Master";
            }
            else if (postion == 2)
            {
                MasterPageFile = "~/MasterPage.Master";
            }
            else
            {
                MasterPageFile = "~/MasterPageEmp.Master";
            }
        }

        protected void Page_Load(object sender, EventArgs e)
        {

        }
    }
}