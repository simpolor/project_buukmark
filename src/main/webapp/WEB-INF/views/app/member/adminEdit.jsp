<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="admin-layout">
    <tiles:putAttribute name="admin-body">
    
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
    
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
		
			function f_edit(){
				if(isNull($("#member_name").val())){
					alert('이름을 입력해주세요.');	
					return false;
				}
				if(isNull($("#member_nickname").val())){
					alert('닉네임을 입력해주세요.');	
					return false;
				}
				if(isNull($("#member_email").val())){
					alert('이메일을 입력해주세요.');
					return false;
				}
				$("#editForm").submit();
			}
		</script>
		
		<div id="section">
			<div class="member-nav">
				<div class="member-nav-title">
					<span>회원가입</span>
				</div>
			</div>
			
			<div class="member-field">
				<form:form id="editForm" action="${pageContext.request.contextPath}/admin/member/edit" method="post" commandName="memberVO" >
					<div class="member-field-form">
						<div class="member-field-text">
							<span>아이디</span>${memberVO.member_id}
						</div>
						<div class="member-field-input2">
							<span>성명</span><form:input path="member_name" placeholder="이름" />
						</div>
						<div class="member-field-input2">
							<span>닉네임</span><form:input path="member_nickname" placeholder="닉네임" />
						</div>
						<div class="member-field-input2">
							<span>이메일</span><form:input path="member_email" placeholder="이메일" />
						</div>
						<div class="member-field-btn">
							<button class="member-login-btn" onclick="f_edit(); return false;">회원수정</button>
						</div>
					</div>
				</form:form>
			</div>
			
			<div class="member-link">
				<div class="member-link-group">
					<a href="${pageContext.request.contextPath}/admin/member/view">뒤로가기</a>
				</div>
			</div>
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>