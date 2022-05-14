<%-- 
    Document   : Index
    Created on : 07.12.2020, 09:03:30
    Author     : A-Terra
--%>

<%@page import="Model.Person"%>
<%@page import="Controller.Phonebook"%>
<%@page import="View.GUIController" %>
<%--<%@page import="java.io.IOException"%>--%>
<%--<%@page import="Logic.Phonebook"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    #controlbar {
        display:               grid;
        background-color: lightgrey;
        border:           20px;
        height:                100%;
        grid-template-columns: 1fr 3fr 1fr 1fr 1fr;
    }
    #controlbar *{
        width: 90%;
        height: 90%;
        padding: 5%;
        text-align: center;    
    }
    #inputarea *{
        display: grid;
        margin: 1%;
        grid-template-columns: 10% 90%;
        grid-template-rows: 1fr 1fr 1fr;
        grid-row-gap:1%;
        align-content: center;
        
    }
    #container{
        background-color: lawngreen;
        border:           black solid 2px;
    }
    #btnSend{
        grid-column:1/span 2;
        width: 10%;
        
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phonebook</title>
    </head>
    <body>
        <%
            if (request.getParameter("<") != null){
                GUIController.getInstance().getPrevious();
            }
            if (request.getParameter("X") != null){
                GUIController.getInstance().deleteRecord();
            }
            if (request.getParameter("*") != null){
                GUIController.getInstance().addnew();
            }
            if (request.getParameter(">") != null){
                GUIController.getInstance().getSubsequent();
            }
            if (request.getParameter("Send") != null) {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                GUIController.getInstance().saveRecord(name, email, phone);
            }
        %>
        <div id="container">
            <section id="controlbar">
                <form method="post">
                    <input  type="submit" name="<" value="<" <%out.print(GUIController.getInstance().isPreviousenabled());%> >
                </form>
                <div>
                    <%
                       out.print(GUIController.getInstance().getDescription());
                    %>
                </div>
                <form method="post">
                    <input onclick="if (confirm('Are you sure you want to delete?')){ form.action='index.jsp'}else{return false}" type="submit" name="X" value="X" <%out.print(GUIController.getInstance().isDeleteenabled());%> >
                </form>
                <form method="post">
                    <input  type="submit" name="*" value="*">
                </form>
                <form method="post">
                    <input  type="submit" name=">" value=">" <%out.print(GUIController.getInstance().isSubsequentenabled());%> >
                </form>
            </section>
            <section id="inputarea">
                <form action="index.jsp" method="post">
                    <label for="name" >Name: </label>
                    <input id=name name="name" value="<%out.print(GUIController.getInstance().getName());%>" >
                    <label for="email">Email: </label>
                    <input id="email" name="email" value="<%out.print(GUIController.getInstance().getEmail());%>" >
                    <label for="phone">Phone: </label>
                    <input id="phone" name="phone" value="<%out.print(GUIController.getInstance().getPhone());%>" >
                    <input  type="submit" id="btnSend"  name="Send" value="Save" di>
                </form>
            </section>
        </div>
    </body>
</html>
