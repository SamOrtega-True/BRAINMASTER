package com.example.brainmaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brainmaster.Entidades.Estudiante;
import com.example.brainmaster.Services.ApiService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class P_Login extends AppCompatActivity {
    TextView txtErrorMessage, txtReg, txtRec,Contrasena;
    EditText Correo;
    LinearLayout ErrorLogin;
    ImageView PressBack, ViewPass;
    //DbProccess _db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_login);
        IniciarComponentes();
        Registrase();
        Recuperar();
        Back();
        ContrasenaView();
        //_db = new DbProccess(getApplicationContext());
    }

    private void IniciarComponentes(){
        txtErrorMessage = (TextView)findViewById(R.id.txtErrorMessage);
        ErrorLogin = (LinearLayout)findViewById(R.id.errorLogin);
        Correo = (EditText)findViewById(R.id.txtCorreo);
        Contrasena = (EditText) findViewById(R.id.txtContrasena);
        PressBack = (ImageView)findViewById(R.id.ImgBack);
        ViewPass = (ImageView)findViewById(R.id.imgEye);
    }

    private void ContrasenaView(){
        ViewPass.setOnClickListener(new View.OnClickListener() {
            int val = 0;
            @Override
            public void onClick(View v) {
                if (val == 0){
                    val = 1;
                    ViewPass.setImageResource(R.drawable.baseline_visibility_24);
                    Contrasena.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    val = 0;
                    ViewPass.setImageResource(R.drawable.baseline_visibility_off_24);
                    Contrasena.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    public void Prueba(View view){
        ValidarDatos();
    }



    // METODO DEL PROFESOR
    private void ValidarDatos(){
        String user =  Correo.getText().toString();
        String pass = Contrasena.getText().toString();


        Call<Estudiante> response = ApiService.getApiService().getEstudianteLogin(user,pass);
        response.enqueue(new Callback<Estudiante>(){
            @Override
            public void onResponse(Call<Estudiante> call, Response<Estudiante> response) {
                if (response.isSuccessful()){
                    Estudiante estudiante = response.body();
                    if (estudiante != null){
                        GuardarSesion(estudiante.getTipo(), "true", estudiante.getEmail());
                        if(estudiante.getTipo().equals("0")){
                            Intent P = new Intent(getApplicationContext(), P_Menu_Principal.class);
                            startActivity(P);
                        }else if(estudiante.getTipo().equals("1")){
                            Intent P = new Intent(getApplicationContext(), P_Principal.class);
                            startActivity(P);
                        }
                        ErrorLogin.setAlpha(0);
                    }else if(Correo.equals("") || Contrasena.equals("")){
                        txtErrorMessage.setText("Usted ha dejado casillas sin responder, intente nuevamente.");
                        ErrorLogin.setAlpha(1);
                    }else if(!ValidarCorreo(user)){
                        txtErrorMessage.setText("Formato del correo inválido.");
                        ErrorLogin.setAlpha(1);
                    }else{
                        txtErrorMessage.setText("Ups… el correo o la contraseña proporcionada es incorrecta.");
                        ErrorLogin.setAlpha(1);
                    }
                }else {
                    int x = 1;
                }
            }

            @Override
            public void onFailure(Call<Estudiante> call, Throwable t) {
                int x = 1;
            }
        });
    }


    //METODO
    private boolean ValidarCorreo(String email){
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@utp.ac.pa$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    private void Registrase(){
        txtReg = (TextView)findViewById(R.id.txtRegistrarse);

        txtReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Registro = new Intent(getApplicationContext(), P_Registro.class);
                startActivity(Registro);
            }
        });
    }

    private void Recuperar(){
        txtRec = (TextView)findViewById(R.id.txtRecuperar);

        txtRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Registro = new Intent(getApplicationContext(), P_Recuperar_Contrasena.class);
                startActivity(Registro);
            }
        });
    }

    private void Back(){
        PressBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void GuardarSesion(String tipo, String reg, String email){
        try {
            SharedPreferences SP = getSharedPreferences("Sesion", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = SP.edit();
            editor.putString("type", tipo);
            editor.putString("reg", reg);
            editor.putString("correo", email);
            editor.commit();
        }catch (Exception e){}
    }
}