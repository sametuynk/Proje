package com.sametuyanik.proje.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
import com.sametuyanik.proje.R;

public class girisyap extends AppCompatActivity {

    private EditText editEmail, editSifre,editKullaniciAdi, editTelefon;
    private String txtEmail, txtSifre,txtKullaniciAdi,txtTelefon;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private FirebaseUser mUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn=(Button) findViewById(R.id.kayıtbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(girisyap.this, kayitOl.class);
                startActivity(intent);
            }
        });

        editEmail=(EditText) findViewById(R.id.editTextTextGirisEmail);
        editSifre=(EditText) findViewById(R.id.editTextTextGirisSifre);

        mAuth=FirebaseAuth.getInstance();

    }

    public void girisyap(View view){
        txtEmail=editEmail.getText().toString();
        txtSifre=editSifre.getText().toString();

        if (!TextUtils.isEmpty(txtEmail)&&!TextUtils.isEmpty(txtSifre)){
            mAuth.signInWithEmailAndPassword(txtEmail,txtSifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Intent intent=new Intent(girisyap.this, anasayfa.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(girisyap.this, "Giriş işlemi başarısız. Bilgileri doğru giriniz.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            }else
            Toast.makeText(this, "Email ve Şifre boş olamaz.", Toast.LENGTH_SHORT).show();
    }
}
