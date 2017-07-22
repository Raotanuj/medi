package myreport.example.com.myreport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Tanuj on 21-07-2017.
 */

public class uploadrecord_activity extends AppCompatActivity {

        private StorageReference mChatPhotosStorageReference;
        private DatabaseReference mMessagesDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadrecord);
        Intent intent = getIntent();
        final int control=intent.getIntExtra("control",0);
        String str = intent.getStringExtra("address");
        final ArrayList<String> address = new ArrayList<>();
        address.add(str);


        final ArrayList<Integer> idarray = new ArrayList<>();
        idarray.add(R.id.img1);
        idarray.add(R.id.img2);
        idarray.add(R.id.img3);
        idarray.add(R.id.img4);
        idarray.add(R.id.img5);
        idarray.add(R.id.img6);

        final int[] reporttype={0,0,0,0,0,0};



       final int size = address.size();
        File imgFile = new File(address.get(size - 1));


        reporttype[size-1]=1;

        if (imgFile.exists()) {

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            ImageView myImage = (ImageView) findViewById(idarray.get(size - 1));
            myImage.setImageBitmap(myBitmap);




           final TextView tx1=(TextView)findViewById(R.id.report);

           final TextView tx2=(TextView)findViewById(R.id.prescription);

           final  TextView tx3=(TextView)findViewById(R.id.invoice);

            tx2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tx2.setTextColor(Color.parseColor("#2196F3"));
                    tx1.setTextColor(Color.parseColor("#9E9E9E"));
                    tx3.setTextColor(Color.parseColor("#9E9E9E"));
                    reporttype[size-1]=2;


                }
            });

            tx1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tx1.setTextColor(Color.parseColor("#2196F3"));
                    tx2.setTextColor(Color.parseColor("#9E9E9E"));
                    tx3.setTextColor(Color.parseColor("#9E9E9E"));
                    reporttype[size-1]=1;


                }
            });

            tx3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tx3.setTextColor(Color.parseColor("#2196F3"));
                    tx2.setTextColor(Color.parseColor("#9E9E9E"));
                    tx1.setTextColor(Color.parseColor("#9E9E9E"));
                    reporttype[size-1]=3;
                }
            });

            Log.i("reporttype",""+reporttype[size-1]);

            ImageView img=(ImageView)findViewById(idarray.get(size));
            img.setImageResource(R.drawable.add);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(uploadrecord_activity.this,galleryactivity.class);
                    intent.putExtra("flag",1);
                    intent.putExtra("address",address);
                    intent.putExtra("idarray",idarray);
                    intent.putExtra("reporttype",reporttype);
                    intent.putExtra("control",0);
                    startActivityForResult(intent,1);

                }

            });



            TextView upload =(TextView)findViewById(R.id.upload);
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent returnIntent = new Intent();


                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();


                }
            });


        }


    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                final String result = data.getStringExtra("result");
                final ArrayList<String> address = data.getStringArrayListExtra("address");
                final ArrayList<Integer> idarray = data.getIntegerArrayListExtra("idarray");
                final int[] reporttype = data.getIntArrayExtra("reporttype");
                address.add(result);
                final int size = address.size();
                File imgFile = new File(address.get(size - 1));

                reporttype[size - 1] = 1;

                final TextView tx1 = (TextView) findViewById(R.id.report);

                final TextView tx2 = (TextView) findViewById(R.id.prescription);

                final TextView tx3 = (TextView) findViewById(R.id.invoice);

                tx2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tx2.setTextColor(Color.parseColor("#2196F3"));
                        tx1.setTextColor(Color.parseColor("#9E9E9E"));
                        tx3.setTextColor(Color.parseColor("#9E9E9E"));
                        reporttype[size - 1] = 2;


                    }
                });

                tx1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tx1.setTextColor(Color.parseColor("#2196F3"));
                        tx2.setTextColor(Color.parseColor("#9E9E9E"));
                        tx3.setTextColor(Color.parseColor("#9E9E9E"));
                        reporttype[size - 1] = 1;


                    }
                });

                tx3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tx3.setTextColor(Color.parseColor("#2196F3"));
                        tx2.setTextColor(Color.parseColor("#9E9E9E"));
                        tx1.setTextColor(Color.parseColor("#9E9E9E"));
                        reporttype[size - 1] = 3;
                    }
                });
                int i = 0;
                for (i = 0; i < size; i++) {
                    Log.i("reporttype", "" + reporttype[i]);
                }


                if (imgFile.exists()) {

                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    ImageView myImage = (ImageView) findViewById(idarray.get(size - 1));
                    myBitmap = getResizedBitmap(myBitmap, 200);
                    myImage.setImageBitmap(myBitmap);

                    if (size <= 5) {
                        ImageView img = (ImageView) findViewById(idarray.get(size));
                        img.setImageResource(R.drawable.add);


                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(uploadrecord_activity.this, galleryactivity.class);
                                intent.putExtra("flag", 1);
                                intent.putExtra("address", address);
                                intent.putExtra("idarray", idarray);
                                intent.putExtra("reporttype", reporttype);
                                startActivityForResult(intent, 1);

                            }
                        });









                        TextView upload = (TextView) findViewById(R.id.upload);
                        upload.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                Intent returnIntent = new Intent();

                                setResult(Activity.RESULT_OK, returnIntent);
                                finish();

                            }
                        });


                    }


                }
                if (resultCode == Activity.RESULT_CANCELED) {
                    Log.i("message", "sth happened");
                }


            }
        }
    }









    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
