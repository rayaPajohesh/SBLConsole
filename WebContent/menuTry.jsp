<%@ page import="java.util.Iterator" %>
<%@ page import="com.iac.ambit.utils.Config" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:importAttribute />


<tiles:useAttribute id="cssPath" name="cssPath" scope="page" />


<link href="<%=cssPath%>" rel="stylesheet" type="text/css"/>

<script language="javascript">
	var mainMenuIds = new Array();
	var num = 0;
</script>

<tiles:useAttribute id="items" name="items" scope="page" classname="java.util.List"/>
<div id="main_menu" style="height: 350px; width: 100%">

		<%		 
				Iterator it = items.iterator();
				
				int count = 0;
				while(it.hasNext()) {
					String name = (String)it.next();
					count ++;
					
			%>
			
				<tiles:insert name="<%=name%>" flush="false" />
			<%  } %>

</div>
<form method="post" name="navigationForm" action="">
  <input type="HIDDEN" name="navigationFormTitle" id="navigationFormTitle"/>
</form>

<script language="javascript">


	<%
		String navigationFormTitle = request.getParameter("navigationFormTitle");
		if(navigationFormTitle!=null)
			session.setAttribute("navigationFormTitle",navigationFormTitle);
		else
		    navigationFormTitle = (String)session.getAttribute("navigationFormTitle");
		    
		    
	%>

	function menuInit(){
		var id = '<%= navigationFormTitle %>';
		if(id=='null') return;
		var temp = id.split('_');
		changeMenu(temp[0]);
		try {
			
			menuItem = document.getElementById("menu_item_"+id);
			menuItem.innerHTML = "<B>»</B> " + menuItem.innerHTML;
		} catch(ex){
		}
	}

	function changeMenu(id,countItem) {
	
		for(var i=0;i<<%=count%>;i++) {
		
			menuItem = document.getElementById("menu_item_" + mainMenuIds[i]);
			if(menuItem!=null) {
				if(mainMenuIds[i]==id){
					menuItem.style.display = 'block';
					if(countItem!=null && countItem!=''){
					var fix = "";
					var sizeSubmenu="";
					fix='<%=Config.getProperty("subMenu.Size")%>' ;
					sizeSubmenu = countItem * fix;
					menuItem.style.height = sizeSubmenu;
					}
				}
				else {
					menuItem.style.display = 'none';
				}
			}
		}
	}
	
	function setActiveMenu(id, link,count) {
	
		var temp = id.split('_');
		var items = count;	
		
		changeMenu(temp[0],items);
		document.navigationForm.navigationFormTitle.value = id;
		document.navigationForm.action = link; 
		if(link!=null && link!='')
			document.navigationForm.submit();
	}

	menuInit();

</script>