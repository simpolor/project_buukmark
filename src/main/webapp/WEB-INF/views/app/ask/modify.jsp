<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="user-layout">
    <tiles:putAttribute name="user-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ask.css">
		
		<script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
  		<script>
  			tinymce.init({ 
 				selector: '#ask_content',
 				menubar: false,
  				width: 888,
  				height: 500
  			});
  		</script>		
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
			
			function f_modify(){
				if(isNull($("#ask_title").val())){
					alert('제목를 입력해주세요.');	
					return false;
				}
				
				tinyMCE.triggerSave(); // tinymce editor
				if(isNull($("#ask_content").val())){
					alert('내용을 입력해주세요.');	
					return false;
				}
				$("#askModifyForm").submit();
			}
			
		</script>
	
		<div id="section">
			<div class="ask-nav">
				<div class="ask-nav-title">
					<span>문의사항 수정</span>
				</div>
			</div>
			
			<div class="ask-write">
				<form:form id="askModifyForm" action="${pageContext.request.contextPath}/ask/modify" method="post" commandName="askVO">
					<form:hidden path="ask_seq" />
					<table>
						<caption>게시판 글쓰기</caption>
						<colgroup>
							<col width="100%" />
						</colgroup>
						<thead>
							<tr>
								<th><form:input path="ask_title" class="ask-write-title" placeholder="제목" /></th>
							</tr>
						</thead>
						<tbody>
							<tr style="height: 40px">
								<td>
									<form:radiobutton path="secret_yn" value="N" label="공개" />
									<form:radiobutton path="secret_yn" value="Y" label="비공개" />
								</td>
							</tr>
							<tr>
								<td><form:textarea path="ask_content" class="ask-write-content" placeholder="내용" /></td>
							</tr>
						</tbody>
					</table>		
				</form:form>			
			</div>
			
			<div class="ask-btn">
				<div class="ask-btn-list">
					<div class="ask-btn-group">
						<a href="#" onclick="f_modify(); return false;">완료</a>
						<a href="#"  onclick="history.back(); return false;">취소</a>
					</div>
				</div>
			</div>
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>