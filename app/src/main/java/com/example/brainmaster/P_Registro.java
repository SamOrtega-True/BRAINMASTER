package com.example.brainmaster;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.brainmaster.Entidades.Estudiante;
import com.example.brainmaster.Responses.Facultad;
import com.example.brainmaster.Services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class P_Registro extends AppCompatActivity {
    ImageView PressBack, ViewPass;
    List<Facultad> _facultades;
    EditText txt_nom, txt_ape, txt_correo,txt_cedu ,txt_contra,edad;
    Spinner spn_facu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_registro);
        IniciarComponentes();
        LoadSpinner();
        Back();
        ContrasenaView();
    }
    // CARGA DE ID//
    private void IniciarComponentes(){
        PressBack = (ImageView)findViewById(R.id.ImgBack);
        txt_nom = (EditText) findViewById(R.id.txtnombre);
        txt_ape = (EditText) findViewById(R.id.txtapellido);
        txt_correo = (EditText) findViewById(R.id.txtcorreo);
        txt_contra = (EditText) findViewById(R.id.txtcont);
        spn_facu = (Spinner) findViewById(R.id.spnsalon);
        txt_cedu= (EditText)findViewById(R.id.txtcedula);
        edad=(EditText)findViewById(R.id.txtEdad);
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
                    txt_contra.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    val = 0;
                    ViewPass.setImageResource(R.drawable.baseline_visibility_off_24);
                    txt_contra.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
    //BOTON DE CARGA//
    private void Back(){
        PressBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    // CARGA DEL SPINNER//
    private void LoadSpinner() {
        Call<List<Facultad>> response = ApiService.getApiService().getAllFacultades();
        response.enqueue(new Callback<List<Facultad>>() {
            @Override
            public void onResponse(Call<List<Facultad>> call, Response<List<Facultad>> response) {
                if (response.isSuccessful()){
                    _facultades = response.body();
                    List<String> facultadesListString = new ArrayList<String>();
                    for(Facultad facultad : _facultades){
                        facultadesListString.add(facultad.getFacultad());
                    }
                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item_text,facultadesListString);
                    spn_facu.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Facultad>> call, Throwable t) {
            }
        });
    }

    public void Registrar(View view) {
        try {
            String conca = txt_nom.getText().toString() + " " + txt_ape.getText().toString();
            Estudiante estudiante = new Estudiante();
            estudiante.setNombre_completo(conca);
            estudiante.setCedula(txt_cedu.getText().toString());
            estudiante.setEdad(edad.getText().toString());
            estudiante.setEmail(txt_correo.getText().toString());
            estudiante.setPassword(txt_contra.getText().toString());
            String selectedFac = spn_facu.getSelectedItem().toString();
            String facultadId = "";
            for(Facultad facultad : _facultades){
                if (facultad.getFacultad().equals(selectedFac)){
                    facultadId = facultad.getId();
                }
            }
            estudiante.setFacultad(facultadId);
            Call<Integer> response = ApiService.getApiService().postRegistrarEstudiante(estudiante);
            response.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()){
                        int x = 1;
                        Toast.makeText(getApplicationContext(), "Registro satisfactorio", Toast.LENGTH_LONG).show();
                        onBackPressed();
                    }else{
                        int x = 2;
                    }
                }
                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    int x = 1;
                }
            });
        }catch (Exception e){
            int x= 1;
        }
    }
}