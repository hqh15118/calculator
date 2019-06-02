package cn.zju.id21832083.hqh;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ScrollView;

/**
 * Created by hongqianhui on 2019/6/2.
 *
 */

public class CalculatorUnicodeHelper extends BaseCalculatorHelper<ScrollView> {

    private static final String TAG = "CalculatorUnicodeHelper";

    private EditText evByte,evUtf8,evUtf16,evUtfAscii;

    @Override
    public void init(ScrollView view) {
        evByte = getComponmentById(R.id.ev_byte,EditText.class);
        evUtf8 = getComponmentById(R.id.ev_uft_8,EditText.class);
        evUtf16 = getComponmentById(R.id.ev_uft_16,EditText.class);
        evUtfAscii = getComponmentById(R.id.ev_ascii,EditText.class);
    }


    private static class TextWatcherImpl implements TextWatcher{

        private EditText bindEditText;

        public TextWatcherImpl(EditText editText){
            bindEditText = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            int id = bindEditText.getId();
            String newStr = String.valueOf(charSequence.charAt(i));
            String nativeStr = "";
            switch (id){
                case R.id.ev_byte :
                    nativeStr = newStr;
                    break;
                case R.id.ev_uft_8:

                    break;
                case R.id.ev_uft_16:

                    break;
                case R.id.ev_ascii:

                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}
