if(window.location.pathname == "/tim-benh-nhan"){
	window.history.replaceState(null, document.title, "/nhan-vien/quan-ly");
}

function displayAddForm(id){
	document.getElementById("add-" + id + "-form").style.display = "block";
}

function getBenhNhanInfo(id){
	let position = id;
	let ten = document.getElementById("ten-benh-nhan-" + position).innerHTML;
	let gioiTinh = document.getElementById("gioi-tinh-benh-nhan-" + position).innerHTML;
	let sdt = document.getElementById("sdt-benh-nhan-" + position).innerHTML;
	document.getElementById("mini-panel-ten").innerHTML = ten;
	document.getElementById("mini-panel-gioi-tinh").innerHTML = gioiTinh;
	document.getElementById("mini-panel-sdt").innerHTML = sdt;
	
	console.log(position);
	console.log(ten);
	console.log(gioiTinh);
	console.log(sdt);

}