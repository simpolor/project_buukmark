function validateEmail(email) {
	var regex  = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	return regex.test(email);
}

function validatePassword(password) {
	var regex  = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{7,15}$/;
	return regex.test(password);
}

function validateIdentify(id) {
	var regex  = /^[a-z]+[a-z0-9]{5,19}$/g;
	return regex.test(id);
}