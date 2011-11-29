package com.tistory.sunwoont;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

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

public class GsonSum extends Activity implements OnClickListener {
	private Button btn;
	private TextView txt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sum);

		btn = (Button) findViewById(R.id.sumbtn);
		txt = (TextView) findViewById(R.id.subtxt);

		btn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.sumbtn:

			Integer arr_sum = 0;
			String url = "http://211.114.147.131/boardfree/GsonSum.json";

			try {
				InputStream source = retrieveStream(url);
				Reader reader = new InputStreamReader(source);

				Gson gson = new Gson();
				
				ArrSum1 arr = gson.fromJson(reader, ArrSum1.class);
				
				for(Sum sum : arr.getGsonSum()){
					arr_sum += sum.getSum();
				}
				txt.setText("arr_Sum = "+arr_sum);
			} catch (Exception e) {
				txt.setText(e.getMessage());
			}

			break;
		}
	}

	private InputStream retrieveStream(String url) {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost getRequest = new HttpPost(url);

		try {
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();
			
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

	public class ArrSum1 {
		private ArrayList<Sum> GsonSum;

		public ArrayList<Sum> getGsonSum() {
			return GsonSum;
		}

		public void setGsonSum(ArrayList<Sum> gsonSum) {
			GsonSum = gsonSum;
		}
	}

	public class Sum {
		private Integer sum;

		public Integer getSum() {
			return sum;
		}

		public void setSum(Integer sum) {
			this.sum = sum;
		}
	}
}
