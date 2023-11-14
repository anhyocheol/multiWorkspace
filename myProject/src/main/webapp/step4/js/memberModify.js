let xhr = new XMLHttpRequest();

function ajaxGetMemberHandler() {
	if(xhr.readyState === 4 && xhr.status === 200) {
		memberBodyRender(JSON.parse(xhr.responseText));
	}
}

function ajaxGetMember() {
	xhr.onload = ajaxGetMemberHandler;
	let param = '?command=modify&id=' + localStorage.getItem('userId');
	
	xhr.open('GET', '/myProject/step3/ajaxMemberController.jsp' + param, true);
	xhr.send();
}

function ajaxMemberModifyHandler() {
	if(xhr.readyState === 4 && xhr.status === 200) {
		let resultObj = JSON.parse(xhr.responseText);
		
		if(resultObj.result === 1) {
			location.href = 'memberList.html';
		}
		else {
			document.querySelector('#msg').innerHTML = '회원 정보 수정 중 오류가 발생했습니다.';
		}
	}
}

function ajaxChangePasswd(id, passwd, newPasswd) {
	xhr.onload = ajaxMemberModifyHandler;
	
	let param = 'command=changePasswd&id=' + id + '&passwd=' + passwd + 
				'&newPasswd=' + newPasswd;
	xhr.open('POST', '/myProject/step3/ajaxMemberController.jsp', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send(param);
}

function ajaxChangeGrade(id, grade) {
	xhr.onload = ajaxMemberModifyHandler;
	
	let param = '?command=changeGrade&id=' + id + '&grade=' + grade;
	xhr.open('GET', '/myProject/step3/ajaxMemberController.jsp' + param, true);
	xhr.send();
}

function ajaxDeleteMember(id) {
	xhr.onload = ajaxMemberModifyHandler;
	
	let param = '?command=deleteMember&id=' + id;
	xhr.open('GET', '/myProject/step3/ajaxMemberController.jsp' + param, true);
	xhr.send();
}

function modifyHandler(event) {
	let buttonId = event.target.getAttribute('id');
	let id = document.querySelector('#id');
	let passwd = document.querySelector('#passwd');
	let newPasswd = document.querySelector('#newPasswd');
	let grade = document.querySelector('#grade');
		
	if(buttonId === 'changePasswd') {
		if(newPasswd.value === '' || newPasswd.value.length < 4 || passwd.innerHTML === newPasswd.value) {
			document.querySelector('#msg').innerHTML = '새로운 비밀번호는 4글자 이상이고 기존 비밀번호와 달라야 합니다.';
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
