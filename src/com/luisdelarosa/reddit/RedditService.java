package com.luisdelarosa.reddit;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface RedditService {
	@GET("/r/{subreddit}/hot.json")
	
	// Uses async callback
	void listHotPostsInSubreddit(@Path("subreddit") String subreddit, Callback<RedditResponse> callback);
}
