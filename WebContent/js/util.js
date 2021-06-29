function showAlert(message) {
	var html = "<div class='alert'>" + message + "</div>";
	$("body").append(html);
	$(".alert").fadeIn();
	
	setTimeout(function() {
		$(".alert").fadeOut(2000, function() {
			$(this).remove();
		});
	}, 3000);
}

function formatDate(date) {
	return (date.getMonth() + 1) + "/" + date.getDate() + "/" + (date.getYear() + 1900);
}

function formatCurrency(usd) {
	return "$" + usd.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function formatPercentage(decimal) {
	return (decimal * 100) + "%";
}