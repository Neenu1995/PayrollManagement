<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.Master" AutoEventWireup="true" CodeBehind="Paystub Employee Choice Page.aspx.cs" Inherits="Paystub_Employee_Choice_Page" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    

    <%-- <form id="form1" runat="server"> --%>
        <div style="margin-left:30px">
            <h1>Bi-Weekly Paystub Generator</h1>
            <asp:Label ID="errorLabel" runat="server" Text="" Forecolor="Red" Font-Bold="true" Font-Size="Large"></asp:Label>
            <br/>
            <asp:Label ID="Employee_ID_Label" runat="server" Text="Employee ID"></asp:Label>  &nbsp
            <asp:TextBox ID="Employee_ID_Textbox" runat="server" Width="253px"></asp:TextBox>
            <br/>
            <br/>
            <br/>
        </div>
        <div style="margin-left:30px">
        <p>
            <asp:Label ID="Label1" runat="server" Text="Choose Bi-Weekly Payment Period End Date "></asp:Label>
            <asp:Calendar ID="End_Calendar" runat="server" BackColor="White" BorderColor="White" BorderWidth="1px" Font-Names="Verdana" Font-Size="9pt" ForeColor="Black" Height="300px" NextPrevFormat="FullMonth" Width="400px">
            <DayHeaderStyle Font-Bold="True" Font-Size="8pt" />
            <NextPrevStyle Font-Bold="True" Font-Size="8pt" ForeColor="#333333" VerticalAlign="Bottom" />
            <OtherMonthDayStyle ForeColor="#999999" />
            <SelectedDayStyle BackColor="#333399" ForeColor="White" />
            <TitleStyle BackColor="White" BorderColor="Black" BorderWidth="4px" Font-Bold="True" Font-Size="12pt" ForeColor="#333399" />
            <TodayDayStyle BackColor="#CCCCCC" />
            </asp:Calendar>
        </p>
            </div>
        <div style="margin-left:300px">
            <asp:Button ID="Calculate_Button" runat="server" Text="Calculate" OnClick="Calculate_Button_Click" />
        </div>
    <%-- </form> --%>
</asp:Content>
