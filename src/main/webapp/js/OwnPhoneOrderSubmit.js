/**
 * Serve for portal/designyourownphone.jsp and backstage/modifyownphoneorder.jsp .
 */

var staticEmergencyValue = null;

var staticCurrentEmergencyKeyNumber = null;

function isNotEmpty(fieldName) {
	var attributeValue = document.getElementsByName(fieldName)[0].value;
	if (attributeValue == null || attributeValue.length == 0) {
		return false;
	} else {
		return true;
	}
}

function submitInput(fieldValue, fieldName) {
	document.getElementsByName(fieldName)[0].value = fieldValue;
	preview();
}

function showAndHide(showOrHideObjId, hideObj1Id, hideObj2Id) {
	var showOrHideObj = document.getElementById(showOrHideObjId);
	var showOrHideObjDisplay = showOrHideObj.style.display;
	if (showOrHideObjDisplay == null || showOrHideObjDisplay.length == 0
			|| showOrHideObjDisplay == "none") {
		showOrHideObj.style.display = "block";
		document.getElementById(showOrHideObjId + "symbol").innerHTML = "-";
	} else {
		showOrHideObj.style.display = "none";
		document.getElementById(showOrHideObjId + "symbol").innerHTML = "+";
	}

	document.getElementById(hideObj1Id).style.display = "none";
	document.getElementById(hideObj1Id + "symbol").innerHTML = "+";
	document.getElementById(hideObj2Id).style.display = "none";
	document.getElementById(hideObj2Id + "symbol").innerHTML = "+";
}

function showThenHide(showObjId, hideObjId) {
	showElement(showObjId);
	HideElement(hideObjId);
}

function showElement(ObjId) {
	document.getElementById(ObjId).style.display = "block";
}

function HideElement(ObjId) {
	document.getElementById(ObjId).style.display = "none";
}

function showPhoneColorList() {
	if (isNotEmpty("ownPhoneOrder.keypad")) {
		showAndHide("phonecolor", "keypad", "phonestyle");
	}
}

function showPhoneStyleList() {
	if (isNotEmpty("ownPhoneOrder.keypad")
			&& isNotEmpty("ownPhoneOrder.phonecolor")) {
		showAndHide("phonestyle", "keypad", "phonecolor");
	}
}

function ChangeEmergencySinceKeypadChanged() {
	if (staticCurrentEmergencyKeyNumber != null
			&& staticCurrentEmergencyKeyNumber.length != 0) {

		var keypadValue = document.getElementsByName("ownPhoneOrder.keypad")[0].value;

		if (keypadValue != null && keypadValue.length != 0
				&& keypadValue != staticCurrentEmergencyKeyNumber) {

			var emergencyKeynameObjOld = document
					.getElementsByName("ownPhoneOrder.keyname"
							+ staticCurrentEmergencyKeyNumber)[0];
			var emergencyKeynumberObjOld = document
					.getElementsByName("ownPhoneOrder.keynumber"
							+ staticCurrentEmergencyKeyNumber)[0];

			if (emergencyKeynameObjOld != null
					&& emergencyKeynumberObjOld != null) {

				var emergencyKeynameObjNew = document
						.getElementsByName("ownPhoneOrder.keyname"
								+ keypadValue)[0];
				var emergencyKeynumberObjNew = document
						.getElementsByName("ownPhoneOrder.keynumber"
								+ keypadValue)[0];

				if (emergencyKeynameObjNew != null
						&& emergencyKeynumberObjNew != null) {

					emergencyKeynameObjOld.value = "";
					emergencyKeynumberObjOld.value = "";

					emergencyKeynameObjOld.readOnly = false;
					emergencyKeynumberObjOld.readOnly = false;

					emergencyKeynameObjNew.value = "110";
					emergencyKeynumberObjNew.value = "110";

					emergencyKeynameObjNew.readOnly = true;
					emergencyKeynumberObjNew.readOnly = true;

					staticCurrentEmergencyKeyNumber = keypadValue;
				}
			}
		}
	}
}

function submitKeypad(fieldValue, fieldName) {
	submitInput(fieldValue, fieldName);
	ChangeEmergencySinceKeypadChanged();
}

function checkRadio() {
	var emergencyRadioOld = document
			.getElementsByName("ownPhoneOrder.emergency");

	if (emergencyRadioOld != null) {
		for ( var i = 0; i < emergencyRadioOld.length; i++) {

			if (emergencyRadioOld[i].checked) {

				staticEmergencyValue = emergencyRadioOld[i].value;
			}
		}
	}

	// Set initial readOnly value
	inputEmergency();
}

