package com.luisdelarosa.reddit;

public class RedditPost {

	public RedditPostData data;

	public String getTitle() {
		if (data == null) { return ""; }
		return data.title;
	}
}
