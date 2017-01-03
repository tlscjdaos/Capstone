package ex.com.bitmapex;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

class BitmapView extends View
{
    public BitmapView(Context context)
    {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);
        Paint pnt = new Paint();

        //여기에 res 폴더에 있는것들이 포함되어 있다고 생각하면됨.
        Resources resources = getResources();

        BitmapDrawable bitmapDrawable = (BitmapDrawable) resources.getDrawable(R.drawable.com);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        //이 포인터에 맞는 것을 그려줌.
        canvas.drawBitmap(bitmap, 10,10, null);
        //bitmap을 저기 Rect 위치에 정해진 크기에 맞게 가져옴.
        canvas.drawBitmap(bitmap, null, new Rect(320,10,370,85),null);
        //비트맵 만들때 사용 앞에 400,300 크기 뒤에 형식
        Bitmap makeBitmap = Bitmap.createBitmap(400,300, Bitmap.Config.ARGB_8888);
        //의미 makeBitmap에서만 쓸 수 있는 캔버스를 만들어줌.
        Canvas bitmapCanvas = new Canvas(makeBitmap);
        bitmapCanvas.drawColor(Color.GREEN);
        //그림은 빨간색으로
        pnt.setColor(Color.RED);
        for(int x =0; x<400; x+=10)
        {
            //x , 0 시작 지점 400-x 300 끝나는 지점.
            bitmapCanvas.drawLine(x,0,400-x,300,pnt);
        }
        canvas.drawBitmap(makeBitmap,50,400,null);
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BitmapView bitmapView = new BitmapView(this);
        setContentView(bitmapView);
    }
}
