const checkoutItems = [
	{
		type: "Ladder",
		brand: "Werner",
		code: "LADW"
	},
	{
		type: "Chainsaw",
		brand: "Stihl",
		code: "CHNS"
	},
	{
		type: "Jackhammer",
		brand: "Ridgid",
		code: "JAKR"
	},
	{
		type: "Jackhammer",
		brand: "DeWalt",
		code: "JAKD"
	}
];

function loadCheckoutScreen() {
	$('#main-area').empty();
	$(".nav-item-selected").removeClass("nav-item-selected");
	$("#nav-checkout").addClass("nav-item-selected");
	$.each(checkoutItems, function(i, item) {
		//show tile
		var html = "<div id='" + item.code + "' class='checkout-item'>" +
					"<div class='checkout-item-info-container'>" +
						"<p>" + item.type + "</p>" +
						"<p>" + item.brand + "</p>" +
						"<p>" + item.code + "</p>" +
					"</div>" +
				"</div>";
		$("#main-area").append(html);
		
		//assign background image
		var imageUrl = "css/images/";
		switch (item.type) {
			case "Ladder": {
				imageUrl += "ladder.png";
				break;
			}
			case "Jackhammer": {
				imageUrl += "jackhammer.png";
				break;
			}
			case "Chainsaw": {
				imageUrl += "chainsaw.png";
			}
		}
		$("#" + item.code).css({
			"background-image": "url(" + imageUrl + ")"
		});
	});
	//add button
	var html = "<div id='checkout-btn' class='action-btn'>Checkout</div>" +
				"<div id='rental-days-container'>" +
					"<p>Rental Days:</p>" +
					"<input id='rental-days' type='text'>" +
					"<div>d</div>" +
				"</div>" +
				"<div id='discount-percent-container'>" +
					"<p>Discount Percentage:</p>" +
					"<input id='discount-percent' type='text' value='0'>" +
					"<div>%</div>" +
				"</div>";
	$("#main-area").append(html);
	
	$(".checkout-item").on("click", selectTile);
	$("#checkout-btn").on("click", checkout);
}

function selectTile() {
	if ($(this).hasClass("selected-item")) {
		$(this).removeClass("selected-item")
	}
	else {
		$(".selected-item").removeClass("selected-item");
		$(this).addClass("selected-item");	
	}
}

function decreaseItemCount() {
	var $count = $(this).parent().find(".checkout-item-count");
	if ($count.text() != 0) {
		//decrement tile count
		$count.text(parseInt($count.text()) - 1);
		
		//decrement pre-receipt
		var code = $(this).parent().attr("id");
		$("#" + code + "-pre-receipt .value").text($count.text());
	}
}

function checkout() {
	//validate
	var checkoutDate = (new Date()).setHours(0, 0, 0, 0);
	
	if ($(".selected-item").length < 1) {
		showAlert("No Item Selected");
		return;
	}
	else if ($("#discount-percent").val() < 0 || $("#discount-percent").val() > 100) {
		showAlert("Invalid Discount Range");
		return;
	}
	else if ($("#rental-days").val() < 1) {
		showAlert("Rental Must Be One Day or More");
		return;
	}
	
	//get data
	var checkoutData = {
		code: $(".selected-item").attr("id"),
		checkoutDate: checkoutDate,
		rentalDays: $("#rental-days").val(),
		discount: $("#discount-percent").val() / 100
	}
	$.ajax({
		url: "/ToolRental/toolrental",
		data: checkoutData,
		dataType: "json",
		success: function(data) {
			console.log("og data:");
			console.log(data);
			showRentalAgreement(data);
		},
		error: function(xhr) {
			console.error(xhr);
		}
	});
}

function showRentalAgreement(data) {
	console.log("data:");
	console.log(data);
	
	formatRentalAgreementData(data);
	
	$('#main-area').empty();
	$(".nav-item-selected").removeClass("nav-item-selected");
	$("#nav-agreement").addClass("nav-item-selected");
	
	$("#main-area").append("<div id='rental-agreement'><table></table></div>");
	$.each(data, function(key, value) {
		var html = "<tr>" +
						"<td>" + key.replaceAll("_", " ") + "</td>" +
						"<td>" + value
					"</tr>";
		$("#rental-agreement table").append(html);
	});
	
	$("#main-area").append("<div id='return-btn' class='action-btn'>Return</div>");
	$("#return-btn").on("click", loadCheckoutScreen);
}

function formatRentalAgreementData(data) {
	data.checkout_date = formatDate(new Date(data.checkout_date));
	data.due_date = formatDate(new Date(data.due_date));
	data.daily_charge = formatCurrency(data.daily_charge);
	data.discount = formatPercentage(data.discount);
	data.discount_amount = formatCurrency(data.discount_amount);
	data.pre_discount_charge = formatCurrency(data.pre_discount_charge);
	data.final_charge = formatCurrency(data.final_charge);
}

$(document).ready(function() {
	loadCheckoutScreen();
});