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
			
			function f_password(){
				if(isNull($("#member_pw_org").val())){
					alert('기존의 비밀번호를 입력해주세요.');
					return false;
				}
				if(isNull($("#member_pw").val())){
					alert('변경할 비밀번호를 입력해주세요.');
					return false;
				}
				if(isNull($("#member_pw2").val())){
					alert('변경할 비밀번호 재확인을 입력해주세요.');
					return false;
				}
				if(!isEqual($("#member_pw").val(), $("#member_pw2").val())){
					alert('변경할 비밀번호가 일치하지 않습니다.');
					$("#member_pw2").val("");
					return false;
				}
				$("#passwordForm").submit();
			}
		</script>
		
		<div id="section">
			<div class="member-nav">
				<div class="member-nav-title">
					<span>비밀번호 변경</span>
				</div>
			</div>
			
			<div class="member-field">
				<form:form id="passwordForm" action="${pageContext.request.contextPath}/member/password" method="post" commandName="memberVO" >
					<form:hidden path="member_id" />
					<div class="member-field-form">
						<div class="member-field-input">
							<form:password path="member_pw_org" placeholder="현재 비밀번호" />
						</div>
						<div class="member-field-input">
							<form:password path="member_pw" placeholder="변경할 비밀번호" />
						</div>
						<div class="member-field-input">
							<form:password path="member_pw2" placeholder="변경할 비밀번호 확인" />
						</div>
						<div class="member-field-btn">
							<button class="member-login-btn" onclick="f_edit(); return false;">비밀번호 변경</button>
						</div>
					</div>
				</form:form>
			</div>
			
			<div class="member-link">
				<div class="member-link-group">
					<a href="${pageContext.request.contextPath}/member/view">뒤로가기</a>
				</div>
			</div>
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>