<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
   <head>
      <link href="Back&Menu.css" rel="stylesheet">
      <link href="Table.css" rel="stylesheet">
      <link href="ModalWindow.css" rel="stylesheet">
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

       <form  method="post" onsubmit='redirect();return false;'>
       <c:if test="${not empty inputDate}">
   <div class="change">
                        <div class="modal-window">
                           <div class="changeValues" align="center">

<table>
<h1>Виберіть дату проведення медичного огляду</h1>
                           <tr>
                              <td>Дата Огляду</td>
                              <td><input type="date" name="DateOfCheckUp" required></td>
                           </tr>
                       </table>

                              <input type="submit" value="Готово" name="inputDateOfCheckUp">
                              <input type="submit" value="Скасувати" name="cancelInputingDateOfCheckUp">
                           </div>
                        </div>
                     </div>
                     </c:if>
   </form>

    <form  method="post" action="/checkUp">
            <c:if test="${regime eq 'AddCheckUp'}">
               <div class="change">
                  <div class="modal-window">
                     <div class="changeValues" align="center">
                        <table >
                           <tr>
                              <td>Паціент</td>
                              <td>
                              <select name="newPerson"  required>
                                     <option value="" selected disabled hidden>Виберіть водія</option>
                                        <c:forEach items="${staff}" var="staff">
                                             <option  >${staff.name} ${staff.surname} ${staff.patronymic}</option>
                                    </c:forEach>
                                </select>
                                </td>
                           </tr>

                           <tr>
                              <td>Артеріальний тиск</td>
                              <td> <input type="text" name="newPressure"  required></td>
                           </tr>

                           <tr>
                              <td>Вміст алкоголю в орг.</td>
                              <td><input type="text" name="newPpm"  required></td>
                           </tr>

                           <tr>
                              <td>Самопочуття</td>
                              <td><input type="text" name="newWellBeing"  required></td>
                           </tr>

                           <tr>
                              <td>Нотатки</td>
                              <td><input type="text" name="newNote"></td>
                           </tr>


                                <tr>
                              <td>Медперсонал</td>
                              <td>
                              <select name="newDoctor"  required>
                                     <option value="" selected disabled hidden>Виберіть лікаря</option>
                                        <c:forEach items="${doctor}" var="doctor">
                                             <option  >${doctor.name} ${doctor.surname} ${doctor.patronymic}</option>
                                    </c:forEach>
                                </select>
                                </td>
                           </tr>


                        </table>
                        <input type="submit" name="confirmCheckUp" value="Підтвердити" >
                        <input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
                     </div>
                  </div>
               </div>
            </c:if>
         </form>


<form  method="post"  onsubmit='redirect();return false;'>
         <c:if test="${regime eq 'DeleteCheckUp'}">
            <c:choose>
               <c:when test="${empty changeCheckUpId}">
                  <div class="deleteField">
                     <h1 >Видалення</h1>
                  </div>
               </c:when>
               <c:otherwise>
                  <div class="change">
                     <div class="modal-window">
                        <div class="changeValues" align="center">
                           <h1>Ви дійсно хочете видалити це з бази ?</h1>
                           <input type="submit" value="Так" name="deleteCheckUp">
                           <input type="submit" value="Скасувати" name="cancelDeletingCheckUp">
                        </div>
                     </div>
                  </div>
               </c:otherwise>
            </c:choose>
         </c:if>
      </form>


