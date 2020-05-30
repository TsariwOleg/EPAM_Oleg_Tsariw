<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<html>
	<head>
		<link href="Back&Menu.css" rel="stylesheet">
		<link href="Staff.css" rel="stylesheet">
		<link href="ModalWindow.css" rel="stylesheet">
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

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
    <form  method="post" action="/buspark">
    			<c:if test="${regime eq 'AddBus'}">
    				<div class="change">
    					<div class="modal-window">
    						<div class="changeValues" align="center">

    							<table >

    								<tr>
    									<td>Номер автобуса</td>
    									<td><input type="text" name="newBusNo" required></td>
    								</tr>

    								<tr>
    									<td>Модель</td>
    									<td> <input type="text" name="newModel"  required></td>
    								</tr>

    								<tr>
    									<td>Рік випуску</td>
    									<td><input type="number" name="newYear"  required></td>
    								</tr>

    								<tr>
    									<td>Маршрут</td>
    									<td><select name="newRoute"  required>
    										<option value="" selected disabled hidden>Виберіть маршрут</option>
    										<c:forEach items="${route}" var="route">
    											<option  >${route.route}</option>
    										</c:forEach>
    									</select></td>
    								</tr>

    							</table>
    							<input type="submit" name="confirmBus" value="Підтвердити" >


    							<input type="submit" value="Скасувати" onClick="removeRequired(this.form)">
    						</div>
    					</div>
    				</div>
    			</c:if>

    		</form>


    		<form  method="post"  onsubmit='redirect();return false;'>
    			<c:if test="${regime eq 'DeleteBus'}">


    				<c:choose>
    					<c:when test="${empty deleteBusId}">
    						<div class="deleteField">
    							<h1 >Видалення</h1>
    						</div>
    					</c:when>
    					<c:otherwise>
    						<div class="change">
    							<div class="modal-window">
    								<div class="changeValues" align="center">
    									<h1>Ви дійсно хочете видалити цей автобус з бази?</h1>
    									<input type="submit" value="Так" name="deleteBus">
    									<input type="submit" value="Скасувати" name="cancelDeletingBus">
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
        		<table class="table" id="table_staff">
        			<tr>
        				<thead>
        					<th>Номер автобуса</th>
        					<th>Модель</th>
        					<th>Рік випуску</th>
        					<th>Маршрут</th>
        				</thead>
        			</tr>
        			<c:choose>
        				<c:when test="${empty regime}">
        					<c:forEach items="${buses}" var="buses">
        						<tr onclick="location.href='/bus?id=${buses.id}'">
        							<td>${buses.busNo}</td>
        							<td>${buses.model}</td>
        							<td>${buses.yearOfIssue}</td>
        							<td>${buses.route}</td>
        						</tr>
        					</c:forEach>
        				</c:when>
        				<c:otherwise>
        					<c:forEach items="${buses}" var="buses">
        						<tr onclick="location.href='/buspark?regime=DeleteBus&id=${buses.id}'">
                                        							<td>${buses.busNo}</td>
                                        							<td>${buses.model}</td>
                                        							<td>${buses.yearOfIssue}</td>
                                        							<td>${buses.route}</td>
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
            					<img  src="resources/edit.png" width=30px onclick="location.href='/buspark?regime=AddBus'">
            				</div>

            				<div class="person delete">
            					<img src="resources/trash.png" width=30px onclick="location.href='/buspark?regime=DeleteBus'">
            				</div>
            			</c:otherwise>
            		</c:choose>
            	</div>


    </body>
    </html>