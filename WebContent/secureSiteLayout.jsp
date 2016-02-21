<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" autoFlush="false" buffer="200kb" %>
<%@ page import="com.iac.ambit.utils.Constants"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>


<c:set var="locale" value="${sessionScope.struts_action_language}" />
<c:set var="Persian" value="fa" />
<c:set var="English" value="en" />
<html >
<script type="text/javascript" src="javascripts/automaticLogout.js"></script>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    
    <link rel="shortcut icon" href="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>favicon.ico" >
    <link rel="icon" href="<%=  session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>favicon.ico"  >    
    <link href="<%= session.getAttribute( Constants.JspConstants.PATH_CSS  )%>default.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" media="all" href="<%= session.getAttribute( Constants.JspConstants.PATH_CSS  )%>calendar-win2k-cold-1.css" title="win2k-cold-1"/>
    

    
    <title>
      <bean:message key="loginTitle"/>
    </title>
    <SCRIPT LANGUAGE="JavaScript">
				var w=20;
				function access(){
				sWidth=window.screen.width;
				sHeight=0;
				//sHeight=window.screen.height-185;
				cWidth=770;
				shadowWidth=24;
				patternWidth=(sWidth-(cWidth+2*shadowWidth))/2;
				if (patternWidth<0){
					patternWidth=0
				}
				var existingTable=document.all.mubz.innerHTML;
				document.all.mubz.innerHTML="<table width='100%' height='100%' border='0' align='center' cellpadding='0' cellspacing='0'>  <tr><td width='"+patternWidth+"' background='images/PatternLeft.jpg' style='background-position:right'><div align='right'></div></td><td width='"+shadowWidth+"' background='images/PatternLeftShadow.jpg' style='background-position:right'></td><td width='"+cWidth+"' bgcolor='#FFFFFF'><div align='left'></div>"+existingTable+"</td><td width='"+shadowWidth+"' background='images/PatternRightShadow.jpg'></td><td width='"+patternWidth+"'  background='images/PatternLeft.jpg'><div align='left'></div></td> </tr></table>";
				}
			history.go(+1); 

				
	</SCRIPT>
  </head>
  <body  onunload="logOutWhenIEClose();" class="bodyCenter mainTable"  id="mubz"  >
   <table height="100%" width="100%" cellspacing="0" border="0" cellpadding="0" align="center" >
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
  <table height="100%" width="903px" cellspacing="0" border="0" cellpadding="0.3" align="center" >
  	<tr>
  		<td width="8px" class="bordarShadowLeft"></td>
  		<td>
		    <table class="globalTable"  cellpadding="0" cellspacing="0" border="0" align="center">
		      <tr>
		        <td align="center" valign="top" colspan="3" height="130px">
		        	<table cellpadding="0" cellspacing="0" width = "100%" >
		        		<Tr>
			        		<td ><tiles:insert attribute="header" flush="false"/></td>
		   	          	</Tr>
		            </table>
		        </td>
		      </tr>
		      <tr valign="top" >
		        <td align="center" valign="top" colspan="3" height="100%">
		       		<table  cellpadding="0" cellspacing="0" border="0" height="100%" width="100%">
		       		<tr><TD>
		        	<table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%" >
		        		<tr>
		        			<td  valign="top"  >
		          				<table cellpadding="0" cellspacing="0" border="0" width="180px" height="100%">
		            				<tr valign="top" height="100%">
		              					<td valign="top"  bgcolor="#e2e7eb">
		                					<tiles:insert attribute="menu" flush="false"/>
		              					</td>
		            				</tr>
		          				</table>
		        			</td>
		        			<td align="left" valign="top"  height="100%" width="813px;"class="bodyTable">
		          				<table cellpadding="0" cellspacing="0" border="0" height="100%" >
		          				<tr >
		          					<td  >
		          						<table cellpadding="0.7" cellspacing="1" border="0" height="100%" width="100%">
		          						<tr>
		          				    
									   
								    		<td   width="10%"></td>
									    <td  valign="top"><table background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg" width="100%" border="0"  cellspacing="0" cellpadding="0">
									        <tr >
									          
									          	<td  ><img src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_2.jpg"></td>
									             <td  class="HeaderUserNameLabel" ><%= session.getAttribute("LABEL_PARAMETER")%>								          
										          <c:choose >
											        <c:when test="${sessionScope.CUSTOMER_IN_SESSION != null }">	          
											           <%=session.getAttribute("VALUE_PARAMETER")%>
											        </c:when>
										        	<c:otherwise>&nbsp;</c:otherwise>
										          </c:choose>
									          </td>
									          <td    class='HeaderLabels'  align="center" >
									          
									          	<a href="#" style="text-decoration : none;font-weight: bold" onclick="logOut();">
									        			<bean:message key="global.logout"/>
									        			</a>
									          </td>
									          <td background="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>Footer_1.jpg" width="30px"></td>
									        </tr>
									      </table>
									     </td>
									 </tr>
								  </table>
								 </td>
							   </tr>

		            				 <%   
					                if (request.getAttribute("org.apache.struts.action.ERROR")!= null) 
					                {
					            %>
					            <tr>
					              <td valign="top">
					                <tr>
					                  <td>&nbsp;</td>
					                </tr>
					                <tr>
					                  <td align="center">
					                    <table cellpadding="0" cellspacing="0" border="0" class="errorTable">
					                      <tr>
					                        <td class="errorTopCell" valign="bottom"/>
					                      </tr>
					                      <tr>
					                        <td class="errorMidCell" valign="top">
					                          <img src="<%= session.getAttribute( Constants.JspConstants.PATH_IMAGE  )%>erroricon.gif" border="0" align="middle"/>
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
					                  <td>&nbsp;</td>
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
					                <tr>
					                  <td>&nbsp;</td>
					                </tr>
					                <tr>
					                  <td align="center">
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
					                  <td>&nbsp;</td>
					                </tr>
					              
					           
					            <%            
					                } 
					            %>
									<tr valign="top">
										<td valign="top" align="left" width="100%" class="contentCell" height="100%" >
		          							<table width="100%" cellpadding="0" cellspacing="2" border="0" align="left" height="100%"  >
		            							<tr width="100%" height="100%">
												  <td align="left" valign="top" height="100%" width="713px"  >
													<tiles:insert attribute="body" flush="false"/>
												  </td>
												</tr>
											</table>
										</td>
									</tr>
								  </table>
								</td>
							</tr>
						</table>
						</td>
						</tr>
						</table>
					</td>
			      </tr>		  
		      	  <tr>
		          	<td align="left"  colspan="3" valign="top"  >
		          		<tiles:insert attribute="footer" flush="false"/>
		        	</td>
		      	   </tr>
		    	</table>
		    </td>
	   <td width="8px" class="bordarShadowRight"></td>
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
  	 	<tr>
	</table>
  </body>
  <head>
  

    <META HTTP-EQUIV="Expires" CONTENT="Sat, 6 May 1995 12:00:00 GMT"/>
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-store, no-cache, must-revalidate"/>
    <META HTTP-EQUIV="Cache-Control" CONTENT="post-check=0, pre-check=0"/>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache"/>
    </head>
</html>

