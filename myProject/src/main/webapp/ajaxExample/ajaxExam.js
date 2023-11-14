let xhr = new XMLHttpRequest();

function ajaxHandler() {
	if(xhr.readyState == 4 && xhr.status == 200) {
		let personsObj = JSON.parse(xhr.responseText);
		
		let persons = '';
				
		for(let i in personsObj) {
			
			persons += 	'<h4>이름: ' + personsObj[i].name + 
						', 성별: ' + personsObj[i].gender +
						', 나이: ' + personsObj[i].age + 
						', 분야: ' + personsObj[i].field + 
						'</h4>';
		}
		document.querySelector('#persons').innerHTML = persons;
	}
}

function sendHandler() {
	xhr.addEventListener('load', ajaxHandler);
	
	//xhr.open('GET', 'persons.json',true);
	xhr.open('GET', 'ajaxJson.jsp',true);
	xhr.send();
}

function clearHandler() {
	document.querySelector('#persons').innerHTML = '';
}

function init() {
	document.querySelector('#send').addEventListener('click', sendHandler);
	document.querySelector('#clear').addEventListener('click', clearHandler);
}

window.addEventListener('load', init);