function inputEmergency() {

	var emergencyRadio = document.getElementsByName("ownPhoneOrder.emergency");
	var keypadValue = document.getElementsByName("ownPhoneOrder.keypad")[0].value;

	var emergencyKeynameObj = document
			.getElementsByName("ownPhoneOrder.keyname" + keypadValue)[0];
	var emergencyKeynumberObj = document
			.getElementsByName("ownPhoneOrder.keynumber" + keypadValue)[0];

	if (emergencyKeynameObj != null && emergencyKeynumberObj != null) {

		if (emergencyRadio != null) {

			for ( var i = 0; i < emergencyRadio.length; i++) {

				if (emergencyRadio[i].value == "有" && emergencyRadio[i].checked) {

					emergencyKeynameObj.value = "110";
					emergencyKeynumberObj.value = "110";

					emergencyKeynameObj.readOnly = true;
					emergencyKeynumberObj.readOnly = true;

					staticEmergencyValue = emergencyRadio[i].value;
					staticCurrentEmergencyKeyNumber = keypadValue;

				} else if (emergencyRadio[i].value == "无"
						&& emergencyRadio[i].checked) {

					if (staticEmergencyValue == "有") {

						emergencyKeynameObj.value = "";
						emergencyKeynumberObj.value = "";

						emergencyKeynameObj.readOnly = false;
						emergencyKeynumberObj.readOnly = false;

						staticEmergencyValue = emergencyRadio[i].value;
						staticCurrentEmergencyKeyNumber = null;
					}
				}
			}
		}
	}
}

function preview() {
	var keypadValue = document.getElementsByName("ownPhoneOrder.keypad")[0].value;
	var phonecolorValue = document
			.getElementsByName("ownPhoneOrder.phonecolor")[0].value;
	var pnonestyleValue = document
			.getElementsByName("ownPhoneOrder.phonestyle")[0].value;

	var previewImg = document.getElementById("previewimg");
	var oldImgUrl = previewImg.src;
	var imageUrl = oldImgUrl.slice(0, oldImgUrl.lastIndexOf("/") + 1);
	
	var namesAndNumbersImg = document.getElementById("namesandnumbersimg");
	var oldNamesAndNumbersImgUrl = namesAndNumbersImg.src;
	var namesAndNumbersImgUrl = oldNamesAndNumbersImgUrl.slice(0, oldNamesAndNumbersImgUrl.lastIndexOf("/") + 1);

	if (keypadValue == null || keypadValue.length == 0) {

		imageUrl += "default_phone";
		namesAndNumbersImgUrl += "2keypads";
	} else {

		imageUrl += keypadValue;
		namesAndNumbersImgUrl += keypadValue + "keypads";

		if (phonecolorValue != null && phonecolorValue.length != 0) {

			switch (phonecolorValue) {

			case "粉色":
				phonecolorValue = "pink";
				break;
			case "蓝色":
				phonecolorValue = "blue";
				break;
			case "绿色":
				phonecolorValue = "green";
				break;
			case "橘色":
				phonecolorValue = "orange";
				break;
			case "红色":
				phonecolorValue = "red";
				break;
			case "黑色":
				phonecolorValue = "black";
				break;
			}

			imageUrl += phonecolorValue;

			if (pnonestyleValue != null && pnonestyleValue.length != 0) {

				switch (pnonestyleValue) {

				case "朴素":
					pnonestyleValue = "";
					break;
				case "图片":
					pnonestyleValue = "image";
					break;
				case "花纹":
					pnonestyleValue = "pattern";
					break;
				}

				imageUrl += pnonestyleValue;
			}
		}
	}

	imageUrl += ".gif";
	namesAndNumbersImgUrl += ".gif";

	previewImg.src = imageUrl;
	namesAndNumbersImg.src = namesAndNumbersImgUrl;

	showNamesAndNumbersInuput();
	showTotalPrice();
	changeColor();
}

