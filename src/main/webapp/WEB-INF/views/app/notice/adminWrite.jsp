<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="admin-layout">
    <tiles:putAttribute name="admin-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice.css">
		
		<script src="${pageContext.request.contextPath}/js/tinymce/tinymce.min.js"></script>
  		<script>
  			tinymce.init({ 
 				selector: '#notice_content',
 				menubar: false,
  				width: 888,
  				height: 450
  			});
  		</script>
  		
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
			
			function f_write(){
				if(isNull($("#notice_title").val())){
					alert('제목를 입력해주세요.');	
					return false;
				}
				
				tinyMCE.triggerSave(); // tinymce editor
				if(isNull($("#notice_content").val())){
					alert('내용을 입력해주세요.');	
					return false;
				}
				$("#noticeWriteForm").submit();
			}
			
		</script>
		
		<div id="section">
			<div class="notice-nav">
				<div class="notice-nav-title">
					<span>(관리자) 공지사항 등록</span>
				</div>
			</div>
			
			<div class="notice-write">
				<form:form id="noticeWriteForm" action="${pageContext.request.contextPath}/admin/notice/write" method="post" commandName="noticeVO">
					<table>
						<caption>게시판 글쓰기</caption>
						<colgroup>
							<col width="100%" />
						</colgroup>
						<thead>
							<tr>
								<th><form:input path="notice_title" class="notice-write-title" placeholder="제목" /></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><form:textarea path="notice_content" class="notice-write-content" placeholder="내용" /></td>
							</tr>
						</tbody>
					</table>		
				</form:form>			
			</div>
			
			<div class="notice-btn">
				<div class="notice-btn-list">
					<div class="notice-btn-group">
						<a href="#" onclick="f_write(); return false;">완료</a>
						<a href="#" onclick="history.back(); return false;">취소</a>
					</div>
				</div>
			</div>
			
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>