<%@ page contentType="application/json; charset=UTF-8"%> 

<jsp:useBean id="jsonPersons" class="ajaxExample.JsonPersons"/>

<%
	out.println(jsonPersons.getPersons());
	out.flush();
%>