package com.sametuyanik.proje.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sametuyanik.proje.R;

import java.util.HashMap;

public class kayitOl extends AppCompatActivity {

    private EditText editEmail, editSifre, editKullaniciAdi, editTelefon;
    private String txtEmail, txtSifre, txtKullaniciAdi, txtTelefon;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private FirebaseUser mUser;
    private HashMap<String, Object> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        editEmail = (EditText) findViewById(R.id.editTextEmail);
        editSifre = (EditText) findViewById(R.id.editTextSifre);
        editKullaniciAdi = (EditText) findViewById(R.id.editTextKayitKullaniciAdi);
        editTelefon = (EditText) findViewById(R.id.editTextKayitTelefon);

        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();
    }

    public void kayitol(View view) {
        txtKullaniciAdi = editKullaniciAdi.getText().toString();
        txtTelefon = editTelefon.getText().toString();
        txtEmail = editEmail.getText().toString();
        txtSifre = editSifre.getText().toString();

        if (!TextUtils.isEmpty(txtKullaniciAdi) && !TextUtils.isEmpty(txtTelefon) && !TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtSifre)) {
            mAuth.createUserWithEmailAndPassword(txtEmail, txtSifre)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                mUser = mAuth.getCurrentUser();

                                mData = new HashMap<>();
                                mData.put("KullaniciAdı", txtKullaniciAdi);
                                mData.put("KullaniciEmail:", txtEmail);
                                mData.put("KullaniciTelefon", txtTelefon);
                                mData.put("KullaniciSifre:", txtSifre);
                                mData.put("KullaniciId:", mUser.getUid());

                                mReference.child("Kullanıcılar").child(mUser.getUid())
                                        .setValue(mData)
                                        .addOnCompleteListener(kayitOl.this, new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(kayitOl.this, "Kayıt İşlemi Başarılı.", Toast.LENGTH_SHORT).show();
                                                    new Handler().postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Intent intent = new Intent(kayitOl.this, girisyap.class);
                                                            startActivity(intent);
                                                            finish();
                                                        }
                                                    },1000);

                                                } else {
                                                    Log.d("Register", "onComplete: " + task.getException().getMessage());
                                                    Toast.makeText(kayitOl.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            } else {
                                Log.d("Register", "onComplete: " + task.getException().getMessage());
                                Toast.makeText(kayitOl.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Bilgilerinizi eksiksiz doldurunuz", Toast.LENGTH_SHORT).show();
        }


    }
}