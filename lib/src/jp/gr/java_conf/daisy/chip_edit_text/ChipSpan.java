package jp.gr.java_conf.daisy.chip_edit_text;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @link android.text.Spannable} who has {@link android.widget.TextView}
 * and {@link android.widget.ImageView}.
 *
 * Some of code is from https://github.com/kpbird/chips-edittext-library
 */
class ChipSpan extends ImageSpan {
    public ChipSpan(Context context, int viewResource, int textViewId,
                    int imageViewId, Uri iconUri, CharSequence text) {
        super(context, createBitmap(context, viewResource, textViewId, imageViewId, iconUri, text));
    }

    private static Bitmap createBitmap(Context context, int viewResource, int textViewId,
                                       int imageViewId, Uri iconUri, CharSequence text) {
        LayoutInflater inflater
                = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(viewResource, null);
        ((TextView) view.findViewById(textViewId)).setText(text);
        ((ImageView) view.findViewById(imageViewId)).setImageURI(iconUri);

        int spec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(spec, spec);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(
                view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(canvas);
        view.setDrawingCacheEnabled(true);
        Bitmap viewBmp = view.getDrawingCache().copy(Bitmap.Config.ARGB_8888, true);
        view.destroyDrawingCache();
        return viewBmp;
    }
}
