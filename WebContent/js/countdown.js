function countdown(id, mstime) {
	if(mstime < 0)
		return;
	var stime = mstime / 1000;
	var day = Math.floor(stime / (60 * 60 * 24));
	
	var modulo = stime % (60 * 60 * 24);
	var hours = Math.floor(modulo / (60 * 60));
	
	modulo = modulo % (60 * 60);
	var minutes = Math.floor(modulo / 60);
	
	var seconds = Math.floor(modulo % 60);
	
	document.getElementById(id).innerHTML = day + "天" + hours + "时" + minutes + "分" + seconds + "秒";
	setTimeout(function () {
        countdown(id, mstime-1000);
    }, 1000)
}