package myreport.example.com.myreport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Tanuj on 19-07-2017.
 */

public class ImageAdapter extends BaseAdapter
{
    private Context mContext;
    private String[] Imageids;
    public ImageAdapter(Context c,String[] Imageid1 )
    {
        mContext = c;
        Imageids = Imageid1;
    }

    @Override
    public int getCount()
    {
        return Imageids.length;
    }
    @Override
    public Object getItem(int position)
    {
        return position;
    }
    @Override
    public long getItemId(int position)
    {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup
            parent)
    {
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView = convertView;
        if (convertView == null)
        {

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.column_layout, null);
            // set value into textview


        }

        File imgFile = new File(Imageids[position]);


        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
           myBitmap=getResizedBitmap(myBitmap,200);

            ImageView myImage = (ImageView) gridView.findViewById(R.id.grid_item_image);

            myImage.setImageBitmap(myBitmap);



        }

        return gridView;
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
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


