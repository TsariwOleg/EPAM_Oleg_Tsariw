<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
   <head>
      <link href="Back&Menu.css" rel="stylesheet">
      <link href="Person.css" rel="stylesheet">
      <link href="ModalWindow.css" rel="stylesheet">
      <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
      <script type="text/javascript" src="js/pop-upMenu.js"></script>
      <script>
         function removeRequired(form){
         $.each(form, function(key, value) {
         if ( value.hasAttribute("required")){
                  value.removeAttribute("required");
         }
         });
         }
      </script>
   </head>
   <body>
      <form  method="post" action="/bus?id=${bus.id}">
         <c:if test="${not empty regime}">
            <div class="change">
               <div class="modal-window">
                  <div class="changeValues" align="center">
                     <c:if test="${regime eq 'UpdateInfoBus'}">
                        <table >
                           <tr>
                              <td>Номер автобуса</td>
                              <td><input type="text" name="newBusNo" value="${bus.busNo }" required></td>
                           </tr>
                           <tr>
                              <td>Модель</td>
                              <td><input type="text" name="newModel" value="${bus.model}" required></td>
                           </tr>
                           <tr>
                              <td>Рік випуску автобуса</td>
                              <td> <input type="number" name="newYearOfIssue" value="${bus.yearOfIssue }" required></td>
                           </tr>
                           <tr>
                              <td>Розхід палива</td>
                              <td><input type="text" name="newFuelConsumption" value="${bus.fuelConsumption }" required></td>
                           </tr>
                           <tr>
                              <td>Маршрут</td>
                              <td>
                                 <select name="newRoute" required>
                                    <option value="" selected disabled hidden>Виберіть маршрут</option>
                                    <c:forEach items="${constRoute}" var="constRoute">
                                       <option>${constRoute.route}</option>
                                    </c:forEach>
                                 </select>
                              </td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmBus" value="Підтвердити" >
                        <input type="submit" name="deleteBus" value="Очистити" onClick="removeRequired(this.form)">
                     </c:if>
                     <input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
                  </div>
               </div>
            </div>
         </c:if>
      </form>
      <div class="Bar">
         <ul id="main-ul">
            <li class="men" id="one" onclick="location.href='/staff'">Персонал</li>
            <li class="men" id="two" onclick="location.href='/buspark'">Автопарк</li>
            <li class="men" id="three">
               Відділення</a>
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
      <form method="post" onsubmit='redirect();return false;'>
         <div id="information_about_person_div">
            <div id="information_about_person">
               <table class="table">
                  <tr>
                     <td>Номер автобуса:</td>
                     <td>${bus.busNo}</td>
                  </tr>
                  <tr>
                     <td>Модель:</td>
                     <td>${bus.model}</td>
                  </tr>
                  <tr>
                     <td>Рік випуску:</td>
                     <td>${bus.yearOfIssue}</td>
                  </tr>
                  <tr>
                     <td>Розхід палива:</td>
                     <td>${bus.fuelConsumption}</td>
                  </tr>
                  <tr>
                     <td>
                        <svg height="60" width="200" ></svg>
                     </td>
                  </tr>
                  <tr class="input">
                     <td onclick="location.href='/historyOfRepair?target=bus&id=${bus.id}'">
                        <label >
                           <p class="ntr">Історія ремонтів
                           <p>
                        </label>
                     </td>
                  </tr>
                  <tr class="input">
                     <td onclick="location.href='/bus?id=${bus.id}&regime=UpdateInfoBus'">
                        <label>
                           <img src="resources/edit-48.png" class="icon">
                           <p class="ntr">Редагувати
                           <p>
                        </label>
                     </td>
                  </tr>
               </table>
               <div class="table2">
                  <script type="text/javascript"></script>
                  <hr>
                  <p class="spoiler-title2 closed">Маршрут</p>
                  <div class="spoiler-body2">
                     <table class="table">
                        <tr>
                           <td>Маршрут:</td>
                           <td>${route.fullRoute}</td>
                        </tr>
                        <tr>
                           <td>Середня виручка:</td>
                           <td>${route.avarageProfit}</td>
                        </tr>
                        <tr>
                           <td>Сереній розхід палива:</td>
                           <td>${route.avarageFuelConsuption}</td>
                        </tr>
                     </table>
                  </div>
               </div>
               <div class="table4">
                  <hr>
                  <div class="spoiler-body4">
                  </div>
               </div>
            </div>
         </div>
      </form>
   </body>
</html>