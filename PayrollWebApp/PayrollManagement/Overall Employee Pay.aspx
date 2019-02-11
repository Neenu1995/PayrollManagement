<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPageBigMac.Master" AutoEventWireup="true" CodeBehind="Overall Employee Pay.aspx.cs" Inherits="PayrollManagement.Overall_Employee_Pay" %>
<%@ Register assembly="System.Web.DataVisualization, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35" namespace="System.Web.UI.DataVisualization.Charting" tagprefix="asp" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <div style="margin-left:300px">
            <h1>Employee Pay Information</h1>
            <p>
                <asp:Label ID="Label1" runat="server" Text="Choose Table"></asp:Label> &nbsp
                <asp:DropDownList ID="DropDownList1" runat="server" AutoPostBack="true" OnSelectedIndexChanged="DropDownList1_SelectedIndexChanged">
                    <asp:ListItem>Pie Chart</asp:ListItem>
                    <asp:ListItem>Bar Chart</asp:ListItem>
                    <asp:ListItem>Bubble Chart</asp:ListItem>
                    <asp:ListItem>Donut Chart</asp:ListItem>
                    <asp:ListItem>Stacked Column</asp:ListItem>
                    <asp:ListItem>Radar Chart</asp:ListItem>
                </asp:DropDownList>
            </p>
        <asp:Chart ID="Chart1" runat="server" DataSourceID="SqlDataSource1" Height="412px" Width="738px" >
            <series>
                <asp:Series ChartType="Pie" Legend="Legend1" Name="Percentage of Payrate" XValueMember="hourlyPayRate" YValueMembers="theCount" IsValueShownAsLabel="True" Label="#PERCENT{P}" XValueType="Double" YValueType="Double" BorderColor="Black" YValuesPerPoint="2">
                </asp:Series>
                <asp:Series ChartArea="ChartArea1" ChartType="Pie" Label="#VAL{C}" Legend="Legend2" Name="Payrate in dollars" XValueMember="hourlyPayRate" YValueMembers="hourlyPayRate" Font="Microsoft Sans Serif, 7.8pt" YValueType="Double" YValuesPerPoint="2" XValueType="Double">
                </asp:Series>
            </series>
            <chartareas>
                <asp:ChartArea Name="ChartArea1">
                </asp:ChartArea>
            </chartareas>
            <Legends>
                <asp:Legend Name="Legend1" Title="Hourly Pay Rate Percentage" Font="Microsoft Sans Serif, 7.8pt" IsTextAutoFit="False">
                </asp:Legend>
                <asp:Legend Name="Legend2" Title="Hourly Payrate">
                </asp:Legend>
            </Legends>
            <Titles>
                <asp:Title Name="Title1" Text="Employee Hourly Payrates" Font="Microsoft Sans Serif, 13.8pt">
                </asp:Title>
            </Titles>
        </asp:Chart>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:Software_ProjectDBConnectionString %>" SelectCommand="SELECT DISTINCT hourlyPayRate, COUNT(hourlyPayRate) AS theCount FROM Employee GROUP BY hourlyPayRate"></asp:SqlDataSource>
       <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" DataKeyNames="EmployeeID" DataSourceID="SqlDataSource2" Width="384px">
            <Columns>
                <asp:BoundField DataField="EmployeeID" HeaderText="EmployeeID" InsertVisible="False" ReadOnly="True" SortExpression="EmployeeID" />
                <asp:BoundField DataField="hourlyPayRate" HeaderText="hourlyPayRate" SortExpression="hourlyPayRate" />
            </Columns>
        </asp:GridView>
            <asp:SqlDataSource ID="SqlDataSource2" runat="server" ConnectionString="<%$ ConnectionStrings:Software_ProjectDBConnectionString %>" SelectCommand="SELECT [EmployeeID], [hourlyPayRate] FROM [Employee]"></asp:SqlDataSource>
        </div>
</asp:Content>
