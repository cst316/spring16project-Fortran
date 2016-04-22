var x = 0;

function changeColor(){
	x = 1;
	setInterval(change, 1000);
}

function change(){
	if(x === 1){
		color = "red";
		x = 2;
	}else if(x === 2){
		color = "green";
		x = 3;
	}else if(x === 3){
		color = "blue";
		x = 4;
	}else{
		color = "yellow";
		x = 1;
	}
	document.getElementById("team").style.color = color;
	return true;
}

function featuresPage(){
	window.open("features.html");
	return true;
}

function changeColors(){

}

function downloadIt(){
	window.open("download.html");
	return true;
}

function doIt(){
	document.getElementById("thebutton").style.height="20%";
	document.getElementById("thebutton").style.width ="20%";

}

function getItOut(){
	document.getElementById("thebutton").style.height="14%";
	document.getElementById("thebutton").style.width ="14%";
}