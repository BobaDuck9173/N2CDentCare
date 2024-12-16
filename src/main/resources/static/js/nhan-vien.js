if(window.location.pathname == "/tim-benh-nhan"){
	window.history.replaceState(null, document.title, "/nhan-vien/quan-ly");
}

function displayAddForm(id){
	document.getElementById("add-" + id + "-form").style.display = "block";
	if(id == 'benh-nhan'){
		document.getElementById("btn-add-benhnhan").innerHTML = "Tạo hồ sơ";
	}
}

function getBenhNhanInfo(id){
	let position = id;
	let ten = document.getElementById("ten-benh-nhan-" + position).innerHTML;
	let gioiTinh = document.getElementById("gioi-tinh-benh-nhan-" + position).innerHTML;
	let sdt = document.getElementById("sdt-benh-nhan-" + position).innerHTML;
	document.getElementById("mini-panel-ten").innerHTML = ten;
	document.getElementById("mini-panel-gioi-tinh").innerHTML = gioiTinh;
	document.getElementById("mini-panel-sdt").innerHTML = sdt;
}

function suaBenhNhan(){
	document.getElementById("update-benh-nhan-form").style.display = "block";
	let ten = document.getElementById("mini-panel-ten").innerHTML;
	let sdt = document.getElementById("mini-panel-sdt").innerHTML;
	document.getElementById("update-benh-nhan-ten").value = ten;
	document.getElementById("update-benh-nhan-sdt").value = sdt;
	document.getElementById("update-benh-nhan-sdt-cu").value = sdt;
	document.getElementById("btn-update-benh-nhan").innerHTML = "Sửa thông tin";
}

function xoaBenhNhan(){
	let sdt = document.getElementById("mini-panel-sdt").innerHTML;
	document.getElementById("delete-benh-nhan-sdt").value = sdt;
	var btn = document.getElementById("btn-delete-benh-nhan");
	btn.click();
}