function changePasswdHandler() {
	let hiddenForm = document.querySelector('#hiddenForm');
	let passwd = document.querySelector('#passwd');
	let newInputPasswd = document.querySelector('#newInputPasswd');
	let newPasswd = document.querySelector('#newPasswd');
	let msg = document.querySelector('#msg');
	
	if(newInputPasswd.value.length < 4 || passwd.value === newInputPasswd.value) {
		msg.innerHTML = "새로운 비밀번호는 4글자 이상이고 이전 비밀번호와 달라야 합니다.";
		return;
	}
	
	newPasswd.value = newInputPasswd.value;
	hiddenForm.setAttribute('action', '/myProject/step2/changePasswd');
	hiddenForm.submit();
}

function changeGradeHandler() {
	let hiddenForm = document.querySelector('#hiddenForm');
	let newInputGrade = document.querySelector('#newInputGrade');
	let grade = document.querySelector('#grade');
	
	grade.value = newInputGrade.value;
	hiddenForm.setAttribute('action', '/myProject/step2/changeGrade');
	hiddenForm.submit();
}

function deleteMemberHandler() {
	if(confirm('현재 회원을 정말 삭제하시겠습니까?')) {
		let hiddenForm = document.querySelector('#hiddenForm');

		hiddenForm.setAttribute('action', '/myProject/step2/deleteMember');
		hiddenForm.submit();
	}
}
	

function init() {
	document.querySelector('#changePasswd').addEventListener('click', changePasswdHandler);
	document.querySelector('#changeGrade').addEventListener('click', changeGradeHandler);
	document.querySelector('#deleteMember').addEventListener('click', deleteMemberHandler);
}

window.addEventListener('load', init);








