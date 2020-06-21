<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
   <head>
      <link href="Back&Menu.css" rel="stylesheet">
      <link href="Person.css" rel="stylesheet">
      <link href="ModalWindow.css" rel="stylesheet">
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
      <script type="text/javascript" src="js/litezoom.js"></script>
      <script type="text/javascript" src="js/pop-upMenu.js"></script>
      <script>
         function removeRequired(form){
         $.each(form, function(key, value) {
         if ( value.hasAttribute("required") || value.hasAttribute("min")){
                  value.removeAttribute("required");
                  value.removeAttribute("min");
         }
         });
         }
      </script>
   </head>
   <body>

      <div class="Bar">
         <ul id="main-ul">
            <li class="men" id="one" onclick="location.href='/staff'">Персонал</li>
            <li class="men" id="two" onclick="location.href='/buspark'">Автопарк</li>
            <li class="men" id="three" onclick="location.href='/department'">Відділення</li>
            <li class="men" id="four" onclick="location.href='/login'">Ввійти</li>
            <hr id="hr_css">
         </ul>
      </div>

      <form method="post" onsubmit='redirect();return false;'  enctype="multipart/form-data">
         <div id="information_about_person_div">
            <div id="information_about_person">
               <svg height="15%"  ></svg>
               <h1 align="center">Доступ заборонено</h1>
               <svg height="15%"  ></svg>
            </div>
         </div>
      </form>
   </body>
</html>