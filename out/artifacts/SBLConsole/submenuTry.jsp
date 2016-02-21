<%@ page import="java.util.List"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<tiles:importAttribute />
<tiles:useAttribute id="id" name="id" scope="page" />
<tiles:useAttribute id="subitemCount" name="subitemCount" scope="page" />

<script language="javascript">
	mainMenuIds[num++] = "<%=id%>";
	
</script>
<%
  List MENU_LIST = (List) session.getAttribute("MENU_LIST");
  if (MENU_LIST != null) {
%>

	<%
	  if (MENU_LIST.contains(id)) {
	  
	%>
	
	
		<logic:present name="link">
			<tiles:useAttribute id="link" name="link" scope="page" />
			<div  class="mainItem" onClick="setActiveMenu('<%=id%>', '<%= link %>',<tiles:getAsString name="subitemCount" />);">
			<tiles:getAsString name="title" />
			</div>
		</logic:present>
		<logic:notPresent name="link">
			<div  class="mainItem" onClick="setActiveMenu('<%=id%>', '',<tiles:getAsString name="subitemCount" />);">
			<tiles:getAsString name="title" />
			</div>
		</logic:notPresent>
		
		
		
		
		
		
		<logic:present name="items">
			<div style="display: none;" id="menu_item_<%=id%>">
				<table>
					<logic:iterate id="item" name="items" type="com.iac.ambit.model.MenuItem">
						<%if (MENU_LIST.contains(item.getId())) {
								 
								%>
						<tr>
							<td class="subItem">
								<div onClick="setActiveMenu('<%=id%>_<%=item.getId()%>', '<%= item.getLink() %>',<tiles:getAsString name="subitemCount" />);">
									<a class="subItemLink" href="#" id="menu_item_<%=id%>_<%=item.getId()%>"><%=item.getValue()%></a>
								</div>
							</td>
						</tr>
						<%}
		
						%>
					</logic:iterate>
				</table>
			</div>
		</logic:present>

	<%
	  }
	%>
<%
  }
%>
