package renny.SimpleCal;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import name.quq.SimpleCal.R;

/**
 * <p>Title: MainActivity</p>
 * <p>Description: Android计算器</p>
 * @version 3.0.0.150723 
 * @since JDK 1.8.0_45
 * @author renny
 * @date 2017-5-30
 */
public class MainActivity extends Activity implements OnPageChangeListener{
	private final String FILENAME = "history";
	
	private LayoutInflater inflater;
	private ViewGroup viewGroup;
	private ViewPager viewPager;  
	private MyPagerAdapter mAdapter;
	private ArrayList<View> viewList; 
	
	private CalLayout calView;
	private HistoryLayout hisView;
	private String calHistory;
	private String hisHistory;
	private boolean isFirstOpenHistory = true;
	private boolean isCalHisChanged = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        inflater = getLayoutInflater();
        calView = new CalLayout(this);
        hisView = new HistoryLayout(this);
        viewList = new ArrayList<View>();  
        viewList.add(calView);
        viewList.add(hisView);
        
        viewGroup = (ViewGroup)inflater.inflate(R.layout.activity_main, null);  
        viewPager = (ViewPager)viewGroup.findViewById(R.id.viewPager);  
        mAdapter = new MyPagerAdapter(this,viewList);
        viewPager.setAdapter(mAdapter); 
        
        viewPager.addOnPageChangeListener(this);
        setContentView(viewGroup); 
    }
    //当滑动状态改变时调用
    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub
        
    }

    //当当前页面被滑动时调用
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub
        
    }

    /* (non-Javadoc)
     * <p>Title: onPageSelected</p>
     * <p>Description: </p>
     * @see android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected(int)
     * @param position
     * @author renny
     * @date 2015-7-19 下午7:03:09
     */
    @Override
    public void onPageSelected(int position) {
    	switch(position){
    	case 0:
    		break;
    	case 1:
    		calHistory = calView.getHistory();
    		if ( isFirstOpenHistory ) {	//第一次打开，加载本地历史记录
    			isFirstOpenHistory = false;
    			hisHistory = hisView.load();
    			if ( calHistory != "") {
        			hisView.updateHistory(hisHistory + calHistory);
        			calView.clearCalHistory();
        		}
        		else if ( !TextUtils.isEmpty(hisHistory) ){
        			hisView.updateHistory(hisHistory);
        		}
    		}
    		else if ( calHistory != "") {
    			hisView.updateHistory(calHistory);
    			calView.clearCalHistory();
    		}
    		break;
    	default:
    		break;
    	}
    }
    /* (non-Javadoc)
     * <p>Title: onDestroy</p>
     * <p>Description: </p>
     * @see android.app.Activity#onDestroy()
     * @author renny
     * @date 2017-5-302 上午09:34:19
     */
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	hisHistory = hisView.getHistory();
		save(hisHistory + calView.getHistory());
	}
    
    /**
     * <p>Title: save</p>
     * <p>Description: </p>
     * @param s
     * @author renny
     * @date 2017-5-30 上午09:34:01
     */
    public void save(String s){
    	FileOutputStream out = null;
    	BufferedWriter writer = null;
    	try{
    		out = openFileOutput(FILENAME, Context.MODE_PRIVATE);
    		writer = new BufferedWriter(new OutputStreamWriter(out));
    		writer.write(s);
    	} catch (IOException e){
    		e.printStackTrace();
    	} finally {
    		try {
    			if ( writer != null) {
    				writer.flush();
    				writer.close();
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {

		menu.add(1, 1, 1, "查看历史");
		menu.add(1, 2, 2, "作者");
		menu.add(1, 3, 3, "班级");
		menu.add(1, 4, 4, "更多");
		// 指定菜单项的组号、ID、排序号、标题
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		switch (item.getItemId()) {
		case 1:
			Toast.makeText(MainActivity.this, "向左滑动可查看历史计算", Toast.LENGTH_LONG)
					.show();
			return true;
		case 2:
			Toast.makeText(MainActivity.this, "韩锐 1503030103",
					Toast.LENGTH_LONG).show();
			return true;
		case 3:
			Toast.makeText(MainActivity.this, "软件1班", Toast.LENGTH_LONG).show();
			return true;
		case 4:

			new AlertDialog.Builder(MainActivity.this)
					.setTitle("系统提示")
					// 设置对话框标题

					.setMessage(
							"本软件作者：软件1班 韩锐 1503030103\n Clone with HTTPS Use Git or checkout with SVN using the web URL.\nhttps://github.com/rennysky/simple-cal.git\n 个人网站 HTTPS://QUQ.NAME \n HTTPS://WWW.NOTEX.XYZ")
					// 设置显示的内容

					.setPositiveButton("退出计算器",
							new DialogInterface.OnClickListener() {// 添加确定按钮

								@Override
								public void onClick(DialogInterface dialog,
										int which) {// 确定按钮的响应事件

									// TODO Auto-generated method stub

									finish();

								}

							})
					.setNegativeButton("返回",
							new DialogInterface.OnClickListener() {// 添加返回按钮

								@Override
								public void onClick(DialogInterface dialog,
										int which) {// 响应事件

									// TODO Auto-generated method stub

									Log.i("alertdialog", " 请保存数据！");

								}

							}).show();// 在按键响应事件中显示此对话框

			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}