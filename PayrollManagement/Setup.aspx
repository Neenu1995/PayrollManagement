<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPageAdmin.Master" AutoEventWireup="true" CodeBehind="Setup.aspx.cs" Inherits="PayrollManagement.Setup" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <%--<form id="form1" runat="server">--%>
        <div>
            <h1>Initial Setup Manager</h1>
            <h5>Please edit all fields to configure the environment to meet your needs</h5>
            <table>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Country" />
                    </td>
                    <td>
                        <asp:DropDownList ID="countryList" runat="server">
                            <asp:ListItem Selected="True">Canada</asp:ListItem>
                            <asp:ListItem Enabled="false">USA</asp:ListItem>
                        </asp:DropDownList>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Province or State" />
                    </td>
                    <td>
                        <asp:DropDownList ID="provinceList" runat="server"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Type of Business" />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="businessTypeBox"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="businessValidator" runat="server" ErrorMessage="Business Type Required" ControlToValidate="businessTypeBox"></asp:RequiredFieldValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Root User Name" />
                    </td>
                    <td>
                        <asp:TextBox ID="nameBox" runat="server"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="nameValidator" runat="server" ErrorMessage="Username Required" ControlToValidate="nameBox"></asp:RequiredFieldValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Contact Number" />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="numberTxtBox" TextMode="Number" MaxLength="10"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="contactValidator" runat="server" ErrorMessage="Contact Number Required" ControlToValidate="numberTxtBox"></asp:RequiredFieldValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Email Address" />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="emailTxtBox" TextMode="Email"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="emailValidator" runat="server" ErrorMessage="Email Required" ControlToValidate="emailTxtBox"></asp:RequiredFieldValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Password" />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="passwordTxtBox" />
                        <asp:RequiredFieldValidator ID="passwordValidator" runat="server" ErrorMessage="Password Required" ControlToValidate="passwordTxtBox"></asp:RequiredFieldValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Confirm Passowrd" />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="confirmPasswordTxtBox" />
                        <asp:CompareValidator ID="confirmPasswordValidator" runat="server" ErrorMessage="Password does not match" ControlToValidate="confirmPasswordTxtBox" ControlToCompare="passwordTxtBox"></asp:CompareValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label ID="resultLabel" runat="server" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Button ID="confirmBtn" runat="server" Text="Confirm" OnClick="confirmBtn_Click" />
                    </td>
                    <td>
                        <asp:Button ID="cancelBtn" runat="server" Text="Cancel" />
                    </td>
                </tr>
            </table>
        </div>
    <%--  </form>--%>
</asp:Content>
