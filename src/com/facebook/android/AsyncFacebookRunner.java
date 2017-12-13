

package com.facebook.android;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import android.content.Context;
import android.os.Bundle;

public class AsyncFacebookRunner {

    Facebook fb;

    public AsyncFacebookRunner(Facebook fb) 
    {
        this.fb = fb;
    }
    public void logout(final Context context,
                       final RequestListener listener,
                       final Object state) {
        new Thread() {
            @Override public void run() {
                try {
                    String response = fb.logout(context);
                    if (response.length() == 0 || response.equals("false")){
                        listener.onFacebookError(new FacebookError(
                                "auth.expireSession failed"), state);
                        return;
                    }
                    listener.onComplete(response, state);
                } catch (FileNotFoundException e) {
                    listener.onFileNotFoundException(e, state);
                } catch (MalformedURLException e) {
                    listener.onMalformedURLException(e, state);
                } catch (IOException e) {
                    listener.onIOException(e, state);
                }
            }
        }.start();
    }

    public void logout(final Context context, final RequestListener listener) 
    {
        logout(context, listener, /* state */ null);
    }
    public void request(Bundle parameters, RequestListener listener,final Object state) 
    {
        request(null, parameters, "GET", listener, state);
    }
    public void request(String graphPath, RequestListener listener) 
    {
        request(graphPath, new Bundle(), "GET", listener, /* state */ null);
    }
    
    public void request(Bundle parameters, RequestListener listener) {
        request(null, parameters, "GET", listener, /* state */ null);
    }

   
    public void request(String graphPath, RequestListener listener, final Object state) 
    {
        request(graphPath, new Bundle(), "GET", listener, state);
    }
    public void request(String graphPath, Bundle parameters, RequestListener listener, final Object state) 
    {
        request(graphPath, parameters, "GET", listener, state);
    }

    public void request(String graphPath, Bundle parameters, RequestListener listener) 
    {
        request(graphPath, parameters, "GET", listener, /* state */ null);
    }

    
    public void request(final String graphPath,
                        final Bundle parameters,
                        final String httpMethod,
                        final RequestListener listener,
                        final Object state) {
        new Thread() {
            @Override public void run() {
                try {
                    String resp = fb.request(graphPath, parameters, httpMethod);
                    listener.onComplete(resp, state);
                } catch (FileNotFoundException e) {
                    listener.onFileNotFoundException(e, state);
                } catch (MalformedURLException e) {
                    listener.onMalformedURLException(e, state);
                } catch (IOException e) {
                    listener.onIOException(e, state);
                }
            }
        }.start();
    }

    /**
     * Callback interface for API requests.
     *
     * Each method includes a 'state' parameter that identifies the calling
     * request. It will be set to the value passed when originally calling the
     * request method, or null if none was passed.
     */
    public static interface RequestListener {

        /**
         * Called when a request completes with the given response.
         *
         * Executed by a background thread: do not update the UI in this method.
         */
        public void onComplete(String response, Object state);

        /**
         * Called when a request has a network or request error.
         *
         * Executed by a background thread: do not update the UI in this method.
         */
        public void onIOException(IOException e, Object state);

        /**
         * Called when a request fails because the requested resource is
         * invalid or does not exist.
         *
         * Executed by a background thread: do not update the UI in this method.
         */
        public void onFileNotFoundException(FileNotFoundException e,
                                            Object state);

        /**
         * Called if an invalid graph path is provided (which may result in a
         * malformed URL).
         *
         * Executed by a background thread: do not update the UI in this method.
         */
        public void onMalformedURLException(MalformedURLException e,
                                            Object state);

        /**
         * Called when the server-side Facebook method fails.
         *
         * Executed by a background thread: do not update the UI in this method.
         */
        public void onFacebookError(FacebookError e, Object state);

    }

}
