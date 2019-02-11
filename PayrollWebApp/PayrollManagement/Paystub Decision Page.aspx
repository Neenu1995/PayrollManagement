<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.Master" AutoEventWireup="true" CodeBehind="Paystub Decision Page.aspx.cs" Inherits="PayrollManagement.Paystub_Decision_Page" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
      <div>
        <h1>Paystub and Verification Options</h1>
            <h3>Please Choose an Option Below:</h3> 
            </div>
        <div style="margin-left:50px">
            <asp:Menu ID="Menu1" runat="server">
                <Items>
                    <asp:MenuItem NavigateUrl="~/Paystub Employee Choice Page.aspx" Text="Generate a Bi-Weekly Paystub" Value="Generate a Bi-Weekly Paystub"></asp:MenuItem>
                    <asp:MenuItem NavigateUrl="~/Calculate Pay Page.aspx" Text="Generate a Custom Paystub For Any Period" Value="Generate a Custom Paystub For Any Period"></asp:MenuItem>
                    <asp:MenuItem NavigateUrl="~/Create Employee Schedule.aspx" Text="View Schedule" Value="View Schedule"></asp:MenuItem>
                    <asp:MenuItem NavigateUrl="~/Overall Employee Pay.aspx" Text="Verify all Employee Payrates" Value="New Item"></asp:MenuItem>
                </Items>
            </asp:Menu>
        </div>
</asp:Content>
