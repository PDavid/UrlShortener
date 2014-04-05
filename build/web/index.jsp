<%-- 
    Document   : index
    Created on : Apr 4, 2014, 1:53:47 PM
    Author     : paksyd
--%>

<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Shorten a URL</h3>

<form  role="form" method="post" action="shorten" class="form-inline">
    <div class="form-group">
        <label class="sr-only" for="url">URL</label>
        <input type="text" 
               name="url" 
               id="url" 
               class="form-control urlfield" 
               placeholder="Paste a link to shorten it"/>
        
        <button type="submit" class="btn btn-primary">Shorten</button>
    </div>
</form>

