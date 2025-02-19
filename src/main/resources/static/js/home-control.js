// This is the version used for regular HTML + FreeMarker with jQuery

function healthCheck() {
	$.ajax(
			{
				type : "GET",
				url  : "/cs480/ping",
				data : {
				},
				success : function(result) {
					$('#status').text(result);
				},
				error: function (jqXHR, exception) {
					$('#status').text("Failed to get the status");
				}
			});
}

function heAwaits() {
	$.ajax(
		{
			type : "GET",
			url  : "/cs480/chris",
			data : {
			},
			success : function(result) {
				$('#msg').text(result);
			},
			error: function (jqXHR, exception) {
				$('#msg').text("Failed to fulfill the request");
			}
		});
}

//Pedro's function (A3)
function pedrosFirstHttpApi() {
	$.ajax(
		{
			type : "GET",
			url  : "/cs480/pedro",
			data : {
			},
			success : function(result) {
				$('#msg1').text(result);
			},
			error: function (jqXHR, exception) {
				$('#msg1').text("Failed to fulfill the request");
			}
		});
}

//Pedro's function (A4)
function pedrosUserInfo() {
	$.ajax(
		{
			type : "GET",
			url  : "/cs480/pedro2",
			data : {
			},
			success : function(result) {
				$('#pedrosUserInfo').text(result);
			},
			error: function (jqXHR, exception) {
				$('#pedrosUserInfo').text("Failed to fulfill the request");
			}
		});
}

function carmeloPasswordGenerator() {
	$.ajax(
		{
			type : "GET",
			url  : "/cs480/carmelo",
			data : {
			},
			success : function(result) {
				$('#carmelo').text(result);
			},
			error: function (jqXHR, exception) {
				$('#carmelo').text("Failed to fulfill the request");
			}
		});
}

function carmeloDatabase() {
	$.ajax(
		{
			type : "GET",
			url  : "/cs480/carmelo2",
			data : {
			},
			success : function(result) {
				$('#carmelo2').text(result);
			},
			error: function (jqXHR, exception) {
				$('#carmelo2').text("Failed to fulfill the request");
			}
		});
}

function testing123() {
	$.ajax(
		{
			type : "GET",
			url  : "/cs480/sonia",
			data : {
			},
			success : function(result) {
				$('#sonia').text(result);
			},
			error: function (jqXHR, exception) {
				$('#sonia').text("Failed to fulfill the request");
			}
		});
}

function darrenHelloWorld() {
	$.ajax(
		{
			type : "GET",
			url  : "/cs480/darren",
			data : {
			},
			success : function(result) {
				$('#darren').text(result);
			},
			error: function (jqXHR, exception) {
				$('#darren').text("Failed to fulfill the request");
			}
		});
}

function deleteUser(userId) {
	$.ajax(
			{
				type : "DELETE",
				url  : "/cs480/user/" + userId,
				data : {
				},
				success : function(result) {
					location.reload();
				},
				error: function (jqXHR, exception) {
					alert("Failed to delete the photo.");
				}
			});
}

function addUser() {

	var userId = $('#input_id').val();
	var userName = $('#input_name').val();
	var userMajor = $('#input_major').val();

	if (userId) {
		$.ajax(
				{
					type : "POST",
					url  : "/cs480/user/" + userId,
					data : {
						"name" : userName,
						"major" : userMajor
					},
					success : function(result) {
						location.reload();
					},
					error: function (jqXHR, exception) {
						alert("Failed to add the user. Please check the inputs.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
}

function getUser(userId) {
	var userId = $('#query_id').val();
	if (userId) {
		$.ajax(
				{
					type : "GET",
					url  : "/cs480/user/" + userId,
					data : {
					},
					success : function(result) {
						$('#result_id').text(result.id);
						$('#result_name').text(result.name);
						$('#result_major').text(result.major);
					},
					error: function (jqXHR, exception) {
						alert("Failed to get the user.");
					}
				});
	} else {
		alert("Invalid user Id");
	}
}
