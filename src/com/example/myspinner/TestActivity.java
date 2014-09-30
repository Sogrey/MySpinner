/**
 * 
 */
package com.example.myspinner;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.Toast;

/**
 * @author Administrator
 * 
 */
public class TestActivity extends Activity {

	private Button but_menu;
	private Button open_id;
	private Button save_id;
	View contentView;
	private PopupWindow m_popupWindow;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		init();
		setListener();

	}

	private void init() {
		contentView = getLayoutInflater().inflate(R.layout.popupmenu, null,
				true);
		but_menu = (Button) findViewById(R.id.but_menu);
		open_id = (Button) contentView.findViewById(R.id.btn_popup_information);
		save_id = (Button) contentView.findViewById(R.id.btn_popup_quote);
		// PopupWindow弹出的窗口显示的view,第二和第三参数：分别表示此弹出窗口的大小
		m_popupWindow = new PopupWindow(contentView, LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT, true);

		m_popupWindow.setBackgroundDrawable(new BitmapDrawable());// 有了这句才可以点击返回（撤销）按钮dismiss()popwindow
		m_popupWindow.setOutsideTouchable(true);
		m_popupWindow.setAnimationStyle(R.style.PopupAnimation);
	}

	private void setListener() {
		contentView.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				m_popupWindow.dismiss();
			}
		});
		// m_popupWindow = new PopupWindow();
		but_menu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {

					if (m_popupWindow.isShowing()) {

						m_popupWindow.dismiss();
					}
					m_popupWindow.showAsDropDown(v);

				} catch (Exception e) {
					Toast.makeText(TestActivity.this, e.getMessage(),
							Toast.LENGTH_SHORT);
				}
			}
		});
		open_id.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				m_popupWindow.dismiss();
				Toast.makeText(TestActivity.this, "打开被触发", Toast.LENGTH_SHORT)
						.show();
			}

		});
		save_id.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				m_popupWindow.dismiss();
				Toast.makeText(TestActivity.this, "保存被触发", Toast.LENGTH_SHORT)
						.show();
			}

		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (m_popupWindow != null && m_popupWindow.isShowing()) {
				m_popupWindow.dismiss();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);

	}

}
