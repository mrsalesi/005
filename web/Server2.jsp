<%@page import="cms.tools.ServerLog"%>
<%@page import="cms.tools.Server"%>
<%
    ServerLog.Print("~~~~"+request.getRequestURL());//for debuging
    request.getRequestDispatcher(Server.mainPage).forward(request, response);
%>
