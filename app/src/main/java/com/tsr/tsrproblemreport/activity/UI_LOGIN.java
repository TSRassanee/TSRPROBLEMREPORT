package com.tsr.tsrproblemreport.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.tsr.tsrproblemreport.MusicActivity;
import com.tsr.tsrproblemreport.MusicActivity2;
import com.tsr.tsrproblemreport.MusicActivity3;
import com.tsr.tsrproblemreport.MusicActivity4;
import com.tsr.tsrproblemreport.MusicActivity5;
import com.tsr.tsrproblemreport.R;
import com.tsr.tsrproblemreport.app.Config;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class UI_LOGIN extends Activity implements OnClickListener,OnCheckedChangeListener {
	static final private int FORGET = Menu.FIRST;
	static final private int REGISTER = Menu.FIRST+1;
	Button btnLogin=null;
	EditText txtUsername=null;
	EditText txtPassword=null;
	TextView lbldate,lbltime=null;
			
	Utils util=null;
	 private ProgressDialog pdialog;
	 public static final String NOTI_ADD = "mobilepay";
	    public static final String LOGINUSER = "loginuser";
	    public static final String LOGINPASS = "pass";
	    public static final String LOGOUTTIME = "logouttime";
	    public static boolean logout=false;
	    public static long lasttime= System.currentTimeMillis();
	    public static int logouthour=-1;
	private BroadcastReceiver mRegistrationBroadcastReceiver;
	private TextView txtRegId, txtMessage;
	String positionA="",usernameA="",passwordA="",tokenA="",tokenb="";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loginmain);





		mRegistrationBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {

				// checking for type intent filter
				if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
					// gcm successfully registered
					// now subscribe to `global` topic to receive app wide notifications
					FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

					displayFirebaseRegId();

				} else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
					// new push notification is received

					String message = intent.getStringExtra("message");

					Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

					txtMessage.setText(message);
				}
			}
		};

		displayFirebaseRegId();







        util=new Utils();
        pdialog = new ProgressDialog(this);
        pdialog.setMessage("Loading...");
        pdialog.setCancelable(false);
        pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pdialog.setMax(100);
        UI_LOGIN.lasttime= System.currentTimeMillis();
        initlogin();
     
        String s = (new ManageSharedPreferences()).LoadPreferences(this, NOTI_ADD, LOGINUSER);
        if(s!=null)
        {
        	String pass=(new ManageSharedPreferences()).LoadPreferences(this, NOTI_ADD, LOGINPASS);
//        	txtUsername.setText(s);
//        	txtPassword.setText(pass);
        	username=s;
        	password=pass;
        	 if(checkConnection()==true)
             {

        	if(s.length()>3)
        	{
        		txtUsername.setVisibility(View.INVISIBLE);
        		txtPassword.setVisibility(View.INVISIBLE);
        		btnLogin.setVisibility(View.INVISIBLE);
        	
        		Thread t=new Thread(myThreadForget);
				t.start();
        	}
             }
        	 else
        	 {
        		 Utils.showDialog(this, getString(R.string.pleaseconnectinternet));
        	 }
        }
        
        
    }
	// Fetches reg id from shared preferences
	// and displays on the screen
	private void displayFirebaseRegId() {
		SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
		String regId = pref.getString("regId", null);
		tokenA=regId;
		Log.e(TAG, "Firebase reg id: " + regId);

		if (!TextUtils.isEmpty(regId))
			tokenA=regId;
			//	txtRegId.setText(regId);
			//txtRegId.setText("");
		else
			tokenb=regId;
	}
    protected boolean checkConnection(){ 
		 boolean re=false;
		 try{
	        ConnectivityManager conMan = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

	        NetworkInfo networkInfo = conMan.getActiveNetworkInfo();

	        final boolean connected = networkInfo != null
	                && networkInfo.isAvailable()
	                && networkInfo.isConnected();

	        if ( !connected) {
	            re= false;
	        }
	        else
	        	re=true;
		 }catch(Exception err){}
	        return re;
	    }

    public void initlogin()
    {
    	txtUsername=(EditText)findViewById(R.id.txtusername);
    	txtPassword=(EditText)findViewById(R.id.txtpassword);
    	btnLogin=(Button)findViewById(R.id.btnLogin);

    	btnLogin.setOnClickListener(this);
//    	lbldate.setText(Utils.getSystemDateTextMonth());
    //	lbltime.setText(Utils.getSystemTime());
    	
    }
    public void showInfoDialog(String str)
    {
    	final Dialog dialog = new Dialog(this);
		LinearLayout layout=new LinearLayout(this);
		layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
	            LayoutParams.WRAP_CONTENT));
		layout.setOrientation(LinearLayout.VERTICAL);
		TextView lblinfo=new TextView(this);
		lblinfo.setText(str);
		Button btnOK=new Button(this);
		btnOK.setText("OK");
		btnOK.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		layout.addView(lblinfo);
		layout.addView(btnOK);
		dialog.setContentView(layout);			
		dialog.setTitle("Info");
		dialog.show();
    }
    String username="";
    String password="";
	public void onClick(View v) {
		SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
		String regId = pref.getString("regId", null);
		// TODO Auto-generated method stub
		if(v==btnLogin)
		{
			UI_LOGIN.lasttime= System.currentTimeMillis();
//			Intent mIntent = new Intent(this, UI_MAINMENU.class);
//            startActivityForResult(mIntent,0);
			if(txtUsername.getText() !=null && txtPassword.getText() !=null)
			{
				username=txtUsername.getText().toString();
				username=username.toUpperCase();
				password=txtPassword.getText().toString();


				if (!TextUtils.isEmpty(regId))
					//txtRegId.setText(regId);
					tokenA=regId;

				if(Utils.isEmpty(username))
				{
					showDialog(UI_LOGIN.this,"Please input all field");
				}
				else
				{
					if(checkConnection()==true)
					{
					pdialog.show();
					Thread t=new Thread(myThreadForget);
					t.start();
					}
					 else
		        	 {
						 
			                      Utils.showDialog(this, getString(R.string.pleaseconnectinternet));
		              
					
		        		
		        	 }
				}
			}
		}
		
		
	}
	 public void showDialog(Context context, final String text)
	    {
	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
	        builder1.setMessage(text);
	        builder1.setCancelable(true);
	        builder1.setPositiveButton("OK",
	                new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int id) {
	                dialog.cancel();
					dialog.dismiss();
					
	            }
	        });
	       

	        AlertDialog alert11 = builder1.create();
	        alert11.show();
	    	
	    }




	    String namethai="",username2="",position="",description="",picture,backgroud="",teamleader="",name_teamleader="",supervisor="",
                name_supervisor="",linemanager="",name_linemanager="",salemanager="",name_salemanager="";
	private Runnable myThreadForget = new Runnable(){
        public void run() {
        	login(username,password);
        	
        }
    };
    Handler myHandleForget = new Handler(){
    	@Override
    	public void handleMessage(android.os.Message msg) {
    		if(txt !=null || !txt.equals(""))
    		{
    			 pdialog.dismiss();
    			if(loginsuccess==true)
    			{
    				(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINUSER, Utils.scode);
    				(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINPASS, Utils.password);
    			logout=false;
    	            	//Intent mIntent = new Intent( UI_LOGIN.this, MainActivity.class);
    	    			//startActivityForResult(mIntent,0);



                    namethai=Utils.namethai;
					position=Utils.position;
                    picture=Utils.picture;
                    backgroud=Utils.backgroud;
                    teamleader=Utils.teamleader;
                    name_teamleader=Utils.name_teamleader;
                    username2=Utils.scode;
                    //picture=Utils.namethai;





					Intent i = new Intent(UI_LOGIN.this, MusicActivity.class);

                    Bundle bun=new Bundle();

                    bun.putString("namethai", namethai);
					bun.putString("position", position);
                    bun.putString("picture", picture);
                    bun.putString("backgroud", backgroud);
                    bun.putString("teamleader", teamleader);
                    bun.putString("name_teamleader", name_teamleader);
                    bun.putString("username", username2);



					ManageSharedPreferences.SavePreferences(UI_LOGIN.this,getLocalClassName(),"position",position);
					ManageSharedPreferences.SavePreferences(UI_LOGIN.this,getLocalClassName(),"picture",picture);

                    i.putExtras(bun);


					txtUsername.setText("");
					txtPassword.setText("");
					startActivity(i);

                    finish();


    			}

				else if(loginsuccess2==true)
				{
					(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINUSER, Utils.scode);
					(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINPASS, Utils.password);
					logout=false;
					//Intent mIntent = new Intent( UI_LOGIN.this, MainActivity.class);
					//startActivityForResult(mIntent,0);



					namethai=Utils.namethai;
					position=Utils.position;
					picture=Utils.picture;
					backgroud=Utils.backgroud;
					supervisor=Utils.supervisor;
					name_supervisor=Utils.name_supervisor;
					username2=Utils.scode;
					//picture=Utils.namethai;


					Intent i = new Intent(UI_LOGIN.this, MusicActivity2.class);

					Bundle bun=new Bundle();

					bun.putString("namethai", namethai);
					bun.putString("position", position);
					bun.putString("picture", picture);
					bun.putString("backgroud", backgroud);
					bun.putString("supervisor", supervisor);
					bun.putString("name_supervisor", name_supervisor);
					bun.putString("username", username2);

					i.putExtras(bun);
					txtUsername.setText("");
					txtPassword.setText("");
					startActivity(i);

					finish();


				}
				else if(loginsuccess3==true)
				{
					(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINUSER, Utils.scode);
					(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINPASS, Utils.password);
					logout=false;
					//Intent mIntent = new Intent( UI_LOGIN.this, MainActivity.class);
					//startActivityForResult(mIntent,0);



					namethai=Utils.namethai;
					position=Utils.position;
					picture=Utils.picture;
					backgroud=Utils.backgroud;
					linemanager=Utils.linemanager;
					name_linemanager=Utils.name_linemanager;
					username2=Utils.scode;
					//picture=Utils.namethai;


					Intent i = new Intent(UI_LOGIN.this, MusicActivity3.class);

					Bundle bun=new Bundle();

					bun.putString("namethai", namethai);
					bun.putString("position", position);
					bun.putString("picture", picture);
					bun.putString("backgroud", backgroud);
					bun.putString("linemanager", linemanager);
					bun.putString("name_linemanager", name_linemanager);
					bun.putString("username", username2);

					i.putExtras(bun);
					txtUsername.setText("");
					txtPassword.setText("");
					startActivity(i);

					finish();


				}
				else if(loginsuccess4==true)
				{
					(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINUSER, Utils.scode);
					(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINPASS, Utils.password);
					logout=false;
					//Intent mIntent = new Intent( UI_LOGIN.this, MainActivity.class);
					//startActivityForResult(mIntent,0);



					namethai=Utils.namethai;
					position=Utils.position;
					picture=Utils.picture;
					backgroud=Utils.backgroud;
					salemanager=Utils.salemanager;
					name_salemanager=Utils.name_salemanager;
					username2=Utils.scode;
					//picture=Utils.namethai;


					Intent i = new Intent(UI_LOGIN.this, MusicActivity4.class);

					Bundle bun=new Bundle();

					bun.putString("namethai", namethai);
					bun.putString("position", position);
					bun.putString("picture", picture);
					bun.putString("backgroud", backgroud);
					bun.putString("salemanager", salemanager);
					bun.putString("name_salemanager", name_salemanager);
					bun.putString("username", username2);

					i.putExtras(bun);
					txtUsername.setText("");
					txtPassword.setText("");
					startActivity(i);

					finish();


				}
				else if(loginsuccess5==true)
				{
					(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINUSER, Utils.scode);
					(new ManageSharedPreferences()).SavePreferences(UI_LOGIN.this, NOTI_ADD, LOGINPASS, Utils.password);
					logout=false;
					//Intent mIntent = new Intent( UI_LOGIN.this, MainActivity.class);
					//startActivityForResult(mIntent,0);



					namethai=Utils.namethai;
					position=Utils.position;
					picture=Utils.picture;
					backgroud=Utils.backgroud;
					teamleader=Utils.teamleader;
					name_teamleader=Utils.name_teamleader;
					username2=Utils.scode;
					//picture=Utils.namethai;

					//ManageSharedPreferences.SavePreferences(UI_LOGIN.this,getLocalClassName(),"position",position);
					ManageSharedPreferences.SavePreferences(UI_LOGIN.this,getLocalClassName(),"picture",picture);


					Intent i = new Intent(UI_LOGIN.this, MusicActivity5.class);

					Bundle bun=new Bundle();

					bun.putString("namethai", namethai);
					bun.putString("position", position);
					bun.putString("picture", picture);
					bun.putString("backgroud", backgroud);
					bun.putString("teamleader", teamleader);
					bun.putString("name_teamleader", name_teamleader);
					bun.putString("username", username2);

					i.putExtras(bun);



					startActivity(i);

					finish();
					txtUsername.setText("");
					txtPassword.setText("");

				}
    			else if(error==true)
    			{
    				txtUsername.setVisibility(View.VISIBLE);
            		txtPassword.setVisibility(View.VISIBLE);
            		btnLogin.setVisibility(View.VISIBLE);

    			showDialog(UI_LOGIN.this,txt);
    			}
    		}
            
    	}       	

    };
    String txt="";
    boolean error=false;
    boolean loginsuccess=false;
	boolean loginsuccess2=false;
	boolean loginsuccess3=false;
	boolean loginsuccess4=false;
	boolean loginsuccess5=false;
	public void login(String email, String password)
    {
       
        email=email.trim();
        email=email.trim();
        password=password.trim();
        password=password.trim();
		tokenA=tokenA.trim();
		tokenA=tokenA.trim();
        try{
			String data="username="+email+"&pass="+password+"&keyfcm="+tokenA;
        //	String data="username="+email+"&pass="+password;
     String re=Utils.sendPostData(data.getBytes(), Utils.LOGIN_URL);
      System.out.println("re="+re);
            if(re.indexOf("success") != -1)
            {
            	String arr[]=re.split(";");
            	loginsuccess=true;
            	String shop=arr[0];
            	shop=shop.replace(" ", "");
            	if(Utils.isEmpty(shop))
            		Utils.shop=email;
            	else
            		Utils.shop=shop;
            	
            	Utils.scode=email;
            	Utils.password=password;
            	Utils.returnhour= Integer.parseInt(arr[2]);
            	int ishop= Integer.parseInt(arr[1]);


                String namethai=arr[3];
				String position=arr[4];
                String picture=arr[5];
                String backgroud=arr[6];
                String teamleader=arr[7];
                String name_teamleader=arr[8];

                Utils.namethai=namethai;
				Utils.position=position;
                Utils.picture=picture;
                Utils.backgroud=backgroud;
                Utils.teamleader=teamleader;
                Utils.name_teamleader=name_teamleader;

            	if(ishop==1)
            		Utils.isshop=true;
            	else
            		Utils.isshop=false;
            }
           else if(re.indexOf("ok") != -1)
			{
				String arr[]=re.split(";");
				loginsuccess2=true;
				String shop=arr[0];
				shop=shop.replace(" ", "");
				if(Utils.isEmpty(shop))
					Utils.shop=email;
				else
					Utils.shop=shop;

				Utils.scode=email;
				Utils.password=password;
				Utils.returnhour= Integer.parseInt(arr[2]);
				int ishop= Integer.parseInt(arr[1]);


				String namethai=arr[3];
				String position=arr[4];
				String picture=arr[5];
				String backgroud=arr[6];
				String supervisor=arr[7];
				String name_supervisor=arr[8];

				Utils.namethai=namethai;
				Utils.position=position;
				Utils.picture=picture;
				Utils.backgroud=backgroud;
				Utils.supervisor=supervisor;
				Utils.name_supervisor=name_supervisor;

				if(ishop==1)
					Utils.isshop=true;
				else
					Utils.isshop=false;
			}
            else if(re.indexOf("SSSS") != -1)
			{
				String arr[]=re.split(";");
				loginsuccess3=true;
				String shop=arr[0];
				shop=shop.replace(" ", "");
				if(Utils.isEmpty(shop))
					Utils.shop=email;
				else
					Utils.shop=shop;

				Utils.scode=email;
				Utils.password=password;
				Utils.returnhour= Integer.parseInt(arr[2]);
				int ishop= Integer.parseInt(arr[1]);


				String namethai=arr[3];
				String position=arr[4];
				String picture=arr[5];
				String backgroud=arr[6];
				String linemanager=arr[7];
				String name_linemanager=arr[8];

				Utils.namethai=namethai;
				Utils.position=position;
				Utils.picture=picture;
				Utils.backgroud=backgroud;
				Utils.linemanager=linemanager;
				Utils.name_linemanager=name_linemanager;

				if(ishop==1)
					Utils.isshop=true;
				else
					Utils.isshop=false;
			}
            else if(re.indexOf("LLLL") != -1)
			{
				String arr[]=re.split(";");
				loginsuccess4=true;
				String shop=arr[0];
				shop=shop.replace(" ", "");
				if(Utils.isEmpty(shop))
					Utils.shop=email;
				else
					Utils.shop=shop;

				Utils.scode=email;
				Utils.password=password;
				Utils.returnhour= Integer.parseInt(arr[2]);
				int ishop= Integer.parseInt(arr[1]);


				String namethai=arr[3];
				String position=arr[4];
				String picture=arr[5];
				String backgroud=arr[6];
				String salemanager=arr[7];
				String name_salemanager=arr[8];

				Utils.namethai=namethai;
				Utils.position=position;
				Utils.picture=picture;
				Utils.backgroud=backgroud;
				Utils.salemanager=salemanager;
				Utils.name_salemanager=name_salemanager;

				if(ishop==1)
					Utils.isshop=true;
				else
					Utils.isshop=false;
			}
            else if(re.indexOf("DDDD") != -1)
			{
				String arr[]=re.split(";");
				loginsuccess5=true;
				String shop=arr[0];
				shop=shop.replace(" ", "");
				if(Utils.isEmpty(shop))
					Utils.shop=email;
				else
					Utils.shop=shop;

				Utils.scode=email;
				Utils.password=password;
				Utils.returnhour= Integer.parseInt(arr[2]);
				int ishop= Integer.parseInt(arr[1]);


				String namethai=arr[3];
				String position=arr[4];
				String picture=arr[5];
				String backgroud=arr[6];
				String teamleader=arr[7];
				String name_teamleader=arr[8];

				Utils.namethai=namethai;
				Utils.position=position;
				Utils.picture=picture;
				Utils.backgroud=backgroud;
				Utils.teamleader=teamleader;
				Utils.name_teamleader=name_teamleader;

				if(ishop==1)
					Utils.isshop=true;
				else
					Utils.isshop=false;
			}





            else
            {
            	loginsuccess=false;
				loginsuccess2=false;
				loginsuccess3=false;
				loginsuccess4=false;
				loginsuccess5=false;

            	error=true;
                txt=re;
            }
           
        }
        catch(java.net.ConnectException ce)
        {
        	loginsuccess=false;
			loginsuccess2=false;
			loginsuccess3=false;
			loginsuccess4=false;
			loginsuccess5=false;

        	error=true;
        	txt="Please connect internet.";
        }
       catch(SecurityException se)
        {
    	   loginsuccess=false;
			loginsuccess2=false;
			loginsuccess3=false;
			loginsuccess4=false;
			loginsuccess5=false;
            	error=true;
            	txt=("User must allow to connect internet for this operation");
        }
              
        catch(Exception err)
        {
        	loginsuccess=false;
			loginsuccess2=false;
			loginsuccess3=false;
			loginsuccess4=false;
			loginsuccess5=false;
        	error=true;
            err.printStackTrace();
            txt="Please connect internet.";
        }
        myHandleForget.sendMessage(myHandleForget.obtainMessage());
    }
	 @Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	        if (keyCode == KeyEvent.KEYCODE_BACK) {
	        	Intent intent=new Intent();
	        	setResult(RESULT_OK, intent);
	        	finish();
	             return true;
	        }
	       
	        return super.onKeyDown(keyCode, event);
	    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		/*if(resultCode==RESULT_OK)
		{
			Intent intent=new Intent();
        	setResult(RESULT_OK, intent);
        	finish();
		}*/
	}
	


		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked==true)
			{
				(new ManageSharedPreferences()).SavePreferences(this, NOTI_ADD, LOGINUSER, txtUsername.getText().toString());
				(new ManageSharedPreferences()).SavePreferences(this, NOTI_ADD, LOGINPASS, txtPassword.getText().toString());
			}
			else
			{
				(new ManageSharedPreferences()).SavePreferences(this, NOTI_ADD, LOGINUSER, "");
				(new ManageSharedPreferences()).SavePreferences(this, NOTI_ADD, LOGINPASS, "");
			}
		}
}
