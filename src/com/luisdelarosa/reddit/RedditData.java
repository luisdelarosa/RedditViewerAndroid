package com.luisdelarosa.reddit;

import java.util.List;

public class RedditData {

	public String modhash;
	public List<RedditPost> children;
	
	public int getPostCount() {
		if (children == null) { return 0; }
		return children.size();
	}

	public RedditPost getPostAt(int position) {
		if (children == null) { return null; }
		return children.get(position);
	}
	
}
