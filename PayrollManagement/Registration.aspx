<%@ Page Title="" Language="C#" MasterPageFile="~/MasterPage.Master" AutoEventWireup="true" CodeBehind="Registration.aspx.cs" Inherits="PayrollManagement.Registration" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <%--<form id="form1" runat="server">--%>
        <div id="outerFrame" runat="server">
            <h1>Registration</h1>
            <div id="personalInformation" style="float: left; clear: none">
                <table>
                    <tr>
                        <td>
                            <asp:Label runat="server" Text="First Name" />
                        </td>
                        <td>
                            <asp:TextBox runat="server" ID="firstNameTxtBox"></asp:TextBox>
                            <asp:RequiredFieldValidator ID="firstNameValidator" runat="server" ErrorMessage="First Name Required" ControlToValidate="firstNameTxtBox"></asp:RequiredFieldValidator>
                        </td>
                        <td>
                            <asp:Label runat="server" Text="Last Name" />
                        </td>
                        <td>
                            <asp:TextBox runat="server" ID="lastNameTxtBox"></asp:TextBox>
                            <asp:RequiredFieldValidator ID="lastNameValidator" runat="server" ErrorMessage="Last Name Required" ControlToValidate="lastNameTxtBox"></asp:RequiredFieldValidator>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <asp:Label runat="server" Text="Address" />
                        </td>
                        <td>
                            <asp:TextBox runat="server" ID="addressTxtBox" TextMode="MultiLine"></asp:TextBox>
                            <asp:RequiredFieldValidator ID="addressValidator" runat="server" ErrorMessage="Address Required" ControlToValidate="addressTxtBox"></asp:RequiredFieldValidator>
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
                            <asp:Label runat="server" Text="Age" />
                        </td>
                        <td>
                            <asp:TextBox runat="server" ID="ageTxtBox" TextMode="Number"></asp:TextBox>

                            <asp:RequiredFieldValidator ID="ageValidator" runat="server" ErrorMessage="Age Required" ControlToValidate="ageTxtBox"></asp:RequiredFieldValidator>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="JobInformation" style="float: left; clear: left">
                <table>
                    <tr>
                        <td>
                            <asp:Label runat="server" Text="Position" />
                        </td>
                        <td>
                            <asp:DropDownList runat="server" ID="positionsList" />
                            <asp:RequiredFieldValidator ID="positionValidator" runat="server" ErrorMessage="Select the position" ControlToValidate="positionsList"></asp:RequiredFieldValidator>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <asp:Label runat="server" Text="Pay Rate" />
                        </td>
                        <td>
                            <asp:TextBox ID="payRate" runat="server" TextMode="Number" />
                            <asp:RequiredFieldValidator ID="payRateValidator" runat="server" ErrorMessage="Payrate must asign" ControlToValidate="payRate"></asp:RequiredFieldValidator>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <asp:Label runat="server" Text="Social Security Number" />
                        </td>
                        <td>
                            <asp:TextBox runat="server" ID="SINTxtBox" MaxLength="9" />
                            <%--<asp:RangeValidator ID="SINRange" runat="server" ErrorMessage="SIN number must be 9 Digits" ValidationExpression="^.{8,9}$" ControlToValidate="SINTxtBox"></asp:RangeValidator>--%>
                            <asp:RequiredFieldValidator ID="SINValidator" runat="server" ErrorMessage="SIN number must be 9 Digits" ControlToValidate="SINTxtBox"></asp:RequiredFieldValidator>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="buttons" style="clear: both; margin-left: 50%">
                <asp:Button runat="server" ID="confirm" Text="Confirm" OnClick="confirm_Click"/>
                <asp:Button runat="server" ID="clear" Text="Clear" OnClick="clear_Click" />
            </div>
        </div>
        <div runat="server" id="LoginInformation" style="float: initial; clear: both; margin-left: 50%">
            <table>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Employee ID" />
                    </td>
                    <td>
                        <asp:TextBox runat="server" Enabled="false" ID="EmployeeIdTxtBox" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Password" />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="passwordTxtBox" />
                        <asp:RequiredFieldValidator ID="passwordValidator" runat="server" ErrorMessage="Password Must Required" ControlToValidate="passwordTxtBox" ValidationGroup="Group2"></asp:RequiredFieldValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label runat="server" Text="Confirm Passowrd" />
                    </td>
                    <td>
                        <asp:TextBox runat="server" ID="confirmPasswordTxtBox" />
                        <asp:CompareValidator ID="CompareValidator1" runat="server" ErrorMessage="Mismatch Password" ControlToValidate="confirmPasswordTxtBox" ControlToCompare="passwordTxtBox" ValidationGroup="Group2"></asp:CompareValidator>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Button runat="server" Text="Create" ID="create" OnClick="create_Click" CausesValidation="true" ValidationGroup="Group2" />
                    </td>
                    <td>
                        <asp:Button runat="server" Text="Clear" ID="clearPass" OnClick="clearPass_Click" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:Label ID="commonLabel" runat="server" Text="" Enabled="false" Font-Size="Large" Font-Bold="true"></asp:Label>
                    </td>
                </tr>
            </table>
        </div>
  <%--  </form>--%>
</asp:Content>
