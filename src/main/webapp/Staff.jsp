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

     <ul id="main-ul">

     <li class="men" id="one" onclick="location.href='/staff'">Персонал</li>
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






<div id="div_table_staff">
<table class="qw" id="table_staff">
<tr>
<thead>
   <th>Ім`я</th>
   <th>Прізвище</th>
   <th>По-батькові</th>
   <th>Вік</th>
   <th>Відділення</th>
</thead>
</tr>

    <c:forEach items="${staff}" var="staffjsp">
<tr onclick="location.href='/person?id=${staffjsp.id}'">
   <td>${staffjsp.name}</td>
   <td>${staffjsp.surname}</td>
   <td>${staffjsp.patronymic}</td>
   <td>${staffjsp.age}</td>
   <td>${staffjsp.department}</td>
</tr>

    </c:forEach>

</table>
</div>



    </body>
</html>
