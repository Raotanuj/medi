package myreport.example.com.myreport;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Tanuj on 21-07-2017.
 */

public class galleryactivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);


        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Intent intent = getIntent();
        final int x = intent.getIntExtra("flag", 0);




        final ArrayList<Integer> y = intent.getIntegerArrayListExtra("idarray");
        final ArrayList<String> z = intent.getStringArrayListExtra("address");
        final int[] a = intent.getIntArrayExtra("reporttype");

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        int count = cursor.getCount();
        final String[] arrPath = new String[count];
        Log.i("count", "" + count);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                //Store the path of the image
                arrPath[i] = cursor.getString(dataColumnIndex);


                ImageAdapter adapter = new ImageAdapter(galleryactivity.this, arrPath);
                GridView grid = (GridView) findViewById(R.id.grid_view);
                grid.setAdapter(adapter);


                grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        if (x == 1) {

                            Intent returnIntent = new Intent();

                            returnIntent.putExtra("result", arrPath[position]);
                            returnIntent.putExtra("address", z);
                            returnIntent.putExtra("idarray", y);
                            returnIntent.putExtra("reporttype", a);


                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                        } else {
                            Intent intent = new Intent(galleryactivity.this, uploadrecord_activity.class);
                            intent.putExtra("address", arrPath[position]);
                            intent.putExtra("control", 5);
                            startActivityForResult(intent, 3);

                        }
                    }
                });

            }


        } else {
            Log.i("array", "Empty");
        }
        cursor.close();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 3) {
            if (resultCode == Activity.RESULT_OK) {



                Intent returnIntent = new Intent();


                setResult(Activity.RESULT_OK, returnIntent);
                finish();



            }
        }
    }
}