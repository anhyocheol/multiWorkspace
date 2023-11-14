let xhr = new XMLHttpRequest();

function parseMemberJson(membersJsonStr) {
	let member = JSON.parse(membersJsonStr);
	
	let memberTr = '<tr>'
				+		'<th>아이디:</th>'																
				+		'<td id="id ">' + member.id + '</td>'
				+	'</tr>'
				+	'<tr>'	
				+		'<th>비밀번호:</th>'
				+		'<td id="member.passwd">' + member.passwd + '</td>'													
				+	'</tr>'
				+	'<tr>'
				+		'<th>변경 비밀번호:</th>'
				+		'<td><input type="password" name="newInputPasswd" id="newInputPasswd" /></td>'
				+	'</tr>'
				+	'<tr>'
				+		'<th>이름:</th>'
				+		'<td>'+ member.name +'</td>'
				+	'</tr>'
				+	'<tr>'
				+		'<th>등록일:</th>'
				+		'<td>'+ member.regdate +'</td>'
				+	'</tr>'
				+	'<tr>'
				+		'<th>등급:</th>'
				+		'<td>'
				+		'<select name="grade" id="grade">'
				+		'<option' + (member.grade === 1 ? "select" : "") + '>1</option>'
				+		'<option' + (member.grade === 2 ? "select" : "") + '>2</option>'
				+		'<option' + (member.grade === 3 ? "select" : "") + '>3</option>'
				+		'</select>'
				+	'</td>'
				+	'</tr>'
				+	'<tr>'
				+		'<td colspan="2">'
				+		'<button id="changePasswd">비밀번호 변경</button>'
				+		'<button id="changeGrade">등급 변경</button>'
				+		'<button id="deleteMember">회원 삭제</button>'
				+		'</td>'
				+	'</tr>';
	
	return memberTr;
}

function ajaxGetMemberHandler() {
	if(xhr.readyState === 4 && xhr.status === 200 ) {
		document.querySelector('#memberTable').innerHTML = parseMemberJson(xhr.responseText) // 서버가 응답에 제공한 결과
	}
}

function ajaxGetMember() {
	xhr.onload = ajaxGetMemberHandler;
	let param = '?command=modify&id=' + localStorage.getItem('userId');
	xhr.open('GET', 'ajaxMemberController.jsp' + param, true);
	xhr.send();
}

function ajaxMemberModifyHandler() {
	if(xhr.readyState === 4 && xhr.status === 200) {
		let resultObj = JSON.parse(xhr.responseText);
		
		if(resultObj.result === 1) {
			lacation.href = 'memberList.html';
		}
		else {
			document.querySelector('#msg').innerHTML = '회원 정보 수정시 오류가 발생했습니다';
		}
	}
}

function ajaxChangePasswd(id, passwd, newPasswd) {
	xhr.onload = ajaxMemberModifyHandler;
	
	let param = 'command=changePasswd&id=' + id + '&passwd=' + passwd + '&newPasswd=' + newPasswd;
	xhr.open('POST', "ajaxMemberController.jsp", true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send(param);
}


function ajaxChangeGrade(id, grade) {
	xhr.onload = ajaxMemberModifyHandler;
	
	let param = '?command=changeGrade&id=' + id + '&grade=' + grade;
	xhr.open('GET', "ajaxMemberController.jsp" + param, true);
	xhr.send(param);
}

function ajaxDeleteMember(id) {
	xhr.onload = ajaxMemberModifyHandler;
	
	let param = '?command=deleteMember&id=' + id;
	xhr.open('GET', "ajaxMemberController.jsp" + param, true);
	xhr.send(param);
}

function modifyHandler() {
	let buttonId = event.target.getAttribute('id');
	let id = document.querySelector('#id');
	let passwd = document.querySelector('#passwd');
	let newPasswd = document.querySelector('#newPasswd');
	let grade = document.querySelector('#grade');
	
	if(buttonId === 'changePasswd') { 
		if(newPasswd.value === '' || newPasswd.value.length < 4 || passwd.innerHTML === newPasswd.value) { 
			document.querySelector('#msg').innerHTML = '새로운 비밀번호는 4글자이고, 기존 비밀번호와 달라야 합니다.';
		}
		else { 
			ajaxChangePasswd(id.innerHTML, passwd.innerHTML, newPasswd.value);
		}
	}
	else if(buttonId === 'changeGrade') { 
		ajaxChangeGrade(id.innerHTML, grade.value);		
	}
	else if(buttonId === 'deleteMember') {
		if(confirm('현재 회원을 정말 삭제하시겠습니까?')) {
			ajaxDeleteMember(id.innerHTML);		
		} 
	}
}


function init() {
	document.querySelector('#memberTable').addEventListener('click', modifyHandler);
	ajaxGetMember();
}

window.addEventListener('load', init);