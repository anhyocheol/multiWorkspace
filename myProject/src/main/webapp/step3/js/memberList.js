let xhr = new XMLHttpRequest();

function parseMembersJson(membersJsonStr) {		
	let members = JSON.parse(membersJsonStr);	
	let membersTr = '<tr><th>번호</th><th>아이디</th><th>이름</th><th>등록일</th><th>회원정보 수정</th></tr>';
	
	for(let i in members) {
		membersTr += 	'<tr><td>' + ((i * 1) + 1) + 
						'</td><td>' + members[i].id + 
						'</td><td>' + members[i].name + 
						'</td><td>' + members[i].regdate + 
						'</td><td><button id="' + members[i].id + '">수정</button></td></tr>';
	}
	
	return membersTr;
}


function ajaxMemberListHandler() {
	if(xhr.readyState === 4 && xhr.status === 200) {
		let membersTr = parseMembersJson(xhr.responseText);	// 각각의 회원정보를 행으로 생성해준다~
		document.querySelector('#listTable').innerHTML = membersTr;
	}
}



function ajaxMemberList() {
	xhr.onload = ajaxMemberListHandler;
	
	xhr.open('GET', '/myProject/step3/ajaxMemberController.jsp?command=list', true);
	xhr.send();
}



function ajaxMemberInsertHandler() {	// 인서트 요청에 대한 응답을 받은 것임! 그 서버의 응답은 무엇일까? => JsonMember.java의 3번(result), 4번(msg) 메서드!!
	if(xhr.readyState === 4 && xhr.status === 200) {
		let resObj = JSON.parse(xhr.responseText);
		
		if(resObj.result) {	// 값이 있기만 하면 true! 값이 없으면 undefined로 false => result가 값이 있다는 것은 insert가 성공했다는 의미!
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
		inputCheck = false;
	}
	else if(passwd.value !== repasswd.value) {
		msg = '비밀번호와 비밀번호 확인은 같은 값이어야 합니다.';
		inputCheck = false;
	}
	else if(name.value.length < 2) {
		msg = '이름은 두 글자 이상이어야 합니다.';
		inputCheck = false;
	}
	
	
	
	if(inputCheck) {	// 올바른 사용자 입력일 때
		ajaxMemberInsert(id.value, passwd.value, name.value);
	}
	else {				// 틀린 사용자 입력일 때
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