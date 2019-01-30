package com.specter.inspecter.ui.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class IndexImageMultipleItemEntity implements MultiItemEntity {
    public static final int INDEX_TYPE_IMAGE = 0;
    public static final int INDEX_TYPE_VIDEO = 1;

    private String avatarUrl;
    private String userName;
    private String postTime;
    private String postLocation;
    private String pictureUrl;
    private String videoUrl;
    private String likeNumber;
    private String commentNumber;

    private int itemType;

    public IndexImageMultipleItemEntity(int itemType, String avatarUrl, String userName, String postTime, String postLocation, String pictureUrl, String videoUrl, String likeNumber, String commentNumber) {
        this.avatarUrl = avatarUrl;
        this.userName = userName;
        this.postTime = postTime;
        this.postLocation = postLocation;
        this.pictureUrl = pictureUrl;
        this.videoUrl = videoUrl;
        this.likeNumber = likeNumber;
        this.commentNumber = commentNumber;
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public static int getIndexTypeImage() {
        return INDEX_TYPE_IMAGE;
    }

    public static int getIndexTypeVideo() {
        return INDEX_TYPE_VIDEO;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(String postLocation) {
        this.postLocation = postLocation;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
