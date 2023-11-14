<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Member Modify</title>
	<link rel="stylesheet" href="/myProject/css/modify.css" />
	<script src="/myProject/step2/js/memberModify.js" charset="UTF-8"></script>
</head>
<body>

	<h2>회원 정보 조회 &amp; 수정</h2>
	<hr />
	
	<h3>[회원 정보]</h3>
	
	<table>
		<tr>
			<th>아이디:</th>
			<td>${member.id}</td>
		</tr>
		<tr>
			<th>비밀번호:</th>
			<td>${member.passwd}</td>
		</tr>
		<tr>
			<th>변경 비밀번호:</th>
			<td><input type="password" name="newInputPasswd" id="newInputPasswd" /></td>
		</tr>
		<tr>
			<th>이름:</th>
			<td>${member.name}</td>
		</tr>
		<tr>
			<th>등록일:</th>
			<td>${member.regdate}</td>
		</tr>
		<tr>
			<th>등급:</th>
			<td>
				<select name="newInputGrade" id="newInputGrade">
					<option ${member.grade == 1 ? "selected" : ""}>1</option>
					<option ${member.grade == 2 ? "selected" : ""}>2</option>
					<option ${member.grade == 3 ? "selected" : ""}>3</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button id="changePasswd">비밀번호 변경</button>
				<button id="changeGrade">등급 변경</button>
				<button id="deleteMember">회원 삭제</button>
			</td>
		</tr>
	</table>
	
	<div id="msg"></div>
	
	<hr />
	<a href="/myProject/step2/list">회원 리스트 페이지</a>
	
	<form method="POST" id="hiddenForm">
		<input type="hidden" id="id" name="id" value="${member.id}" />
		<input type="hidden" id="passwd" name="passwd" value="${member.passwd}" />
		<input type="hidden" id="newPasswd" name="newPasswd" />
		<input type="hidden" id="grade" name="grade" value="${member.grade}" />
	</form>

</body>
</html>





















