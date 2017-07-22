package myreport.example.com.myreport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tanuj on 21-07-2017.
 */

public class pic_activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic);
        Intent intent=getIntent();
        final int control=intent.getIntExtra("control",0);;

        Button upload=(Button) findViewById(R.id.uploadphoto);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(pic_activity.this,galleryactivity.class);
                intent.putExtra("control",5);
                startActivityForResult(intent,3);

            }
        });


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
