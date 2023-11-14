
function changePasswdHandler() {
	let hiddenForm = document.querySelector('#hiddenForm');
	let passwd = document.querySelector('#passwd'); // 기존 저장된 사용자의 passwd
	let newInputPasswd = document.querySelector('#newInputPasswd'); // 사용자 입력으로 받는 passwd
	let newPasswd = document.querySelector('#newPasswd'); //hiddenform 안에 서버에게 전달되는 passwd
	let command = document.querySelector('#command');
	let msg = document.querySelector('#msg');
	
	if(newInputPasswd.value.length < 4 || passwd.value == newInputPasswd.value) {
		msg.innerHTML = "새로운 비밀번호는 4글자 이상이고 이전 비밀번호와 달라야 합니다.";
		return;
	}
	
	newPasswd.value = newInputPasswd.value;
	command.value = "changePasswd";
	hiddenForm.submit();
}
function changeGradeHandler() {
	let hiddenForm = document.querySelector('#hiddenForm');
	let newInputGrade = document.querySelector('#newInputGrade');
	let grade = document.querySelector('#grade');
	let command = document.querySelector('#command');

	grade.value = newInputGrade.value;
	command.value = "changeGrade";
	hiddenForm.submit();

}
function deleteMemberHandler() {
	if(confirm("현재 회원을 정말 삭제하시겠습니까?")) {	
		let hiddenForm = document.querySelector('#hiddenForm');
		let command = document.querySelector('#command');

		command.value = "deleteMember";
		hiddenForm.submit();	
	}
}

function init() {
	document.querySelector('#changePasswd').addEventListener('click', changePasswdHandler);
	document.querySelector('#changeGrade').addEventListener('click', changeGradeHandler);
	document.querySelector('#deleteMember').addEventListener('click', deleteMemberHandler);
}

window.addEventListener('load', init);
