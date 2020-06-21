<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
   <head>
      <link href="Back&Menu.css" rel="stylesheet">
      <link href="ModalWindow.css" rel="stylesheet">
      <link href="Login.css" rel="stylesheet">
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
      <script type="text/javascript" src="js/pop-upMenu.js"></script>
      <!-- Подключение jQuery плагина Masked Input -->
      <script src="js/jquery.maskedinput.min.js"></script>
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
            <c:if test="${empty access}">
               <li class="men" id="four" onclick="location.href='/login'">Ввійти</li>
            </c:if>
            <c:if test="${not empty access}">
               <li class="men" id="four" onclick="location.href='/login?regime=logout'">Вийти</li>
            </c:if>
            <hr id="hr_css">
         </ul>
      </div>
      < c:if test="${regime ne 'logout'}">
      <form  method="post"  onsubmit='redirect();return false;'>
         <div class="main">
            <p class="sign" align="center">Вхід</p>
            <div class="form1">
               <input class="un " type="text" name="login" align="center" placeholder="Username" required>
               <input class="pass" type="password" name="password" align="center" placeholder="Password" required>
               <input type="submit" class="submit" value="Ввійти" name="signIn">
            </div>
         </div>
      </form>
      </c:if>
      < c:if test="${regime eq 'logout'}">
      <form  method="post" onsubmit='redirect();return false;'>
         <c:if test="${regime eq 'logout'}">
            <div class="change">
               <div class="modal-window">
                  <div class="changeValues" align="center">
                     <h1>Ви справді хочете вийти</h1>
                     <input type="submit" value="Так" name="logout">
                     <input type="submit" value="Ні" name="cancellogout">
                  </div>
               </div>
            </div>
         </c:if>
      </form>
      </c:if>
   </body>
</html>