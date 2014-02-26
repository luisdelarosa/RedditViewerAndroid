package com.luisdelarosa.reddit;

public class RedditResponse {

	public String kind;
	public RedditData data;
	
	public int getPostCount() {
		return data.getPostCount();
	}

	public RedditPost getPostAt(int position) {
		return data.getPostAt(position);
	}
}
