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
         if ( value.hasAttribute("required")||value.hasAttribute("min")){
                  value.removeAttribute("required");
                value.removeAttribute("min");
         }
         });
         }



      </script>
   </head>
   <body>

    <c:if test="${access eq '1' || access eq '0'}">
         <form  method="post" action="/route?id=${route.id}">
            <c:if test="${not empty regime}">
               <div class="change">
                  <div class="modal-window">
                     <div class="changeValues" align="center">
                        <c:if test="${regime eq 'UpdateInfoRoute'}">
                           <table >
                              <tr>
                                 <td>Назва маршрута</td>
                                 <td><input type="text" name="newRoute" value="${route.route }" required></td>
                              </tr>
                              <tr>
                                 <td>Повний маршрут</td>
                                 <td><textarea class="textarea" rows="4" name="newFullRoute" value="${route.fullRoute}" required></textarea></td>
                                </tr>

                              <tr>
                                 <td>Середня виручка в день</td>
                                 <td> <input type="number"  name="newAvarageProfit" value="${route.avarageProfit }" required></td>
                              </tr>



                              <tr>
                                 <td>Середній розхід палива в день</td>
                                 <td><input type="text"  name="newAvarageFuelConsumption" value="${route.avarageFuelConsuption }" required></td>
                              </tr>


                           </table>
                           <input type="submit" name="confirmRoute" value="Підтвердити" >
                           <input type="submit" name="deleteRoute" value="Очистити" onClick="removeRequired(this.form)">
                        </c:if>
                        <input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
                     </div>
                  </div>
               </div>
            </c:if>
         </form>
         </c:if>




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

 <form method="post" onsubmit='redirect();return false;'>
         <div id="information_about_person_div">
            <div id="information_about_person">
               <table class="table">
                  <tr>
                                      <td>Назва маршруту:</td>
                                      <td>${route.route}</td>
                                   </tr>
                  <tr>
                     <td>Маршрут:</td>
                     <td>${route.fullRoute}</td>
                  </tr>
                  <tr>
                     <td>Середня виручка за день:</td>
                     <td>${route.avarageProfit}</td>
                  </tr>
                  <tr>
                     <td>Розхід палива за день:</td>
                     <td>${route.avarageFuelConsuption}</td>
                  </tr>

                  <tr>
                     <td>
                        <svg height="60" width="200" ></svg>
                     </td>
                  </tr>

                  <c:if test="${access eq '1' || access eq '0'}">

                  <tr class="input">
                     <td onclick="location.href='/route?id=${route.id}&regime=UpdateInfoRoute'">
                        <label>
                           <img src="resources/edit-48.png" class="icon">
                           <p class="ntr">Редагувати
                           <p>
                        </label>
                     </td>
                  </tr>
                  </c:if>
               </table>

               <div class="table2">
                  <script type="text/javascript"></script>
                  <hr>
                  <p class="spoiler-title2 closed">Автобуси ,які прив'язані до маршруту</p>
                  <div class="spoiler-body2">
                     <table class="table">
                       <c:forEach items="${buses}" var="buses">

                        <tr class="inputBus" onclick="location.href='/bus?id=${buses.id}'">
                           <td><label>

                                                      <p class="ntr">${buses.busNo}<p>
                                                   </label>
                        </td>
                        </tr>


  <tr>
                     <td>
                        <svg height="6" width="0" ></svg>
                     </td>
                  </tr>

                        </c:forEach>
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
