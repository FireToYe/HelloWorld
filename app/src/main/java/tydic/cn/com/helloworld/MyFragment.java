package tydic.cn.com.helloworld;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/9.
 */
public class MyFragment extends Fragment {
    private TextView textView;
    public MyListener myListener;
    public interface MyListener{
        public void thank(String code);
    }

    @Override
    public void onAttach(Activity activity) {
        myListener=(MyListener) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        textView = (TextView) view.findViewById(R.id.reqText);
        String text = getArguments().get("msg") + "";
        textView.setText(text);
        myListener.thank("谢谢你的访问");
        Toast.makeText(getActivity(), "成功接收到数据" + text,
                Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "向activity" + text,
                Toast.LENGTH_SHORT).show();
        return view;
    }
}
