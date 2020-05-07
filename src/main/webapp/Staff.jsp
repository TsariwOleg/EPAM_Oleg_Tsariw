<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <link href="Back&Menu.css" rel="stylesheet">
    <link href="Staff.css" rel="stylesheet">

</head>
    <body>
        <div class="Bar">
        <div class="Bar1">


     <ul>

     <li class="men" id="one">Персонал</li>

      <li class="men" id="two">Автопарк</li>
       <li class="men" id="three">Відділення</a>

         <ul>
           <li id="adm">Адміністрація</li>
           <li id="Dri">Водії</li>
           <li id="Mech">Автомеханіки</li>
           <li id="Doc">Медперсонал</li>
         </ul>

       </li>


       <li class="men" id="four">Ввійти</li>
        <hr id="hr_css">
     </ul>
   </div>
        </div>



<%String ds ="df"; %>
<% ArrayList ar =(ArrayList)request.getAttribute("staff");%>

 <c:if test="${ds eq 'df'}">
sdfsdfsdfsdf
 </c:if>

<h1>${s}</h1>

<div id="lol">

    <c:forEach items="${staff}" var="staffjsp">
    <%= ar %>
    <%= ds %>
    <>

    </c:forEach>
</div>



    </body>
</html>
