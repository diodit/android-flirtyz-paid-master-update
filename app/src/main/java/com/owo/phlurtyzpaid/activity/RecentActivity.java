package com.owo.phlurtyzpaid.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.owo.phlurtyzpaid.R;
import com.owo.phlurtyzpaid.adapter.GridAdapter;
import com.owo.phlurtyzpaid.adapter.ModGridAdapter;
import com.owo.phlurtyzpaid.core.BaseActivity;
import com.owo.phlurtyzpaid.database.DatabaseHandler;
import com.owo.phlurtyzpaid.model.GridItem;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by kibrom on 3/20/17.
 */

public class RecentActivity extends BaseActivity {

    private Context context;

    private Activity activity;

    ModGridAdapter modGridAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        context = getApplicationContext();

        activity = this;

        setContentView(R.layout.activity_recent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        GridView gridView = (GridView) findViewById(R.id.recent_gridview);

        DatabaseHandler db = new DatabaseHandler(this);

 //       final Stack<GridItem> items = db.getRecentList();

//        List<String> itemNames = new ArrayList<>();
//
//        List<Bitmap> images = new ArrayList<>();
//
//        while(!items.empty()) {
//
//            GridItem item = items.pop();
//
//            try {
//
//                InputStream is = this.openFileInput(item.getAssetPath());
//
//                images.add(BitmapFactory.decodeStream(is));
//
//            } catch (FileNotFoundException e) {
//
//                e.printStackTrace();
//            }
//
//            itemNames.add(item.getAssetName());
//        }
//
//        GridView gridView = (GridView) findViewById(R.id.recent_gridview);
//
//        GridAdapter adapter = new GridAdapter(this, itemNames, images);
//
//        gridView.setAdapter(adapter);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                String fileName = items.get(position).getAssetPath();
//
//                final File photoFile = new File(context.getFilesDir(), fileName);
//
//                Uri uri = FileProvider.getUriForFile(context, "com.owo.phlurtyzpaid", photoFile);
//
//                Intent sendIntent = ShareCompat.IntentBuilder.from(activity)
//
//                        .setType("image/png")
//
//                        .setSubject("Subject")
//
//                        .setStream(uri)
//
//                        .setChooserTitle("Share Emoji")
//
//                        .createChooserIntent()
//
//                        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//
//                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//
//                context.startActivity(sendIntent);
//
//            }
//        });

        final Stack<GridItem> items = db.getRecentList();
        final Stack<GridItem> itemsUntouched = items;

        //  Toast.makeText(context, String.valueOf(itemsUntouched.size()), Toast.LENGTH_SHORT).show();




        modGridAdapter = new ModGridAdapter(context, items);
        gridView.setAdapter(modGridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shareImage(items.get(position), context);
            }
        });



    }

    static public void shareImage(GridItem fetchedImage, final Context context) {
        final String url = fetchedImage.getAssetPath();

        if(Build.VERSION.SDK_INT > 23){
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        Picasso.with(context).load(url).into(new Target() {
            @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap, context));
                context.startActivity(Intent.createChooser(i, "Share Image"));
            }
            @Override public void onBitmapFailed(Drawable errorDrawable) {
                Toast.makeText(context, "Image path is invalid", Toast.LENGTH_SHORT).show();
            }
            @Override public void onPrepareLoad(Drawable placeHolderDrawable) { }
        });

    }

    static public Uri getLocalBitmapUri(Bitmap bmp, Context context) {
        Uri bmpUri = null;
        try {
            File file =  new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
