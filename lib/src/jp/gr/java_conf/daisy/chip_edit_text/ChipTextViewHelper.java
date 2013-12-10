package jp.gr.java_conf.daisy.chip_edit_text;

import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class that allow you to display your text content on {@link android.widget.TextView} to
 * be shown as chips.
 * Some of code is from https://github.com/kpbird/chips-edittext-library
 * The main differences are
 * 1) You can pass your own layout resource for chip
 * 2) This class can be used with any subclass of {@link android.widget.TextView}, e.g.,
 * {@link android.widget.EditText}.
 */
public class ChipTextViewHelper {
    public interface OnNameDeletedListener {
        public void onNameDeleted(ChipItem item);
    }
    public interface OnChipsRefreshedListener {
        public void onRefreshed();
    }
    public interface OnExtraTextChangedListener {
        public void onExtraTextChanged(String extraText);
    }

    private final TextView textView;
    private OnNameDeletedListener onNameDeletedListener;
    private OnChipsRefreshedListener onChipsRefreshedListener;
    private OnExtraTextChangedListener onExtraTextChangedListener;
    private List<ChipItem> selectedItems = new ArrayList<ChipItem>();
    private String delimiter = ",";
    private String extraText = "";

    public ChipTextViewHelper(TextView textView) {
        this.textView = textView;
        textView.addTextChangedListener(new NameDeletedWatcher());
    }

    public void setOnNameDeletedListener(OnNameDeletedListener listener) {
        onNameDeletedListener = listener;
    }

    public void setOnChipsRefreshedListener(OnChipsRefreshedListener onChipsRefreshedListener) {
        this.onChipsRefreshedListener = onChipsRefreshedListener;
    }

    public void setOnExtraTextChangedListener(OnExtraTextChangedListener onExtraTextChangedListener) {
        this.onExtraTextChangedListener = onExtraTextChangedListener;
    }

    private void refreshChips(){
        if (selectedItems.size() == 0) {
            textView.setText(extraText);
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        for (int i = 0; i < selectedItems.size(); i++) {
            spannableStringBuilder.append(selectedItems.get(i).getDisplayName());
            if (i != selectedItems.size() - 1) {
                spannableStringBuilder.append(delimiter);
            }
        }
        spannableStringBuilder.append(delimiter);
        int cursorIndex = 0;
        for(ChipItem item : selectedItems){
            String chipText = item.getDisplayName();
            spannableStringBuilder.setSpan(
                    new ChipSpan(textView.getContext(), R.layout.default_chip_item,
                            R.id.chip_item_name, R.id.chip_item_icon,
                            item.getIconUri(), chipText),
                    cursorIndex, cursorIndex + chipText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            cursorIndex = cursorIndex + chipText.length() + delimiter.length();
        }
        spannableStringBuilder.append(extraText);
        textView.setText(spannableStringBuilder);
        if (onChipsRefreshedListener != null) {
            onChipsRefreshedListener.onRefreshed();
        }
    }

    public void removeItem(ChipItem item) {
        selectedItems.remove(item);
        refreshChips();
    }

    /**
     * Add an item. This clears current editing text.
     * @param item ChipItem to add.
     */
    public void addItem(ChipItem item) {
        selectedItems.add(item);
        extraText = "";
        refreshChips();
    }

    public List<ChipItem> getSelectedItems() {
        return selectedItems;
    }

    private class NameDeletedWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            if (after == 0) {
                String str = s.subSequence(start, start + count).toString().trim();
                if (str.length() > 0 && !delimiter.trim().equals(str) && onNameDeletedListener != null) {
                    ChipItem item = getSelectedItemByName(str);
                    selectedItems.remove(item);
                    onNameDeletedListener.onNameDeleted(item);
                }
            }
        }

        private ChipItem getSelectedItemByName(String name) {
            for (ChipItem item: selectedItems) {
                if (name.equals(item.getDisplayName())) {
                    return item;
                }
            }
            return null;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String str = s.toString().trim();
            if (str.endsWith(delimiter)) {
                extraText = "";
            } else if (str.split(delimiter).length > 1) {
                String[] sliced = s.toString().split(delimiter);
                extraText = sliced[sliced.length - 1];
            } else {
                extraText = s.toString();
            }
            if (onExtraTextChangedListener != null) {
                onExtraTextChangedListener.onExtraTextChanged(extraText);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}
