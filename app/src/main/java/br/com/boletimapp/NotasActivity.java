package br.com.fasj.unibrasapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.layout.simple_spinner_item;

public class NotasActivity extends AppCompatActivity {

    String urlWebServicesDesenvolvimentoSemestres = "http://192.168.1.103/boletim/getSemestres.php";
    String urlWebServicesProducaoSemestres = "gs://webservicesboletimapp/getSemestres.php";
    String urlWebServicesDesenvolvimentoNotas = "http://192.168.1.103/boletim/getNotas.php";
    String urlWebServicesProducaoNotas = "gs://webservicesboletimapp/getNotas.php";
    private static ProgressDialog mProgressDialog;
    private ArrayList<GoodModel> goodModelArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    private Spinner spinner;
    private String selected;
    private String login;
    private RecyclerView recyclerView;
    private NotaAdapter notaAdapter;
    private ArrayList<Nota> NotaList;
    private RequestQueue requestQueue;
    private Button btnDetalharA1;
    private Button btnDetalharA2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        Intent novatela = getIntent();
        if(novatela != null){
            Bundle params = ((Intent) novatela).getExtras();
            if(params != null){
                String txtCpf = params.getString("edtCpf");

                TextView cpfTV = (TextView) findViewById(R.id.txtCpf);

                cpfTV.setText("CPF: "+txtCpf);
                login = txtCpf;
            }
        }

        spinner = findViewById(R.id.spnSemestres);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

/**        btnDetalharA1 = findViewById(R.id.btnDetalharA1);
        btnDetalharA2 = findViewById(R.id.btnDetalharA2);

        btnDetalharA1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Implementação em andamento...", Toast.LENGTH_SHORT).show();
            }
        });

        btnDetalharA2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Implementação em andamento...", Toast.LENGTH_SHORT).show();
            }
        });
**/
        retrieveJSONSemestres();

    }

    private void retrieveJSONSemestres() {

        showSimpleProgressDialog(this, "Carregando...","Buscando dados",false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlWebServicesDesenvolvimentoSemestres,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {

                        Log.d("strrrrr", ">>" + response + login + selected);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("erro").equals("false")) {

                                goodModelArrayList = new ArrayList<>();
                                final JSONArray dataArray  = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    GoodModel playerModel = new GoodModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setDescricao(dataobj.getString("descricao"));

                                    goodModelArrayList.add(playerModel);

                                }

                                for (int i = 0; i < goodModelArrayList.size(); i++){
                                    names.add(goodModelArrayList.get(i).getDescricao().toString());
                                }

                                final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(NotasActivity.this, simple_spinner_item, names);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spinner.setAdapter(spinnerArrayAdapter);

                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        selected = names.get(position);

                                        NotaList = new ArrayList<>();
                                        requestQueue = Volley.newRequestQueue(NotasActivity.this);
                                        retrieveJSONNotas();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                                removeSimpleProgressDialog();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void retrieveJSONNotas() {
        Log.d("strrrrr", ">>" + login + selected);

        showSimpleProgressDialog(this, "Carregando...","Buscando dados",false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlWebServicesDesenvolvimentoNotas,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        try{
                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("erro").equals("false")) {
                                final JSONArray jsonArray = obj.getJSONArray("data");

                                TextView nomeAlunoTV = (TextView) findViewById(R.id.txtNomeAluno);
                                JSONObject aluno = jsonArray.getJSONObject(0);
                                String nome = aluno.getString("aluno");
                                nomeAlunoTV.setText("NOME: " +nome);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject data = jsonArray.getJSONObject(i);

                                    String disciplina = data.getString("disciplina");
                                    String turma = data.getString("turma");
                                    String a1 = data.getString("a1");
                                    String a2 = data.getString("a2");
                                    String sub = data.getString("sub");
                                    String a3 = data.getString("a3");
                                    int faltasA1 = data.getInt("faltasA1");
                                    int faltasA2 = data.getInt("faltasA2");

                                    NotaList.add(new Nota(disciplina, turma, a1, a2, sub, a3, faltasA1, faltasA2));
                                }

                                NotaAdapter notaAdapter = new NotaAdapter(NotasActivity.this, NotaList);
                                recyclerView.setAdapter(notaAdapter);

                            }else{
                                NotaAdapter notaAdapter = new NotaAdapter(NotasActivity.this, NotaList);
                                recyclerView.setAdapter(null);
                                Toast.makeText(getApplicationContext(),
                                        obj.getString("mensagem"),
                                        Toast.LENGTH_LONG).show();
                            }

                            removeSimpleProgressDialog();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("login", login);
                params.put("semestre", selected);
                return params;
            }
        };

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);

    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Pressione VOLTAR novamente para sair.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