function showNamesAndNumbersInuput() {
	var keypadValue = document.getElementsByName("ownPhoneOrder.keypad")[0].value;

	if (keypadValue != null && keypadValue.length != 0) {
		for ( var k = 1; k <= 12; k++) {
			var inputObj = document.getElementById("namesandnumbersinput" + k);
			var lineObj = document.getElementById("namesandnumbersline" + k);

			if (k <= keypadValue) {
				if (inputObj != null) {
					inputObj.style.display = "table-row";
				}

				if (lineObj != null) {
					lineObj.style.display = "table-row";
				}
			} else {
				if (inputObj != null) {
					inputObj.style.display = "none";
				}

				if (lineObj != null) {
					lineObj.style.display = "none";
				}
			}
		}
	} else {
		for ( var k = 1; k <= 12; k++) {
			var inputObj = document.getElementById("namesandnumbersinput" + k);
			var lineObj = document.getElementById("namesandnumbersline" + k);

			if (inputObj != null) {
				inputObj.style.display = "none";
			}

			if (lineObj != null) {
				lineObj.style.display = "none";
			}
		}
	}
}

function showTotalPrice() {
	var price = 200;

	var keypadValue = document.getElementsByName("ownPhoneOrder.keypad")[0].value;

	var priceObj = document.getElementById("ownphoneorderprice");

	if (priceObj != null) {
		if (keypadValue != null && keypadValue.length != 0) {

			price += (keypadValue - 2) / 2 * 50;
		}
	}

	priceObj.innerHTML = price;

	document.getElementsByName("ownPhoneOrder.price")[0].value = price;
}

function changeColor() {
	var phonecolorValue = document
			.getElementsByName("ownPhoneOrder.phonecolor")[0].value;

	var colorRgb = "#000000";

	if (phonecolorValue != null && phonecolorValue.length != 0) {
		switch (phonecolorValue) {

		case "粉色":
			colorRgb = "rgb(236,0,140)";
			break;
		case "蓝色":
			colorRgb = "rgb(32,114,181)";
			break;
		case "绿色":
			colorRgb = "rgb(5,169,81)";
			break;
		case "橘色":
			colorRgb = "rgb(244,116,33)";
			break;
		case "红色":
			colorRgb = "rgb(226,39,28)";
			break;
		case "黑色":
			colorRgb = "rgb(54,49,48)";
			break;
		}
	}

	for ( var k = 1; k <= 4; k++) {
		var changeObj = document.getElementById("changecolortitle" + k);

		if (changeObj != null) {
			changeObj.style.color = colorRgb;
		}
	}

	var previewTitle = document.getElementById("previewownphoneimg");
	if (previewTitle != 0) {
		previewTitle.style.color = colorRgb;
	}

	var totalPriceObj = document.getElementById("ownphoneorderprice");
	if (totalPriceObj != 0) {
		totalPriceObj.style.color = colorRgb;
	}
}

function feedBack() {

	var keypadValue = document.getElementsByName("ownPhoneOrder.keypad")[0].value;
	var phonecolorValue = document
			.getElementsByName("ownPhoneOrder.phonecolor")[0].value;
	var pnonestyleValue = document
			.getElementsByName("ownPhoneOrder.phonestyle")[0].value;

	if (keypadValue != null && keypadValue.length != 0
			&& phonecolorValue != null && phonecolorValue.length != 0
			&& pnonestyleValue != null && pnonestyleValue.length != 0) {

		showThenHide("ownphoneorderform", "designownphonelist");
		showElement("orderButton");
	}

	preview();
}

function autoFixedElement(ObjId) {

	var obj = document.getElementById(ObjId);
	var top = getTop(obj);
	var left = getLeft(obj);

	window.onscroll = function() {
		// 获取窗口的scrollTop
		var bodyScrollTop = document.documentElement.scrollTop
				|| document.body.scrollTop;

		if (bodyScrollTop > top) {
			/*
			 * 当窗口的滚动高度大于浮动层距离窗口顶部的距离时，也就是原理中说的第一种情况
			 * 我们把这个浮动层position值设为fixed，top值设为0px，让它定位在窗口顶部
			 */
			obj.style.position = "fixed";
			obj.style.top = "0px";
			obj.style.left = left + "px";
		} else {
			/*
			 * 当窗口的滚动高度小于浮动层距离窗口顶部的距离时，也就是原理中说的第一种情况
			 * 我们把这个浮动层position值设为static或为空，让它回归文档流 让它回到原来的位置上去
			 */
			obj.style.position = "static";
		}
	}
}

function getTop(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null)
		offset += getTop(e.offsetParent);
	return offset;
}

function getLeft(e) {
	var offset = e.offsetLeft;
	if (e.offsetParent != null)
		offset += getLeft(e.offsetParent);
	return offset;
}