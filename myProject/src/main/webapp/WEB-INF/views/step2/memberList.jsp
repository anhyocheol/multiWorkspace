<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Member Insert &amp; List</title>
	<link rel="stylesheet" href="/myProject/css/list.css" />
	<script src="/myProject/step2/js/memberList.js" charset="UTF-8"></script>
</head>
<body>

	<h2>회원 가입 &amp; 및 전체 회원 조회</h2>
	<hr />
	
	<h3>[회원가입]</h3>

	<form method="POST" action="/myProject/step2/insert" id="insertMember">
	<fieldset>
		<legend>회원 정보 입력</legend>
		
		<label for="id">아이디</label>
		<input type="email" name="id" id="id" placeholder="ex) example@gmail.com" required /><br />
		
		<label for="passwd">비밀번호</label>
		<input type="password" name="passwd" id="passwd" placeholder="4글자 이상" required /><br />
		
		<label for="repasswd">비밀번호 확인</label>
		<input type="password" id="repasswd" placeholder="동일 비밀번호 입력" required /><br />
		
		<label for="name">이름</label>
		<input type="text" name="name" id="name" placeholder="2글자 이상" required /> :::
		
		<input type="submit" value="가입" />
	</fieldset>
	</form>
	
	<div id="msg">${msg}</div>
	
	<hr />
	<h3>[전체 회원 조회]</h3>
	
	<table id="listTable">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름</th>
			<th>등록일</th>
			<th>회원정보수정</th>
		</tr>
	<c:forEach items="${memberList}" var="member" varStatus="status">	
		<tr>
			<td>${status.count}</td>
			<td>${member.id}</td>
			<td>${member.name}</td>
			<td>${member.regdate}</td>
			<td><button id="${member.id}">수정</button></td>
		</tr>
	</c:forEach>
	</table>
	
</body>
</html>






















