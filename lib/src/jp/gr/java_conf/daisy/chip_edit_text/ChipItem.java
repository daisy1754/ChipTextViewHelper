package jp.gr.java_conf.daisy.chip_edit_text;

import android.net.Uri;

/**
 * Class that represents data used in {@link ChipTextViewHelper}
 */
public class ChipItem {
    private final String displayName;
    private final Uri iconUri;

    public ChipItem(String displayName, Uri iconUri) {
        this.displayName = displayName;
        this.iconUri = iconUri;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Uri getIconUri() {
        return iconUri;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
