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
        context.getResources().getDrawable(R.drawable.back_home);
        switch (tableSize){
            case 3:
                return context.getResources().getDrawable(R.drawable.backgroud);
            case 4:
                return context.getResources().getDrawable(R.drawable.backgroud1);
            case 5:
                return context.getResources().getDrawable(R.drawable.backgroud2);
            case 6:
                return context.getResources().getDrawable(R.drawable.backgroud3);
            case 7:
                return context.getResources().getDrawable(R.drawable.backgroud5);
            case 8:
                return context.getResources().getDrawable(R.drawable.backgroud6);
            default:
                return context.getResources().getDrawable(R.drawable.backgroud_number2);

        }
    }

}
