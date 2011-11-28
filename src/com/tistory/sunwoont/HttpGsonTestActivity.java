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
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

public class HttpGsonTestActivity extends Activity implements OnClickListener {
	private Button btn;
	private Button btn1;
	private TextView txt;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);

		txt = (TextView) findViewById(R.id.txt1);
		btn = (Button) findViewById(R.id.btn);
		btn1 = (Button)findViewById(R.id.btn1);

		btn.setOnClickListener(this);
		btn1.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn:
			String url = "http://211.114.147.131/boardfree/GsonEx.json";

			try {
				String txt1 = "�ֹ� ���\n";

				InputStream source = retrieveStream(url);
				Reader reader = new InputStreamReader(source);

				Gson gson = new Gson();// Gson ��ü �� �ν��Ͻ� ����.
				prod prod = gson.fromJson(reader, prod.class);// �ٸ�
																// Ŭ����(InnerClass)��
																// �����Ͽ�
																// �װ��� Json������
																// ����.

				for (Item item : prod.getComputer()) {// for���� ������ ������ �´� �迭
														// name�� ���� ��
					txt1 += "��ǰ��:" + item.getProduct() + ",������:"
							+ item.getMaker() + ",����:" + item.getPrice() + "\n";
				}

				txt.setText(txt1);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				txt.setText(e.getMessage());
			}
			break;
		case R.id.btn1:
			Intent intent = new Intent(getApplication(), InternetGsonTest.class);
			startActivity(intent);
			break;
		}
	}

	private InputStream retrieveStream(String url) {

		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost getRequest = new HttpPost(url);

		try {
			HttpResponse getResponse = client.execute(getRequest);
			final int statusCode = getResponse.getStatusLine().getStatusCode();//url�� ���� ���¸� ���

			if (statusCode != HttpStatus.SC_OK) {
				Log.w(getClass().getSimpleName(), "Error " + statusCode
						+ " for URL " + url);
				return null;

			}
			HttpEntity getResponseEntity = getResponse.getEntity();
			return getResponseEntity.getContent();

		} catch (IOException e) {
			getRequest.abort();//http ��û�� ��
			Log.w(getClass().getSimpleName(), "Error for URL " + url, e);
		}
		return null;
	}

	public class prod {
		private ArrayList<Item> computer;

		public ArrayList<Item> getComputer() {
			return computer;
		}

		public void setComputer(ArrayList<Item> computer) {
			this.computer = computer;
		}
	}

	// �迭�� �ִ� ����. �迭��
	public class Item {
		private String Product;
		private String Maker;
		private Integer Price;

		public String getProduct() {
			return Product;
		}

		public void setProduct(String product) {
			Product = product;
		}

		public String getMaker() {
			return Maker;
		}

		public void setMaker(String maker) {
			Maker = maker;
		}

		public Integer getPrice() {
			return Price;
		}

		public void setPrice(Integer price) {
			Price = price;
		}
	}
}