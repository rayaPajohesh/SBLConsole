<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
  <tr valign="middle">
    <td align="center">
      <table cellpadding="5" cellspacing="5" border="0">
        <tr valign="top">
          <td class="errorsCell">&nbsp;</td>
          <td align="center" valign="middle" >          	
            <span class="errorText">
              <bean:message key="global.noSuchResource"/>
            </span>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>