<form  method="post"  onsubmit='redirect();return false;'>
         <c:if test="${regime eq 'EditCheckUp'}">
            <c:choose>
               <c:when test="${empty changeCheckUpId}">
                  <div class="deleteField">
                     <h1 >Редагування</h1>
                  </div>
               </c:when>
               <c:otherwise>
                  <div class="change">
                     <div class="modal-window">
                        <div class="changeValues" align="center">
                           <table >
                                                     <tr>
                                                        <td>Паціент</td>
                                                        <td>
                                                        <select name="newPerson"  required>
                                                               <option value="" selected disabled hidden>Виберіть водія</option>
                                                                  <c:forEach items="${staff}" var="staff">
                                                                       <option  >${staff.name} ${staff.surname} ${staff.patronymic}</option>
                                                              </c:forEach>
                                                          </select>
                                                          </td>
                                                     </tr>

                                                     <tr>
                                                        <td>Артеріальний тиск</td>
                                                        <td> <input type="text" name="newPressure"  value="${oneCheckUp.pressure}" required></td>
                                                     </tr>

                                                     <tr>
                                                        <td>Вміст алкоголю в орг.</td>
                                                        <td><input type="text" name="newPpm" value="${oneCheckUp.ppm}"  required></td>
                                                     </tr>

                                                     <tr>
                                                        <td>Самопочуття</td>
                                                        <td><input type="text" name="newWellBeing" value="${oneCheckUp.wellBeing}" required></td>
                                                     </tr>

                                                     <tr>
                                                        <td>Нотатки</td>
                                                        <td><input type="text" name="newNote" value="${oneCheckUp.note}"></td>
                                                     </tr>


                                                          <tr>
                                                        <td>Медперсонал</td>
                                                        <td>
                                                        <select name="newDoctor"  required>
                                                               <option value="" selected disabled hidden>Виберіть лікаря</option>
                                                                  <c:forEach items="${doctor}" var="doctor">
                                                                       <option  >${doctor.name} ${doctor.surname} ${doctor.patronymic}</option>
                                                              </c:forEach>
                                                          </select>
                                                          </td>
                                                     </tr>


                                                  </table>
                                                   <input type="submit" name="confirmEdittingCheckUp" value="Підтвердити" >
                       <input type="submit" value="Скасувати" name="cancelDeletingCheckUp" onClick="removeRequired(this.form)">
                        </div>
                     </div>
                  </div>
               </c:otherwise>
            </c:choose>
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


  <div id="div_table">

  <h1>Медичний огляд водіїв за:${date}</h1>

          <table class="table" id="table">
             <tr>
             <thead>
                <th>Паціент</th>
                <th>Артер. тиск</th>
                <th>Вміст алкоголю в орг.</th>
                <th>Самопочуття</th>
                <th>Нотатки</th>
                <th>Медперсонал</th>
             </thead>
             </tr>
             <c:choose>
                <c:when test="${empty regime}">
                   <c:forEach items="${checkUp}" var="checkUp">
                      <tr>
                         <td>${checkUp.NSP}</td>
                         <td>${checkUp.pressure}</td>
                         <td>${checkUp.ppm}</td>
                         <td>${checkUp.wellBeing}</td>
                         <td>${checkUp.note}</td>
                         <td>${checkUp.doctorNSP}</td>
                      </tr>
                   </c:forEach>
                </c:when>
                <c:otherwise>
                   <c:forEach items="${checkUp}" var="checkUp">

                   <c:if test="${regime eq 'DeleteCheckUp'}">
                      <tr onclick="location.href='/checkUp?regime=DeleteCheckUp&id=${checkUp.id}'">
                   </c:if>

                   <c:if test="${regime eq 'EditCheckUp'}">
                      <tr onclick="location.href='/checkUp?regime=EditCheckUp&id=${checkUp.id}'">
                   </c:if>

                         <td>${checkUp.NSP}</td>
                                                  <td>${checkUp.pressure}</td>
                                                  <td>${checkUp.ppm}</td>
                                                  <td>${checkUp.wellBeing}</td>
                                                  <td>${checkUp.note}</td>
                                                  <td>${checkUp.doctorNSP}</td>
                      </tr>
                   </c:forEach>
                </c:otherwise>
             </c:choose>
          </table>
       </div>


        <div class="tooltip">
                <c:choose>
                   <c:when test="${regime eq 'DeleteBus'}">
                      <img id="close" src="resources/close.png" width=35px onClick="location.href='/buspark'"/>
                   </c:when>
                   <c:otherwise>
                      <img class="wrench" src="resources/feature.png" width=35px/>
                      <div class="person create">
                         <img  src="resources/edit.png" width=30px onclick="location.href='/checkUp?regime=AddCheckUp'">
                      </div>
                      <div class="person delete">
                         <img src="resources/trash.png" width=30px onclick="location.href='/checkUp?regime=DeleteCheckUp'">
                      </div>
                      <div class="person edit">
                          <img src="resources/fix.png" width=30px onclick="location.href='/checkUp?regime=EditCheckUp'">
                      </div>

                      <div class="person changeDate">
                          <img src="resources/changeDate.png" width=30px onclick="location.href='/checkUp?regime=ChangeDate'">
                      </div>

                   </c:otherwise>
                </c:choose>
             </div>

   </body>

   </html>