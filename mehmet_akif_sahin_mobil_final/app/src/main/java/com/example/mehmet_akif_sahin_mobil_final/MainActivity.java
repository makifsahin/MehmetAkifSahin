package com.example.mehmet_akif_sahin_mobil_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;
    EditText emailGiris;
    EditText sifreGiris;
    Button girisYapButon;
    TextView kayitYonlendirme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        emailGiris=(EditText) findViewById(R.id.editTextEmailGiris);
        sifreGiris=(EditText) findViewById(R.id.editTextSifreGiris);


        girisYapButon=(Button) findViewById(R.id.GirisButon);
        kayitYonlendirme=(TextView) findViewById(R.id.KayitaGitButon);

        girisYapButon.setOnClickListener(this);
        kayitYonlendirme.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.GirisButon:

                String email=emailGiris.getText().toString().trim();
                String sifre=sifreGiris.getText().toString().trim();
                if(email.isEmpty())
                {
                    emailGiris.setError("Email gerekli");
                    emailGiris.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {

                    emailGiris.setError("Email geçersiz");
                    emailGiris.requestFocus();
                }
                else if(sifre.isEmpty())
                {

                    sifreGiris.setError("Şifre gerekli");
                    sifreGiris.requestFocus();
                }
                else if(sifre.length()<6)
                {
                    sifreGiris.setError("Lütfen en az 6 karakterli şifre kullanın");
                    sifreGiris.requestFocus();
                }
                else
                {
                    auth.signInWithEmailAndPassword(email,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                startActivity(new Intent(MainActivity.this,AnaEkranDrawer.class));
                            }
                            else

                            {

                                Toast.makeText(MainActivity.this,"Giriş başarısız",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

                break;
            case R.id.KayitaGitButon:
                startActivity(new Intent(this,KayitActivity.class));
                break;

        }
    }
}