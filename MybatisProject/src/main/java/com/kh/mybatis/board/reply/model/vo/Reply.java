package com.kh.mybatis.board.reply.model.vo;

import java.util.Date;

public class Reply {
	private int replyNo;
	private String replyContent;
	private int boardNo;
	private int replyWriter;
	private Date createDate;
	private String status;
	
	public Reply() {}

	
	
	public Reply(String replyContent, int boardNo, int replyWriter) {
		super();
		this.replyContent = replyContent;
		this.boardNo = boardNo;
		this.replyWriter = replyWriter;
	}



	public int getReplyNo() {
		return replyNo;
	}



	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}



	public String getReplyContent() {
		return replyContent;
	}



	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}



	public int getBoardNo() {
		return boardNo;
	}



	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

	public int getReplyWriter() {
		return replyWriter;
	}



	public void setReplyWriter(int replyWriter) {
		this.replyWriter = replyWriter;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", boardNo=" + boardNo
				+ ", boardWriter=" + replyWriter + ", createDate=" + createDate + ", status=" + status + "]";
	}
}
