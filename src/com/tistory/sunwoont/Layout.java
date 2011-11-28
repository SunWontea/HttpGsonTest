package com.tistory.sunwoont;

import java.util.ArrayList;

public class Layout {
	private String layout;
	private String halfLayout;
	private ArrayList<Article> articles;

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getHalfLayout() {
		return halfLayout;
	}

	public void setHalfLayout(String halfLayout) {
		this.halfLayout = halfLayout;
	}

	public ArrayList<Article> getArticles() {
		return articles;
	}

	public void setArticles(ArrayList<Article> articles) {
		this.articles = articles;
	}
}
