const Intro = ({name}) =>
		
		<article className="intro">
			<header>안녕하세요!</header>
		
			<div>
				<h4>저의 이름은 {name}입니다.</h4>
			</div>
		</article>
		
	function init() {
		let container = document.querySelector('#root');
		
		ReactDOM.render(
			<Intro name="손흥민"/>,
			container
	);
}
		
window.addEventListener('load', init);