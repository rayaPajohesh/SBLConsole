<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="publicError.jsp" autoFlush="false" buffer="200kb" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ page import="com.iac.ambit.utils.Constants"%>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
    <META HTTP-EQUIV="Expires" CONTENT="-1"/>
  <link rel="shortcut icon" href="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>favicon.ico" >
    <link rel="icon" href="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>favicon.ico"  >
    <link href="<%= session.getAttribute( Constants.JspConstants.PATH_CSS  )%>publicScreens.css" rel="stylesheet" type="text/css"/>
    <link href="<%= session.getAttribute( Constants.JspConstants.PATH_CSS  )%>default.css" rel="stylesheet" type="text/css"/>
    <title>
      <bean:message key="loginTitle"/>
    </title>
  </head>
  <body id="mubz" bgcolor="#FFFFFF">
  <table   height="100%" width="100%" cellspacing="0" border="0" cellpadding="0" align="center" bgcolor="#FFFFFF">
  <tr>
  <td width="50%" valign="top">
  	 		<table width="100%" border="0" height="300px" cellspacing="0" cellpadding="0" valign="top"
					 style="background-repeat: repeat-x">
					<tr>
						<td background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>page_Bkg.jpg">
						&nbsp;</td>
						

					</tr>
				</table>
  	 	</td>
  <td>
  <table height="100%" width="902px"   cellspacing="0" border="0" cellpadding="0" align="center" >
  	<tr>
  	
  		<td width="8px" class="bordarShadowRight"></td>
  		<td>
	    <table class="globalTable" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	      <tr>
	        <td align="center"  valign="bottom" >
	        <table cellpadding="0" cellspacing="0"   border="0">
	        	<tr>
	        		<td><tiles:insert attribute="header" flush="false" /></td>
	          	</tr>
	        </table>
	        </td>
	      </tr>
	      <tr>
		      <td align="center"  valign="top" height="100%"  >
			      <table cellpadding="0" cellspacing="0" height="100%" width="100%"  align="center">
			      <tr>
			        <td valign="top" align="left" height="100%">
			          <table width="100%" cellpadding="0" cellspacing="0" border="0" align="left" height="100%" class="contentRow">
			            <tr width="100%" >
			              <td width="100%" valign="top" align="left" height="100%" bgcolor="#FFFFFF"  >
			                <tiles:insert attribute="body" flush="false" />
			              </td>
			            </tr>
			          </table>
			        </td>
			      </tr>
			      </table>
		      </td>
	      </tr>
	      <tr>     
	        <td align="left" valign="bottom" dir="ltr" >
	          <tiles:insert attribute="footer" flush="false" />
	        </td>
	      </tr>
	     </table>
	    </td>
	   <td width="8px" class="bordarShadowLeft"  ></td>
	   
	 </tr>
	</table>
	</td>
		<td width="50%" valign="top">
  	 		<table width="100%" border="0" height="300px" cellspacing="0" cellpadding="0" valign="top"
					 style="background-repeat: repeat-x">
					<tr>
						<td background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>page_Bkg.jpg">
						&nbsp;</td>
						

					</tr>
				</table>
  	 	</td>
  	 	</tr>
	</table>
  </body>
 
</html>
