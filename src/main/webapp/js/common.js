// 공백 검사
function isNull(s) {
	if (s.replace(/(^\s*)|(\s*$)/g, "") && s != null) {
		return false;
	} else {
		return true;
	}
}

// 공백 검사
function isEqual(s1, s2) {
	if (s1 == s2){
		return true;
	}else{
		return false;
	}
}
