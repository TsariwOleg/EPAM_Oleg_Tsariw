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
      <form  method="post" action="/person?id=${staff.id}">
         <c:if test="${not empty regime}">
            <div class="change">
               <div class="modal-window">
                  <div class="changeValues" align="center">
                     <c:if test="${regime eq 'UpdateInfoAboutPers'}">
                        <table >
                           <tr>
                              <td>Ім`я</td>
                              <td><input type="text" name="newName" value="${staff.name }" required></td>
                           </tr>
                           <tr>
                              <td>Прізвище</td>
                              <td><input type="text" name="newSurname" value="${staff.surname }" required></td>
                           </tr>
                           <tr>
                              <td>По-батькові</td>
                              <td> <input type="text" name="newPatronymic" value="${staff.patronymic }" required></td>
                           </tr>
                           <tr>
                              <td>Вік</td>
                              <td><input type="number" min="16" max="70" name="newAge" value="${staff.age }" required></td>
                           </tr>
                           <tr>
                              <td>Відділення</td>
                              <td>
                                 <select name="newDepartment"  required>
                                    <option value="" selected disabled hidden>Виберіть відділення</option>
                                    <c:forEach items="${departments}" var="department">
                                       <option  >${department.department}</option>
                                    </c:forEach>
                                 </select>
                              </td>
                           </tr>
                           <tr>
                              <td>Посада</td>
                              <td>
                                 <select name="newPosition" required>
                                    <option value="" selected disabled hidden>Виберіть посаду</option>
                                    <c:forEach items="${positions}" var="position">
                                       <option  >${position.position}</option>
                                    </c:forEach>
                                 </select>
                              </td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmPerson" value="Підтвердити" >
                        <input type="submit" name="deletePerson" value="Очистити" onClick="removeRequired(this.form)">
                     </c:if>
                     <c:if test="${regime eq 'UpdateInfoPassport'}">
                        <table>
                           <tr>
                              <td>Дата народження</td>
                              <td><input type="date" name="newDateOfBirth" value="${passport.dateOfBirth}" required></td>
                           </tr>
                           <tr>
                              <td>Країна народження</td>
                              <td><input type="text" name="newCountryOfBirth" value="${passport.countryOfBirth}" required></td>
                           </tr>
                           <tr>
                              <td>Область народження</td>
                              <td><input type="text" name="newRegionOfBirth" value="${passport.regionOfBirth}" required></td>
                           </tr>
                           <tr>
                              <td>Місце народження</td>
                              <td><input type="text" name="newCityOfBirth" value="${passport.cityOfBirth}" required></td>
                           </tr>
                           <tr>
                              <td>Номер документа</td>
                              <td><input type="number" name="newDocumentNo" value="${passport.documentNo}" required></td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmPassport" value="Підтвердити">
                        <input type="submit" name="deletePassport" value="Очистити" onClick="removeRequired(this.form)">
                     </c:if>
                     <c:if test="${regime eq 'UpdateInfoTaxpayerCard'}">
                        <table>
                           <tr>
                              <td>Місце народження</td>
                              <td><input type="number" name="newTaxpayerNumber" value="${taxpayerCard.taxpayerNumber}" required></td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmTaxpayerCard" value="Підтвердити">
                        <input type="submit" name="deleteTaxpayerCard" value="Очистити" onClick="removeRequired(this.form)">
                     </c:if>
                     <c:if test="${regime eq 'UpdateInfoMedicalBook'}">
                        <table>
                           <tr>
                              <td>Дата проходження медогляду</td>
                              <td><input type="date" name="newDateOfMedicalExam" value="${medicalBook.dateOfMedicalExam}" required></td>
                           </tr>
                           <tr>
                              <td>Дата наступного медогляду</td>
                              <td><input type="date" name="newDateOfNextMedicalExam" value="${medicalBook.dateOfNextMedicalExam}" required></td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmMedicalBook" value="Підтвердити">
                        <input type="submit" name="deleteMedicalBook" value="Очистити" onClick="removeRequired(this.form)" required>
                     </c:if>
                     <c:if test="${regime eq 'UpdateInfoOther'}">
                        <table>
                           <tr>
                              <td>Робочі години</td>
                              <td>
                                 <select name="newStartWorkHour" required>
                                    <option value="" selected disabled hidden>Виберіть графік роботи</option>
                                    <c:forEach items="${workHours}" var="workHours">
                                       <option>${workHours.startWorkHour}-${workHours.endWorkHour}</option>
                                    </c:forEach>
                                 </select>
                              </td>
                           </tr>
                           <tr>
                              <td>Робочий автобус</td>
                              <td>
                                 <select name="newBus" required>
                                    <option value="" selected disabled hidden>Виберіть робочий автобус</option>
                                    <c:forEach items="${workBuses}" var="workBuses">
                                       <option >${workBuses.busNo}</option>
                                    </c:forEach>
                                 </select>
                              </td>
                           </tr>
                        </table>
                        <input type="submit" name="confirmBusDriver" value="Підтвердити">
                        <input type="submit" name="deleteBusDriver" value="Очистити" onClick="removeRequired(this.form)">
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
      <form method="post" onsubmit='redirect();return false;'  enctype="multipart/form-data">
         <div id="information_about_person_div">
            <div id="information_about_person">
               <div class="image">
                  <h3>Фотографія робітника</h3>
                  <c:choose>
                     <c:when test="${empty staff.photo}">
                        <img class="img" src="resources/empty.jpg"/>
                     </c:when>
                     <c:otherwise>
                        <img class="img" src="data:image/jpg;base64,${staff.photo}"/>
                     </c:otherwise>
                  </c:choose>
                  <ul class="input-file">
                     <li>  <input type="file" name="staff-img" accept="image/*">  </li>
                     <li>  <input type="submit" name="staff-button" >  </li>
                  </ul>
               </div>
               <table class="table">
                  <tr>
                     <td>Ім`я:</td>
                     <td>${staff.name}</td>
                  </tr>
                  <tr>
                     <td>Прізвище:</td>
                     <td>${staff.surname}</td>
                  </tr>
                  <tr>
                     <td>По-батькові:</td>
                     <td>${staff.patronymic}</td>
                  </tr>
                  <tr>
                     <td>Вік:</td>
                     <td>${staff.age}</td>
                  </tr>
                  <tr>
                     <td>Відділення:</td>
                     <td>${department.department}</td>
                  </tr>
                  <tr>
                     <td>Посада:</td>
                     <td>${positionOfPerson.position}</td>
                  </tr>
                  <tr>
                     <td>Заробітна плата роб. :    </td>
                     <td>${positionOfPerson.salary}</td>
                  </tr>
                  <tr>
                     <td>
                        <svg height="60" width="0" ></svg>
                     </td>
                  </tr>
                  <c:if test="${department.department eq 'Автомеханіки'}">
                     <tr class="input">
                        <td onclick="location.href='/historyOfRepair?target=carMechanic&id=${staff.id}'">
                           <label >
                              <p class="ntr">Історія ремонтів
                              <p>
                           </label>
                        </td>
                     </tr>
                     <tr>
                        <td>
                           <svg height="20" width="0" ></svg>
                        </td>
                     </tr>
                  </c:if>
                  <tr class="input">
                     <td onclick="location.href='/person?id=${staff.id}&regime=UpdateInfoAboutPers'">
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
                  <p class="spoiler-title2 closed">Паспорт</p>
                  <div class="spoiler-body2">
                     <div class="image">
                        <h3>Скан документа</h3>
                        <c:choose>
                           <c:when test="${empty passport.scannedPassport}">
                              <img class="img" src="resources/empty.jpg"/>
                           </c:when>
                           <c:otherwise>
                              <img class="img" src="data:image/jpg;base64,${passport.scannedPassport}" />
                           </c:otherwise>
                        </c:choose>
                        <ul class="input-file">
                           <li>  <input type="file" name="passport-img"  accept="image/*" >  </li>
                           <li>  <input type="submit" name="passport-button">  </li>
                        </ul>
                     </div>
                     <table class="table">
                        <tr>
                           <td>Дата народження:</td>
                           <td>${passport.dateOfBirth}</td>
                        </tr>
                        <tr>
                           <td>Країна народження:</td>
                           <td>${passport.countryOfBirth}</td>
                        </tr>
                        <tr>
                           <td>Область народження:</td>
                           <td>${passport.regionOfBirth}</td>
                        </tr>
                        <tr>
                           <td>Місто народження:</td>
                           <td>${passport.cityOfBirth}</td>
                        </tr>
                        <tr>
                           <td>Номер документа:</td>
                           <td>${passport.documentNo}</td>
                        </tr>
                        <tr>
                           <td>
                              <svg height="60" width="0" ></svg>
                           </td>
                        </tr>
                        <tr class="input">
                           <td onclick="location.href='/person?id=${staff.id}&regime=UpdateInfoPassport'">
                              <label>
                                 <img src="resources/edit-48.png" class="icon">
                                 <p class="ntr">Редагувати
                                 <p>
                              </label>
                           </td>
                        </tr>
                     </table>
                  </div>
               </div>
               <div class="table3">
                  <script type="text/javascript"></script>
                  <hr>
                  <p class="spoiler-title3 closed">Картка платника податків</p>
                  <div class="spoiler-body3">
                     <div class="image">
                        <h3>Скан документа</h3>
                        <c:choose>
                           <c:when test="${empty taxpayerCard.scannedTaxpayerCard}">
                              <img class="img" src="resources/empty.jpg" />
                           </c:when>
                           <c:otherwise>
                              <img class="img" src="data:image/jpg;base64,${taxpayerCard.scannedTaxpayerCard}"/>
                           </c:otherwise>
                        </c:choose>
                        <ul class="input-file">
                           <li>  <input type="file" name="taxpayerCard-img" accept="image/*">  </li>
                           <li>  <input type="submit" name="taxpayerCard-button">  </li>
                        </ul>
                     </div>
                     <table class="table">
                        <tr>
                           <td>Номер карти платника:</td>
                           <td>${taxpayerCard.taxpayerNumber}</td>
                        </tr>
                        <tr>
                           <td>
                              <svg height="60" width="0" ></svg>
                           </td>
                        </tr>
                        <tr class="input">
                           <td onclick="location.href='/person?id=${staff.id}&regime=UpdateInfoTaxpayerCard'">
                              <label>
                                 <img src="resources/edit-48.png" class="icon">
                                 <p class="ntr">Редагувати
                                 <p>
                              </label>
                           </td>
                        </tr>
                     </table>
                  </div>
               </div>
               <div class="table4">
                  <script type="text/javascript"></script>
                  <hr>
                  <p class="spoiler-title4 closed">Медична книжка</p>
                  <div class="spoiler-body4">
                     <table class="table">
                        <tr>
                           <td>Дата проходження медогляду:</td>
                           <td>${medicalBook.dateOfMedicalExam}</td>
                        </tr>
                        <tr>
                           <td>Дата наступного медогляду:</td>
                           <td>${medicalBook.dateOfNextMedicalExam}</td>
                        </tr>
                        <tr>
                           <td>
                              <svg height="60" width="0" ></svg>
                           </td>
                        </tr>
                        <tr class="input">
                           <td onclick="location.href='/person?id=${staff.id}&regime=UpdateInfoMedicalBook'">
                              <label>
                                 <img src="resources/edit-48.png" class="icon">
                                 <p class="ntr">Редагувати
                                 <p>
                              </label>
                           </td>
                        </tr>
                     </table>
                  </div>
               </div>
               <c:if test="${department.department eq 'Водії'}">
                  <div class="table2">
                     <script type="text/javascript"></script>
                     <hr>
                     <p class="spoiler-title2 closed">Більше</p>
                     <div class="spoiler-body2">
                        <div class="image">
                           <h3>Скан посвідчення водія</h3>
                           <c:choose>
                              <c:when test="${empty busDriver.driverLicense}">
                                 <img class="img" src="resources/empty.jpg"/>
                              </c:when>
                              <c:otherwise>
                                 <img class="img" src="data:image/jpg;base64,${busDriver.driverLicense}" />
                              </c:otherwise>
                           </c:choose>
                           <ul class="input-file">
                              <li>  <input type="file" name="driverLicense-img"  accept="image/*" >  </li>
                              <li>  <input type="submit" name="driverLicense-button">  </li>
                           </ul>
                        </div>
                        <table class="table">
                           <tr>
                              <td>Початок роб. дня:</td>
                              <td>${busDriver.startWorkHours}</td>
                           </tr>
                           <tr>
                              <td>Кінець роб. дня:</td>
                              <td>${busDriver.endWorkHours}</td>
                           </tr>
                           <tr>
                              <td>Робочий автобус:</td>
                              <td>${busDriver.workBus}</td>
                           </tr>
                           <tr>
                              <td>
                                 <svg height="60" width="0" ></svg>
                              </td>
                           </tr>
                           <tr class="input">
                              <td onclick="location.href='/person?id=${staff.id}&regime=UpdateInfoOther'">
                                 <label>
                                    <img src="resources/edit-48.png" class="icon">
                                    <p class="ntr">Редагувати
                                    <p>
                                 </label>
                              </td>
                           </tr>
                        </table>
                     </div>
                  </div>
               </c:if>
               <div class="table4">
                  <hr>
                  <div class="spoiler-body4">
                  </div>
               </div>
            </div>
         </div>
      </form>
      <script type="text/javascript">$('.img').litezoom({speed:400, viewTitle:true});</script>
   </body>
</html>