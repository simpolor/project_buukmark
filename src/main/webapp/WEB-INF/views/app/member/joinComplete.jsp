<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="user-layout">
    <tiles:putAttribute name="user-body">
    	
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
    	
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
			
			function f_login(){
				var url = "${pageContext.request.contextPath}/member/login";
	        	$(location).attr('href', url);
			}
		</script>
		
		<div id="section">
			
			<div class="member-nav">
				<div class="member-nav-title">
					<span>회원가입 완료</span>
				</div>
			</div>
			
			<div class="member-field">
				<div class="member-field-form">
					<div class="member-field-text2">
						<span>${memberVO.member_name}</span> 님<br />
						회원가입이 완료되었습니다.
					</div>
					<div class="member-field-btn">
						<button onclick="f_login(); return false;">로그인 화면으로 이동</button>
					</div>
				</div>
			</div>
			
		</div><!-- #section -->
	
	</tiles:putAttribute>
</tiles:insertDefinition>