using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.DataVisualization.Charting;
using System.Web.UI.WebControls;

namespace PayrollManagement
{
    public partial class Overall_Employee_Pay : System.Web.UI.Page
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




        protected void DropDownList1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (DropDownList1.SelectedIndex == 0)
            {
                Chart1.Series[0].ChartType = (SeriesChartType)17;
                Chart1.Series[1].ChartType = (SeriesChartType)17;
            }

            else if (DropDownList1.SelectedIndex == 1)
            {
                Chart1.Series[0].ChartType = (SeriesChartType)7;
                Chart1.Series[1].ChartType = (SeriesChartType)7;
            }


            else if (DropDownList1.SelectedIndex == 2)
            {
                Chart1.Series[0].ChartType = (SeriesChartType)2;
                Chart1.Series[1].ChartType = (SeriesChartType)2;
            }


            else if (DropDownList1.SelectedIndex == 3)
            {
                Chart1.Series[0].ChartType = (SeriesChartType)18;
                Chart1.Series[1].ChartType = (SeriesChartType)18;
            }

            else if (DropDownList1.SelectedIndex == 4)
            {
                Chart1.Series[0].ChartType = (SeriesChartType)11;
                Chart1.Series[1].ChartType = (SeriesChartType)11;
            }


            else if (DropDownList1.SelectedIndex == 5)
            {
                Chart1.Series[0].ChartType = (SeriesChartType)25;
                Chart1.Series[1].ChartType = (SeriesChartType)25;
            }
        }
    }
}