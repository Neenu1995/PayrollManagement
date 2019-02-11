<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPageBigMac.Master" AutoEventWireup="true" CodeBehind="Create Employee Schedule.aspx.cs" Inherits="PayrollManagement.Create_Employee_Schedule" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
      <h1>Create Employee Schedule</h1>
            <asp:Label ID="errorLabel" runat="server" Text="" Forecolor="Red" Font-Bold="true" Font-Size="Large"></asp:Label>

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

                 <asp:Label ID="BeginTimeLabel" runat="server" Text="Begin Time:"></asp:Label>

            <asp:TextBox ID="BeginTime" TextMode="Time" runat="server" Height="50px" Width="200px"></asp:TextBox>   
           </div>

            <div id="date2" style="position: relative; left: 500px; margin-top: -380px;">
                <asp:Label ID="Label2" runat="server" Text="Choose End Date"></asp:Label>
            <asp:Calendar ID="End_Calendar" runat="server" BackColor="White" BorderColor="Black" BorderWidth="2px" Font-Names="Verdana" Font-Size="9pt" ForeColor="Black" Height="300px" NextPrevFormat="FullMonth" Width="400px">
                 <DayHeaderStyle Font-Bold="True" Font-Size="8pt" />
            <NextPrevStyle Font-Bold="True" Font-Size="8pt" ForeColor="#333333" VerticalAlign="Bottom" />
            <OtherMonthDayStyle ForeColor="#999999" />
            <SelectedDayStyle BackColor="#333399" ForeColor="White" />
            <TitleStyle BackColor="White" BorderColor="Black" BorderWidth="4px" Font-Bold="True" Font-Size="12pt" ForeColor="#333399" />
            <TodayDayStyle BackColor="#CCCCCC" />
            </asp:Calendar>
                 <asp:Label ID="EndTimeLabel" runat="server" Text="End Time"></asp:Label>
                <asp:TextBox ID="EndTime" TextMode="Time" runat="server" Height="50px" Width="200px"></asp:TextBox>   
            </div>
            <br/>
            <br/>
                <asp:Label ID="Label3" runat="server" Text="For Employee: "></asp:Label>
&nbsp;
        <asp:TextBox ID="EmployeeIDTextBox" runat="server"></asp:TextBox>
                <br/>
        
        <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Create Schedule" />
</asp:Content>
