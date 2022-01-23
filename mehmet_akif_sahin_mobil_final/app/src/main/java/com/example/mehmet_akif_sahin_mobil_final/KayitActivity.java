package com.example.mehmet_akif_sahin_mobil_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class KayitActivity extends AppCompatActivity implements View.OnClickListener {


    FirebaseAuth auth;

    EditText emailKayit;
    EditText sifreKayit;
    Button kayitOlButon;
    TextView girisYonlendirme;
    CheckBox sozlesmeKabul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit);


        auth=FirebaseAuth.getInstance();

        emailKayit=(EditText) findViewById(R.id.editTextEmailKayit);
        sifreKayit=(EditText) findViewById(R.id.editTextSifreKayit);

        sozlesmeKabul=(CheckBox) findViewById(R.id.anlasmaCheck);
        kayitOlButon=(Button) findViewById(R.id.KayitButon);
        girisYonlendirme=(TextView) findViewById(R.id.GiriseGitButon);

        kayitOlButon.setOnClickListener(this);
        girisYonlendirme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.KayitButon:
                String email=emailKayit.getText().toString().trim();
                String sifre=sifreKayit.getText().toString().trim();
                if(email.isEmpty())
                {
                    emailKayit.setError("Email gerekli");
                    emailKayit.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {

                    emailKayit.setError("Email geçersiz");
                    emailKayit.requestFocus();
                }
                else if(sifre.isEmpty())
                {

                    sifreKayit.setError("Şifre gerekli");
                    sifreKayit.requestFocus();
                }
                else if(sifre.length()<6)
                {
                    sifreKayit.setError("Lütfen en az 6 karakterli şifre kullanın");
                    sifreKayit.requestFocus();
                }
                else if(!sozlesmeKabul.isChecked())
                {
                    sozlesmeKabul.setError("");
                    sozlesmeKabul.requestFocus();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                //veritabanina ekle
                                User newUser=new User(email);
                                FirebaseDatabase.getInstance().getReference("Users").
                                        child(auth.getCurrentUser().getUid()).setValue(newUser)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful())
                                        {
                                            startActivity(new Intent(KayitActivity.this,AnaEkranDrawer.class));

                                        }
                                        else
                                        {
                                            Toast.makeText(KayitActivity.this,"Kayıt başarısız",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else
                            {
                                Toast.makeText(KayitActivity.this,"Kayıt başarısız",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

                break;
            case R.id.GiriseGitButon:
                startActivity(new Intent(this,MainActivity.class));
                break;


        }
    }
}