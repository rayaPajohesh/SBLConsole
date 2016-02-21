<%@ page import="com.iac.ambit.utils.*"%>

<%

if ( ( request.getSession(  ).getAttribute( Constants.CUSTOMER_IN_SESSION ) == null ) )
{
	//request.getSession().getServletContext().getInitParameter("");
    RequestDispatcher rd = request.getRequestDispatcher( "" );

    rd.forward( request, response );

}
else {

    RequestDispatcher rd = request.getRequestDispatcher( "pageNotFound.do" );

    rd.forward( request, response );
    
}
%>

