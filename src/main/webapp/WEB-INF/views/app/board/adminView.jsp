<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:insertDefinition name="admin-layout">
    <tiles:putAttribute name="admin-body">
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
		
		<script type="text/javascript">
			$(document).ready(function() { 
				var alertMessage = '${alertMessage}';
				
				if(!isNull(alertMessage)){
					alert(alertMessage);
				}
			});
			
			function f_modify(){
				$("#boardViewForm").attr("action", "${pageContext.request.contextPath}/admin/board/modify");
				$("#boardViewForm").attr("method", "get");
				$("#boardViewForm").submit();
			}
			
			function f_delete(){
				if(confirm("등록한 글을 삭제하시겠습니까?")){
					$("#boardViewForm").attr("action", "${pageContext.request.contextPath}/admin/board/delete");
					$("#boardViewForm").attr("method", "post");
					$("#boardViewForm").submit();
				}
			}
			
			function f_replyWrite(){
				if(isNull($("#reply_content").val())){
					alert('댓글 내용을 입력해주세요.');	
					return false;
				}
				
				var memberId = '${sessionScope.SESSION_MEMBER_ID}';
				var ajaxUrl = '${pageContext.request.contextPath}/board/replyWrite';
				var ajaxData = {
					"board_seq": $('.reply-write-body').find('#board_seq').val(), 
					"reply_content": $("#reply_content").val()
				};
				
				$.ajax({
					type : "POST",
					url : ajaxUrl,
					data : ajaxData,
					dataType : "json",
					success : function(data){
						var result = data.result;
						if(result == 'success'){
							var html = '';
							var list = data.list;
							for(var i=0; i<list.length; i++){
								html += '<div id="reply_list_group_'+list[i].reply_seq+'" class="reply-list-group">';
								html += '	<div class="reply-list-header">';
								html += '		<span>'+list[i].reg_id+'</span>';	
								if(memberId == list[i].reg_id){
									html += '	<a href="#" onclick="f_replyModifyForm(\''+list[i].reply_seq+'\'); return false;">수정</a>';
									html += '	<a href="#" onclick="f_replyDelete(\''+list[i].reply_seq+'\'); return false;">삭제</a>';
								}
								html += '	</div>';
								html += '	<div class="reply-list-body">';
								html += '		<input type="hidden" id="board_seq_'+list[i].reply_seq+'" value="'+list[i].board_seq+'" />';
								html += '		<input type="hidden" id="reply_seq_'+list[i].reply_seq+'" value="'+list[i].reply_seq+'" />';
								html += '		<div id="reply_content_'+list[i].reply_seq+'">'+list[i].reply_content+'</div>';
								html += '	</div>';
								html += '</div>';
							}
							
							if(html != ''){
								$('.reply-list').html(html);
								$('#reply_content').val('');	
							}
						}
					}
				});
			}
			
			function f_replyModifyForm(replySeq){
				var memberId = '${sessionScope.SESSION_MEMBER_ID}';
				var boardSeqId = '#board_seq_'+replySeq;
				var replySeqId = '#reply_seq_'+replySeq;
				var replyContentId = '#reply_content_'+replySeq
				var replyListGroupId = '#reply_list_group_'+replySeq;
				
				var html = '';
				html += '<div id="reply_list_group_'+replySeq+'" class="reply-list-group">';
				html += '	<div class="reply-list-header">';
				html += '		<span>'+memberId+'</span>';	
				html += '		<a href="#" onclick="f_replyModify(\''+replySeq+'\'); return false;">완료</a>';
				html += '		<a href="#" onclick="f_replyCancel(\''+replySeq+'\'); return false;">취소</a>';
				html += '	</div>';
				html += '	<div class="reply-list-body">';
				html += '		<input type="hidden" id="board_seq_'+replySeq+'" value="'+$(boardSeqId).val()+'" />';
				html += '		<input type="hidden" id="reply_seq_'+replySeq+'" value="'+$(replySeqId).val()+'" />';
				html += '		<textarea id="reply_content_'+replySeq+'">'+$(replyContentId).text()+'</textarea>';
				html += '	</div>';
				
				if(html != ''){
					$(replyListGroupId).html(html);
				}
			}
			
			function f_replyModify(replySeq){
				var memberId = '${sessionScope.SESSION_MEMBER_ID}';
				var boardSeqId = '#board_seq_'+replySeq;
				var replySeqId = '#reply_seq_'+replySeq;
				var replyContentId = '#reply_content_'+replySeq
				
				if(isNull($(replyContentId).val())){
					alert('댓글 내용을 입력해주세요.');	
					return false;
				}
				
				var ajaxUrl = '${pageContext.request.contextPath}/board/replyModify';
				var ajaxData = {
					"reply_seq": $(replySeqId).val(), 
					"board_seq": $(boardSeqId).val(), 
					"reply_content": $(replyContentId).val()
				};
				
				$.ajax({
					type : "POST",
					url : ajaxUrl,
					data : ajaxData,
					dataType : "json",
					success : function(data){
						var result = data.result;
						if(result == 'success'){
							var html = '';
							var list = data.list;
							for(var i=0; i<list.length; i++){
								html += '<div id="reply_list_group_'+list[i].reply_seq+'" class="reply-list-group">';
								html += '	<div class="reply-list-header">';
								html += '		<span>'+list[i].reg_id+'</span>';	
								if(memberId == list[i].reg_id){
									html += '	<a href="#" onclick="f_replyModifyForm(\''+list[i].reply_seq+'\'); return false;">수정</a>';
									html += '	<a href="#" onclick="f_replyDelete(\''+list[i].reply_seq+'\'); return false;">삭제</a>';
								}
								html += '	</div>';
								html += '	<div class="reply-list-body">';
								html += '		<input type="hidden" id="board_seq_'+list[i].reply_seq+'" value="'+list[i].board_seq+'" />';
								html += '		<input type="hidden" id="reply_seq_'+list[i].reply_seq+'" value="'+list[i].reply_seq+'" />';
								html += '		<div id="reply_content_'+list[i].reply_seq+'">'+list[i].reply_content+'</div>';
								html += '	</div>';
								html += '</div>';
							}
							
							if(html != ''){
								$('.reply-list').html(html);
								$('#reply_content').val('');	
							}
						}
					}
				});
			}
			
			function f_replyCancel(replySeq){
				var memberId = '${sessionScope.SESSION_MEMBER_ID}';
				var boardSeqId = '#board_seq_'+replySeq;
				var replySeqId = '#reply_seq_'+replySeq;
				var replyContentId = '#reply_content_'+replySeq
				var replyListGroupId = '#reply_list_group_'+replySeq;
				
				var html = '';
				html += '<div id="reply_list_group_'+replySeq+'" class="reply-list-group">';
				html += '	<div class="reply-list-header">';
				html += '		<span>'+memberId+'</span>';	
				html += '		<a href="#" onclick="f_replyModifyForm(\''+replySeq+'\'); return false;">수정</a>';
				html += '		<a href="#" onclick="f_replyDelete(\''+replySeq+'\'); return false;">삭제</a>';
				html += '	</div>';
				html += '	<div class="reply-list-body">';
				html += '		<input type="hidden" id="board_seq_'+replySeq+'" value="'+$(boardSeqId).val()+'" />';
				html += '		<input type="hidden" id="reply_seq_'+replySeq+'" value="'+$(replySeqId).val()+'" />';
				html += '		<div id="reply_content_'+replySeq+'">'+$(replyContentId).text()+'</div>';
				html += '	</div>';
				
				if(html != ''){
					$(replyListGroupId).html(html);
				}
			}
			
			function f_replyDelete(reply_seq){
				var memberId = '${sessionScope.SESSION_MEMBER_ID}';
				var boardSeqId = '#board_seq_'+reply_seq;
				var replySeqId = '#reply_seq_'+reply_seq;
				var ajaxUrl = '${pageContext.request.contextPath}/board/replyDelete';
				var ajaxData = {
					"board_seq": $(boardSeqId).val(), 
					"reply_seq": $(replySeqId).val()
				};
				
				$.ajax({
					type : "POST",
					url : ajaxUrl,
					data : ajaxData,
					dataType : "json",
					success : function(data){
						var result = data.result;
						if(result == 'success'){
							var html = '';
							var list = data.list;
							for(var i=0; i<list.length; i++){
								html += '<div id="reply_list_group_'+list[i].reply_seq+'" class="reply-list-group">';
								html += '	<div class="reply-list-header">';
								html += '		<span>'+list[i].reg_id+'</span>';	
								if(memberId == list[i].reg_id){
									html += '	<a href="#" onclick="f_replyModifyForm(\''+list[i].reply_seq+'\'); return false;">수정</a>';
									html += '	<a href="#" onclick="f_replyDelete(\''+list[i].reply_seq+'\'); return false;">삭제</a>';
								}
								html += '	</div>';
								html += '	<div class="reply-list-body">';
								html += '		<input type="hidden" id="board_seq_'+list[i].reply_seq+'" value="'+list[i].board_seq+'" />';
								html += '		<input type="hidden" id="reply_seq_'+list[i].reply_seq+'" value="'+list[i].reply_seq+'" />';
								html += '		<div id="reply_content_'+list[i].reply_seq+'">'+list[i].reply_content+'</div>';
								html += '	</div>';
								html += '</div>';
							}
							
							if(html != ''){
								$('.reply-list').html(html);
								$('#reply_content').val('');	
							}
						}
					}
				});
			}
			
		</script>
		
		<div id="section">
			<div class="board-nav">
				<div class="board-nav-title">
					<span>(관리자) 자유게시판 상세보기</span>
				</div>
			</div>
			
			<div class="board-view">
				<form id="boardViewForm" action="${pageContext.request.contextPath}/admin/board/modify" method="get">
					<input type="hidden" id="board_seq" name="board_seq" value="${boardVO.board_seq}" />
					<table>
						<caption>자유게시판 상세보기</caption>
						<colgroup>
							<col width="100%" />
						</colgroup>
						<thead>
							<tr>
								<th class="board-view-title">${boardVO.board_title}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="board-view-content">${boardVO.board_content}</td>
							</tr>
						</tbody>
					</table>	
				</form>				
			</div>
			
			<div class="board-btn">
				<div class="board-btn-list">
					<div class="board-btn-group">
						<a href="#" onclick="f_modify(); return false;">수정</a>
						<a href="#" onclick="f_delete(); return false;">삭제</a>
						<a href="${pageContext.request.contextPath}/admin/board/list">목록</a>
					</div>
				</div>
			</div>
			
			<div class="reply-list">
				<c:forEach var="boardReplyList" items="${boardReplyList}">
					<div id="reply_list_group_${boardReplyList.reply_seq}" class="reply-list-group">
						<div class="reply-list-header">
							<span>${boardReplyList.reg_id}</span>
							<c:if test="${! empty sessionScope.SESSION_MEMBER_ID && boardReplyList.reg_id eq sessionScope.SESSION_MEMBER_ID}">
								<a href="#" onclick="f_replyModifyForm('${boardReplyList.reply_seq}'); return false;">수정</a>
								<a href="#" onclick="f_replyDelete('${boardReplyList.reply_seq}'); return false;">삭제</a>
							</c:if>
						</div>
						<div class="reply-list-body">
							<input type="hidden" id="board_seq_${boardReplyList.reply_seq}" value="${boardReplyList.board_seq}" />
							<input type="hidden" id="reply_seq_${boardReplyList.reply_seq}" value="${boardReplyList.reply_seq}" />
							<div id="reply_content_${boardReplyList.reply_seq}">${boardReplyList.reply_content}</div> 
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="reply-write">
				<div class="reply-write-group">
					<c:choose>
						<c:when test="${! empty sessionScope.SESSION_MEMBER_ID}">
							<div class="reply-write-header">
								<span>${sessionScope.SESSION_MEMBER_ID}</span>
							</div>
							<div class="reply-write-body">
								<input type="hidden" id="board_seq" value="${boardVO.board_seq}" />
								<textarea id="reply_content" placeholder="내용"></textarea>
							</div>
							<div class="reply-write-btn">
								<a href="#" onclick="f_replyWrite(); return false;">댓글 등록</a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="reply-write-guide">
								<span>로그인을 하시면 답변을 등록할 수 있습니다.</span>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div><!-- #section -->
		
	</tiles:putAttribute>
</tiles:insertDefinition>