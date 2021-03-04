package com.lhw.cop.board.service;

import com.lhw.cmm.CmmPagingModel;

public class Board extends CmmPagingModel {

    /**
     * 게시글 번호
     */
    private int boardNo;

    /**
     * 제목
     */
    private String title;

    /**
     * 내용
     */
    private String contents;

    /**
     * 최초등록일
     */
    private String firstDate;

    /**
     * 최초등록자 아이디
     */
    private String firstId;

    /**
     * 최초등록자
     */
    private String firstName;

    /**
     * 최종수정일
     */
    private String lastDate;

    /**
     * 최종수정자 아이디
     */
    private String lastId;

    /**
     * 최종수정자
     */
    private String lastName;

    /**
     * 삭제여부
     */
    private String deleteAt;

    public int getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public String getFirstId() {
        return firstId;
    }

    public void setFirstId(String firstId) {
        this.firstId = firstId;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLastId() {
        return lastId;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(String deleteAt) {
        this.deleteAt = deleteAt;
    }
}
