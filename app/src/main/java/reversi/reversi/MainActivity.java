package reversi.reversi;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tv_display;
    private ListView lv_mainlist;
    private EditText et_new_strings;
    private ArrayList<String> al_strings;
    private ArrayAdapter<String> aa_strings;
    private ArrayList<CustomItem> al_items;
    private CustomArrayAdapter caa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*tv_display = (TextView) findViewById(R.id.tv_display);
        lv_mainlist = (ListView) findViewById(R.id.lv_mainlist);
        et_new_strings = (EditText) findViewById(R.id.et_new_strings);
        al_items = new ArrayList<CustomItem>();
        caa = new CustomArrayAdapter(this, al_items);
        lv_mainlist.setAdapter(caa);

        et_new_strings.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView view, int actionid, KeyEvent event)
            {
                if(actionid == EditorInfo.IME_ACTION_DONE) {
                    al_items.add(new
                            CustomItem(et_new_strings.getText().toString(), System.currentTimeMillis()));
                    caa.notifyDataSetChanged();
                    tv_display.setText("added item: " + et_new_strings.getText());
                    return true;
                }
                return false;
            }
        });


        lv_mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterview, View view, int pos,
                                    long id) {

                tv_display.setText("item " + pos + " selected");
            }
        });

        lv_mainlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> adapterview, View view, int
                    pos, long id) {

                tv_display.setText("item " + pos + " long clicked");
                return true;
            }
        });*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
