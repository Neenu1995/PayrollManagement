<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPageBigMac.Master" AutoEventWireup="true" CodeBehind="Calculate Pay Page.aspx.cs" Inherits="PayrollManagement.Calculate_Pay_Page" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div>
            <h1>Calculate Individual Employee Pay</h1>
            <asp:Label ID="errorLabel" runat="server" Text="" Forecolor="Red" Font-Bold="true" Font-Size="Large"></asp:Label>
            <br/>
            <asp:Label ID="Employee_ID_Label" runat="server" Text="Employee ID"></asp:Label>  &nbsp
            <asp:TextBox ID="Employee_ID_Textbox" runat="server" Width="253px"></asp:TextBox>
            <asp:Button ID="AcceptButton" runat="server" Text="Calculate" OnClick="AcceptButton_Click" style="margin-left:50px" Width="150px"/>
             <asp:Button ID="AllEmployeesPayButton" runat="server" Text="View all Employees Payrates" style="margin-left:50px" Width="300px" OnClick="AllEmployeesPayButton_Click"/>
            <br/>
            <br/>
            <asp:Label ID="AverageHoursLabel" runat="server" Text="Average Hours Worked Per Shift"></asp:Label>  &nbsp
            <asp:TextBox ID="AverageHoursTextBox" runat="server" Width="253px"></asp:TextBox>
            <br/>
            <br/>
            <asp:Label ID="AverageShiftsLabel" runat="server" Text="Average # of Shifts Per Week"></asp:Label>  &nbsp
            <asp:TextBox ID="AverageShiftsTextBox" runat="server" Width="253px"></asp:TextBox>
            <br/>
            <br/>
            <asp:Panel ID="ResultsPanel" runat="server" Visible="false">
            <asp:Label ID="PayRateLabel" runat="server" Text="Pay per Hour:"></asp:Label>
         <asp:Label ID="PayRateTextBox" runat="server" ReadOnly="True"></asp:Label>
            <br/>
            <br/>
          <asp:Label ID="GrossPayLabel" runat="server" Text="Gross Pay Owed:"></asp:Label>
         <asp:Label ID="GrossPayTextBox" runat="server" ReadOnly="True"></asp:Label>
            <br/>
            <br/>
            <asp:Label ID="TotalHoursWorkedLabel" runat="server" Text="Total Hours Worked:"></asp:Label>
         <asp:Label ID="TotalHourWorkedTextbox" runat="server" ReadOnly="True"></asp:Label>
            <br/>
            <br/>
             <asp:Label ID="ShiftCountLabel" runat="server" Text="Total Number of Shifts:"></asp:Label>
         <asp:Label ID="TotalShiftsTextbox" runat="server" ReadOnly="True"></asp:Label>
            <br/>
            <br/>
             <asp:Label ID="PayPeriodLabel" runat="server" Text="For the Period of:"></asp:Label>
             <asp:Label ID="PayPeriodTextbox" runat="server"></asp:Label>
            <br/>
            <br/>
            <asp:Label ID="EILabel" runat="server" Text="EI Deductions:"></asp:Label>
             <asp:Label ID="EITextbox" runat="server"></asp:Label>
            <br/>
            <br/>
            <asp:Label ID="CPPLabel" runat="server" Text="CPP Deductions:"></asp:Label>
             <asp:Label ID="CPPTextbox" runat="server"></asp:Label>
            <br/>
            <br/>
            <asp:Label ID="IncomeTaxLabel" runat="server" Text="Income Tax Deductions"></asp:Label>
             <asp:Label ID="IncomeTaxTextbox" runat="server"></asp:Label>
            <br/>
            <br/>
            <asp:Label ID="NetPayLabel" runat="server" Text="Net Pay:"></asp:Label>
             <asp:Label ID="NetPayTextbox" runat="server"></asp:Label>
                <br />
                <br />
                <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Convert Results to Paystub" />
                </asp:Panel>
            <br/>
            <br/>
           <div id="date1"> 
                 <asp:Label ID="Label1" runat="server" Text="Choose Start Date"></asp:Label>
               <asp:Calendar ID="Begin_Calendar" runat="server" BackColor="White" BorderColor="Black" BorderWidth="2px" Font-Names="Verdana" Font-Size="9pt" ForeColor="Black" Height="300px" NextPrevFormat="FullMonth" Width="400px">
                 <DayHeaderStyle Font-Bold="True" Font-Size="8pt" />
            <NextPrevStyle Font-Bold="True" Font-Size="8pt" ForeColor="#333333" VerticalAlign="Bottom" />
            <OtherMonthDayStyle ForeColor="#999999" />
            <SelectedDayStyle BackColor="#333399" ForeColor="White" />
            <TitleStyle BackColor="White" BorderColor="Black" BorderWidth="4px" Font-Bold="True" Font-Size="12pt" ForeColor="#333399" />
            <TodayDayStyle BackColor="#CCCCCC" />
            </asp:Calendar>
           </div>

            <div id="date2" style="position: relative; left: 500px; margin-top: -320px;">
                <asp:Label ID="Label2" runat="server" Text="Choose End Date"></asp:Label>
            <asp:Calendar ID="End_Calendar" runat="server" BackColor="White" BorderColor="Black" BorderWidth="2px" Font-Names="Verdana" Font-Size="9pt" ForeColor="Black" Height="300px" NextPrevFormat="FullMonth" Width="400px">
                 <DayHeaderStyle Font-Bold="True" Font-Size="8pt" />
            <NextPrevStyle Font-Bold="True" Font-Size="8pt" ForeColor="#333333" VerticalAlign="Bottom" />
            <OtherMonthDayStyle ForeColor="#999999" />
            <SelectedDayStyle BackColor="#333399" ForeColor="White" />
            <TitleStyle BackColor="White" BorderColor="Black" BorderWidth="4px" Font-Bold="True" Font-Size="12pt" ForeColor="#333399" />
            <TodayDayStyle BackColor="#CCCCCC" />
            </asp:Calendar>
            </div>
            <br/>
            <br/>
                <br/>
        </div>
</asp:Content>
