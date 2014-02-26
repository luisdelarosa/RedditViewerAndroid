package com.luisdelarosa.redditandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.luisdelarosa.reddit.RedditPost;
import com.luisdelarosa.reddit.RedditResponse;

public class RedditResponseAdapter extends BaseAdapter implements ListAdapter {

	private RedditResponse mRedditResponse;
	private Context mContext;

	public RedditResponseAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		if (mRedditResponse == null) {
			return 0;
		}

		return mRedditResponse.getPostCount();
	}

	@Override
	public Object getItem(int position) {
		if (mRedditResponse == null) {
			return null;
		}
		if (position < 0) {
			return null;
		}
		if (position >= getCount()) {
			return null;
		}

		return mRedditResponse.getPostAt(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(android.R.layout.simple_list_item_1,
					parent, false);
		} else {
			rowView = convertView;
		}

		RedditPost post = (RedditPost) getItem(position);
		String title = post.getTitle();
		
		TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
		textView.setText(title);
		
		return rowView;
	}

	public void setRedditResponse(RedditResponse redditResponse) {
		this.mRedditResponse = redditResponse;
	}

}
