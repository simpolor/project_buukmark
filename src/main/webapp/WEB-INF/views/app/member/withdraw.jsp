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
			
			function f_withdraw(){
				if(isNull($("#member_pw").val())){
					alert('비밀번호를 입력해주세요.');
					return false;
				}
				$("#withdrawForm").submit();
			}
		</script>
		
		<div id="section">
			<div class="member-nav">
				<div class="member-nav-title">
					<span>회원탈퇴</span>
				</div>
			</div>
			
			<div class="member-field">
				<form:form id="withdrawForm" action="${pageContext.request.contextPath}/member/withdraw" method="post" commandName="memberVO">
					<form:hidden path="member_id" />
					
					<div class="member-field-form">
						<div class="member-field-textarea">
							<p>
								탈퇴한 계정은 다시 가입하실 수 없습니다.
							</p>
						</div>
						<div class="member-field-text">
							<span>${memberVO.member_id}</span>
						</div>
						<div class="member-field-input">
							<form:password path="member_pw" placeholder="비밀번호" />
						</div>
						<div class="member-login-btn">
							<button class="member-field-btn" onclick="f_withdraw(); return false;">회원탈퇴</button>
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