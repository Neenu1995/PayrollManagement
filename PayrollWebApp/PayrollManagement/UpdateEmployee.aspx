<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.Master" AutoEventWireup="true" CodeBehind="UpdateEmployee.aspx.cs" Inherits="PayrollManagement.UpdateEmployee" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <table>
        <tr>
            <td class="auto-style1" colspan="3">
                <h2>Update Employee Information</h2>
            </td>
        </tr>
        <tr>
            <td class="auto-style6">
                <asp:Label ID="Label1" runat="server" Text="Employee ID:"></asp:Label>
            </td>
            <td class="auto-style7">
                <asp:TextBox ID="txtEmployeeID" runat="server"></asp:TextBox>
            </td>
            <td class="auto-style8">
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" ControlToValidate="txtEmployeeID" ErrorMessage="Please enter an employee ID."></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td class="auto-style6">
                <asp:Label ID="Label2" runat="server" Text="First Name:"></asp:Label>
            </td>
            <td class="auto-style7">
                <asp:TextBox ID="txtFirstName" runat="server" TabIndex="1"></asp:TextBox>
            </td>
            <td class="auto-style8"></td>
        </tr>
        <tr>
            <td class="auto-style2">
                <asp:Label ID="Label3" runat="server" Text="Last Name:"></asp:Label>
            </td>
            <td class="auto-style4">
                <asp:TextBox ID="txtLastName" runat="server" TabIndex="2"></asp:TextBox>
            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td class="auto-style2">
                <asp:Label ID="Label4" runat="server" Text="Address:"></asp:Label>
            </td>
            <td class="auto-style4">
                <asp:TextBox ID="txtAddress" runat="server" TabIndex="3" TextMode="MultiLine"></asp:TextBox>
            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td class="auto-style2">
                <asp:Label ID="Label5" runat="server" Text="Contact Number:"></asp:Label>
            </td>
            <td class="auto-style4">
                <asp:TextBox ID="txtContactNumber" runat="server" TabIndex="4" TextMode="Phone"></asp:TextBox>
            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td class="auto-style2">
                <asp:Label ID="Label9" runat="server" Text="Email:"></asp:Label>
            </td>
            <td class="auto-style4">
                <asp:TextBox ID="txtEmail" runat="server" TabIndex="5" TextMode="Email"></asp:TextBox>
            </td>
            <td>
                <asp:RegularExpressionValidator ID="valEmail" runat="server" ControlToValidate="txtEmail" Display="Dynamic" ErrorMessage="Please enter a valid email address." ForeColor="#CC0000" ValidationExpression="\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*"></asp:RegularExpressionValidator>
            </td>
        </tr>
        <tr>
            <td class="auto-style2">
                <asp:Label ID="Label6" runat="server" Text="Position:"></asp:Label>
            </td>
            <td class="auto-style4">
                <asp:DropDownList ID="positionsList" runat="server" TabIndex="6">
                </asp:DropDownList>
            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td class="auto-style9">&nbsp;</td>
            <td class="auto-style4">
                <asp:Label ID="lblResult" runat="server"></asp:Label>
            </td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td class="auto-style10"></td>
            <td class="auto-style5">
                <asp:Button ID="btnUpdate" runat="server" OnClick="btnUpdate_Click" TabIndex="7" Text="Update" />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <asp:Button ID="btnCancel" runat="server" OnClick="btnCancel_Click" TabIndex="8" Text="Clear" />
            </td>
            <td class="auto-style3">&nbsp;</td>
        </tr>
        <tr>
            <td class="auto-style9">&nbsp;</td>
            <td class="auto-style4">&nbsp;</td>
            <td>&nbsp;</td>
        </tr>
    </table>
</asp:Content>
