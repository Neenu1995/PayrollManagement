<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Paystub Page.aspx.cs" Inherits="Paystub_Page" %>

<!DOCTYPE html>

<script type="text/javascript">

    function Print() {
        window.print();
    }
</script>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
	<style type="text/css">
	.auto-style3 {
		margin-left: 1px;
	}
	body {
		border-style: solid;
		border-width: 2px;
		margin-top: auto;
		margin-bottom: auto;
		margin-left: 200px;
	}
	</style>
</head>
<body style="height: 520px; width: 1039px;" >
    <form id="form1" runat="server" style="border:36px;">
        <hr/>
        <div style="width: 1021px"><asp:Label ID="CompanyNameLabel" runat="server" Text="Company Name And Address"  style="margin-right:400px; margin-left:25px;" ReadOnly="True"></asp:Label>
            <asp:Label ID="SubtitleTextBox" runat="server" Text="Earning Statement" ReadOnly="True"></asp:Label>
        </div>
        <hr/>
        <p><span style="margin-right:100px;margin-left:10px;">Employee Name</span><span style="margin-right:100px">Social Security ID</span><span style="margin-right:100px">Employee ID</span> <span>Pay Period</span> </p>
        <hr/>
          <p><span style="margin-right:140px; margin-left:10px;"><asp:Label ID="NameLabel" runat="server" Text=""></asp:Label></span><span style="margin-right:140px"> <asp:Label ID="SSnLabel" runat="server" Text="Social Security "></asp:Label></span><span style="margin-right:120px"><asp:Label ID="IDLabel" runat="server" Text="Label"></asp:Label></span> <span><asp:Label ID="PayPeriodLabel" runat="server" Text=""></asp:Label></span> </p>
        <hr/>
        <br/>
        <br/>
        <div style="float:left; width: 349px;">
         &nbsp;
         <asp:Label ID="FullHoursLabel" runat="server" Text="# of Hours" ></asp:Label>
         <asp:TextBox ID="FullHoursTextBox" runat="server" ReadOnly="True"></asp:TextBox>
         <br/>
        <br/>
          &nbsp;<asp:Label ID="OvertimeHoursLabel" runat="server" Text="# of Overtime Hours"></asp:Label>
         <asp:TextBox ID="OvertimeHoursTextBox" runat="server" ReadOnly="True"></asp:TextBox>
         <br/>
        <br/>
          &nbsp;<asp:Label ID="RegularHoursLabel" runat="server" Text="# of Regular Hours"></asp:Label>
         <asp:TextBox ID="RegularHoursTextBox" runat="server" ReadOnly="True"></asp:TextBox>
         <br/>
        <br/>
         &nbsp;<asp:Label ID="GrossPayLabel" runat="server" Text="Gross Pay"></asp:Label>
         <asp:TextBox ID="GrossPayTextBox" runat="server" ReadOnly="True"></asp:TextBox>
        <br/>
        <br/>
            </div>

        <div style="float:right; width: 671px;" class="auto-style3">
        <asp:Label ID="PayRateLabel" runat="server" Text="Pay per Hour"></asp:Label>
         <asp:TextBox ID="PayRateTextBox" runat="server" ReadOnly="True"></asp:TextBox>
         <br />
         <br />
        <asp:Label ID="IncomeTaxLabel" runat="server" Text="Income Tax Deduction"></asp:Label>
         <asp:TextBox ID="IncomeTaxTextBox" runat="server" ReadOnly="True"></asp:TextBox>
            <br/>
            <br/>
        <asp:Label ID="CPPLabel" runat="server" Text="CPP Deduction"></asp:Label>
         <asp:TextBox ID="CPPTextBox" runat="server" ReadOnly="True"></asp:TextBox>
            <br/>
            <br/>
        <asp:Label ID="EILabel" runat="server" Text="Employment Insurance Deduction"></asp:Label>
         <asp:TextBox ID="EITextBox" runat="server" ReadOnly="True"></asp:TextBox>
            
            </div>
        <div style="margin-top:300px;">  
        <p>&nbsp;<asp:Label ID="NetPayLabel" runat="server" Text="Net Pay: "></asp:Label>
         <asp:TextBox ID="NetPayTextBox" runat="server" ReadOnly="True"></asp:TextBox>          
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
            <asp:Label ID="OverallLabel" runat="server" Text="Overall Deductions: ">  </asp:Label>  
            <asp:Textbox ID="OverallDeductionsTextbox" runat="server" Text="Label"></asp:Textbox>
        </p></div>
          <br/>
         <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Print Pay Stub" />
    </form>
  
</body>
</html>
