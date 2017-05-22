package com.shz.photosel.multiimagechooser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.gdswww.library.R;
import com.shz.photosel.adapter.AlbumGridViewAdapter;
import com.squareup.picasso.Picasso;

public class AlbumActivity extends Activity {

    private GridView gridView;
    private ArrayList<String> dataList = new ArrayList<String>();
    private HashMap<String, ImageView> hashMap = new HashMap<String, ImageView>();
    private ArrayList<String> selectedDataList = new ArrayList<String>();
    private String imageDir = "/DCIM/";
    private ProgressBar progressBar;
    private AlbumGridViewAdapter gridImageAdapter;
    private LinearLayout selectedImageLayout;
    private Button okButton;
    private HorizontalScrollView scrollview;
    private int maxSel = 1;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        selectedDataList = (ArrayList<String>) bundle
                .getSerializable("dataList");
        maxSel = getIntent().getIntExtra("maxSel", 1);
        imageDir = intent.getStringExtra("root");
        init();
        initListener();
    }

    private void init() {

        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        gridView = (GridView) findViewById(R.id.myGrid);
        gridImageAdapter = new AlbumGridViewAdapter(this, dataList,
                selectedDataList);
        gridView.setAdapter(gridImageAdapter);
        refreshData();
        selectedImageLayout = (LinearLayout) findViewById(R.id.selected_image_layout);
        okButton = (Button) findViewById(R.id.ok_button);
        scrollview = (HorizontalScrollView) findViewById(R.id.scrollview);

        initSelectImage();

    }

    public void back(View v) {
        finish();
    }

    private void initSelectImage() {
        if (selectedDataList == null)
            return;
        for (final String path : selectedDataList) {
            ImageView imageView = (ImageView) LayoutInflater.from(
                    AlbumActivity.this).inflate(R.layout.choose_imageview,
                    selectedImageLayout, false);
            selectedImageLayout.addView(imageView);
            hashMap.put(path, imageView);
            Picasso.with(getApplicationContext()).load(new File(path))
                    .resize(200, 200).centerCrop().placeholder(R.drawable.loading_img).
                    error(R.drawable.loading_img).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    removePath(path);
                    gridImageAdapter.notifyDataSetChanged();
                }
            });
        }
        okButton.setText("选择(" + selectedDataList.size() + "/" + maxSel + ")");
    }

    private void initListener() {

        gridImageAdapter
                .setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {

                    @Override
                    public void onItemClick(final ToggleButton toggleButton,
                                            int position, final String path, boolean isChecked) {

//						if (selectedDataList.size() >= maxSel) {
//							toggleButton.setChecked(false);
//							if (!removePath(path)) {
//								Toast.makeText(AlbumActivity.this, "最多选择"+maxSel+"张",
//										Toast.LENGTH_SHORT).show();
//							}
//							return;
//						}

                        if (isChecked) {
                            if (!hashMap.containsKey(path)) {
                                ImageView imageView = (ImageView) LayoutInflater
                                        .from(AlbumActivity.this).inflate(
                                                R.layout.choose_imageview,
                                                selectedImageLayout, false);
                                selectedImageLayout.addView(imageView);
                                imageView.postDelayed(new Runnable() {

                                    @Override
                                    public void run() {

                                        int off = selectedImageLayout
                                                .getMeasuredWidth()
                                                - scrollview.getWidth();
                                        if (off > 0) {
                                            scrollview.smoothScrollTo(off, 0);
                                        }

                                    }
                                }, 100);

                                hashMap.put(path, imageView);
                                selectedDataList.add(path);
                                Picasso.with(getApplicationContext()).load(new File(path))
                                        .resize(200, 200).centerCrop().placeholder(R.drawable.loading_img).
                                        error(R.drawable.loading_img).into(imageView);
                                imageView
                                        .setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {
                                                toggleButton.setChecked(false);
                                                removePath(path);

                                            }
                                        });
                                Intent intent = new Intent();
                                Bundle bundle = new Bundle();
                                // intent.putArrayListExtra("dataList", dataList);
                                bundle.putStringArrayList("dataList", selectedDataList);
                                intent.putExtras(bundle);
                                setResult(RESULT_OK, intent);
                                finish();

                            }
                        } else {
                            removePath(path);
                        }

                    }
                });

        okButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                // intent.putArrayListExtra("dataList", dataList);
                bundle.putStringArrayList("dataList", selectedDataList);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }

    private boolean removePath(String path) {
        if (hashMap.containsKey(path)) {
            selectedImageLayout.removeView(hashMap.get(path));
            hashMap.remove(path);
            removeOneData(selectedDataList, path);
            okButton.setText("选择(" + selectedDataList.size() + "/" + maxSel + ")");
            return true;
        } else {
            return false;
        }
    }

    private void removeOneData(ArrayList<String> arrayList, String s) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equals(s)) {
                arrayList.remove(i);
                return;
            }
        }
    }

    private void refreshData() {

        new AsyncTask<Void, Void, ArrayList<String>>() {

            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
                super.onPreExecute();
            }

            @Override
            protected ArrayList<String> doInBackground(Void... params) {
                ArrayList<String> tmpList = new ArrayList<String>();
                listAllfile(new File(imageDir), tmpList);
                return tmpList;
            }

            protected void onPostExecute(ArrayList<String> tmpList) {

                if (AlbumActivity.this == null
                        || AlbumActivity.this.isFinishing()) {
                    return;
                }
                progressBar.setVisibility(View.GONE);
                dataList.clear();
                dataList.addAll(tmpList);
                gridImageAdapter.notifyDataSetChanged();
                return;

            }


        }.execute();

    }

    private ArrayList<String> listAllfile(File baseFile,
                                          ArrayList<String> tmpList) {
        if (baseFile != null && baseFile.exists()) {
            File[] file = baseFile.listFiles();
            for (File f : file) {
                if (f.getPath().endsWith(".jpg") || f.getPath().endsWith(".JPG") || f.getPath().endsWith(".PNG")
                        || f.getPath().endsWith(".png")) {
                    tmpList.add(f.getPath());
                }
            }
        }
        return tmpList;
    }


    @Override
    public void onBackPressed() {
        finish();
        // super.onBackPressed();
    }

    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        // ImageManager2.from(AlbumActivity.this).recycle(dataList);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
