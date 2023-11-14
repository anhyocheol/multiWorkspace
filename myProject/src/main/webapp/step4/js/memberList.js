let xhr = new XMLHttpRequest();

function ajaxMemberListHandler() {
	if(xhr.readyState === 4 && xhr.status === 200) {
		membersBodyRender(JSON.parse(xhr.responseText));
	}
}


function ajaxMemberList() {
	xhr.onload = ajaxMemberListHandler;
	
	xhr.open('GET', '/myProject/step3/ajaxMemberController.jsp?command=list', true);
	xhr.send();
}

function ajaxMemberInsertHandler() {
	if(xhr.readyState === 4 && xhr.status === 200) {
		let resObj = JSON.parse(xhr.responseText);
		
		if(resObj.result) {
			document.querySelector('#msg').innerHTML = '';
			ajaxMemberList();
		}
		else if(resObj.msg) {
			document.querySelector('#msg').innerHTML = resObj.msg;
		}
	}
}

function ajaxMemberInsert(id, passwd, name) {
	xhr.onload = ajaxMemberInsertHandler;
	
	let param = 'command=insert&id=' + id + '&passwd=' + passwd + '&name=' + name;
	xhr.open('POST', '/myProject/step3/ajaxMemberController.jsp', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.send(param);
}

function submitInsertHandler(event) {
	event.preventDefault();

	let id = document.querySelector('#id');
	let passwd = document.querySelector('#passwd');
	let repasswd = document.querySelector('#repasswd');
	let name = document.querySelector('#name');
	let msg = '';
	let inputCheck = true;
	
	if(passwd.value.length < 4) {
		msg = '비밀번호는 4글자 이상이어야 합니다.';
		passwd.value = '';
		repasswd.value = '';
		
		inputCheck = false;
	}
	else if(passwd.value !== repasswd.value) {
		msg = '비밀번호와 비밀번호 확인은 같은 값이어야 합니다.';
		passwd.value = '';
		repasswd.value = '';
		
		inputCheck = false;
	}
	else if(name.value.length < 2) {
		msg = '이름은 두 글자 이상이어야 합니다.';
		name.value = '';
		
		inputCheck = false;
	}
	
	if(inputCheck) {
		ajaxMemberInsert(id.value, passwd.value, name.value);
		
		id.value = '';
		passwd.value = '';
		repasswd.value = '';
		name.value = '';
	}
	else {		
		document.querySelector('#msg').innerHTML = msg;
	}
}
function modifyHandler(event) {
	localStorage.setItem('userId', event.target.getAttribute('id'));
	location.href = 'memberModify.html';
}

function init() {
	document.querySelector('#insertMember').addEventListener('submit', submitInsertHandler);	
	document.querySelector('#listTable').addEventListener('click', modifyHandler);
	
	ajaxMemberList();
}

window.addEventListener('load', init);
