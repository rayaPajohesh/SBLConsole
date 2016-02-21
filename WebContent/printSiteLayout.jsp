<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" autoFlush="false" buffer="200kb" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <META HTTP-EQUIV="Expires" CONTENT="Sat, 6 May 1995 12:00:00 GMT"/>
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-store, no-cache, must-revalidate"/>
    <META HTTP-EQUIV="Cache-Control" CONTENT="post-check=0, pre-check=0"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
    <link rel="shortcut icon" href="images/favicon.ico" >
    <link rel="icon" href="images/favicon.ico"  >    
    <link href="css/default.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css" title="win2k-cold-1"/>

    <title>
      <bean:message key="loginTitle"/>
    </title>

  </head>
  
  <body class="bodyCenter" >
  <table width="500px" cellspacing="0" border="0" cellpadding="0" align="center" >
  	<tr>

		<td width="20px">&nbsp;</td>
  		<td valign="top">
		    <table  cellpadding="0" cellspacing="0" border="0" align="center" width="100%">
		      <tr>
		        <td align="center" valign="top">
		        	<table cellpadding="0" cellspacing="0" width = "100%" align="center" >
		        		<Tr>
			        		<td align="center" border="0"><tiles:insert attribute="header" flush="false"/></td>
		   	          	</Tr>
		            </table>
		        </td>
		      </tr>
		      <tr valign="top" >
		        <td align="center" valign="top"  height="100%">
		       		<table  cellpadding="0" cellspacing="0" border="1" bordercolor="#d6d6d6" height="100%" width="100%">

		            	<%   
					    	if (request.getAttribute("org.apache.struts.action.ERROR")!= null) 
					    	{
					    %>
					    <tr>
					    	<td align="center">
					        	<table cellpadding="0" cellspacing="0" border="0" class="errorTable">
					            	<tr>
					                	<td class="errorTopCell" valign="bottom"/>
					                </tr>
					                
					                <tr>
					                	<td class="errorMidCell" valign="top">
					                		<img src="images/erroricon.gif" border="0" align="middle"/>
					                        <html:errors/>
					                    </td>
									</tr>
									
					                <tr>
					                	<td class="errorBottomCell"/>
					                </tr>
					                
								</table>
								
							</td>
						</tr>
						
					    <tr>
					    	<td height="10px"></td>
					    </tr>
			
						<%            
							} 
						%>
			
						<%   
							if (request.getAttribute("org.apache.struts.action.ACTION_MESSAGE")!= null) 
							{
						%>
						<tr>
							<td valign="top">
								<table cellpadding="0" cellspacing="0" border="0" class="errorTable">
					            	<tr>
					                	<td class="errorTopCell"  />
					                </tr>
					                <tr>
					                	<td class="errorMidCell" align="center" valign="top">
					                    	<html:messages id="message" message="true" header="messages.header" footer="messages.footer">
					                            <bean:write name="message" filter="false"/>
					                        </html:messages>
										</td>
									</tr>
					                <tr>
					                	<td class="errorBottomCell"/>
					                </tr>
								</table>
							</td>
						</tr>
					    <tr>
					    	<td height="10px"></td>
						</tr>
						<%            
					    	} 
					    %>
						<tr valign="top"  >
							<td valign="top" align="center" width="100%" height="100%" width="600px">
		          				<table width="100%" cellpadding="0" cellspacing="0" border="0" align="left" height="100%"  >
		            				<tr width="100%" height="100%">
										<td align="center" valign="top" height="100%" width="100%"  >
											<tiles:insert attribute="body" flush="false"/>
										</td>
									</tr>
									<tr>
								      	<td height="20px" >
								    	</td>
								    </tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			      		  
		    <tr>
		    	<td align="left"  valign="bottom"  >
		    		<tiles:insert attribute="footer" flush="false"/>
			</td>
			
		</table>
	</td>
</tr>
</table>
 </body>
</html>
