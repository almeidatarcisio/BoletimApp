package br.com.boletimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.santalu.maskedittext.MaskEditText;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.boletimapp.NotasActivity;
import br.com.fasj.unibrasapp.R;

public class LoginActivity extends AppCompatActivity {

    String urlWebServicesDesenvolvimento = "http://192.168.1.102/boletim/getUsuarios.php";
    String urlWebServicesProducao = "https://webservicespredictapp-production.up.railway.app/service1/";

    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    private MaskEditText edtCpf;
    private EditText edtSenha;
    private Button btnLogin;
    private String cpf;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        requestQueue = Volley.newRequestQueue(this);

        edtCpf = findViewById(R.id.edtCpf);
        edtSenha = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean validado = true;

                if(edtCpf.getText().length()==0){
                    edtCpf.setError("Campo CPF Obrigatório");
                    edtCpf.requestFocus();
                    validado = false;
                }

                if(edtSenha.getText().length()==0){
                    edtSenha.setError("Campo Senha Obrigatório");
                    edtSenha.requestFocus();
                    validado = false;
                }

                cpf = edtCpf.getText().toString();
                senha = edtSenha.getText().toString();

                if (validado){
                    Toast.makeText(getApplicationContext(),"Validando seus dados. Aguarde...", Toast.LENGTH_SHORT).show();
                    validarLogin(cpf, senha);
                }

            }
        });
    }

    private void validarLogin(String cpf, String senha){
        // Formatação CPF
        String formattedCPF = formatCPF(cpf);

        stringRequest = new StringRequest(Request.Method.POST,
                urlWebServicesProducao,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("LogLogin", response);

                        try{

                            JSONObject jsonObject = new JSONObject(response);

                            boolean isErro = jsonObject.getBoolean("erro");

                            if(isErro){
                                Toast.makeText(getApplicationContext(),
                                        jsonObject.getString("mensagem"),
                                        Toast.LENGTH_LONG).show();
                            }else{

                                int tpusuario = jsonObject.getInt("tpusuario");
                                if (tpusuario == 1){

                                    Bundle params = new Bundle();
                                    params.putString("cpf", formattedCPF);

                                    Intent novatela = new Intent(LoginActivity.this, NotasActivity.class);

                                    novatela.putExtras(params);

                                    startActivity(novatela);
                                    finish();
                                }else if(tpusuario == 2){
                                    Toast.makeText(getApplicationContext(),
                                            "Você não possui perfil de aluno.",
                                            Toast.LENGTH_LONG).show();
                                }

                            }

                        }catch(Exception e){

                            Log.v("LogLogin", e.getMessage());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("LogLogin", error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("login", formattedCPF);
                params.put("senha", senha);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private String formatCPF(String cpf) {
        return cpf.replaceAll("[^\\d]", "");
    }

}
