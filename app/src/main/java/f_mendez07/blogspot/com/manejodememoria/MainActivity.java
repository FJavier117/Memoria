package f_mendez07.blogspot.com.manejodememoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private EditText editText1;
    private EditText editText2;
    private Button button1;
    private Button button2;
    private Button button3;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generarArchivo();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencias();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPreferencia();
            }
        });

    }

    public void generarArchivo(){
        try {
            editText1 = findViewById(R.id.edt1);
            String nombre = editText1.getText().toString();
            FileOutputStream fileOutputStream = null;
            fileOutputStream = openFileOutput("MiArchivo.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(nombre.getBytes());
            fileOutputStream.close();
            Toast.makeText(MainActivity.this,"Archivo creado",Toast.LENGTH_SHORT).show();
            editText1.setText("");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Hubo un error en la escritura",Toast.LENGTH_SHORT).show();
        }
    }

    public void preferencias(){
        editText2 = findViewById(R.id.edt2);
        editText1 = findViewById(R.id.edt1);
        String nombre = editText1.getText().toString();
        String correo = editText2.getText().toString();
        SharedPreferences mipreferencia = getSharedPreferences("MisDatos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mipreferencia.edit();
        editor.putString("nombre",nombre);
        editor.putString("correo",correo);
        editor.apply();
    }

    public void mostrarPreferencia(){
        textView = findViewById(R.id.tvMostrar);
        SharedPreferences mipreferencia = getSharedPreferences("MisDatos",Context.MODE_PRIVATE);
        String nombre = mipreferencia.getString("nombre","No existe esa variable");
        String correo = mipreferencia.getString("correo", "No existe esa variable");
        String preferencia = "Nombre: "+ nombre+ "\nCorreo: " + correo;
        textView.setText(preferencia);
    }

}
