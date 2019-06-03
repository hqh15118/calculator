package cn.zju.id21832083.hqh;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ScrollView;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hongqianhui on 2019/6/2.
 *
 */

public class CalculatorUnicodeHelper extends BaseCalculatorHelper<ScrollView> {

    private static final String TAG = "CalculatorUnicodeHelper";

    private EditText evNative,evUtf8,evUtf16,evUtfAscii;

    @Override
    public void init(ScrollView view) {
        evNative = getComponmentById(R.id.ev_byte,EditText.class);
        evUtf8 = getComponmentById(R.id.ev_uft_8,EditText.class);
        evUtf16 = getComponmentById(R.id.ev_uft_16,EditText.class);
        evUtfAscii = getComponmentById(R.id.ev_ascii,EditText.class);
        TextWatcherImpl watcherNative = new TextWatcherImpl(evNative, Arrays.
                asList(evUtf8,evUtf16,evUtfAscii));
        evNative.addTextChangedListener(watcherNative);
    }


    private static class TextWatcherImpl implements TextWatcher{

        private EditText bindEditText;
        private StringBuilder sb = new StringBuilder(100);
        private List<EditText> editTexts;
        private final int REF = 0x000000FF;

        TextWatcherImpl(EditText editText, List<EditText> editTextList){
            bindEditText = editText;
            this.editTexts = editTextList;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String str = charSequence.toString();
            byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
            byte[] utf16 = str.getBytes(StandardCharsets.UTF_16);
            byte[] ascii = str.getBytes(StandardCharsets.US_ASCII);
            editTexts.get(0).setText(setStr(utf8,0));
            editTexts.get(1).setText(setStr(utf16,1));
            editTexts.get(2).setText(setStr(ascii,2));
        }

        private String setStr(byte[] bytes,int type){
            sb.delete(0,sb.length());
            int j = 0;
            int len = bytes.length;
            for (; j < len ; j++) {
                if (type == 0){
                    sb.append("[\\u8]");
                }else if(type == 1){
                    sb.append("[\\u16]");
                }else{
                    sb.append("[\\ascii]");
                }
                sb.append(String.format("%02x",bytes[j]&REF));
            }
            return sb.toString();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}
