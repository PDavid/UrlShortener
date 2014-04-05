<%-- 
    Document   : result
    Created on : Apr 4, 2014, 1:13:18 PM
    Author     : paksyd
--%>

<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Result</h1>

<p>
    Your shortened url is ready: 
</p>

<a href="${result}">
    <c:out value="${result}"/>
</a>


    