function formSubmit(method, url, id) {
	$.ajax({
		type:method,
		url:url,
		data:$(id).serializeArray(),
		dataType:"json",
		success:function(data) {
			if(data.success) {
				alert(data.msg);
				window.location.reload();
			} else {
				alert(data.msg);
			}
		},
		error:function() {
			alert("系统异常");
		}
	})
}