<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.Master" AutoEventWireup="true" CodeBehind="Notification.aspx.cs" Inherits="PayrollManagement.Notification" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <%--<form id="form1" runat="server">--%>
    <div id="outerFrame" runat="server">
        <h1>Notification</h1>
        <div id="headerInformation" style="float: left; clear: none">
            <p>
                <asp:Label ID="resultLabel" runat="server" />
            </p>
            <table>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="From: " />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="fromAddressTxtBox"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="fromAddressValidator" runat="server" ErrorMessage="Address Required" ControlToValidate="fromAddressTxtBox"></asp:RequiredFieldValidator>
                    </td>

                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="To: " />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="toAddressTxtBox"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="toAddressValidator" runat="server" ErrorMessage="Address Required" ControlToValidate="toAddressTxtBox"></asp:RequiredFieldValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Subject: " />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="subjectTxtBox"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="subjectValidator" runat="server" ErrorMessage="Subject Required" ControlToValidate="subjectTxtBox"></asp:RequiredFieldValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Message: " />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="messageTxtBox" TextMode="MultiLine" Width="420" Height="130"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="messageValidator" runat="server" ErrorMessage="Body Required" ControlToValidate="messageTxtBox"></asp:RequiredFieldValidator>
                    </td>
                </tr>
            </table>
        </div>
        <div id="formButtons" style="clear: both; margin-left: 50%">
            <asp:Button runat="server" ID="submitBtn" Text="Confirm" OnClick="confirm_Click" />
            <asp:Button runat="server" ID="clearBtn" Text="Clear" OnClick="clear_Click" />
        </div>
    </div>
    <%--  </form>--%>
</asp:Content>
