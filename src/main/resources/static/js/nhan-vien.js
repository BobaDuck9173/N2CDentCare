if(window.location.pathname == "/tim-benh-nhan"){
	window.history.replaceState(null, document.title, "/nhan-vien/quan-ly");
}

function displayAddForm(){
	document.getElementById("add-benh-nhan-form").style.display = "block";
}