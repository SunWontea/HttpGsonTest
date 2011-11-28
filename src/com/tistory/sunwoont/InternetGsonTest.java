package com.tistory.sunwoont;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class InternetGsonTest extends Activity implements OnClickListener {
	private Button btn;
	private TextView text1;
	private TextView text2;
	private TextView text3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.internetgson);

		text1 = (TextView) findViewById(R.id.textView1);
		text2 = (TextView) findViewById(R.id.textView2);
		text3 = (TextView) findViewById(R.id.textView3);
		btn = (Button) findViewById(R.id.button1);

		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			String url = "http://211.114.147.131/boardfree/ExportGsonEx.json";

			try {

				String txt1 = null;
				String txt2 = null;
				// String txt3 = null;

				InputStream source = retrieveStream(url);
				Reader reader = new InputStreamReader(source);

				Gson gson = new Gson();// Gson 객체 및 인스턴스 생성.
				Status status = gson.fromJson(reader, Status.class);

				for (Layout layout : status.getPages()) {
					txt2 += "articales :"+layout.getArticles()+"\n Layout : "+layout.getLayout()
							+"\n halfLayout :"+layout.getHalfLayout();
					for (Article article : layout.getArticles()) {
						txt1 += "articleSeq :" + article.getArticleSeq()
								+ "\n title :" + article.getTitle() + "\n url :"
								+ article.getUrl() + "\n thumbnailUrl :"
								+ article.getThumbnailUrl() + "\n type :"
								+ article.getType() + "\n rank :"
								+ article.getRank();
					}
				}
				text1.setText(txt1);
				text2.setText(txt2);
				// text3.setText(txt3);
			} catch (Exception e) {
				text1.setText(e.getMessage());
			}
			break;
		}
	}

	private InputStream retrieveStream(String url) {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost getRequest = new HttpPost(url);

		try {
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();// url의
																				// 응답
																				// 상태를
																				// 얻어

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;

			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();

		} catch (IOException e) {
			getRequest.abort();// http 요청을 중
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}
		return null;
	}
}
