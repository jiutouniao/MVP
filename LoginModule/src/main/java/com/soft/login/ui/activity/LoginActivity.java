package com.soft.login.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.soft.common.ui.widget.ClearEditText;
import com.soft.common.utils.LogUtil;
import com.soft.common.utils.StringUtils;
import com.soft.login.R;
import com.soft.login.model.db.dao.PersonDao;
import com.soft.login.model.db.dto.PersonDto;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.ct_login_account)
    ClearEditText ctLoginAccount;
    @BindView(R.id.ct_login_password)
    ClearEditText ctLoginPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_login_rempassword)
    TextView tvLoginRempassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }


    private void initData(){

        List<PersonDto> personDtos =PersonDao.getAll();
        if(personDtos.size()!=0){
            ctLoginAccount.setText(personDtos.get(0).getName());
            ctLoginPassword.setText(personDtos.get(0).getDesc());
        }

        if(StringUtils.isEmpty(ctLoginAccount.getText().toString()) ||StringUtils.isEmpty(ctLoginPassword.getText().toString() )){
            tvLogin.setEnabled(false);
        }else{
            tvLogin.setEnabled(true);
        }
    }


    private void initEvent(){
        ctLoginAccount.setOnTextChangeListener(new ClearEditText.OnTextChangeListener() {
            @Override
            public void onTextChange(String message) {
                if(StringUtils.isEmpty(ctLoginAccount.getText().toString()) ||StringUtils.isEmpty(ctLoginPassword.getText().toString() )){
                    tvLogin.setEnabled(false);
                }else{
                    tvLogin.setEnabled(true);
                }
            }
        });

        ctLoginPassword.setOnTextChangeListener(new ClearEditText.OnTextChangeListener() {
            @Override
            public void onTextChange(String message) {
                if(StringUtils.isEmpty(ctLoginAccount.getText().toString()) ||StringUtils.isEmpty(ctLoginPassword.getText().toString() )){
                    tvLogin.setEnabled(false);
                }else{
                    tvLogin.setEnabled(true);
                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.tv_login, R.id.tv_login_rempassword})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                LogUtil.d("tv_login");
                if(PersonDao.getAll().size()==0){
                    PersonDto dto = new PersonDto();
                    dto.setName(ctLoginAccount.getText().toString());
                    dto.setTest(ctLoginPassword.getText().toString());
                    PersonDao.add(dto);
                }
                LogUtil.d("PersonDao  :"+ new Gson().toJson(PersonDao.getAll()));
                break;
            case R.id.tv_login_rempassword:
                break;
        }
    }
}
