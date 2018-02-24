<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="user-layout">
    <tiles:putAttribute name="user-body">
    
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
    	
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsbn.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/prng4.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/rng.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/rsa.js"></script>
		<script type="text/javascript">

			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
		
			function f_login(){
				if(isNull($("#id").val())){
					alert('아이디를 입력해주세요.');	
					return false;
				}
				if(isNull($("#password").val())){
					alert('비밀번호를 입력해주세요.');
					return false;
				}
				
				// Server로부터 받은 공개키 입력
		   	 	var rsa = new RSAKey();
		    		rsa.setPublic("${modulus}", "${exponent}");
				$("#member_id").val(rsa.encrypt($("#id").val()));
				$("#member_pw").val(rsa.encrypt($("#password").val()));
				
				$("#hiddenLoginForm").submit();
			}
		</script>
		
		<div id="section">
			<div class="member-nav">
				<div class="member-nav-title">
					<span>로그인</span>
				</div>
			</div>
			
			<div class="member-login">
				<form id="loginForm">
					<div class="member-login-form">
						<div class="member-login-input">
							<input type="text" id="id" name="id" placeholder="아이디" />
						</div>
						<div class="member-login-input">
							<input type="password" id="password" name="password" placeholder="비밀번호" />
						</div>
						<div class="member-login-btn">
							<button onclick="f_login(); return false;">로그인</button>
						</div>
					</div>
				</form>
			</div>
			
			<div class="member-link">
				<div class="member-link-group">
					<a href="${pageContext.request.contextPath}/member/search">회원찾기</a>
					<a href="${pageContext.request.contextPath}/member/join">회원가입</a>
				</div>
			</div>
		</div><!-- #section -->
		
		<form:form id="hiddenLoginForm" commandName="memberVO" action="${pageContext.request.contextPath}/member/login" method="post">
			<form:hidden path="return_url" value="${returnUrl}" />
			<form:hidden path="member_id" />
			<form:hidden path="member_pw"/>
		</form:form>
				
	
	</tiles:putAttribute>
</tiles:insertDefinition>