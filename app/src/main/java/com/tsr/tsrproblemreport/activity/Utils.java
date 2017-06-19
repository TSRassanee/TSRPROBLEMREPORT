package com.tsr.tsrproblemreport.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class Utils {
	 
	 public static boolean isrun=false;

	 public static int REQUEST_SIZE=800;

	public static final String SERVER_URL="http://app.thiensurat.co.th/assanee/";

	public static final String LOGIN_URL=SERVER_URL+"login.php";

	 public static final String UPLOADPICTURE_URL = SERVER_URL+"uploadphoto.php";

	 
	 public static final String WHOID="<whoid>";
	   public static final String WHOID_END="</whoid>";
	   public static final String AMOUNT="<amount>";
	   public static final String AMOUNT_END="</amount>";
	   public static final String TYPE="<type>";
	   public static final String TYPE_END="</type>";
	   //public static final String picture="<picture>";
	   public static final String picture_END="</picture>";
	   public static final String DATE="<date>";
	   public static final String DATE_END="</date>";
	   public static final String ID="<id>";
	   public static final String ID_END="</id>";
	   public static final String TITLE="<title>";
	   public static final String TITIE_END="</title>";
	   public static final String PRICE="<price>";
	   public static final String PRICE_END="</price>";
	   public static final String SHOPNAME="<shopname>";
	   public static final String SHOPNAME_END="</shopname>";
	   public static final String RETURNHOUR="<returnhour>";
	   public static final String RETURNHOUR_END="</returnhour>";
	   public static final String SENT="<sent>";
	   public static final String SENT_END="</sent>";
	   public static final String NAMEENG="<nameeng>";
	   public static final String NAMEENG_END="</nameeng>";
    public static String scode="";
    public static boolean isshop=false;
    public static String shop="";
	public static String Sales_person="";
	public static String username="";
    public static String password="";
	public static String position="";
	public static String position_val="";
    public static float amount=0;
    public static int returnhour=24;
	public static String namethai="";
	public static String picture="";
	public static String backgroud="";
	public static String teamleader="";
	public static String name_teamleader="";
	public static String supervisor="";
	public static String name_supervisor="";
	public static String linemanager="";
	public static String name_linemanager="";
	public static String salemanager="";
	public static String name_salemanager="";



	public static String MONTHS[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
	public static String getAddress(Context context, double latitude, double longitude) {
        StringBuilder result = new StringBuilder();
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                //ชื่อซอย หรือชื่อตำบล
                result.append(address.getAddressLine(0)).append("\n");
                //อำเภอ
                result.append(address.getLocality()).append("\n");
                //จังหวัด
                result.append(address.getAdminArea()).append("\n");                
                //ชื่อประเทศ
                result.append(address.getCountryName()).append("\n");
                //รหัสประเทศ
              //  result.append(address.getCountryCode()).append("\n");
                //รหัสไปรษณีย์
               // result.append(address.getPostalCode()).append("\n");
            }
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
        }

        return result.toString();
    }
	  public static Bitmap decodeFileFromBytes(byte b[]){
		   try{
		         //Decode image size
		       
			   BitmapFactory.Options o2 = new BitmapFactory.Options();
		         o2.inDither=false;                     //Disable Dithering mode
		         o2.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared
		         o2.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future
		     //    o2.inSampleSize=scale;
		         o2.inTempStorage = new byte[16*1024];

		         return BitmapFactory.decodeByteArray(b, 0, b.length, o2);
		   }
		     catch(Exception err)
		     {
		    	 err.printStackTrace();
		     }
		     return null;
		 }
	public static String getSystemDateTextMonth()
	   {
		   Calendar c= Calendar.getInstance();
		   c.setTimeInMillis(System.currentTimeMillis());
		   String re=c.get(Calendar.DAY_OF_MONTH)+"-"+(MONTHS[c.get(Calendar.MONTH)])+"-"+c.get(Calendar.YEAR);
		   return re;
	   }
	 public static String enc(String text)
	   {
		 if(text=="") return text;
		   String re=text;
		   try {
			re= URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re;
	   }
	 public static String getSystemDate()
	   {
		   Calendar c= Calendar.getInstance();
		   c.setTimeInMillis(System.currentTimeMillis());
		   int month=(c.get(Calendar.MONTH)+1);
		   int day=c.get(Calendar.DAY_OF_MONTH);
		   String strmonth="",strday="";
		   if(month<10)
			   strmonth="0"+month;
		   else
			   strmonth=""+month;
		   if(day <10)
			   strday="0"+day;
		   else
			   strday=""+day;
		   String re=c.get(Calendar.YEAR)+"-"+(strmonth)+"-"+strday;
		   return re;
	   }
	 public static String getFileDate()
	   {
		   Calendar c= Calendar.getInstance();
		   c.setTimeInMillis(System.currentTimeMillis());
		   int month=(c.get(Calendar.MONTH)+1);
		   int day=c.get(Calendar.DAY_OF_MONTH);
		   String strmonth="",strday="";
		   if(month<10)
			   strmonth="0"+month;
		   else
			   strmonth=""+month;
		   if(day <10)
			   strday="0"+day;
		   else
			   strday=""+day;
		   String re=c.get(Calendar.YEAR)+(strmonth)+strday+c.get(Calendar.HOUR_OF_DAY)+""+c.get(Calendar.MINUTE)+""+c.get(Calendar.SECOND)+".jpg";
		   return re;
	   }
	 public static File getPictureFolder()
	 {
		 File folderpicture= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
 		 if(folderpicture.exists() && folderpicture.isDirectory())
 		 {
 			 
 		 }
 		 else
 		 {
 			folderpicture.mkdir();
 		 }
 		
 		 return folderpicture;
	 }
	 public static byte[] BitmapToByteArray(Bitmap bitmap)
	    {
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    	bitmap.compress(CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
	    	byte[] bitmapdata = bos.toByteArray();
	    	return bitmapdata;
	    }
	 public static Bitmap loadBitmapFromView(View v) {
		    Bitmap b = Bitmap.createBitmap( v.getMeasuredWidth(), v.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
		    Canvas c = new Canvas(b);
		    //v.layout(v.getLeft(), v.getTop(), v.getMeasuredWidth(), v.getMeasuredHeight());
		    v.draw(c);
		    return b;
		}
	 public static String saveViewToJpg(Context context, View v)
	 {
		 String path="";
		 try
		 {
		 Bitmap bmp=loadBitmapFromView(v);
		 File file=new File(getPictureFolder(),getFileDate());
		 path=file.getAbsolutePath();
		 writeBitmapToCache(bmp,file);
		 }
		 catch(Exception err)
		 {
			 err.printStackTrace();
		 }
		 if (Build.VERSION.SDK_INT >= 19)
	       	{
	       		System.out.println("level >=19");
	       		context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(path))));
	       	}
	       	else
	       	{
	       		System.out.println("start media scan level <19");
	       	//	new SingleMediaScanner(context, path);
	       	}
		 return path;
	 }
		public static void writeBitmapToCache(Bitmap bitmap, File file) {
			 final int BUFFER_SIZE = 1024 * 8;
			    try {
			        file.createNewFile();
			        FileOutputStream fos = new FileOutputStream(file);
			        final BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER_SIZE);
			        bitmap.compress(CompressFormat.JPEG, 100, bos);
			        bos.flush();
			        bos.close();
			        fos.close();
			    } catch (FileNotFoundException e) {
			        e.printStackTrace();
			    } catch (IOException e) {

			    }

			}
	 public static String getSystemTime()
	   {
		   Calendar c= Calendar.getInstance();
		   c.setTimeInMillis(System.currentTimeMillis());
		   int min=c.get(Calendar.MINUTE);
		  String strmin=""+min;
				   if(min <10)
					   strmin="0"+min;
		   String re=c.get(Calendar.HOUR_OF_DAY)+":"+strmin;
		   return re;
	   }
	 public static int getSystemYear()
	   {
		   Calendar c= Calendar.getInstance();
		   c.setTimeInMillis(System.currentTimeMillis());
		   return c.get(Calendar.YEAR);
	   }
	 public void loadManager()
	 {
		 
	 }
	public static boolean isEmpty(String str)
	{
		if(str==null || str.equals("") || str.equals(" ") || str.equals("  ")  || str.equals("   "))
			return true;
		else
			return false;
	}
	
	public static void log(String str)
	{
		System.out.println(str);
	}
	public static Bitmap decodeSampledBitmapFromFile(String path,
                                                     int reqWidth, int reqHeight) { // BEST QUALITY MATCH

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(path, options);

	    // Calculate inSampleSize
	        // Raw height and width of image
	        final int height = options.outHeight;
	        final int width = options.outWidth;
	        options.inPreferredConfig = Bitmap.Config.RGB_565;
	        int inSampleSize = 1;

	        if (height > reqHeight) {
	            inSampleSize = Math.round((float)height / (float)reqHeight);
	        }

	        int expectedWidth = width / inSampleSize;

	        if (expectedWidth > reqWidth) {
	            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
	            inSampleSize = Math.round((float)width / (float)reqWidth);
	        }


	    options.inSampleSize = inSampleSize;

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;

	        return BitmapFactory.decodeFile(path, options);
	  }
	 public static byte[] readFile(Uri uri)
		{
			System.out.println("uri="+uri.toString());
			byte[] re=null;
			 try {
				
				 File file=new File(new URI(uri.toString()));

				 FileInputStream inStream = new FileInputStream(file);
				 byte buff[]=new byte[100];
		           int c;
		           ByteArrayOutputStream bos=new ByteArrayOutputStream();
		            while (( c = inStream.read(buff)) != -1)
		            {
		            	bos.write(buff,0,c);
		                
		            }

		            inStream.close();
		            bos.flush();
		           re=bos.toByteArray();
		           bos.close();
		      }

		      catch (IOException ioex){
		           Log.e("file", "error: " + ioex.getMessage(), ioex);
		      }
			 catch(Exception err)
			 {
				 err.printStackTrace();
			 }
			return re;
		}
	   
	 public static byte[] readFile(URI uri)
		{
			System.out.println("uri="+uri.toString());
			byte[] re=null;
			 try {
				
				 File file=new File(uri);

				 FileInputStream inStream = new FileInputStream(file);
				 byte buff[]=new byte[100];
		           int c;
		           ByteArrayOutputStream bos=new ByteArrayOutputStream();
		            while (( c = inStream.read(buff)) != -1)
		            {
		            	bos.write(buff,0,c);
		                
		            }

		            inStream.close();
		            bos.flush();
		           re=bos.toByteArray();
		           bos.close();
		      }

		      catch (IOException ioex){
		           Log.e("file", "error: " + ioex.getMessage(), ioex);
		      }
			 catch(Exception err)
			 {
				 err.printStackTrace();
			 }
			return re;
		}
