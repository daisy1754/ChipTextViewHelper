package jp.gr.java_conf.daisy.chip_edit_text;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * {@link android.widget.EditText} that utilize {@link jp.gr.java_conf.daisy.chip_edit_text.ChipTextViewHelper}
 * for showing their content as chips.
 */
public class ChipEditText extends EditText {
    private ChipTextViewHelper helper;
    public ChipEditText(Context context) {
        super(context);
        init(context);
    }

    public ChipEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChipEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context context){
        helper = new ChipTextViewHelper(this);
        helper.setOnChipsRefreshedListener(new ChipTextViewHelper.OnChipsRefreshedListener() {
            @Override
            public void onRefreshed() {
                setSelection(getText().length());
            }
        });
    }

    public ChipTextViewHelper getChipTextViewHelper() {
        return helper;
    }
}
