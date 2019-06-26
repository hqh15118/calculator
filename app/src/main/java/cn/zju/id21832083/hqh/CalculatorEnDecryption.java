package cn.zju.id21832083.hqh;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.codec.binary.Base64;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hongqianhui on 2019/6/3.
 *
 */

public class CalculatorEnDecryption extends BaseCalculatorHelper<LinearLayout>{

    private static final String TAG = "CalculatorEnDecryption";
    /**
     * ENCODE 表示编码 encode --> decode
     * DECODE 表示解码 encode <-- decode
     */
    private static final int ENCODE = 0 , DECODE = 1;

    private int transferState = 0;

    private EditText etEncode , etDecode;

    private TextView tvTransfer , tvRow;

    private static final int BASE_64 = 0;

    private final Base64 BASE64 = new Base64();

    private static final String encode = "encode",decode = "decode";


    @Override
    public void init(LinearLayout view) {
        //
        EditText base64etDecode = getComponmentById(R.id.ev_encode,EditText.class);
        EditText base64etEncode = getComponmentById(R.id.ev_decode,EditText.class);
        TextView base64tvTransfer = getComponmentById(R.id.tv_transfer, TextView.class);
        TextView base64tvRow = getComponmentById(R.id.tv_row,TextView.class);
        new EditTextWrapper(base64etEncode,base64etDecode,base64tvRow,base64tvTransfer){

            @Override
            String encode(String rawText) {
                return "";
            }

            @Override
            String decode(String rawText) {
                return "";
            }
        };
    }


    private static abstract class EditTextWrapper{

        private EditText encodeText;
        private EditText decodeText;
        private TextView tvRow;
        private TextView tvTransfer;
        //0表示解码encode ， 1表示编码decode
        private int transferState = 0;

        public EditTextWrapper(EditText encodeText, EditText decodeText, TextView tvRow, TextView tvTransfer) {
            this.encodeText = encodeText;
            this.decodeText = decodeText;
            this.tvRow = tvRow;
            this.tvTransfer = tvTransfer;
            init();
        }

        private void init() {
            tvRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    transferState ^= 1;
                    tvRow.setRotation(180);
                }
            });
            tvTransfer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    io.reactivex.Observable.create(new ObservableOnSubscribe<String>() {
                        @Override
                        public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                            if (transferState == 0){
                                emitter.onNext(encode(encodeText.getText().toString()));
                            }else{
                                emitter.onNext(decode(decodeText.getText().toString()));
                            }
                        }
                    }).subscribeOn(Schedulers.computation())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    if (transferState == 0){
                                        decodeText.setText(s);
                                    }else{
                                        encodeText.setText(s);
                                    }
                                }
                            });
                }
            });

        }

        abstract String encode(String rawText);
        abstract String decode(String rawText);
    }


}
