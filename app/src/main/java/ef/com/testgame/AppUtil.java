package ef.com.testgame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

public class AppUtil {


    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static float convertPixelsToDp(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static Drawable getBackground(Context context, int tableSize){
        switch (tableSize){
            case 3:
                return context.getResources().getDrawable(R.drawable.backgroud_number);
            case 4:
                return context.getResources().getDrawable(R.drawable.backgroud_number1);
            case 5:
                return context.getResources().getDrawable(R.drawable.backgroud_number2);
            case 6:
                return context.getResources().getDrawable(R.drawable.backgroud_number);
            case 7:
                return context.getResources().getDrawable(R.drawable.backgroud_number1);
            case 8:
                return context.getResources().getDrawable(R.drawable.backgroud_number2);
            default:
                return context.getResources().getDrawable(R.drawable.backgroud_number2);

        }
    }

}
