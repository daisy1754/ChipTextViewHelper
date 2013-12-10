package jp.gr.java_conf.daisy.chip_edit_text.demo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import jp.gr.java_conf.daisy.chip_edit_text.ChipItem;

public class MainActivity extends Activity {
    private ChipItem[] people;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPeople();
        setContentView(R.layout.main);
        ((ListView) findViewById(R.id.name_list)).setAdapter(new ChipAdapter(this));
    }

    private void initPeople() {
        people = new ChipItem[]{
            new ChipItem("Adellecharles", toUri(R.drawable.adellecharles)),
            new ChipItem("Ahmetalpbalkan", toUri(R.drawable.ahmetalpbalkan)),
            new ChipItem("Ahmetsulek", toUri(R.drawable.ahmetsulek)),
            new ChipItem("Alagoon", toUri(R.drawable.alagoon)),
            new ChipItem("Andyisonline", toUri(R.drawable.andyisonline)),
            new ChipItem("Angelceballos", toUri(R.drawable.angelceballos)),
            new ChipItem("Ankitind", toUri(R.drawable.ankitind)),
            new ChipItem("Anoff", toUri(R.drawable.anoff)),
            new ChipItem("Axel", toUri(R.drawable.axel)),
            new ChipItem("Baliomega", toUri(R.drawable.baliomega)),
            new ChipItem("Calebogden", toUri(R.drawable.calebogden)),
            new ChipItem("Catarino", toUri(R.drawable.catarino)),
            new ChipItem("Cemshid", toUri(R.drawable.cemshid)),
            new ChipItem("Chadengle", toUri(R.drawable.chadengle)),
            new ChipItem("Chandlervdw", toUri(R.drawable.chandlervdw)),
            new ChipItem("Chatyrko", toUri(R.drawable.chatyrko)),
            new ChipItem("Commadelimited", toUri(R.drawable.commadelimited)),
            new ChipItem("Dingyi", toUri(R.drawable.dingyi)),
            new ChipItem("Divya", toUri(R.drawable.divya)),
            new ChipItem("Enda", toUri(R.drawable.enda)),
            new ChipItem("Envex", toUri(R.drawable.envex)),
            new ChipItem("Ffbel", toUri(R.drawable.ffbel)),
            new ChipItem("Flashmurphy", toUri(R.drawable.flashmurphy)),
            new ChipItem("Gt", toUri(R.drawable.gt)),
            new ChipItem("Guiiipontes", toUri(R.drawable.guiiipontes)),
            new ChipItem("Holdenweb", toUri(R.drawable.holdenweb)),
            new ChipItem("Iamgarth", toUri(R.drawable.iamgarth)),
            new ChipItem("Idiot", toUri(R.drawable.idiot)),
            new ChipItem("Jarjan", toUri(R.drawable.jarjan)),
            new ChipItem("Jayrobinson", toUri(R.drawable.jayrobinson)),
            new ChipItem("Jm_denis", toUri(R.drawable.jm_denis)),
            new ChipItem("Joshaustin", toUri(R.drawable.joshaustin)),
            new ChipItem("Joshhemsley", toUri(R.drawable.joshhemsley)),
            new ChipItem("Kennyadr", toUri(R.drawable.kennyadr)),
            new ChipItem("Kolage", toUri(R.drawable.kolage)),
            new ChipItem("Kushsolitary", toUri(R.drawable.kushsolitary)),
            new ChipItem("Leemunroe", toUri(R.drawable.leemunroe)),
            new ChipItem("Linux29", toUri(R.drawable.linux29)),
            new ChipItem("Markjenkins", toUri(R.drawable.markjenkins)),
            new ChipItem("Mds", toUri(R.drawable.mds)),
            new ChipItem("Michzen", toUri(R.drawable.michzen)),
            new ChipItem("Mizko", toUri(R.drawable.mizko)),
            new ChipItem("Moscoz", toUri(R.drawable.moscoz)),
            new ChipItem("Mutlu82", toUri(R.drawable.mutlu82)),
            new ChipItem("Nicolai_larsen", toUri(R.drawable.nicolai_larsen)),
            new ChipItem("Nicolasfolliot", toUri(R.drawable.nicolasfolliot)),
            new ChipItem("Noxdzine", toUri(R.drawable.noxdzine)),
            new ChipItem("Peterlandt", toUri(R.drawable.peterlandt)),
            new ChipItem("Roybarberuk", toUri(R.drawable.roybarberuk)),
            new ChipItem("Saschamt", toUri(R.drawable.saschamt)),
            new ChipItem("Simobenso", toUri(R.drawable.simobenso)),
            new ChipItem("Sindresorhus", toUri(R.drawable.sindresorhus)),
            new ChipItem("Snowshade", toUri(R.drawable.snowshade)),
            new ChipItem("Soffes", toUri(R.drawable.soffes)),
            new ChipItem("Soyjavi", toUri(R.drawable.soyjavi)),
            new ChipItem("Suprb", toUri(R.drawable.suprb)),
            new ChipItem("Syropian", toUri(R.drawable.syropian)),
            new ChipItem("Terryxlife", toUri(R.drawable.terryxlife)),
            new ChipItem("Thierrykoblentz", toUri(R.drawable.thierrykoblentz)),
            new ChipItem("Traneblow", toUri(R.drawable.traneblow)),
            new ChipItem("Victorerixon", toUri(R.drawable.victorerixon)),
            new ChipItem("Vladarbatov", toUri(R.drawable.vladarbatov)),
            new ChipItem("Weglov", toUri(R.drawable.weglov)),
            new ChipItem("Wr", toUri(R.drawable.wr)),
            new ChipItem("Yalozhkin", toUri(R.drawable.yalozhkin))
        };
    }

    private Uri toUri(int resId) {
        Resources resources = getResources();
        return Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + resources.getResourcePackageName(resId) + '/'
                + resources.getResourceTypeName(resId) + '/'
                + resources.getResourceEntryName(resId));
    }

    private class ChipAdapter extends ArrayAdapter<ChipItem> {
        public ChipAdapter(Context context)  {
            super(context, 0, people);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                convertView = inflater.inflate(R.layout.simple_list_item_icon_and_name, null);
            }
            ChipItem item = getItem(position);
            ((ImageView) convertView.findViewById(R.id.icon)).setImageURI(item.getIconUri());
            ((TextView) convertView.findViewById(R.id.text)).setText(item.getDisplayName());
            return convertView;
        }
    }
}
