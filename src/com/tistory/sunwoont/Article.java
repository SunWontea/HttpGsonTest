package com.tistory.sunwoont;

public class Article {
	private Integer articleSeq;
	private String title;
	private String url;
	private String type;
	private Integer rank;
	private String thumbnailUrl;

	public Integer getArticleSeq() {
		return articleSeq;
	}

	public void setArticleSeq(Integer articleSeq) {
		this.articleSeq = articleSeq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
}
