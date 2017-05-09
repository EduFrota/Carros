package br.com.livroandroid.carros.Fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import br.com.livroandroid.carros.R;

public class SiteLivroFragment extends BaseFragment {
    public static final String URL_SOBRE="http://www.livroandroid.com.br/sobre.htm";
    private WebView webView;
    private ProgressBar progressBar;
    protected SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_site_livro, container, false);
        webView =(WebView)view.findViewById(R.id.WebView);
        progressBar = (ProgressBar)view.findViewById(R.id.progress);
        setWebViewClient(webView);
        webView.loadUrl(URL_SOBRE);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(OnRefreshListener());
        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_1,
                R.color.refresh_progress_2,
                R.color.refresh_progress_3);

        configJavaScript();
        return view;
    }

    public void configJavaScript(){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new LivroAndroidInterface(),"LivroAndroid");

    }
    class LivroAndroidInterface {
        @JavascriptInterface
        public void sobre(){
            toast("Clicou em livro");
        }
    }

    private SwipeRefreshLayout.OnRefreshListener OnRefreshListener (){
        return new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                webView.reload();
            }
        };
    }
    private void setWebViewClient(WebView webView){
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView webView1, String url, Bitmap favicon) {
                super.onPageStarted(webView1, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webView1, String url){
                progressBar.setVisibility(View.INVISIBLE);
                swipeRefreshLayout.setRefreshing(false);

            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                Log.d("livroandroid","webview url: "+ url);
                if(url != null && url.endsWith("sobre.htm")){
                    AboutDialogText.showAbout(getChildFragmentManager());
                    return true;
                }
                return super.shouldOverrideUrlLoading(view,url);

            }
        });

    }

}
