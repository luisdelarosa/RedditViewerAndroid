package com.luisdelarosa.redditandroid;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.luisdelarosa.reddit.RedditPost;
import com.luisdelarosa.reddit.RedditResponse;
import com.luisdelarosa.reddit.RedditService;

public class MainActivity extends Activity {

	private static final String LOG_TAG = "MainActivity";
	private RedditResponseAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView);
		mAdapter = new RedditResponseAdapter(this);
		listView.setAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();

		loadHotPostsFromSubreddit("androiddev");
	}

	private void loadHotPostsFromSubreddit(String subreddit) {
		RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(
				"http://reddit.com").build();

		RedditService service = restAdapter.create(RedditService.class);
		
		Callback<RedditResponse> callback = new Callback<RedditResponse>() {
			@Override
			public void success(RedditResponse redditResponse, Response response) {
				processRedditResponse(redditResponse);
			}

			private void processRedditResponse(RedditResponse redditResponse) {
				Log.d(LOG_TAG, "reddit response kind:" + redditResponse.kind);	
				Log.d(LOG_TAG, "reddit response modhash:" + redditResponse.data.modhash);	
				
				Log.d(LOG_TAG, "reddit response children:" + redditResponse.data.children);
				Log.d(LOG_TAG, "# of posts:" + redditResponse.data.children.size());
				
				for (RedditPost post : redditResponse.data.children) {
					String title = post.data.title;
					Log.d(LOG_TAG, "post title:" + title);
				}
				
				mAdapter.setRedditResponse(redditResponse);
				mAdapter.notifyDataSetChanged();
			}
			
			@Override
			public void failure(RetrofitError error) {
				Log.d(LOG_TAG, "reddit error:" + error);	
			}

		};
		service.listHotPostsInSubreddit(subreddit, callback);
	}

}
