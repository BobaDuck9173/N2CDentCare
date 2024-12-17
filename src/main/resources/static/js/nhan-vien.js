if(window.location.pathname == "/tim-benh-nhan"){
	window.history.replaceState(null, document.title, "/nhan-vien/quan-ly");
}

function displayAddForm(id){
	document.getElementById("add-" + id + "-form").style.display = "block";
	if(id == 'benh-nhan'){
		document.getElementById("btn-add-benhnhan").con = "Tạo hồ sơ";
	}
}

function getBenhNhanInfo(id){
	let position = id;
	let ten = document.getElementById("ten-benh-nhan-" + position).textContent;
	let gioiTinh = document.getElementById("gioi-tinh-benh-nhan-" + position).textContent;
	let sdt = document.getElementById("sdt-benh-nhan-" + position).textContent;
	document.getElementById("mini-panel-ten").textContent = ten;
	document.getElementById("mini-panel-gioi-tinh").textContent = gioiTinh;
	document.getElementById("mini-panel-sdt").textContent = sdt;
}

function suaBenhNhan(){
	document.getElementById("update-benh-nhan-form").style.display = "block";
	let ten = document.getElementById("mini-panel-ten").textContent;
	let sdt = document.getElementById("mini-panel-sdt").textContent;
	document.getElementById("update-benh-nhan-ten").value = ten;
	document.getElementById("update-benh-nhan-sdt").value = sdt;
	document.getElementById("update-benh-nhan-sdt-cu").value = sdt;
}

function xoaBenhNhan(){
	let sdt = document.getElementById("mini-panel-sdt").textContent;
	document.getElementById("delete-benh-nhan-sdt").value = sdt;
	var btn = document.getElementById("btn-delete-benh-nhan");
	btn.click();
}

function getBenhAnInfo(id){
	console.log(id);
	
	let sdt = id.substring(0,10);
	console.log(sdt);

	let position = id.substring(11);
	console.log(position);

	
	let ten = document.getElementById("benh-an-ten-" + position).textContent;
	let bacsi = document.getElementById("benh-an-bac-si-" + position).textContent;
	let ngayKham = document.getElementById("benh-an-ngay-" + position).textContent;
	let chuanDoan = document.getElementById("benh-an-chuan-doan-" + position).textContent;
	
	document.getElementById("mini-panel-id-ba").textContent = position;
	document.getElementById("mini-panel-sdt-ba").textContent = sdt;
	document.getElementById("mini-panel-ten-bn").textContent = ten;
	document.getElementById("mini-panel-bac-si").textContent = bacsi;
	document.getElementById("mini-panel-ngay").textContent = ngayKham;
	document.getElementById("mini-panel-chuan-doan").textContent = chuanDoan;
}

function suaBenhAn(){
	document.getElementById("update-benh-an-form").style.display = "block";
	let ngayKham = document.getElementById("mini-panel-ngay").textContent;
	let chuanDoan = document.getElementById("mini-panel-chuan-doan").textContent;
	let sdt = document.getElementById("mini-panel-sdt-ba").textContent;
	let id = document.getElementById("mini-panel-id-ba").textContent;
	document.getElementById("update-benh-an-chuan-doan").value = chuanDoan;
	
	document.getElementById("update-benh-an-ngay-value").value = ngayKham;
	document.getElementById("update-benh-an-sdt").value = sdt;
	document.getElementById("update-benh-an-sdt-cu").value = sdt;
	document.getElementById("update-benh-an-chuan-doan").value = chuanDoan;
	document.getElementById("update-benh-an-id").value = id;
}

function xoaBenhAn(){
	let sdt = document.getElementById("mini-panel-sdt").innerHTML;
	document.getElementById("delete-benh-nhan-sdt").value = sdt;
	var btn = document.getElementById("btn-delete-benh-nhan");
	btn.click();
}

function showDatePicker(){
	document.getElementById("update-benh-an-ngay").style.zIndex = "0";
	document.getElementById("update-benh-an-ngay-value").style.zIndex = "-1";
	document.getElementById("update-benh-an-ngay").style.position = "relative";
	document.getElementById("update-benh-an-ngay-value").style.position = "absolute";
}

function pickDate(){
	let date = new Date(document.getElementById("update-benh-an-ngay").value);

	let day = date.getDate();
	let month = date.getMonth();
	month++;
	let year = date.getFullYear();
	
	if (day < 10){
		day = "0" + day;
	}	
	if (month < 10){
		month = "0" + month;
	}
	
	document.getElementById("update-benh-an-ngay-value").value = day + '/' + month + '/' + year;
	document.getElementById("update-benh-an-ngay").style.zIndex = "-1";
	document.getElementById("update-benh-an-ngay-value").style.zIndex = "0";
	document.getElementById("update-benh-an-ngay").style.position = "absolute";
	document.getElementById("update-benh-an-ngay-value").style.position = "relative";
}