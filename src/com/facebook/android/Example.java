package com.facebook.android;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.SessionEvents.AuthListener;
import com.facebook.android.SessionEvents.LogoutListener;
import com.facebook.android.project.startingPoint;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Example extends Activity 
{
	public static int pageselect=0;
    public static final String APP_ID = "269163112880";
    private ProgressDialog mProgress;
    private Handler mRunOnUi = new Handler();
    private LoginButton mLoginButton;
    private TextView mText;
    private Button mRequestButton;
    private Button mPostButton;
    private Button mDeleteButton;
    private Button mUploadButton;
    private int mAuthAttempts = 0;


    private Facebook mFacebook;
    private AsyncFacebookRunner mAsyncRunner;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        mProgress   = new ProgressDialog(this);

        if (APP_ID == null) {
            Util.showAlert(this, "Warning", "Facebook Applicaton ID must be " +
                    "specified before running this example: see Example.java");
        }

        setContentView(R.layout.main);
        mLoginButton = (LoginButton) findViewById(R.id.login);
        mText = (TextView) Example.this.findViewById(R.id.txt);
        mRequestButton = (Button) findViewById(R.id.requestButton);
        mPostButton = (Button) findViewById(R.id.postButton);
        mDeleteButton = (Button) findViewById(R.id.deletePostButton);
        mUploadButton = (Button) findViewById(R.id.uploadButton);

       	mFacebook = new Facebook(APP_ID);
       	mAsyncRunner = new AsyncFacebookRunner(mFacebook);

        SessionStore.restore(mFacebook, this);
        SessionEvents.addAuthListener(new SampleAuthListener());
        SessionEvents.addLogoutListener(new SampleLogoutListener());
        mLoginButton.init(this, mFacebook);

        mRequestButton.setOnClickListener(new OnClickListener() 
        {
            public void onClick(View v) 
            {
            	mAsyncRunner.request("me", new SampleRequestListener());
            	
            }
        });
        mRequestButton.setVisibility(mFacebook.isSessionValid() ?
                View.VISIBLE :
                View.INVISIBLE);

        mUploadButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Bundle params = new Bundle();
                params.putString("method", "photos.upload");

                URL uploadFileUrl = null;
                try {
                    uploadFileUrl = new URL(
                        "http://www.facebook.com/images/devsite/iphone_connect_btn.jpg");
                } catch (MalformedURLException e) {
                	e.printStackTrace();
                }
                try {
                    HttpURLConnection conn= (HttpURLConnection)uploadFileUrl.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    int length = conn.getContentLength();

                    byte[] imgData =new byte[length];
                    InputStream is = conn.getInputStream();
                    is.read(imgData);
                    params.putByteArray("picture", imgData);

                } catch  (IOException e) {
                    e.printStackTrace();
                }

                //mAsyncRunner.request(null, params, "POST", new SampleUploadListener(), null);
                mAsyncRunner.request("me", params, "POST", new SampleUploadListener(), null);
            }
        });
        mUploadButton.setVisibility(mFacebook.isSessionValid() ?
                View.VISIBLE :
                View.INVISIBLE);

        mPostButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                
                
            	
                
                updateStatus("", "Your score is: "+com.facebook.android.project.startingPoint.score);
                	System.out.println("After request");
              //  mFacebook..
            }
        });
        mPostButton.setVisibility(mFacebook.isSessionValid() ?
                View.VISIBLE :
                View.INVISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        mFacebook.authorizeCallback(requestCode, resultCode, data);
    }
    
    private final class WallPostListener extends BaseRequestListener {
        public void onComplete(final String response) {
            mRunOnUi.post(new Runnable() {
                public void run() {
                    mProgress.cancel();
 
                    Toast.makeText(Example.this, "Posted to Facebook", Toast.LENGTH_SHORT).show();
                }
            });
        }

		public void onComplete(String response, Object state) {
			// TODO Auto-generated method stub
			
		}
    }

    public class SampleAuthListener implements AuthListener {

        public void onAuthSucceed() {
            mText.setText("You have logged in! ");
            mRequestButton.setVisibility(View.VISIBLE);
            mUploadButton.setVisibility(View.VISIBLE);
            mPostButton.setVisibility(View.VISIBLE);
        }

        public void onAuthFail(String error) {
            mText.setText("Login Failed: " + error);
        }
    }

    public class SampleLogoutListener implements LogoutListener {
        public void onLogoutBegin() {
            mText.setText("Logging out...");
        }

        public void onLogoutFinish() {
            mText.setText("You have logged out! ");
            mRequestButton.setVisibility(View.INVISIBLE);
            mUploadButton.setVisibility(View.INVISIBLE);
            mPostButton.setVisibility(View.INVISIBLE);
        }
    }

    public class SampleRequestListener extends BaseRequestListener {

        public void onComplete(final String response, final Object state) {
            try {
                // process the response here: executed in background thread
                Log.d("Facebook-Example", "Response: " + response.toString());
                JSONObject json = Util.parseJson(response);
                final String name = json.getString("name");

                // then post the processed result back to the UI thread
                // if we do not do this, an runtime exception will be generated
                // e.g. "CalledFromWrongThreadException: Only the original
                // thread that created a view hierarchy can touch its views."
                Example.this.runOnUiThread(new Runnable() {
                    public void run() {
                        mText.setText("Hello there, " + name + "!");
                    }
                });
            } catch (JSONException e) {
                Log.w("Facebook-Example", "JSON Error in response");
            } catch (FacebookError e) {
                Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
            }
        }
    }

    public class SampleUploadListener extends BaseRequestListener {

        public void onComplete(final String response, final Object state) {
            try {
                // process the response here: (executed in background thread)
                Log.d("Facebook-Example", "Response: " + response.toString());
                JSONObject json = Util.parseJson(response);
                final String src = json.getString("src");

                // then post the processed result back to the UI thread
                // if we do not do this, an runtime exception will be generated
                // e.g. "CalledFromWrongThreadException: Only the original
                // thread that created a view hierarchy can touch its views."
                Example.this.runOnUiThread(new Runnable() {
                    public void run() {
                        mText.setText("Hello there, photo has been uploaded at \n" + src);
                    }
                });
            } catch (JSONException e) {
                Log.w("Facebook-Example", "JSON Error in response");
            } catch (FacebookError e) {
                Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
            }
        }
    }
    public class WallPostRequestListener extends BaseRequestListener {

        public void onComplete(final String response, final Object state) {
            Log.d("Facebook-Example", "Got response: " + response);
            String message = "Rajib and Safat";
            try {
                JSONObject json = Util.parseJson(response);
                message = json.getString("message");
            } catch (JSONException e) {
                Log.w("Facebook-Example", "JSON Error in response");
            } catch (FacebookError e) {
                Log.w("Facebook-Example", "Facebook Error: " + e.getMessage());
            }
            final String text = "Your Wall Post: " + message;
            Example.this.runOnUiThread(new Runnable() {
                public void run() {
                    mText.setText(text);
                }
            });
        }
    }

    public class WallPostDeleteListener extends BaseRequestListener {

        public void onComplete(final String response, final Object state) {
            if (response.equals("true")) {
                Log.d("Facebook-Example", "Successfully deleted wall post");
                Example.this.runOnUiThread(new Runnable() {
                    public void run() {
                        mDeleteButton.setVisibility(View.INVISIBLE);
                        mText.setText("Deleted Wall Post");
                    }
                });
            } else {
                Log.d("Facebook-Example", "Could not delete wall post");
            }
        }
    }

    public class SampleDialogListener extends BaseDialogListener 
    {

        public void onComplete(Bundle values) {
            final String postId = values.getString("post_id");
            
            if (postId != null) {
                Log.d("Facebook-Example", "Dialog Success! post_id=" + postId);
                mAsyncRunner.request(postId, new WallPostRequestListener());
                mDeleteButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        mAsyncRunner.request(postId, new Bundle(), "DELETE",
                                new WallPostDeleteListener(), null);
                    }
                });
                mDeleteButton.setVisibility(View.VISIBLE);
            } else {
                Log.d("Facebook-Example", "No wall post made");
            }
        }
    }
    
    public void updateStatus(String accessToken, String message){  

        try {         
            Bundle bundle = new Bundle();
            bundle.putString("message", message);         
            bundle.putString(Facebook.TOKEN,accessToken);         
            String response = mFacebook.request("me/feed",bundle,"POST");         
            Log.d("UPDATE RESPONSE",""+response);
            showToast("Update process complete. Respose:"+response);
            if(response.indexOf("OAuthException") > -1){
                if(mAuthAttempts==0){
                    mAuthAttempts++;
                    fbAuthAndPost(message);
                }else{
                    showToast("OAuthException:");
                }
            }
        } catch (MalformedURLException e) {         
            Log.e("MALFORMED URL",""+e.getMessage());
            showToast("MalformedURLException:"+e.getMessage());
        } catch (IOException e) {         
            Log.e("IOEX",""+e.getMessage());
            showToast("IOException:"+e.getMessage());
        }

        String s = mFacebook.getAccessToken()+"\n";
        s += String.valueOf(mFacebook.getAccessExpires())+"\n";
        s += "Now:"+String.valueOf(System.currentTimeMillis())+"\n";
        mText.setText(s);

    } 
    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
    private void fbAuthAndPost(final String message){

        mFacebook.authorize(this, new String[]{"publish_stream"}, new DialogListener() {

            public void onComplete(Bundle values) {
                Log.d(this.getClass().getName(),"Facebook.authorize Complete: ");
                saveFBToken(mFacebook.getAccessToken(), mFacebook.getAccessExpires());
                updateStatus(values.getString(Facebook.TOKEN), message);
            }

            public void onFacebookError(FacebookError error) {
                Log.d(this.getClass().getName(),"Facebook.authorize Error: "+error.toString());
            }

            public void onError(DialogError e) {
                Log.d(this.getClass().getName(),"Facebook.authorize DialogError: "+e.toString());
            }

            public void onCancel() {
                Log.d(this.getClass().getName(),"Facebook authorization canceled");
            }
        });
    }
    private void saveFBToken(String token, long tokenExpires){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString("FacebookToken", token).commit();
    }




}
