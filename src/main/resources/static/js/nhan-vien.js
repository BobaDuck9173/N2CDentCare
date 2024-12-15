if(window.location.pathname == "/tim-benh-nhan"){
	window.history.replaceState(null, document.title, "/nhan-vien/quan-ly");
}

function displayAddForm(id){
	document.getElementById("add-" + id + "-form").style.display = "block";
}