public static Bitmap decodeFile(String filePath) {
     // Decode image size
     BitmapFactory.Options o = new BitmapFactory.Options();
     o.inJustDecodeBounds = true;
     BitmapFactory.decodeFile(filePath, o);

     // The new size we want to scale to
     final int REQUIRED_SIZE = 1024;

     // Find the correct scale value. It should be the power of 2.
     int width_tmp = o.outWidth, height_tmp = o.outHeight;
     int scale = 1;
     while (true) {
         if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
             break;
         width_tmp /= 2;
         height_tmp /= 2;
         scale *= 2;
     }

     // Decode with inSampleSize
     BitmapFactory.Options o2 = new BitmapFactory.Options();
     o2.inSampleSize = scale;
     return BitmapFactory.decodeFile(filePath, o2);

 }
public static String cutTime(String str, String spit)
{
	String re="";
	
	int index=str.indexOf(spit);
	if(index== -1)
    	return re;
	index=index+spit.length();
	int lastindex=str.indexOf(spit, index);
	re=str.substring(0,lastindex);
	return re;
}
public static String getData(String str, String title)
{
	String re="";
	try{
	//System.out.println("str="+str);
	//System.out.println(title);
	
	int index;
    index=str.indexOf(title);
    if(index== -1)
    	return re;
   index=index+title.length();
//  System.out.println("index="+index);
   int lastindex=str.indexOf("\n", index);
//   System.out.println("lastindex="+lastindex);
   re=str.substring(index,lastindex);
 //  System.out.println("re="+re);
   re=re.trim();
  // System.out.println("re="+re);
	}
	catch(Exception err)
	{
		err.printStackTrace();
	}
   return re;
}
	public static String getTagData(String tag, String endtag, String cmd)
    {
        String re="";
        int index;
         index=cmd.indexOf(tag);
        index=index+tag.length();
        int endindex=cmd.indexOf(endtag, index);
        if(index==endindex)re="";
        else
        {
        	try{
        		re=cmd.substring(index,endindex);
        	}
        	catch(Exception err)
        	{
        		
        	}
        }
        return re;
    }
	public static String getTagData(String tag, String cmd)
    {
		//"id": "1",
        String re="";
        int index;
         index=cmd.indexOf(tag);
        index=index+tag.length();
        int startindex=cmd.indexOf("\"", index);
        startindex+=1;
        int endindex=cmd.indexOf("\"", startindex);
        if(index==endindex)re="";
        else
        {
        	try{
        		re=cmd.substring(startindex,endindex);
        	}
        	catch(Exception err)
        	{
        		
        	}
        }
        return re;
    }
	 public static void showWebViewDialog(String str, String title, Context context)
	   {
	   	final Dialog dialog = new Dialog(context);
	   
			LinearLayout layout=new LinearLayout(context);
			layout.setLayoutParams(new LayoutParams(600,
		            480));
			layout.setOrientation(LinearLayout.VERTICAL);
			
			
			WebView lblinfo=new WebView(context);
			lblinfo.setBackgroundColor(Color.WHITE);
			lblinfo.setLayoutParams(new LayoutParams(480,
		            400));
			lblinfo.setWebViewClient(new WebViewClient());
			lblinfo.setWebChromeClient(new WebChromeClient());
			lblinfo.getSettings().setJavaScriptEnabled(true);
			lblinfo.loadUrl(str);
			
			Button btnOK=new Button(context);
			btnOK.setText("OK");
			btnOK.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
			layout.addView(lblinfo);
			layout.addView(btnOK);
			dialog.setContentView(layout,new LayoutParams(640, 480));
			dialog.setTitle(title);
			dialog.show();
	   }
	  public static String[] getAllTagData(String tag, String endtag, String cmd)
	    {
	        Vector v=new Vector();
	        int index=cmd.indexOf(tag);
	        while(index != -1)
	        {
	           
	            
	            index=index+tag.length();
	            int endindex=cmd.indexOf(endtag, index);
	            if(index !=endindex)            
	            {
	                    try{
	                          String re=cmd.substring(index,endindex);
	                            v.addElement(re);
	                    }
	                    catch(Exception err)
	                    {

	                    }
	            }
	            endindex+=endtag.length();
	            cmd=cmd.substring(endindex);
	            index=cmd.indexOf(tag);
	        }
	        if(v.size() < 1)return null;
	        else
	        {
	            String arr[]=new String[v.size()];
	            v.copyInto(arr);
	            return arr;
	        }
	    }
	public static byte[] gethttpByte(String strurl) {
		 byte[] b=null;;
		try {
	  		    URL url = new URL(strurl);
	  		    URLConnection conn = url.openConnection();
	  		    // Get the response
	  		    ByteArrayOutputStream bos=new ByteArrayOutputStream();
	  		    InputStream is=conn.getInputStream();
	  		   
	  		
	  		  byte[] bread=new byte[100];
	  		    int c;
	  		    while ((c = is.read(bread)) != -1) {
	  		      bos.write(bread, 0, c);
	  		     
	  		    }
	  		    
	  		    bos.flush();
	  		     b=bos.toByteArray();
	  		    
	  		    bos.close();
	  		    is.close();
	  		    

	  		
		    }
		    catch(Exception err2)
		  {
			  err2.printStackTrace();
			
		  }
		    return b;
	}
	public static String sendPostData(byte[] data, String strurl) throws IOException,Exception
    {
        String re="";
        
        	 URL url = new URL(strurl);
	  		    URLConnection conn = url.openConnection();
	  		    conn.setDoOutput(true);
	  		  OutputStream os=null;
		        InputStream is=null;
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");    // set content type
            os=conn.getOutputStream() ;     // open output stream
            os.write(data);                 // write data
            os.flush();                     // flush for send data
            os.close();                     // close output stream
            is=conn.getInputStream() ;     // opent input strea for read return data from server
            ByteArrayOutputStream bos=new ByteArrayOutputStream();      // decare bos buffer
            byte[] bread=new byte[100];
  		    int c;
  		    while ((c = is.read(bread)) != -1) {
  		      bos.write(bread, 0, c);
  		     
  		    }
            bos.flush();        // flush bos
            byte b[]=bos.toByteArray();
             re=new String(b, "UTF-8"); // convert byte to string
             bos.close();
            is.close();         // close input stream
            os.close();
                  // close connection
       
        return re;  // return data
 }
	 public static void showInfoDialog(String str, Context context)
	    {
	    	final Dialog dialog = new Dialog(context);
			LinearLayout layout=new LinearLayout(context);
			layout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
		            LayoutParams.WRAP_CONTENT));
			layout.setOrientation(LinearLayout.VERTICAL);
			TextView lblinfo=new TextView(context);
			lblinfo.setText(str);
			Button btnOK=new Button(context);
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
	 public static Bitmap decodeFile(File f, int size){
	     try {
	         //Decode image size
	         BitmapFactory.Options o = new BitmapFactory.Options();
	         o.inJustDecodeBounds = true;
	         BitmapFactory.decodeStream(new FileInputStream(f),null,o);

	         //The new size we want to scale to
	         final int REQUIRED_SIZE=size;

	         //Find the correct scale value. It should be the power of 2.
	         int scale=1;
	         while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
	             scale*=2;

	         //Decode with inSampleSize
	         BitmapFactory.Options o2 = new BitmapFactory.Options();
	         o2.inDither=false;                     //Disable Dithering mode
	         o2.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared
	         o2.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future
	         o2.inSampleSize=scale;
	         o2.inTempStorage = new byte[16*1024];

	         return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
	     } catch (FileNotFoundException e) {}
	     catch(Exception err)
	     {
	    	 err.printStackTrace();
	     }
	     return null;
	 }
	 public static Bitmap decodeFileForViewer(String path){
		 System.gc();
		   try{
		      File f=new File(path);
		      if(!f.exists())
		      {
		    	  System.out.println("f is null");
		    	  f=new File("file://" +path);
		    	 // return null;
		      }
		      if(!f.exists())
		      {
		    	  System.out.println("f2 is null");
		      }
		       if((f.length()> 204800)) // 200kb
		      {
		    	  System.out.println("scale f length="+f.length());
		    	  return decodeAndScalesFile(f,REQUEST_SIZE);
		      }
		      else
		      {
		    	  System.out.println("f length="+f.length());
		    	  return decodeFile(f);
		      }
		   }
		     catch(Exception err)
		     {
		    	 err.printStackTrace();
		     }
		     return null;
		 }
	 public static Bitmap decodeFile(File f){
	     try {
	         //Decode image size
	       

	         //Decode with inSampleSize
	         BitmapFactory.Options o2 = new BitmapFactory.Options();
	         o2.inDither=false;                     //Disable Dithering mode
	         o2.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared
	         o2.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future
	     //    o2.inSampleSize=scale;
	         o2.inTempStorage = new byte[16*1024];

	         return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
	     } catch (FileNotFoundException e) {}
	     catch(Exception err)
	     {
	    	 err.printStackTrace();
	     }
	     return null;
	 }
	 public static Bitmap decodeAndScalesFile(File f, int size){
	        try {
	            //decode image size
	            BitmapFactory.Options o = new BitmapFactory.Options();
	            o.inJustDecodeBounds = true;
	            FileInputStream stream1=new FileInputStream(f);
	            BitmapFactory.decodeStream(stream1,null,o);
	            stream1.close();
	            
	            //Find the correct scale value. It should be the power of 2.
	            final int REQUIRED_SIZE=size;
	            int width_tmp=o.outWidth, height_tmp=o.outHeight;
	            int scale=1;
	            while(true){
	                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
	                    break;
	                width_tmp/=2;
	                height_tmp/=2;
	                scale*=2;
	            }
	            
	            //decode with inSampleSize
	            BitmapFactory.Options o2 = new BitmapFactory.Options();
	            o2.inSampleSize=scale;
	            FileInputStream stream2=new FileInputStream(f);
	            Bitmap bitmap= BitmapFactory.decodeStream(stream2, null, o2);
	            stream2.close();
	            return bitmap;
	        } catch (FileNotFoundException e) {
	        } 
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 public static void showDialog(Context context, final String text)
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
	 public static boolean isempty(String str)
		{
			if(str !=null && !str.equals("") && !str.equals(" ") && !str.equals("  ") && !str.equalsIgnoreCase("null"))
			{
				return false;
			}
			else
				return true;
		}
		public static boolean isPicture(String filename)
		{
			boolean re=false;
			String ext=getExtension(filename);
			if(ext!=null)
			{
				if(ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("JPG") || ext.equalsIgnoreCase("JPEG") || ext.equalsIgnoreCase("PNG") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("gif"))
				{
					re=true;
				}
			}
			return re;
		}
		public static boolean isVideo(String filename)
		{
			boolean re=false;
			String ext=getExtension(filename);
			if(ext!=null)
			{
				if(ext.equalsIgnoreCase("mp4") || ext.equalsIgnoreCase("MP4") || ext.equalsIgnoreCase("3GP") || ext.equalsIgnoreCase("3gp") || ext.equalsIgnoreCase("avi"))
				{
					re=true;
				}
			}
			return re;
		}
		public static String getExtension(String filename)
		{
			return filename.substring(filename.lastIndexOf(".")+1);
		}
	 public static String getMIMEType(String filename)
	 {
		 String re="";
		 String ext=getExtension(filename);
		 if(ext.equalsIgnoreCase("mp4"))
			{
				re="video/mp4";
			}
		 else if(ext.equalsIgnoreCase("3gp"))
		 {
			 re="video/3gpp";
		 }
		 else if(ext.equalsIgnoreCase("avi"))
		 {
			 re="video/x-msvideo";
		 }
		 else if(ext.equalsIgnoreCase("wmv"))
		 {
			 re="video/x-ms-wmv";
		 }
		 else if(ext.equalsIgnoreCase("mov"))
		 {
			 re="video/quicktime";
		 }
		 else if(ext.equalsIgnoreCase("flv"))
		 {
			 re="video/x-flv";
		 }
		 else if(ext.equalsIgnoreCase("jpg") || ext.equalsIgnoreCase("jpeg") )
		{
			 re="image/jpeg";
		}
		 else if(ext.equalsIgnoreCase("png"))
		 {
			 re="image/png";
		 }
		 else if(ext.equalsIgnoreCase("gif"))
		 {
			 re="image/gif";
		 }
		 else if(ext.equalsIgnoreCase("bmp"))
		 {
			 re="image/bmp";
		 }
		 return re;
	 }
	 public static String doFileUpload(byte[] byteData, String exsistingFileName){
	    	HttpURLConnection conn =    null;
	    	DataOutputStream dos = null;
	    	InputStream inStream = null;
	    
	    	// Is this the place are you doing something wrong.
	    	String lineEnd = "\r\n";
	    	String twoHyphens = "--";
	    	String boundary =  "*****";

	    	String urlString =Utils.UPLOADPICTURE_URL;

	    	try
	    	    {
	    	        //------------------ CLIENT REQUEST 
	    	        Log.e("MediaPlayer","Inside second Method");
	    	       

	    	                                        // open a URL connection to the Servlet
	    	                                        URL url = new URL(urlString);

	    	                                        // Open a HTTP connection to the URL
	    	                                        conn = (HttpURLConnection) url.openConnection();

	    	                                        // Allow Inputs
	    	                                        conn.setDoInput(true);

	    	                                        // Allow Outputs
	    	                                        conn.setDoOutput(true);

	    	                                        // Don't use a cached copy.
	    	                                        conn.setUseCaches(false);

	    	                                        // Use a post method.
	    	                                        conn.setRequestMethod("POST");
	    	                                        conn.setRequestProperty("Connection", "Keep-Alive");
	    	                                        conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

	    	                                        dos = new DataOutputStream( conn.getOutputStream() );
	    	                                        dos.writeBytes(twoHyphens + boundary + lineEnd);
	    	                                        dos.writeBytes("Content-Disposition: form-data; name=\"userfile\";filename=\""
	    	                                                            + exsistingFileName + "\"" + lineEnd);
	    	                                        dos.writeBytes(lineEnd);
	    	                                        Log.e("MediaPlayer","Headers are written");

	    	                                        // read file and write it into form...
	    	                                        
	    	                                        dos.write(byteData);
	    	                                       

	    	                                        // send multipart form data necesssary after file data...
	    	                                        dos.writeBytes(lineEnd);
	    	                                        dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

	    	                                        // close streams
	    	                                        Log.e("MediaPlayer","File is written");
	    	                                        dos.flush();
	    	                                        dos.close();

	    	                    }

	    	      catch (MalformedURLException ex)

	    	     {

	    	           Log.e("MediaPlayer", "error: " + ex.getMessage(), ex);

	    	      }



	    	      catch (IOException ioe)

	    	      {

	    	           Log.e("MediaPlayer", "error: " + ioe.getMessage(), ioe);

	    	      }
	    	  String response="";
	    	      //------------------ read the SERVER RESPONSE
	    	      try {
	    	            inStream =  ( conn.getInputStream() );
	    	            ByteArrayOutputStream bos=new ByteArrayOutputStream();      // decare bos buffer
	    	            byte[] bread=new byte[100];
	    	  		    int c;
	    	  		    while ((c = inStream.read(bread)) != -1) {
	    	  		      bos.write(bread, 0, c);
	    	  		     
	    	  		    }
	    	            bos.flush();        // flush bos
	    	            byte b[]=bos.toByteArray();
	    	             response=new String(b, "UTF-8"); // convert byte to string
	    	             bos.close();
	    	            inStream.close();
	    	            System.out.println("upload re="+response);
	    	      }

	    	      catch (IOException ioex){
	    	           Log.e("MediaPlayer", "error: " + ioex.getMessage(), ioex);
	    	      }
	    	      return response;
}
}
